package com.yll.springboot.demo.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.yll.springboot.demo.dao.ISysPermissionDao;
import com.yll.springboot.demo.service.ISysPermissionService;
import com.yll.springboot.demo.vo.SysPermissionVO;

@Named
public class SysPermissionService  implements ISysPermissionService{

    @Autowired
    ISysPermissionDao sysPermissionDao;
    /**
     * @param userId
     * @return
     */
    @Override
    public List<SysPermissionVO> selectById(String userId)
    {
        return sysPermissionDao.selectById(userId);
    }
	
}
