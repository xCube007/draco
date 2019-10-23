package com.universe.draco.sys.controller;

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
import java.util.Date;

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
     * 功能描述: 用户登录
     *
     * @param loginVo: 前端传入的json
     * @param bindingResult:
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/7/2 20:19
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginVo loginVo, BindingResult bindingResult) {

        Date date = new Date();
        // 校验传入的参数是否符合规范
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()), bindingResult);
            return Result.error("出了点小问题，请联系开发人员哦~");
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
            return Result.error("用户不可用，请联系管理员");
        } else {
            // 更新最后登录时间
            SysUser updateUser = new SysUser();
            updateUser.setId(sysUser.getId());
            updateUser.setLastLoginDate(date);
            sysUserService.updateById(updateUser);
            return Result.successLogin(TokenUtils.createJwtToken(sysUser.getId()), "登录成功", sysUser);
        }
    }


    /**
     *
     * 功能描述: 获取所有用户信息
     *
     * @param user: 获得当前的登录的用户信息
     * @param pageNo: 分页的页码
     * @param pageSize: 每页的数据数量
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/9/8 16:13
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserList(@CurrentUser SysUser user, int pageNo, int pageSize) {
        //除管理员用户外其他用户没有权限
        if (user.getUserType() != 0) {
            return Result.error("权限不足");
        }

        Page<SysUser> p = new Page<>(pageNo, pageSize);
        Page<SysUser> page = sysUserService.selectPage(p);
        return  Result.success("请求成功",page);
    }

    /**
     *
     * 功能描述: 添加用户
     *
     * @param user: 用户数据对象
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/10/22 16:29
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@CurrentUser SysUser user, @RequestBody SysUser userNew) {
        //除管理员用户外其他用户没有权限
        if (user.getUserType() != 0) {
            return Result.error("权限不足");
        }
        return sysUserService.add(userNew);
    }

    /**
     *
     * 功能描述: 根据token获得登录用户信息
     *
     * @param user: 用户数据对象
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/10/23 16:33
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public String getUserInfo(@CurrentUser SysUser user) {

        return Result.success("请求成功", user);
    }
}
