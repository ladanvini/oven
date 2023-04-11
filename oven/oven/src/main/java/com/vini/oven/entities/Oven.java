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

	@Column(name = "light")
	private Boolean light;

	@Column(name = "upper_element")
	private int upper_element;

	@Column(name = "lower_element")
	private int lower_element;

	@Column(name = "grill_temp")
	private int grill_temp;

	@Column(name = "fan_speed")
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

	public String toString() {
		String light_str = "Lights: ";
		if (this.light)
			light_str += "ON";
		else
			light_str += "OFF";
		String upper_str = "Upper Element Temp: " + Integer.toString(this.upper_element);
		String lower_str = "Lower Element Temp:" + Integer.toString(this.lower_element);
		String grill_str = "Grill Temp: " + Integer.toString(this.grill_temp);
		String fan_str = "Fan Speed: " + Integer.toString(this.fan_speed);

		return ("\n" + light_str + "\n" + upper_str + "\n" + lower_str + "\n" + grill_str + "\n" + fan_str + "\n");
	}

}
