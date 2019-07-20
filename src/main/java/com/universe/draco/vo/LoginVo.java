package com.universe.draco.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: LoginVo
 * @Author: Liu Xiaonan
 * @data： 2019/7/10 19:56
 */
public class LoginVo {

    @NotEmpty(message = "用户名不能为空")
    private String account; //用户名（手机号或者邮箱）

    @NotEmpty(message = "密码不能为空")
    private String password; //密码

    @Range(min=1, max=2, message = "登录异常，这是前端的锅哦~")
    private int method; //登录方法  1.手机号  2.邮箱

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
}
