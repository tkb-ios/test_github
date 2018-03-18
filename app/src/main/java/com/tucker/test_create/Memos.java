package com.tucker.test_create;

public class Memos {

    private String title;
    private String memo;

    public Memos(String _title,String _memo){
        this.title = _title;
        this.memo = _memo;
    }

    public String getTitle(){
        return this.title;
    }

    public String getMemo(){
        return this.memo;
    }

}
