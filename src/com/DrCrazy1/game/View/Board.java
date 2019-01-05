package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Services.IGame;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private int sizeTile;
    private IGame game;
    private int[] size;

    public Board(IGame game) {
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
                if (!game.getGameboard()[row][col].isRevealed()) {
                    drawCover(g, x + 1, y + 1, sizeTile - 2);
                    if (game.getGameboard()[row][col].isFlagged()) {
                        g.drawString("F", x + sizeTile / 3, (int)(y + sizeTile / 1.5));
                    }
                }
                int fieldValue = game.getGameboard()[row][col].getValue();
                if (fieldValue > 0 && game.getGameboard()[row][col].isRevealed()) {
                    drawNumber(g, fieldValue, (int)(x + sizeTile / 3.2), y + sizeTile - 6);
                } else if (fieldValue == -1 && game.getGameboard()[row][col].isRevealed()) {
                    g.fillOval(x + 4, y + 4, sizeTile - 8, sizeTile - 8);
                }
            }
        }
        this.setSize(size[1] * sizeTile, size[0] * sizeTile);
    }

    private void drawCover(Graphics g, int x, int y, int width) {
        g.setColor(Color.decode("#bdbdbd"));
        g.fillRect(x, y, width, width);
        g.setColor(Color.BLACK);
    }

    private void drawNumber(Graphics g, int value, int x, int y) {
        switch (value) {
            case 1: g.setColor(Color.decode("#0000ff"));
            break;
            case 2: g.setColor(Color.decode("#007b00"));
            break;
            case 3: g.setColor(Color.decode("#ff0000"));
            break;
            case 4: g.setColor(Color.decode("#00007b"));
            break;
            case 5: g.setColor(Color.decode("#7b0000"));
            break;
            case 6: g.setColor(Color.decode("#01a0a3"));
            break;
            case 7: g.setColor(Color.decode("#867cc2"));
            break;
            case 8: g.setColor(Color.decode("#7b7b7b"));
        }
        int currentFontSize = g.getFont().getSize();
        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 16));
        g.drawString(String.valueOf(value), x, y);
        g.setFont(new Font(g.getFont().getName(), Font.PLAIN, currentFontSize));
        g.setColor(Color.BLACK);
    }
}