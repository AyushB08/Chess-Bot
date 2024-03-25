package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Rook extends Piece{

    String rookCastle;
    boolean canCastle = true;

    public Rook(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (col == 7) {
            rookCastle = "king";
        } else {
            rookCastle = "queen";
        }
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-rook.png");
        } else {
            setImage("src/main/resources/images/black-rook.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves(Board board) throws IOException {
        ArrayList<int[]> moves = new ArrayList<>();
        getHorizontalMoves(moves, board);
        getVerticalMoves(moves, board);


        return (moves);

    }

    @Override
    public Piece clonePiece(Piece x) {
        Rook rook = new Rook(row, col, color);
        rook.setCastle(rook.getCanCastle());
        rook.rookCastle = this.rookCastle;
        return rook;
    }

    public boolean getCanCastle() {
        return canCastle;
    }

    public void setCastle(boolean x) {
        canCastle = x;
    }

    public String getRookCastle() {
        return rookCastle;
    }

    public void getHorizontalMoves(ArrayList<int[]> array, Board board) {

        for (int i = col-1; i >= 0; i--) {

            int[] move = {row, i};

            if (board.getTile(row, i).isOccupied()) {
                if (this.color.equals("white")) {
                    if (board.getTile(row, i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row, i).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }

        for (int i = col + 1; i <= 7; i++) {

            int[] move = {row, i};

            if (board.getTile(row, i).isOccupied()) {
                if (this.color.equals("white")) {
                    if (board.getTile(row, i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row, i).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }
    }

    public void getVerticalMoves(ArrayList<int[]> array, Board board) {
        for (int i = row-1; i >= 0; i--) {

            int[] move = {i, col};

            if (board.getTile(i, col).isOccupied()) {
                if (this.color.equals("white")) {
                    if (board.getTile(i, col).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(i, col).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }

        for (int i = row + 1; i <= 7; i++) {

            int[] move = {i, col};

            if (board.getTile(i, col).isOccupied()) {
                if (this.color.equals("white")) {
                    if (board.getTile(i, col).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(i, col).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }
    }
}
