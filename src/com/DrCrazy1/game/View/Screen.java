package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.*;
import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private JPanel board;
    private JPanel menu;
    private JLabel mineCount;
    private JButton startButton;
    private JLabel time;
    private GameController controller;

    public Screen(GameController gameController) {
        controller = gameController;
        setup();
    }

    private void setup() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 450);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        makeMenu();
        this.getContentPane().add(menu, BorderLayout.PAGE_START);
    }

    public void showFrame() {
        makeBoard();
        this.getContentPane().add(board, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void makeBoard() {
        board = new Board(controller.getGame());
        board.addMouseListener(
                new FieldClickListener(controller));
    }

    private void makeMenu() {
        menu = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 10));
        mineCount = new JLabel(String.valueOf(controller.getGame().getMineCount()));
        menu.add(mineCount);
        startButton = new JButton("Smile");
        startButton.addActionListener(e -> controller.reset());
        menu.add(startButton);
        time = new JLabel("00:00");
        menu.add(time);
    }

    public void updateMineCount(int newNumber) {
        mineCount.setText(String.valueOf(newNumber));
    }

    public void setController(GameController gameController) {
        controller = gameController;
    }

    public void changeText(int gamestate) {
        String result;
        switch (gamestate) {
            default: result = "Smile";
            break;
            case -1: result = "Lose";
            break;
            case 0: result = "Smile";
            break;
            case 1: result = "WIN";
            break;
        }
        startButton.setText(result);
    }
}