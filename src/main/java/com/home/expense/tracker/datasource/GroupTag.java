package com.home.expense.tracker.datasource;

public enum GroupTag {

    None("-"),
    Christmas("Christmas");
    private final String value;

    GroupTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
