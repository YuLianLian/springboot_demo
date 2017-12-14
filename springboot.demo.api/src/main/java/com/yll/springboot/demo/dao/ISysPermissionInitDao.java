package com.yll.springboot.demo.dao;

import java.util.List;

import com.yll.springboot.demo.vo.SysPermissionInitVO;

public interface ISysPermissionInitDao{
    
	List<SysPermissionInitVO> selectAll();
}