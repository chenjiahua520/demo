package com.generator.main;

import com.generator.common.element.ColumnElement;
import com.generator.common.element.ProjectElement;
import com.generator.common.element.TableElement;
import com.generator.common.util.FreeMarkers;
import com.generator.common.util.JdbcUtil;
import com.generator.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjiahua
 * @version 2019-02-01 13:54
 */
public class GeneratorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorUtil.class);

    public static void generator(String tablePrefix, ProjectElement project) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        List<TableElement> tableElementList = jdbcUtil.getTablesWithColumns(tablePrefix);
        jdbcUtil.release();
        if (tableElementList.isEmpty()) {
            LOGGER.info("-----------没有表----------");
            return;
        }
        Configuration cfg = FreeMarkers.buildConfiguration("/tpl");
        project.generatePackage();
        String modelPath = project.getTargetProject() + project.getModelPackage().replaceAll("\\.", "/") + "/";
        String daoPath = project.getTargetProject() + project.getDaoPackage().replaceAll("\\.", "/") + "/";
        String xmlPath = project.getTargetProject() + project.getXmlPackage().replaceAll("\\.", "/") + "/";
        String servicePath = project.getTargetProject() + project.getServicePackage().replaceAll("\\.", "/") + "/";
        String controllerPath = project.getTargetProject() + project.getControllerPackage().replaceAll("\\.", "/") + "/";
        for (TableElement tableElement : tableElementList) {
            tableElement.setProject(project);
            FreeMarkers.renderTemplateToFile(cfg.getTemplate("/Entity.ftl"), tableElement, modelPath + tableElement.getJavaClassName() + ".java");
            FreeMarkers.renderTemplateToFile(cfg.getTemplate("/Dao.ftl"), tableElement, daoPath + tableElement.getJavaClassName() + "Dao.java");
            FreeMarkers.renderTemplateToFile(cfg.getTemplate("/Mapper.ftl"), tableElement, xmlPath + tableElement.getJavaClassName() + "Dao.xml");
            FreeMarkers.renderTemplateToFile(cfg.getTemplate("/Service.ftl"), tableElement, servicePath + tableElement.getJavaClassName() + "Service.java");
            FreeMarkers.renderTemplateToFile(cfg.getTemplate("/Controller.ftl"), tableElement, controllerPath + tableElement.getJavaClassName() + "Controller.java");
        }
        LOGGER.info("-----------THE END-----------");
        System.out.println("-----------THE END-----------");
    }
    
    public static void main(String[] args) throws Exception {
        String str = "hello_world_test";
        System.out.println(StringUtil.lineToHump(str));
        System.out.println(str.indexOf(0));
        String targetProject = "G:/test-estate/";
        String tablePrefix = "zq_loan_";
        String name = "estate";
        ProjectElement projectElement = new ProjectElement(name, targetProject);
        generator(tablePrefix, projectElement);
        System.out.println(String.class.getSimpleName());
        System.out.println(Date.class.getSimpleName());
        System.out.println(GeneratorUtil.class.getResource("/"));
        System.out.println(GeneratorUtil.class.getResource("/").getPath());
    }

}
