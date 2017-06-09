/*
 * Project: door
 * 
 * File Created at 2017年5月12日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.renlg.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renlg.base.BaseController;
import com.renlg.base.JsonResult;
import com.renlg.user.model.User;
import com.renlg.user.service.UserService;

/**
 * @Type UserController.java
 * @Desc 
 * @author renlinggao
 * @date 2017年5月12日 上午10:17:16
 * @version 
 */
@RequestMapping("user")
@Controller
public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;
    
    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("create")
    @ResponseBody
    public JsonResult create(User user){
        JsonResult result = new JsonResult();
        userService.create(user);
        return result;
    }

}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2017年5月12日 renlinggao create
 */