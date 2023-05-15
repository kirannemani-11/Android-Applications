package com.example.madpractice2;

public class Arcticledata {
    String author;
    String title;
    String desc;
    String url;
    String urltoimage;
    String publishedat;
    Arcticledata(String author,String title,String desc,String url,String urltoimage,String publishedat)
    {
        this.author = author;
        this.title =  title;
        this.desc = desc;
        this.url = url;
        this.urltoimage = urltoimage;
        this.publishedat = publishedat;
    }
    public String getautho()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDesc()
    {
        return desc;
    }
    public String getUrl()
    {
        return url;
    }
    public String getUrltoimage()
    {
        return urltoimage;
    }
    public String getPublishedat()
    {
        return publishedat;
    }
}
