package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.GameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {

    private GameController controller;

    public ButtonClickListener(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.reset();
    }
}
