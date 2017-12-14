package com.yll.springboot.demo.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yll.springboot.demo.vo.SysPermissionVO;

import io.swagger.annotations.Api;

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
//@Path("/permission")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Api(value="/permission", tags = "Permission 管理接口",produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
public interface ISysPermissionService
{

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
