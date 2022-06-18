package com.nahfang.studycard.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class cardBean {

    @PrimaryKey(autoGenerate = true)
    private int cid;

    @ColumnInfo(name = "category_name")
    private String category_name;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @Ignore
    private Boolean isRead = false; //配合随机算法使用

    @ColumnInfo(name = "is_delete")
    private int isDelete;

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

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
