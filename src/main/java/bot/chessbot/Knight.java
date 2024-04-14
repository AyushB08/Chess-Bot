package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Knight extends Piece{

    public static final double[][] KNIGHT_TABLE = {
            { -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 },
            { -4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0 },
            { -3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0 },
            { -3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0 },
            { -3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0 },
            { -3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0 },
            { -4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0 },
            { -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 }
    };

    public Knight(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        if (color.equals("white")) {
            setImage("src/main/resources/images/white-knight.png");
        } else {
            setImage("src/main/resources/images/black-knight.png");
        }
    }

    @Override
    public Piece clonePiece(Piece x) {
        return new Knight(row, col, color);
    }

    @Override
    public ArrayList<int[]> getValidMoves(Board board) throws IOException {

        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{row+1, col+2});
        moves.add(new int[]{row+1, col-2});
        moves.add(new int[]{row-1, col+2});
        moves.add(new int[]{row-1, col-2});
        moves.add(new int[]{row+2, col+1});
        moves.add(new int[]{row+2, col-1});
        moves.add(new int[]{row-2, col+1});
        moves.add(new int[]{row-2, col-1});

        ArrayList<int[]> possibleMoves = new ArrayList<>();

        for (int i = 0; i < moves.size(); i++) {
            int[] move = moves.get(i);
            if (move[0] >= 0 && move[0] <= 7 && move[1] >= 0 && move[1] <= 7) {
                if (board.getTile(move[0], move[1]).isOccupied()) {
                    if (this.color.equals("white")) {
                        if (board.getTile(move[0], move[1]).getPiece().getColor().equals("black")) {
                            possibleMoves.add(move);
                        }
                    } else {
                        if (board.getTile(move[0], move[1]).getPiece().getColor().equals("white")) {
                            possibleMoves.add(move);
                        }
                    }
                } else {
                    possibleMoves.add(move);
                }
            }
        }


        return (possibleMoves);

    }

    @Override
    public double getValue() {
        return 30 + getPositionValue();
    }

    @Override
    public double getPositionValue() {
        if (this.color.equals("white")) {
            return KNIGHT_TABLE[7-row][col];
        } else {
            return KNIGHT_TABLE[row][col];
        }
    }
}
