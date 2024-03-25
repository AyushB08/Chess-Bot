package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class King extends Piece{
    boolean canCastle = true;
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
    public ArrayList<int[]> getValidMoves(Board board) throws IOException {

        ArrayList<int[]> possibleMoves = new ArrayList<>();
        getMoves(possibleMoves, board);
        getCastleMoves(possibleMoves, board);


        return possibleMoves;


    }

    @Override
    public Piece clonePiece(Piece x) {
        King king = new King(row, col, color);
        king.setCastle(canCastle);
        return king;
    }

    public boolean getCastle() {
        return canCastle;
    }

    public void setCastle(boolean x) {
        canCastle = x;
    }


    public void getMoves(ArrayList<int[]> array, Board board) {
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


    public void getCastleMoves(ArrayList<int[]> array, Board board) throws IOException {


        ArrayList<Rook> rooks = new ArrayList<Rook>();

        if (this.getCastle()) {

            for (int i = 0; i < 8; i++) {
                for (int a = 0; a < 8; a++) {
                    Tile tile = board.getTile(i, a);
                    if (tile.isOccupied()) {
                        if (tile.getPiece().getName().equals("Rook") && tile.getPiece().getColor().equals(this.color)) {
                            rooks.add((Rook)(tile.getPiece()));
                        }

                    }
                }
            }

            if (this.color.equals("white")) {


                for (Rook rook : rooks) {
                    if (rook.getCanCastle()) {
                        if (rook.getRookCastle().equals("king")) {
                            if (!(board.getTile(0, 5).isOccupied()) && !(board.getTile(0, 6).isOccupied())) {
                                int[] move = {0, 6};
                                array.add(move);
                            }
                        }
                        if (rook.getRookCastle().equals("queen")) {
                            if (!(board.getTile(0, 1).isOccupied()) && !(board.getTile(0, 2).isOccupied()) && !(board.getTile(0, 3).isOccupied())) {
                                int[] move = {0, 2};
                                array.add(move);
                            }
                        }
                    }

                }



            } else {



                for (int i = 0; i < rooks.size(); i++) {
                    Rook rook = rooks.get(i);
                    if (rook.getCanCastle()) {
                        if (rook.getRookCastle().equals("king")) {
                            if (!(board.getTile(7, 5).isOccupied()) && !(board.getTile(7, 6).isOccupied())) {
                                int[] move = {7, 6};
                                array.add(move);
                            }
                        }
                        if (rook.getRookCastle().equals("queen")) {
                            if (!(board.getTile(7, 1).isOccupied()) && !(board.getTile(7, 2).isOccupied()) && !(board.getTile(7, 3).isOccupied())) {
                                int[] move = {7, 2};
                                array.add(move);
                            }
                        }
                    }

                }

            }


        }



    }
}
