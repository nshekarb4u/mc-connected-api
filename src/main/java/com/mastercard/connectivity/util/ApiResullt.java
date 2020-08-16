package com.mastercard.connectivity.util;

public enum ApiResullt {
    YES("yes"),
    NO("no");

    private final String value;

    ApiResullt(String value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
