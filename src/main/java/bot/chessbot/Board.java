package bot.chessbot;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;

public class Board extends Group {
    private Tile[][] board;
    private double tileSize;

    private boolean playerIsWhite;
    private final Color colorOne;


    private final Color colorTwo;

    private final Color activatedOne;
    private final Color activatedTwo;

    private int turn = 0;


    public Board() throws IOException {

        colorOne = Color.web("#B58863",1.0);
        colorTwo = Color.web("#F0D9B5",1.0);

        activatedOne = Color.web("829769", 1.0);
        activatedTwo = Color.web("#646F40",1.0);

        playerIsWhite = true;
        tileSize = 50;

        board = new Tile[8][8];

        initalizeBoard();
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
        return  ((int)(x / tileSize));
    }

    public int rowForYPos(double y) {
        return 7 - ((int)(y / tileSize));
    }

    public void setTile(Tile tile) {
        board[7-tile.getRow()][tile.getColumn()] = tile;
    }

    public Tile getTile(int row, int col) {


        return board[7-row][col];
    }



    public void drawBoard() throws IOException {
        this.getChildren().clear();
        for (int i = 0; i <= 7; i++) {
            for (int a = 0; a <= 7; a++) {

                Tile tile = getTile(7-i, a);
                tile.drawTile();
                tile.setLayoutX(tileSize * a);
                tile.setLayoutY(tileSize * i);

                this.getChildren().add(tile);
            }
        }
    }


    public void initalizeBoard() throws IOException {

        this.getChildren().clear();

        board = new Tile[8][8];

        Tile black_tile_one = new Tile(7, 0, tileSize, colorOne, activatedOne, true, new Rook("black"));
        Tile black_tile_two = new Tile(7, 1, tileSize, colorTwo, activatedTwo, true, new Knight("black"));
        Tile black_tile_three = new Tile(7, 2, tileSize, colorOne, activatedOne, true, new Bishop("black"));
        Tile black_tile_four = new Tile(7, 3, tileSize, colorTwo, activatedTwo, true, new Queen("black"));
        Tile black_tile_five = new Tile(7, 4, tileSize, colorOne, activatedOne, true, new King("black"));
        Tile black_tile_six = new Tile(7,5, tileSize, colorTwo, activatedTwo, true, new Bishop("black"));
        Tile black_tile_seven = new Tile(7, 6, tileSize, colorOne, activatedOne, true, new Knight("black"));
        Tile black_tile_eight = new Tile(7, 7, tileSize, colorTwo, activatedTwo, true, new Rook("black"));

        Tile black_pawn_one = new Tile(6, 0, tileSize, colorTwo, activatedTwo, true, new Pawn("black"));
        Tile black_pawn_two = new Tile(6, 1, tileSize, colorOne, activatedOne, true, new Pawn("black"));
        Tile black_pawn_three = new Tile(6, 2, tileSize, colorTwo, activatedTwo, true, new Pawn("black"));
        Tile black_pawn_four = new Tile(6, 3, tileSize, colorOne, activatedOne, true, new Pawn("black"));
        Tile black_pawn_five = new Tile(6, 4, tileSize, colorTwo, activatedTwo, true, new Pawn("black"));
        Tile black_pawn_six = new Tile(6, 5, tileSize, colorOne , activatedOne, true, new Pawn("black"));
        Tile black_pawn_seven = new Tile(6, 6, tileSize, colorTwo, activatedTwo, true, new Pawn("black"));
        Tile black_pawn_eight = new Tile(6, 7, tileSize, colorOne, activatedOne, true, new Pawn("black"));

        setTile(black_tile_one);
        setTile(black_tile_two);
        setTile(black_tile_three);
        setTile(black_tile_four);
        setTile(black_tile_five);
        setTile(black_tile_six);
        setTile(black_tile_seven);
        setTile(black_tile_eight);

        setTile(black_pawn_one);
        setTile(black_pawn_two);
        setTile(black_pawn_three);
        setTile(black_pawn_four);
        setTile(black_pawn_five);
        setTile(black_pawn_six);
        setTile(black_pawn_seven);
        setTile(black_pawn_eight);


        for (int i = 2; i <= 5; i++) {

            for (int a = 0; a <= 7; a++) {
                Tile tile;
                if ((i % 2 == 0 && a % 2 != 0) || (i % 2 != 0 && a % 2 == 0)) {
                    tile = new Tile(i, a, tileSize, colorOne, activatedOne, false, null);
                } else {
                    tile = new Tile(i, a, tileSize, colorTwo, activatedTwo, false, null);
                }

                setTile(tile);
            }
        }

        Tile white_tile_one = new Tile(0, 0, tileSize, colorTwo, activatedTwo ,true, new Rook("white"));
        Tile white_tile_two = new Tile(0, 1, tileSize, colorOne, activatedOne, true, new Knight("white"));
        Tile white_tile_three = new Tile(0, 2, tileSize, colorTwo, activatedTwo, true, new Bishop("white"));
        Tile white_tile_four = new Tile(0, 3, tileSize, colorOne, activatedOne, true, new Queen("white"));
        Tile white_tile_five = new Tile(0, 4, tileSize, colorTwo, activatedTwo, true, new King("white"));
        Tile white_tile_six = new Tile(0,5, tileSize, colorOne, activatedOne, true, new Bishop("white"));
        Tile white_tile_seven = new Tile(0, 6, tileSize, colorTwo, activatedTwo, true, new Knight("white"));
        Tile white_tile_eight = new Tile(0, 7, tileSize, colorOne, activatedOne, true, new Rook("white"));

        Tile white_pawn_one = new Tile(1, 0, tileSize, colorOne, activatedOne, true, new Pawn("white"));
        Tile white_pawn_two = new Tile(1, 1, tileSize, colorTwo, activatedTwo, true, new Pawn("white"));
        Tile white_pawn_three = new Tile(1, 2, tileSize, colorOne, activatedOne, true, new Pawn("white"));
        Tile white_pawn_four = new Tile(1, 3, tileSize, colorTwo, activatedTwo, true, new Pawn("white"));
        Tile white_pawn_five = new Tile(1, 4, tileSize, colorOne, activatedOne, true, new Pawn("white"));
        Tile white_pawn_six = new Tile(1, 5, tileSize, colorTwo, activatedTwo, true, new Pawn("white"));
        Tile white_pawn_seven = new Tile(1, 6, tileSize, colorOne, activatedOne, true, new Pawn("white"));
        Tile white_pawn_eight = new Tile(1, 7, tileSize, colorTwo, activatedTwo, true, new Pawn("white"));


        setTile(white_tile_one);
        setTile(white_tile_two);
        setTile(white_tile_three);
        setTile(white_tile_four);
        setTile(white_tile_five);
        setTile(white_tile_six);
        setTile(white_tile_seven);
        setTile(white_tile_eight);

        setTile(white_pawn_one);
        setTile(white_pawn_two);
        setTile(white_pawn_three);
        setTile(white_pawn_four);
        setTile(white_pawn_five);
        setTile(white_pawn_six);
        setTile(white_pawn_seven);
        setTile(white_pawn_eight);

    }

    public void removeActiveTiles() throws IOException {
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                Tile tile = getTile(i, a);
                tile.setIsActive(false);
                drawBoard();
            }
        }
    }




}
