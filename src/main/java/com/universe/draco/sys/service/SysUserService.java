package com.universe.draco.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.universe.draco.sys.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Lxn
 * @since 2019-06-30
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 功能描述: 增加用户
     * @param userNew: 用户对象
     * @return: java.lang.String
     */
    String add(SysUser userNew);

}
