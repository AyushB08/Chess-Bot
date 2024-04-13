package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Bishop extends Piece{

    public Bishop(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-bishop.png");
        } else {
            setImage("src/main/resources/images/black-bishop.png");
        }
    }

    @Override
    public ArrayList<int[]> getValidMoves(Board board) throws IOException {

        ArrayList<int[]> possibleMoves = new ArrayList<>();
        getForwardDiagonal(possibleMoves, board);
        getBackwardDiagonal(possibleMoves, board);



        return possibleMoves;

    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public Piece clonePiece(Piece x) {

        return new Bishop(row, col, color);
    }

    public void getBackwardDiagonal(ArrayList<int[]> array, Board board) {
        int maxDiagonalTopLeftSteps = Math.min(7-row, col);

        for (int i = 0; i < maxDiagonalTopLeftSteps; i++) {

            int[] move = {row + 1 + i, col - 1 - i};

            if (board.getTile(row + 1 + i, col - 1 - i).isOccupied()) {



                if (this.color.equals("white")) {
                    if (board.getTile(row + 1 + i, col - 1 - i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row + 1 + i, col - 1 - i).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }

        int maxDiagonalBottomRightSteps = Math.min(row, 7-col);

        for (int i = 0; i < maxDiagonalBottomRightSteps; i++) {

            int[] move = {row - 1 - i, col + 1 + i};

            if (board.getTile(row - 1 - i, col + 1 + i).isOccupied()) {



                if (this.color.equals("white")) {
                    if (board.getTile(row - 1 - i, col + 1 + i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row - 1 - i, col + 1 + i).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }

    }

    public void getForwardDiagonal(ArrayList<int[]> array, Board board) {
        int maxDiagonalTopRightSteps = Math.min(7-row, 7-col);

        for (int i = 0; i < maxDiagonalTopRightSteps; i++) {

            int[] move = {row + 1 + i, col + 1 + i};

            if (board.getTile(row + 1 + i, col + 1 + i).isOccupied()) {



                if (this.color.equals("white")) {
                    if (board.getTile(row + 1 + i, col + 1 + i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row + 1 + i, col + 1 + i).getPiece().getColor().equals("white")) {
                        array.add(move);
                    }
                }
                break;
            } else {
                array.add(move);
            }
        }

        int maxDiagonalBottomLeftSteps = Math.min(row, col);



        for (int i = 0; i < maxDiagonalBottomLeftSteps; i++) {

            int[] move = {row - 1 - i, col - 1 - i};

            if (board.getTile(row - 1 - i, col - 1 - i).isOccupied()) {



                if (this.color.equals("white")) {
                    if (board.getTile(row - 1 - i, col - 1 - i).getPiece().getColor().equals("black")) {
                        array.add(move);
                    }
                } else {
                    if (board.getTile(row - 1 - i, col - 1 - i).getPiece().getColor().equals("white")) {
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
