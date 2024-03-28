package bot.chessbot;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Board extends Group {
    private Tile[][] board;
    private double tileSize;

    private boolean playerIsWhite;
    private final Color colorOne;


    private final Color colorTwo;

    private final Color activatedOne;
    private final Color activatedTwo;

    private int turn = 1;


    public Board(boolean draw) throws IOException {

        colorOne = Color.web("#F0D9B5",1.0);
        colorTwo = Color.web("#B58863",1.0);

        activatedOne = Color.web("#646F40",1.0);
        activatedTwo = Color.web("829769", 1.0);

        playerIsWhite = true;
        tileSize = 80;

        board = new Tile[8][8];


        if (draw) {
            initalizeBoard();
            drawBoard();
        }



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

    public int getTurn() {
        return turn;
    }

    public void setTurn(int x) {
        turn = x;
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

        System.out.println("DRAW BOARD IS DONE. DRAW BOARD IS DONE. DRAW BOARD IS DONE. DRAW BOARD IS DONE. DRAW BOARD IS DONE. DRAW BOARD IS DONE. ");

        System.out.println("ALMOST DONE DRAWING");
    }


    public void initalizeBoard() throws IOException {

        this.getChildren().clear();

        board = new Tile[8][8];

        Tile black_tile_one = new Tile(7, 0, tileSize, colorOne, activatedOne, true, new Rook(7, 0, "black"));
        Tile black_tile_two = new Tile(7, 1, tileSize, colorTwo, activatedTwo, true, new Knight(7, 1, "black"));
        Tile black_tile_three = new Tile(7, 2, tileSize, colorOne, activatedOne, true, new Bishop(7, 2, "black"));
        Tile black_tile_four = new Tile(7, 3, tileSize, colorTwo, activatedTwo, true, new Queen(7, 3, "black"));
        Tile black_tile_five = new Tile(7, 4, tileSize, colorOne, activatedOne, true, new King(7, 4, "black"));
        Tile black_tile_six = new Tile(7,5, tileSize, colorTwo, activatedTwo, true, new Bishop(7, 5, "black"));
        Tile black_tile_seven = new Tile(7, 6, tileSize, colorOne, activatedOne, true, new Knight(7, 6, "black"));
        Tile black_tile_eight = new Tile(7, 7, tileSize, colorTwo, activatedTwo, true, new Rook(7, 7, "black"));

        Tile black_pawn_one = new Tile(6, 0, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 0, "black"));
        Tile black_pawn_two = new Tile(6, 1, tileSize, colorOne, activatedOne, true, new Pawn(6, 1, "black"));
        Tile black_pawn_three = new Tile(6, 2, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 2, "black"));
        Tile black_pawn_four = new Tile(6, 3, tileSize, colorOne, activatedOne, true, new Pawn(6, 3, "black"));
        Tile black_pawn_five = new Tile(6, 4, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 4, "black"));
        Tile black_pawn_six = new Tile(6, 5, tileSize, colorOne , activatedOne, true, new Pawn(6, 5, "black"));
        Tile black_pawn_seven = new Tile(6, 6, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 6, "black"));
        Tile black_pawn_eight = new Tile(6, 7, tileSize, colorOne, activatedOne, true, new Pawn(6, 7, "black"));

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

        Tile white_tile_one = new Tile(0, 0, tileSize, colorTwo, activatedTwo ,true, new Rook(0, 0, "white"));
        Tile white_tile_two = new Tile(0, 1, tileSize, colorOne, activatedOne, true, new Knight(0, 1, "white"));
        Tile white_tile_three = new Tile(0, 2, tileSize, colorTwo, activatedTwo, true, new Bishop(0, 2, "white"));
        Tile white_tile_four = new Tile(0, 3, tileSize, colorOne, activatedOne, true, new Queen(0, 3, "white"));
        Tile white_tile_five = new Tile(0, 4, tileSize, colorTwo, activatedTwo, true, new King(0, 4 ,"white"));
        Tile white_tile_six = new Tile(0,5, tileSize, colorOne, activatedOne, true, new Bishop(0, 5, "white"));
        Tile white_tile_seven = new Tile(0, 6, tileSize, colorTwo, activatedTwo, true, new Knight(0, 6, "white"));
        Tile white_tile_eight = new Tile(0, 7, tileSize, colorOne, activatedOne, true, new Rook(0, 7, "white"));

        Tile white_pawn_one = new Tile(1, 0, tileSize, colorOne, activatedOne, true, new Pawn(1, 0, "white"));
        Tile white_pawn_two = new Tile(1, 1, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 1, "white"));
        Tile white_pawn_three = new Tile(1, 2, tileSize, colorOne, activatedOne, true, new Pawn(1, 2, "white"));
        Tile white_pawn_four = new Tile(1, 3, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 3, "white"));
        Tile white_pawn_five = new Tile(1, 4, tileSize, colorOne, activatedOne, true, new Pawn(1, 4, "white"));
        Tile white_pawn_six = new Tile(1, 5, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 5, "white"));
        Tile white_pawn_seven = new Tile(1, 6, tileSize, colorOne, activatedOne, true, new Pawn(1, 6, "white"));
        Tile white_pawn_eight = new Tile(1, 7, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 7 , "white"));


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

            }
        }

    }

    public boolean isWhiteKingInCheck(Board clone) throws IOException {

        int[] kingLocation = {0, 0};


        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (!clone.getTile(i, a).isOccupied()) {

                } else {

                }

            }
        }

        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (clone.getTile(i, a).isOccupied()) {
                    if (clone.getTile(i, a).getPiece().getName().equals("King") && clone.getTile(i, a).getPiece().getColor().equals("white")) {
                        kingLocation[0] = i;
                        kingLocation[1] = a;

                    }
                }
            }
        }




        for (int i = 0 ; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (clone.getTile(i, a).isOccupied()) {
                    Piece piece = clone.getTile(i, a).getPiece();
                    if (piece.getColor().equals("black")) {
                        ArrayList<int[]> moves = piece.getValidMoves(clone);
                        for (int[] move : moves) {
                            if (move[0] == kingLocation[0] && move[1] == kingLocation[1]) {

                                return true;
                            }
                        }
                    }


                }
            }
        }

        return false;
    }



    public boolean isBlackKingInCheck(Board clone) throws IOException {
        int[] kingLocation = {0, 0};




        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (clone.getTile(i, a).isOccupied()) {
                    if (clone.getTile(i, a).getPiece().getName().equals("King") && clone.getTile(i, a).getPiece().getColor().equals("black")) {
                        kingLocation[0] = i;
                        kingLocation[1] = a;

                    }
                }
            }
        }



        for (int i = 0 ; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (clone.getTile(i, a).isOccupied()) {
                    Piece piece = clone.getTile(i, a).getPiece();
                    if (piece.getColor().equals("white")) {

                        ArrayList<int[]> moves = piece.getValidMoves(clone);
                        for (int[] move : moves) {
                            if (move[0] == kingLocation[0] && move[1] == kingLocation[1]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public Board cloneBoard(Board x) throws IOException {
        Board clone = new Board(false);
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a  < 8; a++ ) {
                clone.setTile(x.getTile(i, a).cloneTile(x.getTile(i, a)));
            }
        }
        return clone;
    }

    public ArrayList<int[]> getLegalMoves() throws IOException {
        ArrayList<int[]> legalMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            for (int a = 0; a  < 8; a++ ) {

                if (getTile(i, a).isOccupied()) {
                    if ((turn % 2 == 1 && getTile(i, a).getPiece().getColor().equals("white")) || (turn % 2 ==0 && getTile(i, a).getPiece().getColor().equals("black"))) {


                        Piece piece = getTile(i, a).getPiece();
                        ArrayList<int[]> moves = piece.getValidMoves(this);

                        for (int c = 0; c < moves.size(); c++) {

                            int[] move = moves.get(c);
                            Tile chosen = getTile(i, a);
                            if (this.testMove(chosen, move[0], move[1])) {

                                int[] array = {i, a, move[0], move[1]};
                                legalMoves.add(array);
                            }
                        }
                    }

                }
            }
        }

        return legalMoves;
    }

    public boolean testMove(Tile chosenTile, int row, int col) throws IOException {


        Board clone = cloneBoard(this);
        System.out.println("test");
        Tile selectedTile = chosenTile.cloneTile(chosenTile);

        clone.setTile(selectedTile);

        boolean moveDone = false;

        if (selectedTile.getPiece().getName().equals("Pawn")) {
            if (Math.abs(row - selectedTile.getRow()) == 2) {
                Pawn pawn = (Pawn) selectedTile.getPiece();
                pawn.set_en_passant(true);
            } else if (Math.abs(row - selectedTile.getRow()) == 1 && Math.abs(col - selectedTile.getColumn()) == 1) {
                if (!clone.getTile(row, col).isOccupied()) {
                    Pawn pawn = (Pawn) selectedTile.getPiece();
                    Tile tile = clone.getTile(selectedTile.getRow(), col);

                    tile.setOccupied(false);
                    selectedTile.setOccupied(false);
                    pawn.setRow(row);
                    pawn.setCol(col);

                }

                if (row == 7) {


                    Tile tile = clone.getTile(row, col);

                    tile.setOccupied(true);
                    selectedTile.setOccupied(false);

                    Queen queen = new Queen(7, col, "white");
                    tile.setPiece(queen);

                    moveDone = true;


                }
                else if (row == 0) {


                    Tile tile = clone.getTile(row, col);

                    tile.setOccupied(true);
                    selectedTile.setOccupied(false);

                    Queen queen = new Queen(0, col, "black");
                    tile.setPiece(queen);

                    moveDone = true;


                }
            }
            if (row == 7) {


                Tile tile = clone.getTile(row, col);

                tile.setOccupied(true);
                selectedTile.setOccupied(false);

                Queen queen = new Queen(7, col, "white");
                tile.setPiece(queen);

                moveDone = true;


            }
            else if (row == 0) {


                Tile tile = clone.getTile(row, col);

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
                        Tile tile = clone.getTile(0, 7);
                        Rook rook = (Rook) clone.getTile(0, 7).getPiece();

                        tile.setOccupied(false);

                        selectedTile.setOccupied(false);


                        clone.getTile(0, 5).setOccupied(true);
                        clone.getTile(0, 5).setPiece(rook);
                        clone.getTile(0, 6).setOccupied(true);
                        clone.getTile(0, 6).setPiece(king);

                        king.setRow(0);
                        king.setCol(6);
                        rook.setRow(0);
                        rook.setCol(5);


                        moveDone = true;


                    } else if (row == 0 && col == 2) {
                        Tile tile = clone.getTile(0, 0);
                        Rook rook = (Rook) clone.getTile(0, 0).getPiece();


                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        clone.getTile(0, 2).setOccupied(true);
                        clone.getTile(0, 2).setPiece(king);
                        clone.getTile(0, 3).setOccupied(true);
                        clone.getTile(0, 3).setPiece(rook);

                        king.setRow(0);
                        king.setCol(2);
                        rook.setRow(0);
                        rook.setCol(3);


                        moveDone = true;


                    }
                } else {
                    if (row == 7 && col == 6) {
                        Tile tile = clone.getTile(7, 7);
                        Rook rook = (Rook) clone.getTile(7, 7).getPiece();

                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        clone.getTile(7, 5).setOccupied(true);
                        clone.getTile(7, 5).setPiece(rook);
                        clone.getTile(7, 6).setOccupied(true);
                        clone.getTile(7, 6).setPiece(king);

                        king.setRow(7);
                        king.setCol(6);
                        rook.setRow(7);
                        rook.setCol(5);

                        moveDone = true;



                    } else if (row == 7 && col == 2) {
                        Tile tile = clone.getTile(7, 0);
                        Rook rook = (Rook) clone.getTile(7, 0).getPiece();

                        tile.setOccupied(false);
                        selectedTile.setOccupied(false);


                        clone.getTile(7, 2).setOccupied(true);
                        clone.getTile(7, 2).setPiece(king);

                        clone.getTile(7, 3).setOccupied(true);
                        clone.getTile(7, 3).setPiece(rook);

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


            clone.getTile(row, col).setPiece(selectedTile.getPiece());
            clone.getTile(row, col).setOccupied(true);
            selectedTile.getPiece().setRow(row);
            selectedTile.getPiece().setCol(col);
            selectedTile.setPiece(null);
            selectedTile.setOccupied(false);


        }


        if (clone.getTurn() % 2 == 1) {
            clone.removeBlackPawnEnPassant();
        } else {
            clone.removeWhitePawnEnPassant();
        }



        if (clone.getTile(row, col).getPiece().getColor().equals("white")) {

            if (clone.isWhiteKingInCheck(clone)) {

                return false;
            }

        }
        else {
            if (clone.isBlackKingInCheck(clone)) {

                return false;
            }

        }


        return true;
    }




    public void removeBlackPawnEnPassant() {
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (getTile(i, a).isOccupied()) {
                    Piece piece = getTile(i, a).getPiece();
                    if (piece.getName().equals("Pawn") && piece.color.equals("black")) {
                        Pawn pawn = (Pawn) piece;
                        pawn.set_en_passant(false);

                    }

                }
            }
        }
    }

    public void removeWhitePawnEnPassant() {
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (getTile(i, a).isOccupied()) {
                    Piece piece = getTile(i, a).getPiece();
                    if (piece.getName().equals("Pawn") && piece.color.equals("white")) {
                        Pawn pawn = (Pawn) piece;
                        pawn.set_en_passant(false);
                    }

                }
            }
        }
    }




}
