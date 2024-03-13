package bot.chessbot;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(String color) {
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-queen.png");
        } else {
            setImage("src/main/resources/images/black-queen.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves() {
        return null;
    }
}
