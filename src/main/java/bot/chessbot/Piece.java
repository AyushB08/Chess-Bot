package bot.chessbot;

import java.util.ArrayList;

public abstract class Piece {
    private int row;
    private int column;
    private String color;


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return row;
    }

    public String getColor() {
        return color;
    }

    public abstract ArrayList<int[]> getValidMoves();

}
