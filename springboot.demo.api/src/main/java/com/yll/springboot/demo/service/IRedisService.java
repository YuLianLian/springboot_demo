package com.yll.springboot.demo.service;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

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
//@Path("/redis")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Api(value="/redis", tags = "Redis 管理接口",produces=MediaType.APPLICATION_JSON,consumes=MediaType.APPLICATION_JSON)
public interface IRedisService
{

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param value
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    boolean set(String key, Object value);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param scoure
     * @param scoure1
     * @return [参数说明]
     * 
     * @return Set<Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param value
     * @param scoure [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void zAdd(String key, Object value, double scoure);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return [参数说明]
     * 
     * @return Set<Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    Set<Object> setMembers(String key);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param value [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void add(String key, Object value);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param k
     * @param l
     * @param l1
     * @return [参数说明]
     * 
     * @return List<Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    List<Object> lRange(String k, long l, long l1);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param k
     * @param v [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void lPush(String k, Object v);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param hashKey
     * @return [参数说明]
     * 
     * @return Object [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    Object hmGet(String key, Object hashKey);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param hashKey
     * @param value [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void hmSet(String key, Object hashKey, Object value);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return [参数说明]
     * 
     * @return Object [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    Object get(String key);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    boolean exists(String key);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param value
     * @param expireTime
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    boolean set(String key, Object value, Long expireTime);

    /** 批量删除对应的value
     * @param keys [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void remove(String... keys);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param pattern [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void removePattern(String pattern);
    
}
