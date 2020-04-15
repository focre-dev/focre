package com.focre.adminrest.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

public class MybatisPlusUtils {
    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Users/ye21st/temp");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setFileOverride(false); // 是否覆盖文件
        gc.setIdType(IdType.AUTO); // AUTO(0, "数据库ID自增"), INPUT(1, "用户输入ID"),
        gc.setAuthor("ye21st");
        gc.setSwagger2(true);
        gc.setOpen(true);
        gc.setDateType(DateType.ONLY_DATE);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig gc, String fieldType) {
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                return super.processTypeConvert(gc, fieldType);
            }
        });
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("ffk_jiaoyi");
        dsc.setUrl("jdbc:mysql://localhost:3306/focre?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        mpg.setDataSource(dsc);

//        dsc.setUsername("root");
//        dsc.setPassword("ffk_jiaoyi");
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/focre?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
//        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.focre.adminrest.modular");
        pc.setController("controller");
        pc.setEntity("model.entity");
        pc.setMapper("mapper");
        pc.setXml("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略

        strategy.setInclude(new String[] { "sys_options"});
        pc.setModuleName("system");


        mpg.setPackageInfo(pc);

        strategy.setEntityLombokModel(true);

        // Admin需要改成这个
//        strategy.setSuperControllerClass("com.focre.admin.modular.common.controller.BaseController");
//        strategy.setRestControllerStyle(false);

        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
        strategy.setVersionFieldName("version");
        // 逻辑删除属性名称
        strategy.setLogicDeleteFieldName("is_del");
        // 是否跳过视图
        strategy.setSkipView(false);
        // 【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        BeetlTemplateEngine templateEngine = new BeetlTemplateEngine();
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java");
        tc.setEntity("/templates/entity.java");
        tc.setMapper("/templates/mapper.java");
        tc.setXml("/templates/mapper.xml");
        tc.setService("/templates/service.java");
        tc.setServiceImpl("/templates/serviceImpl.java");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);
        mpg.setTemplateEngine(templateEngine);

        // 执行生成
        mpg.execute();

        /*AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new BeetlTemplateEngine());

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Users/ye21st/");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);//不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);//XML二级缓存
        gc.setBaseResultMap(true);//XML ResultMap
        gc.setBaseColumnList(false);//XML columList
        gc.setAuthor("ye21st");//作者

        //自定义文件命名，注意%s 会自动填充表实体属性
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/fpay_master?serverTimezone=GMT%2B8");
        dsc.setUsername("root");
        dsc.setPassword("ffk_jiaoyi");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{});//此处可以修改您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名生成策略
        strategy.setInclude(new String[]{"sys_user"});//需要生成的表

        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.demo");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        //执行生成
        mpg.execute();*/
    }

}
