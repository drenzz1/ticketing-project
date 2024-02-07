package org.foo.utils;

public enum Gender {
    MALE("Male"),FEMALE("Female"),STATUS("IN PROGRESS");

    private final String value;

    Gender(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
