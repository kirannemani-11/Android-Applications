package com.example.myapplication;

import java.io.Serializable;

public class Daily implements  Serializable  {
    private final long dailydatetime;
    private final Double dailymaxtemp;
    private final Double dailymintemp;
    private final String dailydesc;
    private final int dailyprec;
    private final int dailyuv;
    //private final String dailydesc;
    private final String icon;
    private final int dailymorn;
    private final int dailyafter;
    private final int dailyeven;
    private final int dailynight;
    Daily(long dailydatetime,Double dailymaxtemp,Double dailymintemp,String dailydesc,int dailyprec,int dailyuv,String icon,int dailymorn,int dailyafter, int dailyeven, int dailynight)
    {
        this.dailydatetime = dailydatetime;
        this.dailymaxtemp = dailymaxtemp;
        this.dailymintemp = dailymintemp;
        this.dailydesc = dailydesc;
        this.dailyuv = dailyuv;
        this.icon = icon;
        this.dailyprec = dailyprec;
        this.dailymorn = dailymorn;
        this.dailyafter = dailyafter;
        this.dailyeven=dailyeven;
        this.dailynight=dailynight;
    }
    long getDailydatetime() {return dailydatetime;}
    Double getDailymaxtemp() {return dailymaxtemp;}
    Double getDailymintemp() {return dailymintemp;}
    String getDailydesc(){ return dailydesc;}
    int getdailyprec() {return dailyprec;}
    int getDailyuv() {return dailyuv;}
    String getIcon() {return icon;}
    int getDailymorn() {return dailymorn;}
    int getDailyafter() {return dailyafter;}
    int getDailyeven() {return dailyeven;}
    int getDailynight() {return dailynight;}
    Double getdailymaxtemp() {return  dailymaxtemp;}
    Double getdailymintemp() {return dailymintemp;}
    //private final String borders;
}
