package com.vini.oven.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oven")
public class Oven {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "key", unique = true, nullable = false)
    private String key;

    @Column(name = "light", nullable = true)
    private Boolean light;

    @Column(name = "upper_element", nullable = true)
    private int upper_element;

    @Column(name = "lower_element", nullable = true)
    private int lower_element;

    @Column(name = "grill_temp", nullable = true)
    private int grill_temp;

    @Column(name = "fan_speed", nullable = true)
    private int fan_speed;

    public Oven() {
	this.setKey("Random" + this.id);
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

}
