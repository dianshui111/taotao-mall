package pojo;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatNode {
	//转为json数据时，自动将属性转为k,v格式。
	@JsonProperty("n")
	private String name;
	@JsonProperty("u")
	private String url;
	@JsonProperty("i")
	private List<?> item;
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
	public List<?> getItem() {
		return item;
	}
	public void setItem(List<?> item) {
		this.item = item;
	}
	
	
	

}
