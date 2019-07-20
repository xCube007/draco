package com.universe.draco.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.utils.Result;
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
     *
     * 功能描述: 登录验证
     *
     * @author: Liu Xiaonan
     * @param loginVo:  前端传入的json
     * @return: java.lang.String
     * @date: 2019/7/2 20:19
     */
    // @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginVo loginVo, BindingResult bindingResult) {
        // 校验传入的参数是否符合规范
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()),bindingResult);
            return Result.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        // 开始对用户名密码进行校验
        SysUser user = new SysUser();
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        int method = loginVo.getMethod();
        if (method == 1){
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
        String md5Psd = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Psd);
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<>(user));
        if (sysUser == null) {
            return Result.error("用户或密码不正确");
        } else {
            return Result.success("登录成功");
        }
    }
}
