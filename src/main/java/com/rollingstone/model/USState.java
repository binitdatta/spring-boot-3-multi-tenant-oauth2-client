package com.rollingstone.model;

public class USState {

    private String usStateCode;

    private String usStateName;

    public USState(String usStateCode, String usStateName) {
        this.usStateCode = usStateCode;
        this.usStateName = usStateName;
    }

    public String getUsStateCode() {
        return usStateCode;
    }

    public void setUsStateCode(String usStateCode) {
        this.usStateCode = usStateCode;
    }

    public String getUsStateName() {
        return usStateName;
    }

    public void setUsStateName(String usStateName) {
        this.usStateName = usStateName;
    }
}
