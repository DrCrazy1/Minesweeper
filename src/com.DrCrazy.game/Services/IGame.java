package com.DrCrazy.game.Services;

public interface IGame {
    int updateBoard(int y, int x, int action);

    IGameboard getGameboard();

    int getMineCount();

    int[] getBoardSize();

    void revealAll();
}
