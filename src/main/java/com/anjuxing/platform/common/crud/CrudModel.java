package com.anjuxing.platform.common.crud;

import com.anjuxing.platform.common.base.BaseModel;
import com.anjuxing.platform.common.base.ValidateData;
import com.anjuxing.platform.common.util.ValidateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;

public abstract class CrudModel<T> extends BaseModel<T> {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer pageNum;
	private Integer pageSize;
	private String order;
	
	public CrudModel() {
		
	}

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//@JsonIgnore
	public Integer getPageNum() {
		return pageNum;
	}
	
	@JsonProperty(value="pageNum")
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	//@JsonIgnore
	public Integer getPageSize() {
		return pageSize;
	}
	
	@JsonProperty(value="pageSize")
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	//@JsonIgnore
	public String getOrder() {
		if (ValidateUtils.isNotEmpty(order)) {
            // SQL过滤，防止注入
            String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                    + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
            Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
            if (sqlPattern.matcher(order).find()) {
                order = "";
            }else { //属性名转列名 ASC DESC小写 
				order = order.trim().replaceAll(" ASC", " asc").replaceAll(" DESC", " desc").replaceAll("[A-Z]", "_$0").toLowerCase();
			}
        }
		return order;
	}
	
	@JsonProperty(value="order")
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 数据校验，插入数据之前执行，子类实现
	 * @return
	 */
	public abstract ValidateData validate();
	/**
	 * 插入数据之前执行方法，子类实现
	 */
	public abstract void preInsert();
	/**
	 * 更新数据之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	/**
	 * 逻辑删除数据之前执行方法，子类实现
	 */
	public abstract void preCancel();
	
	
	public abstract String toString();

}
