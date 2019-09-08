package com.universe.draco.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfig
 * @Description: MybatisPlus配置
 * @Author: Liu Xiaonan
 * @data： 2019/9/8 15:07
 */

@Configuration
public class MybatisPlusConfig {

    /**
     *
     * 功能描述: 分页插件
     * @author: Liu Xiaonan
     * @return: com.baomidou.mybatisplus.plugins.PaginationInterceptor
     * @date: 2019/9/8 15:08
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
