package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Engine {

    Board board = BoardViewer.board;


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
            System.out.println("CLONED IN MINIMAX");


            double value = minimax(clone, 0, true, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            //System.out.println("\nMOVE: " + Arrays.toString(move) + " VALUE: " + value +"\n");

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
        if (board.isStalemate()) {
            //System.out.println("GAME ENDED STALEMATE");
            return value;
        }

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
                System.out.println("CLONED IN MINIMAX");



                double boardValue = minimax(secondClone, depth+1, false, maxDepth, alpha, beta);
                bestVal = Math.max(bestVal, boardValue);
                alpha = Math.max(alpha, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestVal;
        }

        else {
            bestVal = Integer.MAX_VALUE;
            for (Board secondClone : legalMoves) {
                counter += 1;
                //System.out.println("IN MINIMAX: IS MAXIMIZING " + isMaximizing + " MOVE: " + Arrays.toString(legalMove));
                System.out.println("CLONED IN MINIMAX");



                double boardValue = minimax(secondClone, depth+1, true, maxDepth, alpha, beta);
                //System.out.println("BOARD VALUE OF " + "IN MINIMAX: IS MAXIMIZING " + isMaximizing + " MOVE: " + Arrays.toString(legalMove) + " IS " + boardValue +"\n");
                bestVal = Math.min(bestVal, boardValue);
                beta = Math.min(beta, bestVal);
                if (beta <= alpha) {
                    //System.out.println("BROKE HERE");
                    break;
                }
            }
            return bestVal;
        }

                 /*
    functioÍn minimax(node, depth, isMaximizingPlayer, alpha, beta):

    if node is a leaf node :
        return value of the node

    if isMaximizingPlayer :
        bestVal = -INFINITY
        for each child node :
            value = minimax(node, depth+1, false, alpha, beta)
            bestVal = max( bestVal, value)
            alpha = max( alpha, bestVal)
            if beta <= alpha:
                break
        return bestVal

    else :
        bestVal = +INFINITY
        for each child node :
            value = minimax(node, depth+1, true, alpha, beta)
            bestVal = min( bestVal, value)
            beta = min( beta, bestVal)
            if beta <= alpha:
                break
        return bestVal

// Calling the function for the first time.
minimax(0, 0, true, -INFINITY, +INFINITY)
     */
        /*
        int bestVal;

        int value = getValueOfBoard(board);

        if (value == 100 || value == -100) {
            System.out.println("GAME ENDED CHECKMATE");
            return value;
        }

        if (board.isStalemate()) {
            System.out.println("GAME ENDED STALEMATE");
            return 0;
        }

        Board clone = board.cloneBoard(board);
        ArrayList<int[]> legalMoves = clone.getLegalMoves(clone);



        if (isMaximizing) {

            System.out.println("IS MAXIMIZING");

            bestVal = -1000;
            for (int[] move : legalMoves) {
                Board secondClone = clone.cloneBoard(clone);
                Tile selectedTile = secondClone.getTile(move[0], move[1]);
                secondClone.playMove(secondClone, selectedTile, move[2], move[3]);
                if (depth >= maxDepth) {
                    int y = getValueOfBoard(secondClone);
                    System.out.println("Maximizing VALUE: " + y + " " + Arrays.toString(move) + " DEPTH");
                    return y;
                } else {
                    bestVal = Math.max(bestVal, minimax(secondClone, depth + 1, false, maxDepth));
                }

            }
        } else {

            System.out.println("IS MINIMIZING");

            bestVal = 1000;

            for (int[] move : legalMoves) {
                int currValue = 1000;

                Board secondClone = clone.cloneBoard(clone);
                Tile selectedTile = secondClone.getTile(move[0], move[1]);

                secondClone.playMove(secondClone, selectedTile, move[2], move[3]);

                if (depth >= maxDepth) {
                    int y = getValueOfBoard(secondClone);
                    System.out.println("Minimizing VALUE: " + y + " " + Arrays.toString(move) + " DEPTH");
                    return y;
                } else {
                    currValue = Math.min(currValue, minimax(secondClone, depth + 1, true, maxDepth));
                    bestVal = Math.min(bestVal, minimax(secondClone, depth + 1, true, maxDepth));

                }
            }


        }




        return bestVal;

         */
    }



    public double getValueOfBoard(Board board) throws IOException {

        if (board.didWhiteWin()) {
            return 10000;
        }

        else if (board.didBlackWin()) {
            return -10000;
        }

        else if (board.isStalemate()) {
            return 0;
        }

        else {
            //.out.println("WHITE: " + board.getWhiteValue() + " BLACK: " + board.getBlackValue());
            return board.getWhiteValue() - board.getBlackValue();
        }
    }



}
