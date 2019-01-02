package com.DrCrazy1.game.Services;

public class Field implements IField {

    private int value;
    private boolean isRevealed;
    private boolean isFlagged;

    public Field() {
        isRevealed = false;
        isFlagged = false;
        value = 0;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int number) {
        value = number;
    }

    @Override
    public boolean isRevealed() {
        return isRevealed;
    }

    @Override
    public void reveal() {
        isRevealed = true;
    }

    @Override
    public void setFlag() {
        if (!isFlagged && !isRevealed) {
            isFlagged = true;
        } else {
            isFlagged = false;
        }
    }

    @Override
    public boolean isFlagged() {
        return isFlagged;
    }
}
