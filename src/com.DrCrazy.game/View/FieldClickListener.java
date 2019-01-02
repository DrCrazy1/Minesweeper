package com.DrCrazy.game.View;

import com.DrCrazy.game.Controller.GameController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldClickListener implements MouseListener {

    private int width;
    private int height;
    private GameController gameController;

    public FieldClickListener(int width, int height, GameController controller) {
        this.width = width;
        this.height = height;
        gameController = controller;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = (e.getX() - 20) / 23;
        int row = (e.getY() - 20) / 23;
        if (e.getX() + 20 < width && e.getY() + 20 < height) {
            gameController.update(row, col, 0);
        }
        System.out.println(row + " " + col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
