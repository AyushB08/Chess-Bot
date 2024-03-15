package bot.chessbot;

import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Rook extends Piece{

    public Rook(int row, int col, String color) {
        this.row = row;
        this.col = col;

        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-rook.png");
        } else {
            setImage("src/main/resources/images/black-rook.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        getHorizontalMoves(moves);
        getVerticalMoves(moves);
        return moves;
    }

    public void getHorizontalMoves(ArrayList<int[]> array) {

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

    public void getVerticalMoves(ArrayList<int[]> array) {
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
