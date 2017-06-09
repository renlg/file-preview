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

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @Type BaseController.java
 * @Desc 
 * @author renlinggao
 * @date 2017年5月11日 下午3:30:58
 * @version 
 */
public class BaseController {

    /**
     * 日志打印
     */
    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * 基于异常处理机制
     * 
     * @param request
     * @param ex
     * @return
     * @author renlinggao
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exp(HttpServletRequest request, Exception exception) {
        /*
         * 发送错误信息给开发人员
         */
        logger.error(exception.getMessage(), exception);
        String message = "";
        if (exception instanceof NumberFormatException) {
            message = "参数类型错误！";
        } else if (exception instanceof MissingServletRequestParameterException
                || exception instanceof TypeMismatchException
                || exception instanceof HttpMessageNotReadableException) { // 400
            message = "接口请求错误(参数类型不匹配或参数缺失)！";
        } else if (exception instanceof NoSuchAlgorithmException) {
            message = "短信网关异常！";
        } else if (exception instanceof BindException) {
            message = "参数绑定错误！";
        } else if (exception instanceof NullPointerException) {
            message = "参数不可为空！";
        } else if (exception instanceof FileNotFoundException) {
            message = "所选文件不存在！";
        } else if (exception instanceof RuntimeException) {
            message = exception.getMessage().length() <= 20 ? exception.getMessage() : "操作失败";
        } else if (exception instanceof ConnectException) {
            message = "请求连接错误！";
        } else if (exception instanceof MaxUploadSizeExceededException) {
            Long size = (((MaxUploadSizeExceededException) exception).getMaxUploadSize()) / 1024;
            message = "上传文件大小应小于" + size + "KB（" + size / 1024 + "MB）";
        } else {
            message = "系统错误！";
        }
        return new JsonResult(false, "错误原因：" + message, null);
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
