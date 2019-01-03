package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.*;
import com.DrCrazy1.game.Services.*;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private JPanel board;
    private JPanel menu;
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
        this.setSize(400, 400);
        //Location
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        int x = (int) ((dim.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        //content area
        //Menu
        makeMenu();
        //Board
        board = new JPanel();
        board.setLayout(null);
        board.addMouseListener(
                new FieldClickListener(controller));
        board.add(new Board(game));
        board.setBounds(10, 40, 380, 360);
        //Adding to ContentPane
        this.getContentPane().add(menu);
        this.getContentPane().add(board);
        //this.pack();
        //Show JFrame
        this.setVisible(true);
    }

    private void makeMenu() {
        menu = new JPanel();
        menu.setLayout(null);
        mineCount = new JLabel(String.valueOf(game.getMineCount()));
        mineCount.setBounds(10, 10, 20, 20);
        menu.add(mineCount);
        startButton = new JButton("X");
        startButton.setBounds(190, 10, 20, 20);
        menu.add(startButton);
        time = new JLabel("00:00");
        time.setBounds(350, 10 ,40, 20);
        menu.add(time);
        menu.setBounds(0, 0, 400, 40);
    }
}