package com.universe.draco.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: LoginRequired
 * @Description: 在需要登录验证的Controller的方法上使用此注解
 * @Author: Liu Xiaonan
 * @data： 2019/7/21 19:12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
