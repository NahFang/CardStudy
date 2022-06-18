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

    @Query("SELECT * FROM cardBean WHERE category_name like :name AND is_delete = 0")
    List<cardBean> getCardsFollowCategory (String name);

    @Query("SELECT COUNT(*) FROM cardBean WHERE is_delete = 0")
    long getCardCount ();

    @Insert
    void insertCard (cardBean cardBean);
    //物理上删除卡片
    @Delete
    void deleteCard (cardBean cardBean);

    @Update
    int updateCard (cardBean cardBean);

    //逻辑上删除卡片
    @Query("UPDATE cardBean SET is_delete = :isdelete WHERE cid = :cid ")
    void deleteCardOnLogic (int isdelete, int cid);

    //物理上删除所有卡片
    @Query("DELETE FROM cardBean WHERE category_name like :name")
    void realDeleteAllCardFollowName (String name);

    //根据分类名在逻辑上删除卡片
    @Query("UPDATE CARDBEAN SET is_delete = :isdelete WHERE category_name = :name")
    void deleteAllCardFollowName (int isdelete,String name);

    //根据id恢复卡片
    @Query("UPDATE CARDBEAN SET is_delete = :isdelete WHERE cid = :cid")
    void recoverCardFollowCid (int isdelete,int cid);

    //获取所有在逻辑上被删除的卡片
    @Query("SELECT * FROM cardBean WHERE is_delete = 1")
    List<cardBean> getAllDeletedCard ();
}
