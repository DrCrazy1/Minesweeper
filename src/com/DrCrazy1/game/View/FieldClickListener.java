package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldClickListener implements MouseListener {

    private GameController gameController;

    public FieldClickListener(GameController controller) {
        gameController = controller;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int action = e.getButton() == MouseEvent.BUTTON3 ? 1 : 0;
        gameController.update(e.getY() / 23, e.getX() / 23, action);
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
