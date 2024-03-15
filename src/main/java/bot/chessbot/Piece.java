package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Piece {
    int row;
    int col;
    String color;
    String image;

    public void setRow(int x) {
        row = x;
    }

    public void setCol(int x) {
        col = x;
    }


    public String getColor() {
        return color;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String string) {
        image = string;
    }



    public abstract ArrayList<int[]> getValidMoves();

}
