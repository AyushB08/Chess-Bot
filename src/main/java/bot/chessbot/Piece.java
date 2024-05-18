package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static bot.chessbot.BoardViewer.board;

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

    public String getName() {
        return this.getClass().getName().substring(13);
    }


    public String getColor() {
        return color;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String string) { image = string; }



    public abstract Piece clonePiece(Piece x);


    public abstract ArrayList<int[]> getValidMoves(Board board) throws IOException;

    public abstract double getValue();

    public abstract double getPositionValue();









}
