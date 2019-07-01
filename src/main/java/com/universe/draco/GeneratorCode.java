package com.universe.draco;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorCode {

    private static final String DB_URL = "jdbc:mysql://39.97.121.65:3306/draco?useUnicode=true&characterEncoding=utf8&useSSL=false";

    /**
     * @param author                作者
     * @param packageName           包名
     * @param tablePrefix           需要排除的表前缀名称 数组
     * @param tableNames            需要生成表名 数组
     */
    private void generateCode(String author, String packageName, String[] tablePrefix, String[] tableNames) {
        GlobalConfig config = new GlobalConfig();   // 全局配置
        StrategyConfig strategyConfig = new StrategyConfig();   // 生成策略
        DataSourceConfig dataSourceConfig = new DataSourceConfig();   // 数据源
        PackageConfig packageConfig = new PackageConfig();
        // 设置生成文件风格
        config.setActiveRecord(false)
                .setServiceName("%sService")    //  设置生成Service文件名称
                .setAuthor(author)    // 作者
                .setOutputDir("D:\\Code\\universe\\draco\\src\\main\\java")   //  输出路径
                .setEnableCache(false) // XML 二级缓存
                .setFileOverride(false);   //  是否覆盖生成
        //  设置生成代码风格
        strategyConfig
                // 全局大写命名
                .setCapitalMode(true)
                //  【实体】是否为lombok模型（默认 false） <a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setTablePrefix(tablePrefix)
                .setNaming(NamingStrategy.underline_to_camel)   //表名生成策略
                .setRestControllerStyle(true)    //  是否Rest风格
                .setEntityBooleanColumnRemoveIsPrefix(true)   // Boolean类型字段是否移除is前缀处理
//                .setSuperControllerClass("com.hf.bpmcore.common.web.BaseController")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        //  设置生成数据源
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(DB_URL)
                .setUsername("root")
                .setPassword("Cube_007")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        //  设置生成包名
        packageConfig.setParent(packageName)
                .setController("controller")    // 默认包名web
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
        g.generateCode("Lxn",
                "com.universe.draco.sys",
                new String[]{""},
                new String[]{"sys_user"}
                );
    }
}
