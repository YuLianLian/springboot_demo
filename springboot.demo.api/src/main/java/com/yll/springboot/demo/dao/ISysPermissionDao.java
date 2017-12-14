package com.yll.springboot.demo.dao;

import java.util.List;

import com.yll.springboot.demo.vo.SysPermissionVO;

public interface ISysPermissionDao{

    /** <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return [参数说明]
     * 
     * @return List<SysPermissionVO> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    List<SysPermissionVO> selectById(String userId);

}