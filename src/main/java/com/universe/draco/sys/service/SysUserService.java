package com.universe.draco.sys.service;

import com.universe.draco.sys.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.universe.draco.vo.ResultVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Lxn
 * @since 2019-06-30
 */
public interface SysUserService extends IService<SysUser> {

    ResultVo login(String account, String password);
}
