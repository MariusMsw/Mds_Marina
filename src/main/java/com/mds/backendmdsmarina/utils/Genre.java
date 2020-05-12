package com.mds.backendmdsmarina.utils;

public enum Genre {

    DRAGOSTE("De dragoste"),
    BAUTURA("De bautura"),
    SMECHERIE("De smecherie"),
    TRISTETE("La suparare");

    private String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

