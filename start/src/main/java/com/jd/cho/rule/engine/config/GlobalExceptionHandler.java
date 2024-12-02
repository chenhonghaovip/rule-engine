package com.jd.cho.rule.engine.config;

import com.alibaba.fastjson.JSON;
import com.jd.cho.rule.engine.common.base.BaseResponse;
import com.jd.cho.rule.engine.common.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     *
     * @param e 异常信息
     * @return BaseResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse("参数异常");
        return BaseResponse.error(message);
    }

    /**
     * 空指针异常
     *
     * @param e e
     * @return BaseResponse
     */
    @ExceptionHandler(value = NullPointerException.class)
    public BaseResponse<Void> exceptionHandler(NullPointerException e, HttpServletRequest httpServletRequest) {
        log.error("发生空指针异常！原因是:{}", e.getMessage(), e);
        return BaseResponse.error(e.getMessage());
    }


    /**
     * 处理自定义的业务异常
     *
     * @param e e
     * @return BaseResponse
     */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<Void> bizExceptionHandler(BusinessException e, HttpServletRequest httpServletRequest) {
        log.error("发生业务异常！异常code:{},参数为：{}，原因是:{}", e.getErrorCode(), JSON.toJSONString(httpServletRequest.getParameterMap()), e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }


    /**
     * 处理其他异常
     *
     * @param e e
     * @return BaseResponse
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Void> exceptionHandler(Exception e, HttpServletRequest httpServletRequest) {
        log.error("未知异常！原因是:", e);
        return BaseResponse.error("系统异常");
    }
}
