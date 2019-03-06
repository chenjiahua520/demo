package ${project.modelPackage};


/**
 * ${tableRemarks}Entity
 *
 * @author ${project.author}
 * @version ${project.version}
 */
public class ${javaClassName} extends BaseEntity<${javaClassName}> {

    private static final long serialVersionUID = 1L;

<#-- 生成字段属性 -->
<#list columnList as c>
<#-- 如果不是基类属性 -->
    <#if !c.baseColumn>
	/**
	 * ${c.columnRemarks}
	 */
	private ${c.javaType} ${c.javaFieldName};

    </#if>
</#list>
	<#-- 构造方法 -->
	public ${javaClassName}() {
		super();
	}

	public ${javaClassName}(String id){
		super(id);
	}

<#-- 生成get和set方法 -->
<#list columnList as c>
	<#-- 如果不是基类属性 -->
    <#if !c.baseColumn>
	public ${c.javaType} get${c.javaFieldNameAtRuntime}() {
		return ${c.javaFieldName};
	}

	public void set${c.javaFieldNameAtRuntime}(${c.javaType} ${c.javaFieldName}) {
		this.${c.javaFieldName} = ${c.javaFieldName};
	}

    </#if>
</#list>
}