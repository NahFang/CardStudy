package com.nahfang.studycard.components.db.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nahfang.studycard.bean.cardsBean;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface cardsDao {

    @Query("SELECT name_cards FROM CARDSBEAN")
    List<String> getAllCardsName();

    @Query("SELECT * FROM cardsBean")
    List<cardsBean> getAllCards();

    @Insert
    void insertCategory (cardsBean cardsBean);

    @Delete
    void deleteCategory (cardsBean cardsBean);

    @Update
    int updateCategory (cardsBean cardsBean);
}
