package com.generator.common.base;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	protected String id;

	/**
	 * 备注
	 */
	protected String remarks;
	
	/**
	 * 创建日期
	 */
	protected Date createDate;
	
	/**
	 * 更新日期
	 */
	protected Date updateDate;
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核）
	 */
	protected String delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
