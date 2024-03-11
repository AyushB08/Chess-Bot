package bot.chessbot;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class BoardViewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        BorderPane root = new BorderPane();

        Board board = new Board();

        root.setCenter(board);



        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();

    }
}
