package com.vini.oven.entities;

public class Oven {
	private Boolean light;
	private int upper_element;
	private int lower_element;
	private int grill_temp;
	private int fan_speed;
	
	public Oven() {
		this.setLight(false);
		this.setUpper_element(0);
		this.setLower_element(0);
		this.setGrill_temp(0);
		this.setFan_speed(0);
	}
	public Oven(Boolean _light, int _upper, int _lower, int _grill, int _fan_speed) {
		this.setFan_speed(_fan_speed);
		this.setGrill_temp(_grill);
		this.setLight(_light);
		this.setLower_element(_lower);
		this.setUpper_element(_upper);
		
	}
	public int getFan_speed() {
		return fan_speed;
	}

	public void setFan_speed(int fan_speed) {
		this.fan_speed = fan_speed;
	}

	public int getGrill_temp() {
		return grill_temp;
	}

	public void setGrill_temp(int grill_temp) {
		this.grill_temp = grill_temp;
	}

	public int getLower_element() {
		return lower_element;
	}

	public void setLower_element(int lower_element) {
		this.lower_element = lower_element;
	}

	public int getUpper_element() {
		return upper_element;
	}

	public void setUpper_element(int upper_element) {
		this.upper_element = upper_element;
	}

	public Boolean getLight() {
		return light;
	}

	public void setLight(Boolean light) {
		this.light = light;
	}
	
}
