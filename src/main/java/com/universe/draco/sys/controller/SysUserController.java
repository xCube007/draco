package com.universe.draco.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.universe.draco.annotations.CurrentUser;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.utils.Result;
import com.universe.draco.utils.TokenUtils;
import com.universe.draco.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Lxn
 * @since 2019-06-30
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 功能描述:
     *
     * @param loginVo: 前端传入的json
     * @param bindingResult:
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/7/2 20:19
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginVo loginVo, BindingResult bindingResult) {
        // 校验传入的参数是否符合规范
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()), bindingResult);
            return Result.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        // 开始对用户名密码进行校验
        SysUser user = new SysUser();
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        int method = loginVo.getMethod();
        if (method == 1) {
            user.setMobile(account);
        } else {
            user.setEmail(account);
        }
        logger.info("用户 " + account + " 请求登录~~~");

        int count = sysUserService.selectCount(new EntityWrapper<>(user));
        if (count == 0) {
            return Result.error("用户不存在");
        }
        // 密码进行MD5加密
        String md5Psd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5Psd);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<>(user));
        if (sysUser == null) {
            return Result.error("用户或密码不正确");
        } else if (sysUser.getAllowLogin() == 0){
            return Result.error("用户已禁用");
        } else {
            return Result.successLogin(TokenUtils.createJwtToken(sysUser.getId()), "登录成功", sysUser);
        }
    }


    /**
     *
     * 功能描述: 获取所有用户信息
     *
     * @param user: 获得当前的登录的用户信息
     * @param jsonParam: 前端传入的数据
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/9/8 16:13
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserList(@CurrentUser SysUser user, @RequestBody JSONObject jsonParam){
        //除管理员用户外其他用户没有权限
        if (user.getUserType() != 0) {
            return Result.error("权限不足");
        }
        //获取前台发送过来的分页数据
        int pageNo = jsonParam.getByte("pageNo");
        int pageSize = jsonParam.getByte("pageSize");

        Page<SysUser> p = new Page<>(pageNo, pageSize);
        Page<SysUser> page = sysUserService.selectPage(p);
        return  Result.success("请求成功",page);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test (@CurrentUser SysUser user) {

        return Result.success("测试成功", user);
    }
}
