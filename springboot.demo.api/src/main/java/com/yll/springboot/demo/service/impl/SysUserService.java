package com.yll.springboot.demo.service.impl;

import java.util.Date;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.yll.springboot.demo.dao.ISysUserDao;
import com.yll.springboot.demo.service.ISysUserService;
import com.yll.springboot.demo.utils.DESUtil;
import com.yll.springboot.demo.vo.SysUserVO;

@Named
public class SysUserService  implements ISysUserService{

    @Autowired
    ISysUserDao sysUserDao;
    
    /**
     * @param user
     * @return
     */
    @Override
    public SysUserVO selectByUser(SysUserVO user)
    {
        String pawDES = DESUtil.encryptBasedDes(user.getPswd());
        return sysUserDao.selectByNameAndPwd(user.getNickname(),pawDES);
    }

    /**
     * @param user
     */
    @Override
    public void updateLastLoginTime(String userId)
    {
        SysUserVO user = new SysUserVO();
        user.setId(userId);
        user.setLastLoginTime(new Date());
        sysUserDao.update(user);
    }
	
}
