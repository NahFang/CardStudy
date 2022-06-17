package com.nahfang.studycard.components.db.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nahfang.studycard.bean.cardBean;

import java.util.List;

@Dao
public interface cardDao {

    @Query("SELECT * FROM cardBean WHERE category_name like :name")
    List<cardBean> getCardsFollowCategory (String name);

    @Query("SELECT COUNT(*) FROM cardBean")
    long getCardCount ();

    @Insert
    void insertCard (cardBean cardBean);

    @Delete
    void deleteCard (cardBean cardBean);

    @Update
    int updateCard (cardBean cardBean);

    @Query("DELETE FROM cardBean WHERE category_name like :name")
    void deleteAllCardFollowName (String name);
}
