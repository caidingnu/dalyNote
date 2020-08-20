package cn.gathub.exception;

import cn.gathub.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CDN
 * @desc
 * @date 2020/08/20 09:31
 */
@RestControllerAdvice
public class AllException {
    @ExceptionHandler
    public Result exceptiona(Exception ex) {
      return Result.error(ex.getMessage());
    }
}
