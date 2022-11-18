package xpit.top.config;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xpit.top.enums.HttpCodeEnum;
import xpit.top.result.Result;

/**
 * @author PTJ
 * @since 2022/11/18 23:33
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotLoginException.class)
    public Result handleNotLoginException(NotLoginException e){
        log.error("异常信息：{}", e.getMessage());
        return Result.errorResult(HttpCodeEnum.NO_LOGIN);
    }

    @ExceptionHandler(value =NullPointerException.class)
    public Result exceptionHandler(NullPointerException e){
        log.error("异常信息：{}",e.getMessage());
        return Result.errorResult(HttpCodeEnum.NULL_POINTER);
    }
}
