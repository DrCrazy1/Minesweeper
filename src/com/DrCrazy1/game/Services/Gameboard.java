package com.DrCrazy1.game.Services;

import java.util.concurrent.ThreadLocalRandom;

public class Gameboard implements IGameboard {
    private IField[][] board;
    private int mines;

    public Gameboard(int cols, int rows, int mine) {
        board = new Field[cols][rows];
        mines = mine;
        createBoard();
    }

    private void createBoard() {
        initializeBoard();
        setMines();
        fillBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Field();
            }
        }
    }

    private void setMines() {
        for (int count = 0; count < mines; count++) {
            int randomY, randomX;
            do {
                randomY = ThreadLocalRandom.current().nextInt(0, board.length);
                randomX = ThreadLocalRandom.current().nextInt(0, board[0].length);
            }
            while (board[randomY][randomX].getValue() == -1);
            {
                board[randomY][randomX].setValue(-1);
            }
        }
    }

    private void fillBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].getValue() != -1) {
                    board[row][col].setValue(countThings(row, col, "Mine"));
                }
            }
        }
    }

    @Override
    public IField[][] getBoard() {
        return board;
    }

    @Override
    public int countThings(int y, int x, String action) {
        int number = 0;
        for (int yoff = -1; yoff <= 1; yoff++) {
            for (int xoff = -1; xoff <= 1; xoff++) {
                int row = y + yoff;
                int col = x + xoff;
                if (isOnBoard(row, col)) {
                    if (action.equals("Mine")) {
                        number += board[row][col].getValue() == -1 ? 1 : 0;
                    }
                    if (action.equals("Flag") && !board[row][col].isRevealed()
                            && board[row][col].isFlagged()) {
                        number++;
                    }
                }
            }
        }
        return number;
    }

    @Override
    public void flagField(int y, int x) {
        board[y][x].setFlag();
    }

    @Override
    public boolean isFieldRevealed(int y, int x) {
        return board[y][x].isRevealed();
    }

    @Override
    public int getValueOfField(int y, int x) {
        return board[y][x].getValue();
    }

    @Override
    public void revealField(int y, int x) {
        board[y][x].reveal();
    }

    @Override
    public boolean isFieldFlagged(int y, int x) {
        return board[y][x].isFlagged();
    }

    @Override
    public boolean isAllRevealed() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++)
                if (!board[row][col].isRevealed() && !board[row][col].isFlagged()) {
                    return false;
                }
        }
        return true;
    }

    @Override
    public void revealRelatedFields(int y, int x) {
        board[y][x].reveal();
        floodFill(y, x);
    }

    @Override
    public boolean inspectBlock(int y, int x) {
        boolean isCorrect = true;
        IField field;
        for (int yoff = -1; yoff <= 1; yoff++) {
            for (int xoff = -1; xoff <= 1; xoff++) {
                if (isOnBoard(y + yoff, x + xoff)) {
                    field = board[y + yoff][x + xoff];
                    if (field.isFlagged() && field.getValue() != -1) {
                        isCorrect = false;
                        field.setValue(-2);
                    } else if (!(field.isFlagged() && field.getValue() == -1)) {
                        if (field.getValue() == 0) {
                            revealRelatedFields(y + yoff, x + xoff);
                        } else {
                            field.reveal();
                        }
                    }
                }
            }
        }
        return isCorrect;
    }

    @Override
    public void revealAll() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                IField field = board[row][col];
                if (!field.isFlagged() && !field.isRevealed()) {
                    field.reveal();
                }
            }
        }
    }

    private void floodFill(int y, int x) {
        IField neighbor;
        for (int yoff = -1; yoff <= 1; yoff++) {
            for (int xoff = -1; xoff <= 1; xoff++) {
                int row = y + yoff;
                int col = x + xoff;
                if (isOnBoard(row, col)) {
                    neighbor = board[row][col];
                    if (!neighbor.isRevealed() && !neighbor.isFlagged() && neighbor.getValue() > -1) {
                        revealRelatedFields(row, col);
                    }
                }
            }
        }
    }

    private boolean isOnBoard(int y, int x) {
        return (y > -1 && y < board.length && x > -1 && x < board[0].length);
    }
}