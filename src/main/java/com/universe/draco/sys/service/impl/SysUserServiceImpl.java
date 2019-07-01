package com.universe.draco.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.mapper.SysUserMapper;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.vo.ResultVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Lxn
 * @since 2019-06-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public ResultVo login(String account, String password) {

        return null;
    }
}
