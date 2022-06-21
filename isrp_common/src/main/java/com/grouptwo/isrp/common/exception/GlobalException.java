package com.grouptwo.isrp.common.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * @author: zhanghongzhi
 * @description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * 资源不存在异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity errorHandler(NoHandlerFoundException exception) {
        LOGGER.error("找不到资源："+exception.getMessage());
        return new ResponseEntity("资源不存在1111",HttpStatus.NOT_FOUND);
    }
//    /**
//     * 非法请求异常
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public res badRequestException(IllegalArgumentException exception) {
//        LOGGER.error("非法请求："+exception.getMessage(),exception);
//        return ResBean.fail("非法请求，请求失败！");
//    }
//
//
//
//    /**
//     * 请求方式错误
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResBean errorHandler(HttpRequestMethodNotSupportedException exception) {
//        LOGGER.error("请求方法异常："+exception.getMessage());
//        return ResBean.fail(405L, "请求方式错误");
//    }
//
//    /**
//     * 请求参数错误
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResBean errorHandler(HttpMediaTypeNotSupportedException exception) {
//        LOGGER.error("请求参数异常："+exception.getMessage(),exception);
//        return ResBean.fail(415L, "请求参数错误");
//    }
//
//    /**
//     * 数据库异常
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(SQLException.class)
//    public ResBean mySQLException(SQLException exception) {
//        LOGGER.error("数据库异常："+exception.getMessage(),exception);
//        return ResBean.fail("数据库异常，操作失败！");
//    }
//
//    /**
//     * 服务器异常
//     * @param exception
//     * @returnz
//     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = Exception.class)
//    public ResBean errorHandler(Exception exception) {
//        LOGGER.error("服务器异常："+exception.getMessage(),exception);
//        return ResBean.fail("服务器异常");
//    }


}
