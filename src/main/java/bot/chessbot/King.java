package bot.chessbot;

import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class King extends Piece{

    public King(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-king.png");
        } else {
            setImage("src/main/resources/images/black-king.png");
        }
    }
    @Override
    public ArrayList<int[]> getValidMoves() {

        ArrayList<int[]> possibleMoves = new ArrayList<>();
        getMoves(possibleMoves);
        return possibleMoves;
    }

    public void getMoves(ArrayList<int[]> array) {
        ArrayList<int[]> moves = new ArrayList<>();

        moves.add(new int[] {row - 1, col - 1});
        moves.add(new int[] {row - 1, col});
        moves.add(new int[] {row - 1, col + 1});
        moves.add(new int[] {row , col - 1});
        moves.add(new int[] {row , col + 1});
        moves.add(new int[] {row + 1, col - 1});
        moves.add(new int[] {row + 1, col});
        moves.add(new int[] {row + 1, col + 1});

        for (int i = 0 ; i < 8; i++) {
            int[] move = moves.get(i);
            if (move[0] >= 0 && move[0] <= 7 && move[1] >= 0 && move[1] <= 7) {
                if (board.getTile(move[0], move[1]).isOccupied()) {
                    if (color.equals("white")) {
                        if (board.getTile(move[0], move[1]).getPiece().getColor().equals("black")) {
                            array.add(move);
                        }
                    } else {
                        if (board.getTile(move[0], move[1]).getPiece().getColor().equals("white")) {
                            array.add(move);
                        }
                    }

                } else {
                    array.add(move);
                }
            }
        }

    }
}
