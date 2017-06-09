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
package com.renlg.base;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.renlg.user.dao.UserMapper;

/**
 * @Type BaseService.java
 * @Desc 
 * @author renlinggao
 * @date 2017年5月12日 上午10:14:14
 * @version 
 */
public class BaseService {
    protected Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    protected UserMapper userMapper;
}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2017年5月12日 renlinggao create
 */
