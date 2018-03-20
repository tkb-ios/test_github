package com.tucker.test_create;

/**
 * メモを管理するためのクラス。
 */
public class Memos {

    /**メモのタイトル名*/
    private String title;
    /**メモの本文*/
    private String memo;



    /**メモタイトルと本文を引数にとり初期化する。
     * @param _title メモタイトル
     * @param _memo メモ本文*/
    public Memos(String _title,String _memo){
        this.title = _title;
        this.memo = _memo;
    }



    /**メモのタイトルを返す。
     * @return String型でメモタイトルを返す。*/
    public String getTitle(){
        return this.title;
    }

    /**メモの本文を返す。
     * @return String型でメモ本文を返す。*/
    public String getMemo(){
        return this.memo;
    }

}
