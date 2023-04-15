package com.vini.ovenstate.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.vini.ovenstate.entities.Oven;
import com.vini.ovenstate.entities.State;

@Service
public class StateService {
    private static final Random PRNG = new Random();

    public static State randomOvenState() {
	State[] ovenStates = State.values();
	return ovenStates[PRNG.nextInt(ovenStates.length)];
    }

    public Oven getCurrentOvenState(Oven oven) {
	/*
	 * This is where the service would get the oven state from an actual oven. Here,
	 * instead we're going to get a random oven state, and send the updated oven
	 * properties back to oven-svc.
	 */
	State currentOvenState = randomOvenState();

	switch (currentOvenState) {
	case CONVECTION_OVEN:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(PRNG.nextInt(400));
	    oven.setLower_element(PRNG.nextInt(400));
	    break;
	case FAN:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(0);
	    oven.setLower_element(0);
	    break;
	case FAN_GRILL:
	    oven.setLight(false);
	    oven.setFan_speed(PRNG.nextInt(100));
	    oven.setGrill_temp(PRNG.nextInt(400));
	    oven.setUpper_element(0);
	    oven.setLower_element(0);
	    break;
	case FAN_OVEN:
	    oven.setLight(false);
	    oven.setFan_speed(PRNG.nextInt(100));
	    oven.setGrill_temp(0);
	    oven.setUpper_element(PRNG.nextInt(400));
	    oven.setLower_element(PRNG.nextInt(400));
	    break;
	case GRILL:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(PRNG.nextInt(400));
	    oven.setUpper_element(0);
	    oven.setLower_element(0);
	    break;
	case LIGHT:
	    oven.setLight(true);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(0);
	    oven.setLower_element(0);
	    break;
	case LOWER:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(0);
	    oven.setLower_element(PRNG.nextInt(400));
	    break;
	case UPPER:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(PRNG.nextInt(400));
	    oven.setLower_element(0);
	    break;
	default:
	    oven.setLight(false);
	    oven.setFan_speed(0);
	    oven.setGrill_temp(0);
	    oven.setUpper_element(0);
	    oven.setLower_element(0);
	    break;
	}
	return oven;
    }
}
