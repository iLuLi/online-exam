package com.deng.utils;

import com.deng.entity.ColumnEntity;
import com.deng.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hp on 2017/4/19.
 */
public class GenUtils {
    public static void generateCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        String className = columnsToJava(table.get("tableName"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        tableEntity.setTableComment(table.get("tableComment"));

        Configuration config = getConfig();
        List<ColumnEntity> cols = new ArrayList<>();
        for(Map<String, String> col : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(col.get("columnName"));
            columnEntity.setColumnComment(col.get("columnComment"));

            String attrName = columnsToJava(col.get("columnName"));
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            columnEntity.setDataType(config.getString(col.get("dataType")));
            columnEntity.setExtra(col.get("extra"));
            if("PRI".equalsIgnoreCase(col.get("columnsKey")) && tableEntity.getPk() != null) {
                tableEntity.setPk(columnEntity);
            }
            cols.add(columnEntity);
        }
        tableEntity.setColumns(cols);
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        Properties props = new Properties();
        props.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(props);

        //封装模版数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getTableComment());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        List<String> templates = getTemplates();
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tel = Velocity.getTemplate(template, "UTF-8");
            tel.merge(context, sw);

            try {
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"))));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("模板渲染错误", e);
            }
        }
    }
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Dao.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/list.html.vm");
        templates.add("template/list.js.vm");
        templates.add("template/menu.sql.vm");
        return templates;
    }
    public static String columnsToJava(String name) {
        if(StringUtils.isNotBlank(name)) {
            return WordUtils.capitalizeFully(name, new char[]{'_'}).replaceAll("_", "");
        } else {
            return name;
        }
    }
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("配置文件加载异常", e);
        }
    }
    public static String getFileName(String template, String className, String packageName){
        String packagePath = "main" + File.separator + "java" + File.separator;
        if(StringUtils.isNotBlank(packageName)){
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if(template.contains("Entity.java.vm")){
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if(template.contains("Dao.java.vm")){
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if(template.contains("Dao.xml.vm")){
            return packagePath + "dao" + File.separator + className + "Dao.xml";
        }

        if(template.contains("Service.java.vm")){
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if(template.contains("ServiceImpl.java.vm")){
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if(template.contains("Controller.java.vm")){
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if(template.contains("list.html.vm")){
            return "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "page"
                    + File.separator + "generator" + File.separator + className.toLowerCase() + ".html";
        }

        if(template.contains("list.js.vm")){
            return "main" + File.separator + "webapp" + File.separator + "js" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
        }

        if(template.contains("menu.sql.vm")){
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }
}
