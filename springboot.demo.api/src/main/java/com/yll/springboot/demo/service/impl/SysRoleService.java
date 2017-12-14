package com.yll.springboot.demo.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.yll.springboot.demo.dao.ISysRoleDao;
import com.yll.springboot.demo.service.ISysRoleService;
import com.yll.springboot.demo.vo.SysRoleVO;

@Named
public class SysRoleService implements ISysRoleService {

    @Autowired
    ISysRoleDao sysRoleDao;
    
    /**
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleVO> selectById(String userId)
    {
        return sysRoleDao.selectById(userId);
    }
	
}
