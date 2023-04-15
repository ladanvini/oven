package com.vini.ovenstate.entities;

public class Oven {
    private Integer id;

    private String key;

    private Boolean light;

    private int upper_element;

    private int lower_element;

    private int grill_temp;

    private int fan_speed;

    public Oven() {
	this.setKey("Random" + this.id);
	this.setLight(false);
	this.setUpper_element(0);
	this.setLower_element(0);
	this.setGrill_temp(0);
	this.setFan_speed(0);

    }

    public Oven(String key) {
	this.setKey(key);
	this.setLight(false);
	this.setUpper_element(0);
	this.setLower_element(0);
	this.setGrill_temp(0);
	this.setFan_speed(0);
    }

    public Oven(String key, Boolean _light, int _upper, int _lower, int _grill, int _fan_speed) {
	this.setKey(key);
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

    public String toString() {
	String key_str = "Oven Key: " + this.getKey() + "\n";
	String light_str = "Lights: ";
	if (this.light)
	    light_str += "ON" + "\n";
	else
	    light_str += "OFF" + "\n";
	String upper_str = "Upper Element Temp: " + Integer.toString(this.upper_element) + "\n";
	String lower_str = "Lower Element Temp: " + Integer.toString(this.lower_element) + "\n";
	String grill_str = "Grill Temp: " + Integer.toString(this.grill_temp) + "\n";
	String fan_str = "Fan Speed: " + Integer.toString(this.fan_speed) + "\n";

	return ("\n" + key_str + light_str + upper_str + lower_str + grill_str + fan_str);
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
	// if the memory references are equal,
	// then the objects are same hence return true.
	if (this == obj) {
	    return true;
	}
	/*
	 * if the second object is null, but the first object is not null, return false.
	 */
	if (obj == null) {
	    return false;
	}
	/*
	 * if the class type of these two objects are not equal, return false.
	 */
	if (getClass() != obj.getClass()) {
	    return false;
	}
	// Typecast the Object class to the Student object.
	Oven oven = (Oven) obj;

	/*
	 * Business logic: If two students have same enrollment number then they are
	 * equal.
	 */
	if (this.getKey().contentEquals(oven.getKey()) && this.getLight() == oven.getLight()
		&& this.getFan_speed() == oven.getFan_speed() && this.getGrill_temp() == oven.getGrill_temp()
		&& this.getLower_element() == oven.getLower_element()
		&& this.getUpper_element() == oven.getUpper_element()) {
	    return true;
	}
	return false;
    }

}
