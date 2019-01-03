package com.DrCrazy1.game.Services;

public class Game implements IGame {

    private IGameboard gameBoard;
    private int mineCount;

    public Game(int[] setup) {
        mineCount = setup[0] * setup[1] / 5;
        gameBoard = new Gameboard(setup[0], setup[1], mineCount);
    }

    @Override
    public IField[][] getGameboard() {
        return gameBoard.getBoard();
    }

    @Override
    public void revealAll() {
        gameBoard.revealAll();
    }

    @Override
    public int updateBoard(int y, int x, int action) {
        //Flag field
        if (action == 1) {
            mineCount += gameBoard.isFieldFlagged(y, x) == false ? 1 : -1;
            gameBoard.flagField(y, x);
            return 0;
        }
        //Try to updateBoard field which is flagged
        if (!gameBoard.isFieldRevealed(y, x) && gameBoard.isFieldFlagged(y, x)) {
            return 0;
        }
        //Clicked bomb field
        if (gameBoard.getValueOfField(y, x) == -1) {
            gameBoard.revealField(y, x);
            return -1;
        }
        //Clicked field with no bombs around - revealing all other no bomb fields which are related
        if (gameBoard.getValueOfField(y, x) == 0) {
            gameBoard.revealRelatedFields(y, x);
            if (gameBoard.isAllRevealed()) {
                return 1;
            }
            return 0;
        }
        //Clicked field with at least one bomb near by and the field isn´t yet revealed
        if (gameBoard.getValueOfField(y, x) > 0 && !(gameBoard.isFieldRevealed(y, x))) {
            gameBoard.revealField(y, x);
            if (gameBoard.isAllRevealed()) {
                return 1;
            }
            return 0;
        }
        //Clicked field which is revealed to updateBoard all fields around if value >= flags
        if (gameBoard.getValueOfField(y, x) > 0 && !gameBoard.isFieldFlagged(y, x)) {
            int flags = gameBoard.countThings(y, x, "Flag");
            if (flags < gameBoard.getValueOfField(y, x) || flags > gameBoard.getValueOfField(y, x)) {
                return 0;
            } else {
                if (gameBoard.inspectBlock(y, x)) {
                    if (gameBoard.isAllRevealed()) {
                        return 1;
                    }
                    return 0;
                }
                return -1;
            }
        }
        return -1;
    }

    @Override
    public int getMineCount() {
        return mineCount;
    }
}