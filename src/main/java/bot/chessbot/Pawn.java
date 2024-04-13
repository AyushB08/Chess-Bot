package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

import static bot.chessbot.BoardViewer.board;

public class Pawn extends Piece{

    boolean can_be_en_passant = false;
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
    public ArrayList<int[]> getValidMoves(Board board) throws IOException {

        ArrayList<int[]> moves = new ArrayList<>();
        addForwardSquares(moves, board);
        addCaptureSquares(moves, board);
        addEnPassantSquares(moves, board);



        return moves;



    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public Piece clonePiece(Piece x) {
        Pawn pawn = new Pawn(row, col, color);
        pawn.set_en_passant(pawn.can_be_en_passant);
        return pawn;
    }

    public void set_en_passant(boolean x) {
        can_be_en_passant = x;
    }

    public boolean can_be_en_passant() {
        return can_be_en_passant;
    }

    public void addForwardSquares(ArrayList<int[]> array, Board board) {

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

    public void addEnPassantSquares(ArrayList<int[]> array, Board board) {
        if (this.color.equals("white")) {
            if (row < 7) {


                if (col > 0) {
                    Tile tile = board.getTile(row, col - 1);
                    if (tile.isOccupied()) {
                        Piece piece = tile.getPiece();
                        if (piece.getName().equals("Pawn") && !piece.getColor().equals(this.color) && !board.getTile(row + 1, col - 1).isOccupied()) {
                            Pawn pawn = (Pawn) piece;
                            if (pawn.can_be_en_passant) {
                                int[] move = {row + 1, col - 1};
                                array.add(move);
                            }
                        }
                    }
                }

                if (col < 7) {
                    Tile tile = board.getTile(row, col + 1);
                    if (tile.isOccupied()) {
                        Piece piece = tile.getPiece();
                        if (piece.getName().equals("Pawn") && !piece.getColor().equals(this.color) && !board.getTile(row + 1, col + 1).isOccupied()) {
                            Pawn pawn = (Pawn) piece;
                            if (pawn.can_be_en_passant) {
                                int[] move ={row + 1, col + 1};
                                array.add(move);
                            }
                        }

                    }
                }
            }
        } else {
            if (row > 0) {


                if (col > 0) {
                    Tile tile = board.getTile(row, col - 1);
                    if (tile.isOccupied()) {
                        Piece piece = tile.getPiece();
                        if (piece.getName().equals("Pawn") && !piece.getColor().equals(this.color) && !board.getTile(row - 1, col - 1).isOccupied()) {
                            Pawn pawn = (Pawn) piece;
                            if (pawn.can_be_en_passant) {
                                int[] move = {row - 1, col - 1};
                                array.add(move);
                            }
                        }
                    }
                }

                if (col < 7) {
                    Tile tile = board.getTile(row, col + 1);
                    if (tile.isOccupied()) {
                        Piece piece = tile.getPiece();
                        if (piece.getName().equals("Pawn") && !piece.getColor().equals(this.color) && !board.getTile(row - 1, col + 1).isOccupied()) {
                            Pawn pawn = (Pawn) piece;
                            if (pawn.can_be_en_passant) {
                                int[] move = {row - 1, col + 1};
                                array.add(move);
                            }
                        }

                    }
                }
            }
        }

    }

    public void addCaptureSquares(ArrayList<int[]> array, Board board) {
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
