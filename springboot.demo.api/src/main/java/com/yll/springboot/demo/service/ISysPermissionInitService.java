package com.yll.springboot.demo.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yll.springboot.demo.vo.SysPermissionInitVO;

import io.swagger.annotations.Api;

@Component
//@Path("/permission/init")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Api(value="/permission/init", tags = "Permission Init 管理接口",produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
public interface ISysPermissionInitService {

    /** <一句话功能简述>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<SysPermissionInitVO> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    List<SysPermissionInitVO> selectAll();
}
