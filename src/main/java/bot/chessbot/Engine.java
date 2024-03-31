package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;

public class Engine {

    public void playRandomMove(Board board) throws IOException {
        ArrayList<int[]> moves = board.getLegalMoves();
        int n = (int) (Math.random() * moves.size());
        int[] move = moves.get(n);
        Tile selectedTile = board.getTile(move[0], move[1]);
        int row = move[2];
        int col = move[3];

        boolean moveDone = false;

        if (selectedTile.getPiece().getName().equals("Pawn")) {
            if (Math.abs(row - selectedTile.getRow()) == 2) {
                Pawn pawn = (Pawn) selectedTile.getPiece();
                pawn.set_en_passant(true);
            } else if (Math.abs(row - selectedTile.getRow()) == 1 && Math.abs(col - selectedTile.getColumn()) == 1) {
                if (!board.getTile(row, col).isOccupied()) {
                    Pawn pawn = (Pawn) selectedTile.getPiece();
                    Tile tile = board.getTile(selectedTile.getRow(), col);

                    tile.setOccupied(false);
                    selectedTile.setOccupied(false);
                    pawn.setRow(row);
                    pawn.setCol(col);

                }

                if (row == 7) {


                    Tile tile = board.getTile(row, col);

                    tile.setOccupied(true);
                    selectedTile.setOccupied(false);

                    Queen queen = new Queen(7, col, "white");
                    tile.setPiece(queen);

                    moveDone = true;


                }
                else if (row == 0) {


                    Tile tile = board.getTile(row, col);

                    tile.setOccupied(true);
                    selectedTile.setOccupied(false);

                    Queen queen = new Queen(0, col, "black");
                    tile.setPiece(queen);

                    moveDone = true;


                }
            }
            if (row == 7) {


                Tile tile = board.getTile(row, col);

                tile.setOccupied(true);
                selectedTile.setOccupied(false);

                Queen queen = new Queen(7, col, "white");
                tile.setPiece(queen);

                moveDone = true;


            }
            else if (row == 0) {


                Tile tile = board.getTile(row, col);

                tile.setOccupied(true);
                selectedTile.setOccupied(false);

                Queen queen = new Queen(0, col, "black");
                tile.setPiece(queen);

                moveDone = true;


            }



        }
        if (selectedTile.getPiece().getName().equals("Rook")) {

            Rook rook = (Rook) selectedTile.getPiece();
            rook.setCastle(false);
        }
        if (selectedTile.getPiece().getName().equals("King")) {

            King king = (King) selectedTile.getPiece();

            if (king.canCastle) {


                if (king.getColor().equals("white")) {
                    if (row == 0 && col == 6) {
                        Tile tile = board.getTile(0, 7);
                        Rook rook = (Rook) board.getTile(0, 7).getPiece();

                        tile.setOccupied(false);

                        selectedTile.setOccupied(false);


                        board.getTile(0, 5).setOccupied(true);
                        board.getTile(0, 5).setPiece(rook);
                        board.getTile(0, 6).setOccupied(true);
                        board.getTile(0, 6).setPiece(king);

                        king.setRow(0);
                        king.setCol(6);
                        rook.setRow(0);
                        rook.setCol(5);


                        moveDone = true;


                    } else if (row == 0 && col == 2) {
                        Tile tile = board.getTile(0, 0);
                        Rook rook = (Rook) board.getTile(0, 0).getPiece();


                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        board.getTile(0, 2).setOccupied(true);
                        board.getTile(0, 2).setPiece(king);
                        board.getTile(0, 3).setOccupied(true);
                        board.getTile(0, 3).setPiece(rook);

                        king.setRow(0);
                        king.setCol(2);
                        rook.setRow(0);
                        rook.setCol(3);


                        moveDone = true;


                    }
                } else {
                    if (row == 7 && col == 6) {
                        Tile tile = board.getTile(7, 7);
                        Rook rook = (Rook) board.getTile(7, 7).getPiece();

                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        board.getTile(7, 5).setOccupied(true);
                        board.getTile(7, 5).setPiece(rook);
                        board.getTile(7, 6).setOccupied(true);
                        board.getTile(7, 6).setPiece(king);

                        king.setRow(7);
                        king.setCol(6);
                        rook.setRow(7);
                        rook.setCol(5);

                        moveDone = true;



                    } else if (row == 7 && col == 2) {
                        Tile tile = board.getTile(7, 0);
                        Rook rook = (Rook) board.getTile(7, 0).getPiece();

                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        board.getTile(7, 2).setOccupied(true);
                        board.getTile(7, 2).setPiece(king);

                        board.getTile(7, 3).setOccupied(true);
                        board.getTile(7, 3).setPiece(rook);

                        king.setRow(7);
                        king.setCol(2);
                        rook.setRow(7);
                        rook.setCol(3);


                        moveDone = true;


                    }
                }


            }

            king.setCastle(false);


        }



        if (!moveDone) {


            board.getTile(row, col).setPiece(selectedTile.getPiece());
            board.getTile(row, col).setOccupied(true);
            selectedTile.getPiece().setRow(row);
            selectedTile.getPiece().setCol(col);
            selectedTile.setPiece(null);
            selectedTile.setOccupied(false);


        }
    }
}
