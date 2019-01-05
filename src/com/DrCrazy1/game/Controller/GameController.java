package com.DrCrazy1.game.Controller;

import com.DrCrazy1.game.Services.*;
import com.DrCrazy1.game.View.*;

public class GameController {
    private Screen screen;
    private IGame game;
    private int gameState;

    public GameController() {
        game = new Game(new int[]{15, 12});
        screen = new Screen(this);
        gameState = 0;
        screen.showFrame();
    }

    public IGame getGame() {
        return game;
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
    }

    public void update(int y, int x, int action) {
        gameState = game.updateBoard(y, x, action);
        if (action == 1) {
            screen.updateMineCount(game.getMineCount());
        }
        if (gameState < 0) {
            game.revealAll();
        }
        screen.repaint();
    }

    public void reset() {
        game = new Game(new int[]{15, 12});
        //ToDo: refreshing screen board
        gameState = 0;
        screen.showFrame();
    }
}