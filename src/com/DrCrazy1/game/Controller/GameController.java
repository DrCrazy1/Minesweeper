package com.DrCrazy1.game.Controller;

import com.DrCrazy1.game.Services.*;
import com.DrCrazy1.game.View.*;

public class GameController {
    private Screen screen;
    private IGame game;
    private int gameState;

    public GameController() {
        game = new Game(new int[]{15, 10});
        screen = new Screen(this, game);
        gameState = 0;
        screen.showFrame();
    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
    }

    //ToDo: User input does not work!!! Fix!
    public void update(int y, int x, int action) {
        gameState = game.updateBoard(y, x, action);
        if (gameState < 0) {
            game.revealAll();
        }
        screen.repaint();
    }
}