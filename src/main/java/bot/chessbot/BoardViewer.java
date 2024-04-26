package bot.chessbot;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardViewer extends Application {

    static Board board;
    public Engine engine = new Engine();
    boolean selectedTileBool = false;
    Tile selectedTile;

    static Pane root;

    public BoardViewer() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {



        root = new Pane();

        board = new Board(true);
        board.setOnMouseClicked(new tileEventHandler());
        board.setLayoutX(300 - board.getBoundsInParent().getWidth()/2);
        board.setLayoutY(300 - board.getBoundsInParent().getHeight()/2);


        root.getChildren().add(board);

        ComboBox<String> depthDropdown = new ComboBox<>();
        depthDropdown.getItems().addAll("1", "2", "3", "4");
        depthDropdown.setValue("1"); // Set default value
        depthDropdown.setPrefWidth(60);
        depthDropdown.setPrefHeight(30);
        depthDropdown.setOnAction(event -> {
            engine.maxDepth = Integer.parseInt(depthDropdown.getValue()) - 1;


        });

        depthDropdown.setLayoutX(300 - 30);
        depthDropdown.setLayoutY(600 - 50 - 15);

        root.getChildren().add(depthDropdown);



        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Chess");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


        if (!board.getPlayingAsWhite()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                try {

                    engine.playBestMove(board);
                    board.drawBoard();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            delay.play();
        }


    }




    private class tileEventHandler implements EventHandler<MouseEvent> {


        @Override
        public void handle(MouseEvent e) {

            int row = board.rowForYPos(e.getY());
            int col = board.colForXPos(e.getX());

            if ((board.getPlayingAsWhite() && board.getTurn() % 2 == 1) || (!board.getPlayingAsWhite() && board.getTurn() % 2 == 0) && row < 8 && col < 8) {





                boolean moveDone = false;
                if (selectedTileBool && board.getTile(row, col).isOccupied()) {
                    if (selectedTile.getPiece().getColor().equals(board.getTile(row, col).getPiece().getColor())) {
                        selectedTileBool = false;
                    }
                }

                try {
                    if (selectedTileBool && (selectedTile.getColumn() != col || selectedTile.getRow() != row)) {
                        if (board.testMove(selectedTile, row, col) != null) {


                            ArrayList<int[]> moves = null;
                            try {
                                moves = selectedTile.getPiece().getValidMoves(board);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }


                            for (int[] move : moves) {


                                moveDone = false;

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


                                            } else if (row == 0) {


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


                                        } else if (row == 0) {


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
                                        moveDone = true;

                                    }


                                    if (board.getTurn() % 2 == 1) {
                                        board.removeBlackPawnEnPassant();
                                    } else {
                                        board.removeWhitePawnEnPassant();
                                    }

                                    //System.out.println("TURN INCREMENTED");

                                    board.setTurn(board.getTurn() + 1);

                                    //System.out.println("TURN IN BOARD VIEWER: " + board.getTurn());


                                    break;
                                }


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

                        } else {
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
                                        //System.out.println("true here 1");
                                        selectedTileBool = true;
                                    }
                                }
                            } else {
                                if (tile.isOccupied()) {
                                    if (tile.getPiece().getColor().equals("black")) {
                                        tile.onClicked();
                                        selectedTile = board.getTile(row, col);
                                        //System.out.println("true here 2");
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

                    if (moveDone) {
                        try {

                            String color = "";
                            if (board.getTurn() % 2 == 0) {
                                color = "BLACK";
                            } else {
                                color = "WHITE";
                            }

                            ArrayList<Board> array = board.getLegalMoves(board);
                            System.out.println(color + " LEGAL MOVES LEFT: " + array.size());

                            if (board.didWhiteWin()) {
                                System.out.println("CHECKMATE, WHITE WINS");
                            }

                            if (board.didBlackWin()) {
                                System.out.println("CHECKMATE, BLACK WINS");
                            }

                            if (board.isStalemate()) {
                                System.out.println("STALEMATE, DRAW");
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (!board.isGameOver(board)) {
                            PauseTransition delay = new PauseTransition(Duration.seconds(1));
                            delay.setOnFinished(event -> {
                                try {
                                    long startTime = System.nanoTime();
                                    engine.playBestMove(board);
                                    board.drawBoard();
                                    long endTime = System.nanoTime();
                                    long executionTime = (endTime - startTime) / 1000000;
                                    System.out.println("TIME TAKEN: " + executionTime);
                                    System.out.println("# OF POSITIONS: " + Engine.counter);
                                    Engine.counter = 0;
                                    //board.setTurn(board.getTurn() + 1);

                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });
                            delay.play();
                        }

                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



            }



        }
    }

    public static Board getBoard() {
        return board;
    }


}
