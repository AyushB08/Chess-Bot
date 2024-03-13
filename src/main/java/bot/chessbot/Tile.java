package bot.chessbot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tile extends StackPane {
    private int row;
    private int column;
    private double tileSize;
    private Color color;
    private boolean occupied;
    private Piece piece;

    public Tile(int row, int col, double tileSize, Color color, boolean occupied, Piece piece) throws FileNotFoundException {

        this.row = row;
        this.column = col;
        this.tileSize = tileSize;
        this.color = color;
        this.occupied = occupied;
        this.piece = piece;

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

    public void drawTile() throws FileNotFoundException {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(tileSize);
        rectangle.setHeight(tileSize);
        rectangle.setFill(color);

        if (this.occupied) {
            System.out.println(piece.getImage());

            Image image = new Image(new FileInputStream(piece.getImage()));
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

