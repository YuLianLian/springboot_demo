package com.yll.springboot.demo.vo;

public class SysPermissionVO extends BaseVO{

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * url地址
     */
	private String url;
    /**
     * url描述
     */
	private String name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
