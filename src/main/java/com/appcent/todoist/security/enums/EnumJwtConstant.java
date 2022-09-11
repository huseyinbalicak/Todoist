package com.appcent.todoist.security.enums;

public enum EnumJwtConstant {

    BEARER("Bearer ")
    ;

    private final String constant;
    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
