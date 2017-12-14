package com.yll.springboot.demo.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.yll.springboot.demo.dao.ISysPermissionInitDao;
import com.yll.springboot.demo.service.ISysPermissionInitService;
import com.yll.springboot.demo.vo.SysPermissionInitVO;

@Named
public class SysPermissionInitService implements ISysPermissionInitService{
	
    @Autowired
    ISysPermissionInitDao sysPermissionInitDao;
    
	public List<SysPermissionInitVO> selectAll() {
		return sysPermissionInitDao.selectAll();
	}
}
