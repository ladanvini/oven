package com.vini.oven.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.vini.oven.entities.Oven;

@Repository
public class OvenRepository {
	private Oven oven;
	private String path_to_static_resources = "./src/main/resources/static/"; 
	public OvenRepository() {
	}
	private JSONObject read_object_from_storage() throws Exception {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(path_to_static_resources+"oven_state.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONObject oven_obj = (JSONObject) obj;
			
			return oven_obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("My Internal Exception to do");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("My Internal Exception to do");

		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception("My Internal Exception to do");

		}

	}
	private void update_states_from_json(JSONObject oven_obj) {
		Boolean light = (Boolean)oven_obj.get("light");
		int upper = ((Long)oven_obj.get("upper_element")).intValue() ;
		int lower = ((Long)oven_obj.get("lower_element")).intValue()  ;
		int grill = ((Long)oven_obj.get("grill_temp")).intValue()  ;
		int fan = ((Long)oven_obj.get("fan_speed")).intValue()  ;
		oven = new Oven(light, upper, lower, grill, fan);
	}
	public Oven get_oven_state() throws Exception {
		JSONObject json_obj = this.read_object_from_storage();
		this.update_states_from_json(json_obj);
		return this.oven;
	}
}
