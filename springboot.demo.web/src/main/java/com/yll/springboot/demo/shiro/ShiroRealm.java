package com.yll.springboot.demo.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.yll.springboot.demo.service.ISysPermissionService;
import com.yll.springboot.demo.service.ISysRoleService;
import com.yll.springboot.demo.service.ISysUserService;
import com.yll.springboot.demo.vo.SysPermissionVO;
import com.yll.springboot.demo.vo.SysRoleVO;
import com.yll.springboot.demo.vo.SysUserVO;

/**
 * shiro身份校验核心类
 */
public class ShiroRealm extends AuthorizingRealm {
    Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String FORBIT_STATUS="0";
    private static final String LOCK_STATUS="LOCK";
    private static final String EMPTY_COUNT="0";
    @Autowired
    private ISysUserService sysUserService;
    
    @Autowired
    private ISysPermissionService sysPermissionService;
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private ISysRoleService sysRoleService;
    
    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    
    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
        
        //计数大于5时，设置用户被锁定一小时
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name))>=5){
            opsForValue.set(SHIRO_IS_LOCK+name,LOCK_STATUS);
            stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
        }
        if (LOCK_STATUS.equals(opsForValue.get(SHIRO_IS_LOCK+name))){
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        
        // 从数据库获取对应用户名密码的用户
        SysUserVO user = sysUserService.selectByUser(new SysUserVO(name,password));
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        }else if(FORBIT_STATUS.equals(user.getStatus())){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        }else{
            //登录成功
            //更新登录时间 last login time
            sysUserService.updateLastLoginTime(user.getId());
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT+name,EMPTY_COUNT);
        }
        LOGGER.info("身份认证成功，登录用户："+name);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        LOGGER.info("权限认证方法：ShiroRealm.doGetAuthorizationInfo()");
        SysUserVO user = (SysUserVO)SecurityUtils.getSubject().getPrincipal();
        String userId = user.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        
        //根据用户ID查询角色（role），放入到Authorization里。
        List<SysRoleVO> roleList = sysRoleService.selectById(userId);
        Set<String> roleSet = new HashSet<String>();
        for(SysRoleVO role : roleList){
            roleSet.add(role.getType());
        }
        info.setRoles(roleSet);
        
        //根据用户ID查询权限（permission），放入到Authorization里。
        List<SysPermissionVO> permissionList = sysPermissionService.selectById(userId);
        Set<String> permissionSet = new HashSet<String>();
        for(SysPermissionVO Permission : permissionList){
            permissionSet.add(Permission.getName());
        }
        info.setStringPermissions(permissionSet);
        return info;
    }
}
