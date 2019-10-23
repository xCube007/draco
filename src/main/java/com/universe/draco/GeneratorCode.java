package com.universe.draco;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @ClassName: GeneratorCode
 * @Description: 代码生成器
 * @Author: Liu Xiaonan
 * @data： 2019/6/20 18:06
 */
public class GeneratorCode {

    private static final String DB_URL = "jdbc:mysql://39.97.121.65:3306/draco?useUnicode=true&characterEncoding=utf8&useSSL=false";

    /**
     * @param author                作者
     * @param packageName           包名
     * @param tablePrefix           需要排除的表前缀名称 数组
     * @param tableNames            需要生成表名 数组
     */
    private void generateCode(String author, String packageName, String[] tablePrefix, String[] tableNames) {
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        // 生成策略
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        PackageConfig packageConfig = new PackageConfig();
        // 设置生成文件风格
        config.setActiveRecord(false)
                // 设置生成Service文件名称
                .setServiceName("%sService")
                // 作者
                .setAuthor(author)
                // 输出路径
                .setOutputDir("D:\\Code\\universe\\draco\\src\\main\\java")
                // XML 二级缓存
                .setEnableCache(false)
                // 是否覆盖生成
                .setFileOverride(false);
        // 设置生成代码风格
        strategyConfig
                // 全局大写命名
                .setCapitalMode(true)
                //【实体】是否为lombok模型（默认 false） <a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setTablePrefix(tablePrefix)
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 是否Rest风格
                .setRestControllerStyle(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
//                .setSuperControllerClass("com.hf.bpmcore.common.web.BaseController")
                // 修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);

        //  设置生成数据源
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(DB_URL)
                .setUsername("root")
                .setPassword("Cube_007")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        //  设置生成包名
        packageConfig.setParent(packageName)
                // 默认包名web
                .setController("controller")
                .setEntity("entity");
        //  生成
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }

    public static void main(String[] args) {
        GeneratorCode g = new GeneratorCode();
        g.generateCode("Liu Xiaonan",
                "com.universe.draco.sys",
                new String[]{""},
                new String[]{"sys_user"}
                );
    }
}
