package bot.chessbot;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardViewer extends Application {

    static Board board;
    boolean selectedTileBool = false;
    Tile selectedTile;

    public BoardViewer() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();

        board = new Board();
        board.setOnMouseClicked(new tileEventHandler());

        root.setCenter(board);

        Scene scene = new Scene(root, 640, 640);
        stage.setTitle("Chess");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private class tileEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            int row = board.rowForYPos(e.getY());
            int col = board.colForXPos(e.getX());
            if (selectedTileBool && board.getTile(row, col).isOccupied()) {
                if (selectedTile.getPiece().getColor().equals(board.getTile(row, col).getPiece().getColor())) {
                    selectedTileBool = false;
                }
            }

            try {
                if (selectedTileBool && (selectedTile.getColumn() != col || selectedTile.getRow() != row) && board.testMove(selectedTile, row, col)) {

                    ArrayList<int[]> moves = null;
                    try {
                        moves = selectedTile.getPiece().getValidMoves(board);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                    for (int[] move : moves) {

                        boolean moveDone = false;

                        if (move[0] == row && move[1] == col) {

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

                                            selectedTileBool = false;
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

                                            selectedTileBool = false;
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
                                            selectedTileBool = false;


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

                                            selectedTileBool = false;
                                            moveDone = true;


                                        }
                                    }




                                }

                                king.setCastle(false);


                            }



                            if (!moveDone) {

                                selectedTileBool = false;
                                board.getTile(row, col).setPiece(selectedTile.getPiece());
                                board.getTile(row, col).setOccupied(true);
                                selectedTile.getPiece().setRow(row);
                                selectedTile.getPiece().setCol(col);
                                selectedTile.setPiece(null);
                                selectedTile.setOccupied(false);
                            }


                            if (board.getTurn() % 2 == 1) {
                                board.removeBlackPawnEnPassant();
                            } else {
                                board.removeWhitePawnEnPassant();
                            }

                            board.setTurn(board.getTurn() + 1);




                            break;
                        }


                    }

                    selectedTileBool = false;


                    try {
                        board.removeActiveTiles();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    Tile tile = board.getTile(row, col);

                    if (tile.getIsActive()) {

                        try {
                            board.removeActiveTiles();
                            selectedTileBool = false;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else{
                        try {
                            board.removeActiveTiles();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (board.getTurn() % 2 == 1) {
                            if (tile.isOccupied()) {
                                if (tile.getPiece().getColor().equals("white")) {
                                    tile.onClicked();
                                    selectedTile = board.getTile(row, col);
                                    selectedTileBool = true;
                                }

                            }
                        } else {
                            if (tile.isOccupied()) {
                                if (tile.getPiece().getColor().equals("black")) {
                                    tile.onClicked();
                                    selectedTile = board.getTile(row, col);
                                    selectedTileBool = true;
                                }

                            }
                        }


                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            try {
                board.drawBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }



        }
    }

    public static Board getBoard() {
        return board;
    }


}
