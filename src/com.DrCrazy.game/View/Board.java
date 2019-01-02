package com.DrCrazy.game.View;

import com.DrCrazy.game.Services.IGame;

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
        size = game.getBoardSize();
    }

    public void paintComponent(Graphics g) {
        for (int row = 0; row < size[0]; row++) {
            for (int col = 0; col < size[1]; col++) {
                int x = col * sizeTile;
                int y = row * sizeTile;
                g.drawRect(x, y, sizeTile, sizeTile);
                if (!board.isFieldRevealed(row, col)) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x + 1, y + 1, sizeTile - 2, sizeTile - 2);
                    g.setColor(Color.BLACK);
                    if (board.isFieldFlagged(row, col)) {
                        g.drawString("F", x + sizeTile / 2, y + sizeTile / 2);
                    }
                } else {
                    if (board.getValueOfField(row, col) > 0) {
                        String value = String.valueOf(board.getValueOfField(row, col));
                        g.drawString(value, x + sizeTile / 2, y + sizeTile / 2);
                    } else if (board.getValueOfField(row, col) == -1) {
                        g.drawString("B", x + sizeTile / 2, y + sizeTile / 2);
                    } else {
                        g.drawString("!", x + sizeTile / 2, y + sizeTile / 2);
                    }
                }
            }
        }
    }
}