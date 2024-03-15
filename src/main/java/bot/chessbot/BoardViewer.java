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



        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();

    }

    private class tileEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            int row = board.rowForYPos(e.getY());
            int col = board.colForXPos(e.getX());

            if (selectedTileBool && (selectedTile.getColumn() != col || selectedTile.getRow() != row)) {

                ArrayList<int[]> moves = selectedTile.getPiece().getValidMoves();


                for (int i = 0; i < moves.size(); i++) {
                    System.out.println(Arrays.toString(moves.get(i)) + " row: " + row + " col: " + col);
                    if (moves.get(i)[0] == row && moves.get(i)[1] == col) {
                        System.out.println("went here");

                        selectedTileBool = false;
                        board.getTile(row, col).setPiece(selectedTile.getPiece());
                        board.getTile(row, col).setOccupied(true);
                        selectedTile.getPiece().setRow(row);
                        selectedTile.getPiece().setCol(col);
                        selectedTile.setPiece(null);
                        selectedTile.setOccupied(false);

                        board.setTurn(board.getTurn()+1);
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
