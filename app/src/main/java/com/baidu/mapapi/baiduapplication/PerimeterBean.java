package com.baidu.mapapi.baiduapplication;


public class PerimeterBean {
	private String name;
	private String distance;
	
	public PerimeterBean() {
		super();
	}
	
	public PerimeterBean(String name, String distance) {
		// TODO Auto-generated constructor stub
		super();
		this.name=name;
		this.distance=distance;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDistance(String distance){
		this.distance=distance;
	}
	public String getDistance(){
		return distance;
	}
}
