package com.vini.ovenstate.entities;

public enum State {
    GRILL, // Only Grill
    CONVECTION_OVEN, // Upper & lower Elements
    FAN_OVEN, // Fan + Convection
    FAN_GRILL, // Fan + Grill
    UPPER, // Upper element only
    LOWER, // Lower element only
    FAN, // Fan only
    LIGHT // Light only

}
