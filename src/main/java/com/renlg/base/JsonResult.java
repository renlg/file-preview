/*
 * Project: door
 * 
 * File Created at 2017年5月11日
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

/**
 * @Type JsonResult.java
 * @Desc 
 * @author renlinggao
 * @date 2017年5月11日 下午3:42:04
 * @version 
 */
public class JsonResult {
    private Boolean success;
    private String message;
    private Object model;

    public JsonResult() {
        this.success = true;
    }

    public JsonResult(Boolean success, String message, Object model) {
        this.success = success;
        this.message = message;
        this.model = model;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2017年5月11日 renlinggao create
 */
