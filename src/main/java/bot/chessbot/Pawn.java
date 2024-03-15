package bot.chessbot;

import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Pawn extends Piece{

    public Pawn(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-pawn.png");
        } else {
            setImage("src/main/resources/images/black-pawn.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves() {

        ArrayList<int[]> moves = new ArrayList<>();
        addForwardSquares(moves);
        addCaptureSquares(moves);
        return moves;

    }

    public void addForwardSquares(ArrayList<int[]> array) {

        if (this.color.equals("white")) {
            if (row == 1) {
                if (!(board.getTile(row+1, col).isOccupied())) {
                    int[] move = {row+1, col};
                    array.add(move);
                    if (!(board.getTile(row+2, col).isOccupied())) {
                        int[] move_two = {row+2, col};
                        array.add(move_two);
                    }
                }
            } else if (row <= 6) {
                if (!(board.getTile(row+1, col).isOccupied())) {
                    int[] move = {row+1, col};
                    array.add(move);
                }
            }
        }
        else {
            if (row == 6) {
                if (!(board.getTile(row-1, col).isOccupied())) {
                    int[] move = {row-1, col};
                    array.add(move);
                    if (!(board.getTile(row-2, col).isOccupied())) {
                        int[] move_two = {row-2, col};
                        array.add(move_two);
                    }
                }
            } else if (row >= 1) {
                if (!(board.getTile(row-1, col).isOccupied())) {
                    int[] move = {row-1, col};
                    array.add(move);
                }
            }
        }



    }

    public void addCaptureSquares(ArrayList<int[]> array) {
        if (this.color.equals("white")) {
            if (row <= 6 && col >= 1) {
                if (board.getTile(row+1, col-1).isOccupied()) {
                    if (board.getTile(row+1, col-1).getPiece().getColor().equals("black")) {
                        int[] move = {row+1, col - 1};
                        array.add(move);
                    }
                }
            }

            if (row <= 6 && col <= 6) {
                if (board.getTile(row+1, col+1).isOccupied()) {
                    if (board.getTile(row+1, col+1).getPiece().getColor().equals("black")) {
                        int[] move = {row+1, col + 1};
                        array.add(move);
                    }
                }
            }

        } else {
            if (row >= 1 && col >= 1) {
                if (board.getTile(row-1, col-1).isOccupied()) {
                    if (board.getTile(row-1, col-1).getPiece().getColor().equals("white")) {
                        int[] move = {row-1, col - 1};
                        array.add(move);
                    }
                }
            }

            if (row >= 1  && col <= 6) {
                if (board.getTile(row-1, col+1).isOccupied()) {
                    if (board.getTile(row-1, col+1).getPiece().getColor().equals("white")) {
                        int[] move = {row-1, col + 1};
                        array.add(move);
                    }
                }
            }
        }

    }







}
