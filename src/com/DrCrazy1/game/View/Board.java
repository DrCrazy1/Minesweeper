package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Services.IGame;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private int sizeTile;
    private IGame game;
    private int[] size;

    public Board(IGame game) {
        setBackground(Color.LIGHT_GRAY);
        setSize(400, 400);
        this.game = game;
        sizeTile = 23;
        size = new int[]{game.getGameboard().length, game.getGameboard()[0].length};
    }

    public void paintComponent(Graphics g) {
        for (int row = 0; row < size[0]; row++) {
            for (int col = 0; col < size[1]; col++) {
                int x = col * sizeTile;
                int y = row * sizeTile;
                g.drawRect(x, y, sizeTile, sizeTile);
            }
        }
    }
}