package com.universe.draco.common.exception;

import com.universe.draco.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: DefaultExceptionHandler
 * @Description: 统一在这个类中处理异常，全局拦截指定的异常
 * @Author: Liu Xiaonan
 * @data： 2019/7/10 22:02
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String processServiceException(Exception e) {
        if (null != e && StringUtils.isNotBlank(e.getMessage())) {
            if (e instanceof MyException ) {
                logger.debug(e.getMessage(), e);
                MyException  ex = (MyException) e;
                return Result.error(ex.getMsg());
            } else {
                logger.error(e.getMessage(), e);
                return Result.error(e.getMessage());
            }
        }
        assert e != null;
        logger.error(e.getMessage(), e);
        return Result.error("出了点小问题，请联系开发人员哦~");
    }
}
