package bot.chessbot;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

public class Board extends Group {
    private Tile[][] board;
    private double tileSize;

    private boolean playerIsWhite;
    private Color colorOne;


    private Color colorTwo;
    private int turn = 0;


    public Board() throws FileNotFoundException {

        colorOne = Color.web("#B58863",1.0);
        colorTwo = Color.web("#F0D9B5",1.0);
        playerIsWhite = true;
        tileSize = 50;

        board = new Tile[8][8];

        drawBoard();


    }

    public Tile[][] getBoard() {
        return board;
    }

    public double getTileSize() {
        return tileSize;
    }

    public void setTileSize(double size) {
        tileSize = size;
    }

    public double xPosForCol(int col) {
        return col * tileSize;
    }


    public double yPosForRow(int row) {
        return row * tileSize;
    }

    public int colForXPos(double x) {
        return (int)(x / tileSize);
    }

    public int rowForYPos(double y) {
        return (int)(y / tileSize);
    }

    public void drawBoard() throws FileNotFoundException {

        this.getChildren().clear();

        board = new Tile[8][8];

        for (int i = 0; i <= 7; i++) {
            for (int a = 0; a <= 7; a++) {
                Tile tile;
                if ((i % 2 == 0 && a % 2 != 0) || (i % 2 != 0 && a % 2 == 0)) {
                    tile = new Tile(7 - i, a, tileSize, colorOne, false, null);
                } else {
                    tile = new Tile(7 - i, a, tileSize, colorTwo, false, null);
                }



                tile.setLayoutX(tileSize * a);
                tile.setLayoutY(tileSize * i);


                board[i][a] = tile;
                this.getChildren().add(tile);
            }
        }
    }

    public void createBoard() {

    }

}
