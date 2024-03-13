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

public class BoardViewer extends Application {

    Board board;

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

            Tile tile = board.getTile(row, col);

            if (tile.getIsActive()) {

                try {
                    board.removeActiveTiles();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else{
                try {
                    board.removeActiveTiles();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (tile.isOccupied()) {
                    tile.onClicked();
                }

            }


            try {
                board.drawBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
