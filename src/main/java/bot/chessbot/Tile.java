package bot.chessbot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tile extends StackPane {
    private int row;
    private int column;
    private double tileSize;
    private Color color;
    private Color activated;
    private boolean occupied;
    private Piece piece;

    private boolean isActive;




    public Tile(int row, int col, double tileSize, Color color, Color activated, boolean occupied, Piece piece) throws IOException {

        this.row = row;
        this.column = col;
        this.tileSize = tileSize;
        this.color = color;
        this.activated = activated;
        this.occupied = occupied;
        this.piece = piece;


        isActive = false;


        drawTile();



    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Color getColor() {
        return color;
    }

    public Piece getPiece() {
        return piece;
    }

    public double getTileSize() {
        return tileSize;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public void onClicked() {
        if (!isActive) {
            isActive = true;
        }
    }

    public void drawTile() throws IOException {

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(tileSize);
        rectangle.setHeight(tileSize);
        if (isActive) {
            rectangle.setFill(activated);
        } else {
            rectangle.setFill(color);
        }


        if (this.occupied) {



            FileInputStream fileInput = new FileInputStream(piece.getImage());
            Image image = new Image(fileInput);
            fileInput.close();
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);

            this.getChildren().clear();
            this.getChildren().addAll(rectangle, imageView);

        } else {
            this.getChildren().clear();
            this.getChildren().addAll(rectangle);
        }
    }



}

