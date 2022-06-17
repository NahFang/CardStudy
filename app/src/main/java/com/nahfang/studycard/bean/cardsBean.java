package com.nahfang.studycard.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class cardsBean {
    @PrimaryKey(autoGenerate = true)
    private int cards_id;

    @ColumnInfo(name = "name_cards")
    private String name_cards;

    @ColumnInfo(name = "src")
    private int src_cards;

    public int getCards_id() {
        return cards_id;
    }

    public void setCards_id(int cards_id) {
        this.cards_id = cards_id;
    }

    public String getName_cards() {
        return this.name_cards;
    }

    public int getSrc_cards() {
        return src_cards;
    }

    public void setName_cards(@NonNull String name_cards) {
        this.name_cards = name_cards;
    }

    public void setSrc_cards(@NonNull int src_cards) {
        this.src_cards = src_cards;
    }
}
