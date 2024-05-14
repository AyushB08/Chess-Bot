package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Engine {

    Board board = BoardViewer.board;

    public int maxDepth = 0;


    static int counter = 0;

    public Engine() throws IOException {
    }

    public void playRandomMove(Board board) throws IOException {
        ArrayList<Board> moves = board.getLegalMoves(board);
        int n = (int) (Math.random() * moves.size());
        Board move = moves.get(n);

        board.setBoard(move);




    }

    public void playBestMove(Board board) throws IOException {
        Board newBoard = findBestMove(board);
        //newBoard.printBoard(newBoard);
        board.setBoard(newBoard);
    }


    public Board findBestMove(Board board) throws IOException {

        double bestVal = Integer.MAX_VALUE;


        ArrayList<Board> legalMoves = board.getLegalMoves(board);
        System.out.println(legalMoves.size());
        legalMoves.get(0).printBoard(legalMoves.get(0));
        ArrayList<Board> possibleMoves = new ArrayList<>();

        for (Board clone : legalMoves) {

            counter += 1;
            //System.out.println("NEXT MOVE: " + Arrays.toString(move));



            double value = minimax(clone, 0, true, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);


            if (value < bestVal) {

                bestVal = value;
                possibleMoves = new ArrayList<>();
                possibleMoves.add(clone);

            } else if (value == bestVal) {
                possibleMoves.add(clone);
            }
        }

        return possibleMoves.get((int)((Math.random() * possibleMoves.size())));

    }


    public double minimax(Board board, int depth, boolean isMaximizing, int maxDepth, double alpha, double beta) throws IOException {

        //System.out.println("IS MAXIMIZING: " + isMaximizing + " DEPTH: " + depth);
        //board.printBoard(board);
        double value = getValueOfBoard(board);

        if (value == 10000 || value == -10000) {
            //System.out.println("GAME ENDED CHECKMATE");
            return value;
        }
        /*
        if (board.isStalemate()) {
            //System.out.println("GAME ENDED STALEMATE");
            return value;
        }

         */

        if (depth >= maxDepth) {
            return value;
        }
        System.out.println("CLONED IN MINIMAX");
        Board clone = board.cloneBoard(board);

        double bestVal = 0;

        ArrayList<Board> legalMoves = clone.getLegalMoves(clone);

        if (isMaximizing) {
            bestVal = Integer.MIN_VALUE;
            for (Board secondClone : legalMoves) {
                counter += 1;
                //System.out.println("IN MINIMAX: IS MAXIMIZING " + isMaximizing + " MOVE: " + Arrays.toString(legalMove));




                double boardValue = minimax(secondClone, depth+1, false, maxDepth, alpha, beta);
                bestVal = Math.max(bestVal, boardValue);
                alpha = Math.max(alpha, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        else {
            bestVal = Integer.MAX_VALUE;
            for (Board secondClone : legalMoves) {
                counter += 1;
                //System.out.println("IN MINIMAX: IS MAXIMIZING " + isMaximizing + " MOVE: " + Arrays.toString(legalMove));




                double boardValue = minimax(secondClone, depth+1, true, maxDepth, alpha, beta);
                //System.out.println("BOARD VALUE OF " + "IN MINIMAX: IS MAXIMIZING " + isMaximizing + " MOVE: " + Arrays.toString(legalMove) + " IS " + boardValue +"\n");
                bestVal = Math.min(bestVal, boardValue);
                beta = Math.min(beta, bestVal);
                if (beta <= alpha) {
                    //System.out.println("BROKE HERE");
                    break;
                }
            }
        }
        return bestVal;

    }



    public double getValueOfBoard(Board board) throws IOException {
        /*
        if (board.didWhiteWin()) {
            return 10000;
        }

        else if (board.didBlackWin()) {
            return -10000;
        }

        else if (board.isStalemate()) {
            return 0;
        }
        */


        //else {
            //.out.println("WHITE: " + board.getWhiteValue() + " BLACK: " + board.getBlackValue());
            return board.getWhiteValue() - board.getBlackValue();
        //}
    }



}
