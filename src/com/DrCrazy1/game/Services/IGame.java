package com.DrCrazy1.game.Services;

public interface IGame {
    int updateBoard(int y, int x, int action);

    IField[][] getGameboard();

    int getMineCount();

    void revealAll();
}
