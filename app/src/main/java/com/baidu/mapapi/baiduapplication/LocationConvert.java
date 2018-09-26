package com.baidu.mapapi.baiduapplication;

public class LocationConvert {
	private double x_pi = 3.14159265358979324 * 3000.0 / 180.0;  
	public double bd_lat_out;
	public double bd_lon_out;
	public double gg_lat_out;
	public double gg_lon_out;
	public void bd_encrypt(double gg_lat, double gg_lon)  
	{  
	    double x = gg_lon, y = gg_lat;  
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);  
	    bd_lon_out = z * Math.cos(theta) + 0.0065;  
	    bd_lat_out = z * Math.sin(theta) + 0.006;  
	} 
	
	public void bd_decrypt(double bd_lat, double bd_lon )  
	{  
	    double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
	    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
	    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
	    gg_lon_out = z * Math.cos(theta);  
	    gg_lat_out = z * Math.sin(theta);  
	}  
	
}
