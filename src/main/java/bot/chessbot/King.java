package bot.chessbot;

import java.util.ArrayList;

public class King extends Piece{

    public King(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-king.png");
        } else {
            setImage("src/main/resources/images/black-king.png");
        }
    }
    @Override
    public ArrayList<int[]> getValidMoves() {
        return null;
    }
}
