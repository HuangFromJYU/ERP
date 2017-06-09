package edu.jyu.erp.auth.res.vo;

/**
 * 资源
 * 
 * @author Jason
 *
 */
public class ResModel {
	private Long uuid;
	/** 资源名称 */
	private String name;
	/** 资源的URL */
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
}
