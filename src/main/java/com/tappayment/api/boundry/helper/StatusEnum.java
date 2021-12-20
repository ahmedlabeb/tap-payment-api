package com.tappayment.api.boundry.helper;

public enum StatusEnum {
    INITIATED("INITIATED"), SUCCESS("SUCCESS"), FAILED("FAILED");
    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
