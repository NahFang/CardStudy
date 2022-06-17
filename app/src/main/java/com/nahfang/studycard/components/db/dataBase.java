package com.nahfang.studycard.components.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nahfang.studycard.bean.cardBean;
import com.nahfang.studycard.bean.cardsBean;
import com.nahfang.studycard.components.db.Dao.cardDao;
import com.nahfang.studycard.components.db.Dao.cardsDao;

@Database(entities = {cardBean.class, cardsBean.class},version = 1,exportSchema = false)
public abstract class dataBase extends RoomDatabase {

    public abstract cardDao cardDao();

    public abstract cardsDao cardsDao();
}
