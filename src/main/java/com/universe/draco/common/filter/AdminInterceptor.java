package com.universe.draco.common.filter;

import com.universe.draco.common.exception.MyException;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.utils.Result;
import com.universe.draco.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AdminInterceptor
 * @Description: 拦截器
 * @Author: Liu Xiaonan
 * @data： 2019/7/20 17:54
 */

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Autowired
    private SysUserService sysUserService;

    private static AdminInterceptor adminInterceptor;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        adminInterceptor = this;
        adminInterceptor.sysUserService = this.sysUserService;
        // 初使化时将已静态化的testService实例化
    }

    /**
     * 功能描述: 在请求处理之前调用验证token是否合法（Controller方法调用之前）
     *
     * @param request:
     * @param response:
     * @param handler:
     * @author: Liu Xiaonan
     * @return: boolean
     * @date: 2019/7/20 17:56
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("=========================进入拦截器============================");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
//        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 有 @LoginRequired 注解，需要认证
//        if (methodAnnotation != null) {
        // 判断是否存在令牌信息，如果存在，则允许登录
        String accessToken = request.getHeader("Authorization");


        if (null == accessToken) {
            // 无token
            throw new MyException(Result.UNAUTHORIZED, "无token，请重新登录");
        } else {
            // 从Redis 中查看 token 是否过期
            Claims claims;
            try {
                claims = TokenUtils.parseJWT(accessToken);
            } catch (ExpiredJwtException e) {
                throw new MyException(Result.UNAUTHORIZED, "token失效，请重新登录");
            } catch (SignatureException se) {
                throw new MyException(Result.UNAUTHORIZED, "token令牌错误");
            }

            String id = claims.getId();
            SysUser user = adminInterceptor.sysUserService.selectById(id);
            if (user == null) {
                throw new MyException(Result.UNAUTHORIZED, "用户不存在，请重新登录");
            }
            // 当前登录用户@CurrentUser
            request.setAttribute("CurrentUser", user);
            return true;
        }

//        } else {//不需要登录可请求
//            return true;
//        }
    }
}