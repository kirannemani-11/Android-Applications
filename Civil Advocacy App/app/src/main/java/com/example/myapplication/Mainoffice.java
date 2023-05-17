package com.example.myapplication;

import java.io.Serializable;

public class Mainoffice implements  Serializable {
    private String name;
    private String officename;
    private String picurl;
    private String party;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String facebook;
    private String twitter;
    private String youtube;
    public Mainoffice(String name, String officename, String picurl, String party,String address, String phone, String email, String website,String facebook,String twitter,String youtube)
    {
        this.name = name;
        this.officename = officename;
        this.picurl = picurl;
        this.party = party;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.twitter=twitter;
        this.facebook = facebook;
        this.youtube = youtube;
    }
    public String getName()
    {
        return name;
    }
    public String getOfficename()
    {
        return officename;
    }
    public String getPicurl(){
        return picurl;
    }
    public String getparty()
    {
        return party;
    }
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getWebsite() {return website;}
}
