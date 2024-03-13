package bot.chessbot;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(String color) {
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-bishop.png");
        } else {
            setImage("src/main/resources/images/black-bishop.png");
        }
    }
    @Override
    public ArrayList<int[]> getValidMoves() {
        return null;
    }
}
