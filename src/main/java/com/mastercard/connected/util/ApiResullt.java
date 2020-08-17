package com.mastercard.connected.util;

public enum ApiResullt {
    YES("yes"),
    NO("no");

    private final String value;

    ApiResullt(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
