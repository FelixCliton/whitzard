package com.newpi.data.exception;

import com.newpi.data.enums.ResultCode;
import com.newpi.data.model.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/28 5:01 PM
 * @desc: 全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理Validator数据校验失败异常
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultEntity handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors == null || allErrors.isEmpty()) {
            return new ResultEntity(ResultCode.ARGS_ERROR, e);
        }
        ObjectError error = allErrors.get(0);
        String message = error.getDefaultMessage();
        return new ResultEntity(ResultCode.ARGS_ERROR, message);
    }

    /**
     * 处理系统运行时异常
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultEntity handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResultEntity(ResultCode.SERVER_ERROR, e);
    }

    /**
     * 处理Socket超时异常
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(SocketTimeoutException.class)
    public ResultEntity handleSocketTimeOutException(SocketTimeoutException e) {
        log.error(e.getMessage(), e);
        return new ResultEntity(ResultCode.SOCKET_TIME_OUT_ERROR, e);
    }

    /**
     * 处理ClassCastException
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public ResultEntity handleClassCastException(ClassCastException e) {
        log.error(e.getMessage(), e);
        return new ResultEntity(ResultCode.CLASS_CAST_ERROR);
    }

    /**
     * 处理 WhitzardException
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(WhitzardException.class)
    public ResultEntity handleWhitzardException(WhitzardException e) {
        log.error(e.getMessage(), e);
        return new ResultEntity(e.getErrorCode(), e.getErrorCode().getMessage());
    }

    /**
     * 处理 MethodArgumentNotValidException
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ResultEntity(ResultCode.ARGS_ERROR, e);
    }


}
