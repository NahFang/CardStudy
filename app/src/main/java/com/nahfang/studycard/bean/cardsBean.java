package com.nahfang.studycard.bean;

import androidx.annotation.NonNull;

public class cardsBean {
    private String name_cards;
    private int src_cards;

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
