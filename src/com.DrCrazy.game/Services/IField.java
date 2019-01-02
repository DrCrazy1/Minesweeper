package com.DrCrazy.game.Services;

public interface IField {

    int getValue();

    void setValue(int value);

    boolean isRevealed();

    void reveal();

    void setFlag();

    boolean isFlagged();
}
