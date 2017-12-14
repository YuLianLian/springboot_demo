package com.yll.springboot.demo.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yll.springboot.demo.vo.SysRoleVO;

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
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value="/sysrole", tags = "Role 管理接口",produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
public interface ISysRoleService
{

    /** <一句话功能简述>
     * <功能详细描述>
     * @param userId
     * @return [参数说明]
     * 
     * @return List<SysRoleVO> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/role/{userid}")
    @ApiOperation("根据用户获取角色列表")
    List<SysRoleVO> selectById(@PathParam("userid") String userId);
    
}
