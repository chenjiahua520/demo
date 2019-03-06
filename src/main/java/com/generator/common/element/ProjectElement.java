package com.generator.common.element;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目信息
 * 生成代码的模板需要通过get方法获取数据,所有get方法不开省略
 *
 * @author chenjiahua
 * @version 2018-11-23 16:20
 */
public class ProjectElement {

    /**
     * 作者author
     */
    private String author = "chenjiahua";

    /**
     * 版本version
     */
    private String version;

    /**
     * 项目名
     */
    private String name = "example";

    /**
     * groupId
     */
    private String groupId = "com.ziqius";

    /**
     * 项目存放地址
     */
    private String targetProject;

    /**
     * 实体类包
     */
    private String modelPackage;

    /**
     * 数据库接口包
     */
    private String daoPackage;

    /**
     * xml包
     */
    private String xmlPackage;

    /**
     * ServicePackage
     */
    private String servicePackage;

    /**
     * ControllerPackage
     */
    private String controllerPackage;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getXmlPackage() {
        return xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public ProjectElement() {
        this.version = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public ProjectElement(String name, String targetProject) {
        this();
        this.name = name;
        this.targetProject = targetProject;
    }

    public ProjectElement(String name, String targetProject, String author) {
        this(name, targetProject);
        this.author = author;
    }

    public void generatePackage() {
        this.modelPackage = this.groupId + "." + this.name + ".admin.model";
        this.daoPackage = this.groupId + "." + this.name + ".admin.dao";
        this.xmlPackage = this.groupId + "." + this.name + ".admin.dao";
        this.servicePackage = this.groupId + "." + this.name + ".service";
        this.controllerPackage = this.groupId + "." + this.name + ".admin.controller";
        cleanPath(this.targetProject, this.modelPackage);
        cleanPath(this.targetProject, this.daoPackage);
        cleanPath(this.targetProject, this.xmlPackage);
        cleanPath(this.targetProject, this.servicePackage);
        cleanPath(this.targetProject, this.controllerPackage);
    }

    /**
     * 清理文件夹
     *
     * @param targetProject 路径
     * @param packageName 包名
     */
    private void cleanPath(String targetProject, String packageName) {
        String path = targetProject + packageName.replaceAll("\\.", "/");
        File filePath = new File(path);
        if (filePath.exists()) {
            filePath.mkdirs();
        } else {
            File[] files = filePath.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

}
