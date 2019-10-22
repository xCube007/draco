package com.universe.draco.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Lxn
 * @since 2019-06-30
 */
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐值
     */
    private String salt;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户类型 0-管理员用户 1-普通用户
     */
    private Integer userType;
    /**
     * 允许登录 0-不允许 1-允许
     */
    private Integer allowLogin;
    /**
     * 账户启用或禁用原因
     */
    private String allowLoginReason;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 最后登录时间
     */
    private Date lastLoginDate;
    /**
     * 备注信息
     */
    private String remarks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAllowLogin() {
        return allowLogin;
    }

    public void setAllowLogin(Integer allowLogin) {
        this.allowLogin = allowLogin;
    }

    public String getAllowLoginReason() {
        return allowLoginReason;
    }

    public void setAllowLoginReason(String allowLoginReason) {
        this.allowLoginReason = allowLoginReason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        ", id=" + id +
        ", username=" + username +
        ", mobile=" + mobile +
        ", email=" + email +
        ", password=" + password +
        ", salt=" + salt +
        ", name=" + name +
        ", userType=" + userType +
        ", allowLogin=" + allowLogin +
        ", allowLoginReason=" + allowLoginReason +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", lastLoginDate=" + lastLoginDate +
        ", remarks=" + remarks +
        "}";
    }
}
