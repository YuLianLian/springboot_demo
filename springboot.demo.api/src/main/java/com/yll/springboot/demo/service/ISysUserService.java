package com.yll.springboot.demo.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yll.springboot.demo.vo.SysUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
//@Path("/sysuser")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Api(value="/sysuser", tags = "System User 管理接口",produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
public interface ISysUserService
{

    /** 根据用户名与密码获取用户
     * @param nickname
     * @param pwd
     * @return [参数说明]
     * 
     * @return SysUserVO [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    SysUserVO selectByUser(SysUserVO user);

    /** 根据用户名更新登录的最后时间
     * @param userId
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void updateLastLoginTime(String userId);
    
}
