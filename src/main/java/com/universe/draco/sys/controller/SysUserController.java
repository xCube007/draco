package com.universe.draco.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(@RequestBody JSONObject json) {
//        ResultVo result = new ResultVo();
        ResultVo result = new ResultVo();
        try {
            SysUser user = new SysUser();
            String account = json.get("account").toString();
            String password = json.get("password").toString();
            String method = json.get("method").toString();
            if ("1".equals(method)){
                user.setMobile(account);
            } else if ("2".equals(method)){
                user.setEmail(account);
            } else {

            }
            logger.info("用户" + account + "请求登录~");

            int count = sysUserService.selectCount(new EntityWrapper<>(user));
            if (count == 0) {
                result.setCode(ResultVo.ERROR);
                result.setMsg("用户不存在");
                return JSONObject.toJSONString(result);
            }

            String md5Psd = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(md5Psd);
            SysUser sysUser = sysUserService.selectOne(new EntityWrapper<>(user));
            if (sysUser == null) {
                result.setCode(ResultVo.ERROR);
                result.setMsg("用户或密码不正确");
            } else {
                result.setCode(ResultVo.SUCCESS);
                result.setMsg("登录成功");
            }
            return JSONObject.toJSONString(result);
        } catch (Exception e){
            e.printStackTrace();
            result.setCode(ResultVo.ERROR);
            result.setMsg("登录异常");
            return JSONObject.toJSONString(result);
        }

    }
}

