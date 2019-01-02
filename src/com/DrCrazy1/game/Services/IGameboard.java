package com.DrCrazy1.game.Services;

public interface IGameboard {
    IField[][] getBoard();

    int getRowLength();

    int getColumnLength();

    int getMineCount();

    void revealAll();

    void flagField(int y, int x);

    boolean isFieldRevealed(int y, int x);

    int getValueOfField(int y, int x);

    void revealField(int y, int x);

    boolean isFieldFlagged(int y, int x);

    boolean isAllRevealed();

    int countThings(int y, int x, String action);

    void revealRelatedFields(int y, int x);

    boolean inspectBlock(int y, int x);
}