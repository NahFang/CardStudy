package com.nahfang.studycard.bean;

public class cardBean {
    private String category_name;
    private String title;
    private String content;
    private Boolean isRead = false; //配合随机算法使用

    public Boolean getIsRead () {
        return this.isRead;
    }
    public void setIsRead (Boolean bool) {
        this.isRead = bool;
    }
    public String getCategory_name () {
        return this.category_name;
    }
    public void setCategory_name (String s) {
        this.category_name = s;
    }
    public String getTitle () {
        return this.title;
    }
    public void setTitle (String s) {
        this.title = s;
    }
    public String getContent () {
        return this.content;
    }
    public void setContent (String s) {
        this.content = s;
    }
}
