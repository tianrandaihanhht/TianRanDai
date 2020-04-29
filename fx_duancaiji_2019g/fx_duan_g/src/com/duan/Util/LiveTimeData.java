package com.duan.Util;

import java.io.Serializable;

public class LiveTimeData implements Serializable{

	private String meter_name, total_data, total_money, htime, basemeterfieldtypeid;

	public String getBasemeterfieldtypeid() {
		return basemeterfieldtypeid;
	}

	public void setBasemeterfieldtypeid(String basemeterfieldtypeid) {
		this.basemeterfieldtypeid = basemeterfieldtypeid;
	}

	public String getMeter_name() {
		return meter_name;
	}

	public void setMeter_name(String meter_name) {
		this.meter_name = meter_name;
	}

	public String getTotal_data() {
		return total_data;
	}

	public void setTotal_data(String total_data) {
		this.total_data = total_data;
	}

	public String getTotal_money() {
		return total_money;
	}

	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}

	public String getHtime() {
		return htime;
	}

	public void setHtime(String htime) {
		this.htime = htime;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return htime+" "+total_data;
	}
}
