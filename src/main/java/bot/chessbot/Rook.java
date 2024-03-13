package bot.chessbot;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(String color) {
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-rook.png");
        } else {
            setImage("src/main/resources/images/black-rook.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves() {
        return null;
    }
}
