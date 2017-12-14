package com.yll.springboot.demo.vo;


public class SysRoleVO  extends BaseVO{

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 角色类型
     */
	private String type;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
