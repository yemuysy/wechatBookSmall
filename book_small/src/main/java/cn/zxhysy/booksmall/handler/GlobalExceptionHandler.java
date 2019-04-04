package cn.zxhysy.booksmall.handler;

import cn.zxhysy.booksmall.utils.ApiJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: GlobalExceptionHandler
 * @description:
 * @author: zxh
 * @date: 2018-11-20 09:59:51
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiJSONResult errorHandler(Exception ex) {

        return ApiJSONResult.errorMsg(ex.getMessage());
    }

}
