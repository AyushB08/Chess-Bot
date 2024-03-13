package bot.chessbot;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(String color) {
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-pawn.png");
        } else {
            setImage("src/main/resources/images/black-pawn.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves() {
        return null;
    }
}
