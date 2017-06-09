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
package com.renlg.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renlg.base.BaseService;
import com.renlg.base.Constant;
import com.renlg.user.dao.UserMapper;
import com.renlg.user.model.User;
import com.renlg.user.service.UserService;

/**
 * @Type UserServiceImpl.java
 * @Desc 
 * @author renlinggao
 * @date 2017年5月12日 上午10:13:53
 * @version 
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{
    
    @Override
    public void create(User user) {
        user.setCreateTime(new Date());
        user.setFlag(Constant.FLAG_ACTIVE_STATUS);
        userMapper.insert(user);
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