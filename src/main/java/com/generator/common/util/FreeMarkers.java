/**
 * Copyright &copy; 2015-2020 <a href="http://www.ziqius.org/">ziqius</a> All rights reserved.
 */
package com.generator.common.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreeMarkers 工具类
 *
 * @author ziqius
 * @version 2013-01-15
 */
public class FreeMarkers {

    public static String renderString(String templateString, Map<String, ?> model) throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        Template t = new Template("name", new StringReader(templateString), new Configuration(Configuration.VERSION_2_3_28));
        t.process(model, result);
        return result.toString();
    }

    public static String renderTemplate(Template template, Object model) throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    public static void renderTemplateToFile(Template template, Object model, String filePath) throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        template.process(model, result);
        FileUtils.write(new File(filePath), result.toString(), "UTF-8", false);
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        // 兼容模式
        cfg.setClassicCompatible(true);
        cfg.setDefaultEncoding("UTF-8");
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

}
