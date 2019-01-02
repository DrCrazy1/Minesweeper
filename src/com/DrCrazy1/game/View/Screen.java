package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.*;
import com.DrCrazy1.game.Services.*;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private JPanel board;
    private JLabel mineCount;
    private JButton startButton;
    private JLabel time;
    private GameController controller;
    private IGame game;

    public Screen(GameController controller, IGame game) {
        this.controller = controller;
        this.game = game;
    }

    public void showFrame() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Location
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        int x = (int) ((dim.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        //content area
        mineCount = new JLabel(String.valueOf(game.getMineCount()));
        startButton = new JButton();
        time = new JLabel("00:00");
        board = new JPanel();
        board.addMouseListener(
                new FieldClickListener((int) dim.getWidth(), (int) dim.getHeight(), controller));
        board.add(new Board(game));
        this.setContentPane(mineCount);
        this.getContentPane().add(startButton);
        this.getContentPane().add(time);
        this.getContentPane().add(board);

        this.setVisible(true);
    }
}