/*
 * 文 件 名:  LoginController.java
 * 版   权:  xwtec
 * 描     述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2017年10月23日
 * 修改内容:  <修改内容>
 */
package com.yll.springboot.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yll.springboot.demo.service.ISysUserService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年10月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
@RequestMapping("/sysuser")
public class SysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    @Autowired ISysUserService sysUserService;
    
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
   
}
