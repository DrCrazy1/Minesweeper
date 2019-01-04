package com.DrCrazy1.game.View;

import com.DrCrazy1.game.Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

    private JPanel board;
    private JPanel menu;
    private JLabel mineCount;
    private JButton startButton;
    private JLabel time;
    private GameController controller;

    public void setController(GameController c) {
        controller = c;
    }

    public void showFrame() {
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 450);
        //Location to center screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        int x = (int) ((dim.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        //content area
        makeMenu();
        makeBoard();
        //Adding to ContentPane
        this.getContentPane().add(menu, BorderLayout.PAGE_START);
        this.getContentPane().add(board, BorderLayout.CENTER);
        //Show JFrame
        this.setVisible(true);
    }

    private void makeBoard() {
        board = new Board(controller.getGame());
        board.addMouseListener(
                new FieldClickListener(controller));
    }

    private void makeMenu() {
        menu = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        mineCount = new JLabel(String.valueOf(controller.getGame().getMineCount()));
        menu.add(mineCount);
        startButton = new JButton();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.reset();
            }
        });
        menu.add(startButton);
        time = new JLabel("00:00");
        menu.add(time);
    }
}