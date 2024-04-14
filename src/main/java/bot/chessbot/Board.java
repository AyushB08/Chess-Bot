package bot.chessbot;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Board extends Group {
    private Tile[][] board;
    private double tileSize;


    private final Color colorOne;


    private final Color colorTwo;

    private final Color activatedOne;
    private final Color activatedTwo;

    private int turn = 1;

    private boolean playingAsWhite = true;


    public Board(boolean draw) throws IOException {

        colorOne = Color.web("#F0D9B5",1.0);
        colorTwo = Color.web("#B58863",1.0);

        activatedOne = Color.web("#646F40",1.0);
        activatedTwo = Color.web("829769", 1.0);

        tileSize = 50;

        board = new Tile[8][8];


        if (draw) {
            initializeBoard();
            drawBoard();
        }



    }


    public Tile[][] getBoard() {
        return board;
    }

    public boolean getPlayingAsWhite() {
        return playingAsWhite;
    }
    public double getTileSize() {
        return tileSize;
    }

    public void setTileSize(double size) {
        tileSize = size;
    }


    public int getTurn() {
        return turn;
    }

    public void setTurn(int x) {
        turn = x;
    }



    public int colForXPos(double x) {

        if (playingAsWhite) {
            return  ((int)(x / tileSize));
        } else {
            return 7- ((int)(x / tileSize));
        }

    }

    public int rowForYPos(double y) {
        if (playingAsWhite) {
            return 7 - ((int)(y / tileSize));
        } else {
            return ((int)(y / tileSize));
        }

    }

    public void setTile(Tile tile, boolean playingAsWhite) {
        if (playingAsWhite) {
            board[7-tile.getRow()][tile.getColumn()] = tile;
        } else {
            board[tile.getRow()][7-tile.getColumn()] = tile;
        }

    }



    public Tile getTile(int row, int col) {

        if (playingAsWhite) {
            return board[7-row][col];
        } else {
            return board[row][7-col];
        }

    }


    public void drawBoard() throws IOException {
        this.getChildren().clear();
        for (int i = 0; i <= 7; i++) {
            for (int a = 0; a <= 7; a++) {
                Tile tile;
                if (playingAsWhite) {
                    tile = getTile(7-i, a);
                } else {
                    tile = getTile(i, 7-a);
                }

                tile.drawTile();
                tile.setLayoutX(tileSize * a);
                tile.setLayoutY(tileSize * i);

                this.getChildren().add(tile);
            }


        }


    }


    public void initializeBoard() throws IOException {

        if (playingAsWhite) {


            this.getChildren().clear();

            board = new Tile[8][8];

            Tile black_tile_one = new Tile(7, 0, tileSize, colorOne, activatedOne, true, new Rook(7, 0, "black"));
            Tile black_tile_two = new Tile(7, 1, tileSize, colorTwo, activatedTwo, true, new Knight(7, 1, "black"));
            Tile black_tile_three = new Tile(7, 2, tileSize, colorOne, activatedOne, true, new Bishop(7, 2, "black"));
            Tile black_tile_four = new Tile(7, 3, tileSize, colorTwo, activatedTwo, true, new Queen(7, 3, "black"));
            Tile black_tile_five = new Tile(7, 4, tileSize, colorOne, activatedOne, true, new King(7, 4, "black"));
            Tile black_tile_six = new Tile(7, 5, tileSize, colorTwo, activatedTwo, true, new Bishop(7, 5, "black"));
            Tile black_tile_seven = new Tile(7, 6, tileSize, colorOne, activatedOne, true, new Knight(7, 6, "black"));
            Tile black_tile_eight = new Tile(7, 7, tileSize, colorTwo, activatedTwo, true, new Rook(7, 7, "black"));

            Tile black_pawn_one = new Tile(6, 0, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 0, "black"));
            Tile black_pawn_two = new Tile(6, 1, tileSize, colorOne, activatedOne, true, new Pawn(6, 1, "black"));
            Tile black_pawn_three = new Tile(6, 2, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 2, "black"));
            Tile black_pawn_four = new Tile(6, 3, tileSize, colorOne, activatedOne, true, new Pawn(6, 3, "black"));
            Tile black_pawn_five = new Tile(6, 4, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 4, "black"));
            Tile black_pawn_six = new Tile(6, 5, tileSize, colorOne, activatedOne, true, new Pawn(6, 5, "black"));
            Tile black_pawn_seven = new Tile(6, 6, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 6, "black"));
            Tile black_pawn_eight = new Tile(6, 7, tileSize, colorOne, activatedOne, true, new Pawn(6, 7, "black"));

            setTile(black_tile_one, true);
            setTile(black_tile_two, true);
            setTile(black_tile_three, true);
            setTile(black_tile_four, true);
            setTile(black_tile_five, true);
            setTile(black_tile_six, true);
            setTile(black_tile_seven, true);
            setTile(black_tile_eight, true);

            setTile(black_pawn_one, true);
            setTile(black_pawn_two, true);
            setTile(black_pawn_three, true);
            setTile(black_pawn_four, true);
            setTile(black_pawn_five, true);
            setTile(black_pawn_six, true);
            setTile(black_pawn_seven, true);
            setTile(black_pawn_eight, true);


            for (int i = 2; i <= 5; i++) {

                for (int a = 0; a <= 7; a++) {
                    Tile tile;
                    if ((i % 2 == 0 && a % 2 != 0) || (i % 2 != 0 && a % 2 == 0)) {
                        tile = new Tile(i, a, tileSize, colorOne, activatedOne, false, null);
                    } else {
                        tile = new Tile(i, a, tileSize, colorTwo, activatedTwo, false, null);
                    }

                    setTile(tile, true);
                }
            }

            Tile white_tile_one = new Tile(0, 0, tileSize, colorTwo, activatedTwo, true, new Rook(0, 0, "white"));
            Tile white_tile_two = new Tile(0, 1, tileSize, colorOne, activatedOne, true, new Knight(0, 1, "white"));
            Tile white_tile_three = new Tile(0, 2, tileSize, colorTwo, activatedTwo, true, new Bishop(0, 2, "white"));
            Tile white_tile_four = new Tile(0, 3, tileSize, colorOne, activatedOne, true, new Queen(0, 3, "white"));
            Tile white_tile_five = new Tile(0, 4, tileSize, colorTwo, activatedTwo, true, new King(0, 4, "white"));
            Tile white_tile_six = new Tile(0, 5, tileSize, colorOne, activatedOne, true, new Bishop(0, 5, "white"));
            Tile white_tile_seven = new Tile(0, 6, tileSize, colorTwo, activatedTwo, true, new Knight(0, 6, "white"));
            Tile white_tile_eight = new Tile(0, 7, tileSize, colorOne, activatedOne, true, new Rook(0, 7, "white"));

            Tile white_pawn_one = new Tile(1, 0, tileSize, colorOne, activatedOne, true, new Pawn(1, 0, "white"));
            Tile white_pawn_two = new Tile(1, 1, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 1, "white"));
            Tile white_pawn_three = new Tile(1, 2, tileSize, colorOne, activatedOne, true, new Pawn(1, 2, "white"));
            Tile white_pawn_four = new Tile(1, 3, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 3, "white"));
            Tile white_pawn_five = new Tile(1, 4, tileSize, colorOne, activatedOne, true, new Pawn(1, 4, "white"));
            Tile white_pawn_six = new Tile(1, 5, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 5, "white"));
            Tile white_pawn_seven = new Tile(1, 6, tileSize, colorOne, activatedOne, true, new Pawn(1, 6, "white"));
            Tile white_pawn_eight = new Tile(1, 7, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 7, "white"));


            setTile(white_tile_one, true);
            setTile(white_tile_two, true);
            setTile(white_tile_three, true);
            setTile(white_tile_four, true);
            setTile(white_tile_five, true);
            setTile(white_tile_six, true);
            setTile(white_tile_seven, true);
            setTile(white_tile_eight, true);

            setTile(white_pawn_one, true);
            setTile(white_pawn_two, true);
            setTile(white_pawn_three, true);
            setTile(white_pawn_four, true);
            setTile(white_pawn_five, true);
            setTile(white_pawn_six, true);
            setTile(white_pawn_seven, true);
            setTile(white_pawn_eight, true);

        } else {
            this.getChildren().clear();

            board = new Tile[8][8];

            Tile black_tile_one = new Tile(7, 0, tileSize, colorOne, activatedOne, true, new Rook(7, 0, "black"));
            Tile black_tile_two = new Tile(7, 1, tileSize, colorTwo, activatedTwo, true, new Knight(7, 1, "black"));
            Tile black_tile_three = new Tile(7, 2, tileSize, colorOne, activatedOne, true, new Bishop(7, 2, "black"));
            Tile black_tile_four = new Tile(7, 3, tileSize, colorTwo, activatedTwo, true, new Queen(7, 3, "black"));
            Tile black_tile_five = new Tile(7, 4, tileSize, colorOne, activatedOne, true, new King(7, 4, "black"));
            Tile black_tile_six = new Tile(7, 5, tileSize, colorTwo, activatedTwo, true, new Bishop(7, 5, "black"));
            Tile black_tile_seven = new Tile(7, 6, tileSize, colorOne, activatedOne, true, new Knight(7, 6, "black"));
            Tile black_tile_eight = new Tile(7, 7, tileSize, colorTwo, activatedTwo, true, new Rook(7, 7, "black"));

            Tile black_pawn_one = new Tile(6, 0, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 0, "black"));
            Tile black_pawn_two = new Tile(6, 1, tileSize, colorOne, activatedOne, true, new Pawn(6, 1, "black"));
            Tile black_pawn_three = new Tile(6, 2, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 2, "black"));
            Tile black_pawn_four = new Tile(6, 3, tileSize, colorOne, activatedOne, true, new Pawn(6, 3, "black"));
            Tile black_pawn_five = new Tile(6, 4, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 4, "black"));
            Tile black_pawn_six = new Tile(6, 5, tileSize, colorOne, activatedOne, true, new Pawn(6, 5, "black"));
            Tile black_pawn_seven = new Tile(6, 6, tileSize, colorTwo, activatedTwo, true, new Pawn(6, 6, "black"));
            Tile black_pawn_eight = new Tile(6, 7, tileSize, colorOne, activatedOne, true, new Pawn(6, 7, "black"));

            setTile(black_tile_one, false);
            setTile(black_tile_two, false);
            setTile(black_tile_three, false);
            setTile(black_tile_four, false);
            setTile(black_tile_five, false);
            setTile(black_tile_six, false);
            setTile(black_tile_seven, false);
            setTile(black_tile_eight, false);

            setTile(black_pawn_one, false);
            setTile(black_pawn_two, false);
            setTile(black_pawn_three, false);
            setTile(black_pawn_four, false);
            setTile(black_pawn_five, false);
            setTile(black_pawn_six, false);
            setTile(black_pawn_seven, false);
            setTile(black_pawn_eight, false);


            for (int i = 2; i <= 5; i++) {

                for (int a = 0; a <= 7; a++) {
                    Tile tile;
                    if ((i % 2 == 0 && a % 2 != 0) || (i % 2 != 0 && a % 2 == 0)) {
                        tile = new Tile(i, a, tileSize, colorOne, activatedOne, false, null);
                    } else {
                        tile = new Tile(i, a, tileSize, colorTwo, activatedTwo, false, null);
                    }

                    setTile(tile, false);
                }
            }

            Tile white_tile_one = new Tile(0, 0, tileSize, colorTwo, activatedTwo, true, new Rook(0, 0, "white"));
            Tile white_tile_two = new Tile(0, 1, tileSize, colorOne, activatedOne, true, new Knight(0, 1, "white"));
            Tile white_tile_three = new Tile(0, 2, tileSize, colorTwo, activatedTwo, true, new Bishop(0, 2, "white"));
            Tile white_tile_four = new Tile(0, 3, tileSize, colorOne, activatedOne, true, new Queen(0, 3, "white"));
            Tile white_tile_five = new Tile(0, 4, tileSize, colorTwo, activatedTwo, true, new King(0, 4, "white"));
            Tile white_tile_six = new Tile(0, 5, tileSize, colorOne, activatedOne, true, new Bishop(0, 5, "white"));
            Tile white_tile_seven = new Tile(0, 6, tileSize, colorTwo, activatedTwo, true, new Knight(0, 6, "white"));
            Tile white_tile_eight = new Tile(0, 7, tileSize, colorOne, activatedOne, true, new Rook(0, 7, "white"));

            Tile white_pawn_one = new Tile(1, 0, tileSize, colorOne, activatedOne, true, new Pawn(1, 0, "white"));
            Tile white_pawn_two = new Tile(1, 1, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 1, "white"));
            Tile white_pawn_three = new Tile(1, 2, tileSize, colorOne, activatedOne, true, new Pawn(1, 2, "white"));
            Tile white_pawn_four = new Tile(1, 3, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 3, "white"));
            Tile white_pawn_five = new Tile(1, 4, tileSize, colorOne, activatedOne, true, new Pawn(1, 4, "white"));
            Tile white_pawn_six = new Tile(1, 5, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 5, "white"));
            Tile white_pawn_seven = new Tile(1, 6, tileSize, colorOne, activatedOne, true, new Pawn(1, 6, "white"));
            Tile white_pawn_eight = new Tile(1, 7, tileSize, colorTwo, activatedTwo, true, new Pawn(1, 7, "white"));


            setTile(white_tile_one, false);
            setTile(white_tile_two, false);
            setTile(white_tile_three, false);
            setTile(white_tile_four, false);
            setTile(white_tile_five, false);
            setTile(white_tile_six, false);
            setTile(white_tile_seven, false);
            setTile(white_tile_eight, false);

            setTile(white_pawn_one, false);
            setTile(white_pawn_two, false);
            setTile(white_pawn_three, false);
            setTile(white_pawn_four, false);
            setTile(white_pawn_five, false);
            setTile(white_pawn_six, false);
            setTile(white_pawn_seven, false);
            setTile(white_pawn_eight, false);
        }

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
                        ArrayList<int[]> moves = new ArrayList<>();
                        if (piece.getName().equals("King")) {
                            King king = (King) piece;
                            moves = king.getValidMovesWithoutCastle(clone);
                        } else {
                            moves = piece.getValidMoves(clone);
                        }

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

                        ArrayList<int[]> moves = new ArrayList<>();
                        if (piece.getName().equals("King")) {
                            King king = (King) piece;
                            moves = king.getValidMovesWithoutCastle(clone);
                        } else {
                            moves = piece.getValidMoves(clone);
                        }
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

        long startTime = System.nanoTime();

        Board clone = new Board(false);
        clone.setTurn(x.getTurn());
        clone.playingAsWhite  = x.getPlayingAsWhite();
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a  < 8; a++ ) {
                clone.setTile(x.getTile(i, a).cloneTile(x.getTile(i, a)), playingAsWhite);
            }
        }

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000000;
        System.out.println("TIME TAKEN TO CLONE: " + executionTime);
        return clone;


    }

    public boolean isStalemate() throws IOException {
        if (turn % 2 == 1) {
            if (!isWhiteKingInCheck(this) && isGameOver(this)) {
                return true;
            }
        } else {
            if (!isBlackKingInCheck(this) && isGameOver(this)) {
                return true;
            }
        }
        return false;
    }

    public boolean didWhiteWin() throws IOException {
        if (turn % 2 == 0) {
            if (isBlackKingInCheck(this) && isGameOver(this)) {
                return true;
            }
        }
        return false;
    }

    public boolean didBlackWin() throws IOException {
        if (turn % 2 == 1) {
            if (isWhiteKingInCheck(this) && isGameOver(this)) {
                return true;
            }
        }

        return false;
    }

    public boolean isGameOver(Board board) throws IOException {

        for (int i = 0; i < 8; i++) {

            for (int a = 0; a  < 8; a++ ) {

                if (getTile(i, a).isOccupied()) {
                    if ((turn % 2 == 1 && getTile(i, a).getPiece().getColor().equals("white")) || (turn % 2 ==0 && getTile(i, a).getPiece().getColor().equals("black"))) {


                        Piece piece = getTile(i, a).getPiece();
                        ArrayList<int[]> moves = piece.getValidMoves(this);

                        for (int c = 0; c < moves.size(); c++) {

                            int[] move = moves.get(c);
                            Tile chosen = getTile(i, a);
                            //System.out.println("Went in is game over");
                            if (this.testMove(chosen, move[0], move[1])) {

                                return false;

                            }
                        }
                    }

                }
            }
        }

        return true;
    }

    public ArrayList<int[]> getLegalMoves(Board board) throws IOException {
        ArrayList<int[]> legalMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            for (int a = 0; a  < 8; a++ ) {

                if (board.getTile(i, a).isOccupied()) {

                    if ((turn % 2 == 1 && board.getTile(i, a).getPiece().getColor().equals("white")) || (turn % 2 ==0 && board.getTile(i, a).getPiece().getColor().equals("black"))) {


                        Piece piece = board.getTile(i, a).getPiece();
                        ArrayList<int[]> moves = piece.getValidMoves(this);

                        for (int[] move : moves) {

                            Tile chosen = board.getTile(i, a);
                            //System.out.println("went in getLegalMoves");
                            if (board.testMove(chosen, move[0], move[1])) {

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

        System.out.println("CLONED IN TESTMOVE");
        Board clone = cloneBoard(this);

        Tile selectedTile = chosenTile.cloneTile(chosenTile);

        clone.setTile(selectedTile, playingAsWhite);


        clone.playMove(clone, selectedTile, row, col);


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

    public double getBlackValue() {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (getTile(i, a).isOccupied()) {
                    if (getTile(i, a).getPiece().getColor().equals("black")) {
                        Piece piece = getTile(i, a).getPiece();
                        sum += piece.getValue();

                    }
                }
            }
        }
        return sum;
    }

    public double getWhiteValue() {
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (getTile(i, a).isOccupied()) {
                    if (getTile(i, a).getPiece().getColor().equals("white")) {
                        Piece piece = getTile(i, a).getPiece();
                        sum += piece.getValue();
                    }
                }
            }
        }
        return sum;
    }

    public void printBoard(Board board) {
        System.out.println();
        for (int i = 7; i >= 0; i--) {
            System.out.print("ROW " + i);
            for (int a = 0; a < 8; a++) {
                if (board.getTile(i, a).isOccupied()) {
                    System.out.printf("%15s",(board.getTile(i, a).getPiece().getColor() + " " + board.getTile(i, a).getPiece().getName()));
                } else {
                    System.out.printf("%15s","EMPTY");
                }
            }
            System.out.println();

        }
        System.out.println();
    }

    public void playMove(Board clone, Tile selectedTile, int row, int col) throws IOException {
        //System.out.println();
        //.println("SELECTED TILE ROW: " + selectedTile.getRow() + " SELECTED TILE COLUMN: " + selectedTile.getColumn());
        //System.out.println("ROW: " + row + " COL: " + col);
        //System.out.println();
        //System.out.println("PREVIOUS BOARD");
        //clone.printBoard(clone);
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


                } else if (row == 0) {


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


            } else if (row == 0) {


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
                king.setCastle(false);

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
                        if (rook == null) {
                            System.out.println("FOUND BUG");
                        }

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

        clone.setTurn(clone.getTurn() + 1);




        //System.out.println("AFTER BOARD");
        //clone.printBoard(clone);
    }





}
