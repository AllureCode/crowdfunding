package com.crowdfunding.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis逆向生成工具类
 */
public class RunMBG {
    public static void main(String[] args) throws Exception {
        List<String> waring = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("src/MBG.xml");
        ConfigurationParser cp = new ConfigurationParser(waring);
        Configuration configuration = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, waring);
        myBatisGenerator.generate(null);
    }
}
