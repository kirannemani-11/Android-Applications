package com.example.myapplication;

public class Hour {
    private int datetimeEpoch;
    private double temp;
    private String conditions;
    private String icon;

    public Hour(int datetimeEpoch, double temp, String conditions, String icon) {
        this.datetimeEpoch = datetimeEpoch;
        this.temp = temp;
        this.conditions = conditions;
        this.icon = icon;
    }

    public int getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public double getTemp() {
        return temp;
    }

    public String getConditions() {
        return conditions;
    }

    public String getIcon() {
        return icon;
    }
}
