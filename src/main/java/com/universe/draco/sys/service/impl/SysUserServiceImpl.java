package com.universe.draco.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.universe.draco.sys.entity.SysUser;
import com.universe.draco.sys.mapper.SysUserMapper;
import com.universe.draco.sys.service.SysUserService;
import com.universe.draco.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

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

    /**
     *
     * 功能描述: 新增用户
     *
     * @param userNew: 用户数据对象
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/10/22 18:19
     */
    @Override
    public String add(SysUser userNew) {

        //校验邮箱是否已经存在
        SysUser user = new SysUser();
        user.setEmail(userNew.getEmail());
        if (userRegister(user)) {
            return Result.error("该邮箱已存在");
        }
        //校验手机号是否已经存在
        user = new SysUser();
        user.setMobile(userNew.getMobile());
        if (userRegister(user)) {
            return Result.error("该手机号已存在");
        }
        //校验用户名是否已经存在
        user = new SysUser();
        user.setUsername(userNew.getUsername());
        if (userRegister(user)) {
            return Result.error("该用户名已存在");
        }
        String md5Psd = DigestUtils.md5DigestAsHex(userNew.getPassword().getBytes(StandardCharsets.UTF_8));
        userNew.setPassword(md5Psd);
        if (this.insert(userNew)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    /**
     *
     * 功能描述: 校验某些数据是否重复
     *
     * @param userNew: 用户对象
     * @author: Liu Xiaonan
     * @return: boolean
     * @date: 2019/10/22 18:39
     */
    private boolean userRegister(SysUser userNew) {
        int count = this.selectCount(new EntityWrapper<>(userNew));
        return count != 0;
    }

}
