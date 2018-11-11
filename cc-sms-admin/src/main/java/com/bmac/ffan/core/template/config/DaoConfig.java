package com.bmac.ffan.core.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao模板生成的配置
 *
 * @author xuzhanfu
 * @date 2017-05-07 22:12
 */
public class DaoConfig {

    private ContextConfig contextConfig;

    private String daoPathTemplate;
    private String xmlPathTemplate;

    private String packageName;
    private List<String> imports;//所引入的包
    
    public void init() {
    	ArrayList<String> imports = new ArrayList<>();
        imports.add("com.bmac.ffan.core.base.controller.BaseController");
        imports.add("java.util.List");
        imports.add("java.util.Map");
        imports.add("org.apache.ibatis.annotations.Param");
        imports.add("com.baomidou.mybatisplus.plugins.Page");
        imports.add("com.bmac.ffan.common.persistence.model." + contextConfig.getEntityName());
        this.imports = imports;
        this.daoPathTemplate = "\\src\\main\\java\\com\\bmac\\ffan\\modular\\" + contextConfig.getModuleName() + "\\dao\\{}Dao.java";
        this.xmlPathTemplate = "\\src\\main\\java\\com\\bmac\\ffan\\modular\\" + contextConfig.getModuleName() + "\\dao\\mapping\\{}Dao.xml";
        this.packageName = "com.bmac.ffan.modular." + contextConfig.getModuleName() + ".dao";
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDaoPathTemplate() {
        return daoPathTemplate;
    }

    public void setDaoPathTemplate(String daoPathTemplate) {
        this.daoPathTemplate = daoPathTemplate;
    }

    public String getXmlPathTemplate() {
        return xmlPathTemplate;
    }

    public void setXmlPathTemplate(String xmlPathTemplate) {
        this.xmlPathTemplate = xmlPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}
    
}
