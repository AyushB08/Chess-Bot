package bot.chessbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Engine {

    Board board = BoardViewer.board;

    public void playRandomMove(Board board) throws IOException {
        ArrayList<int[]> moves = board.getLegalMoves(board);
        int n = (int) (Math.random() * moves.size());
        int[] move = moves.get(n);
        Tile selectedTile = board.getTile(move[0], move[1]);
        int row = move[2];
        int col = move[3];

        board.playMove(board, selectedTile, row, col);


    }

    public void playBestMove(Board board) throws IOException {
        int[] move = findBestMove(board);
        Tile tile = board.getTile(move[0], move[1]);
        board.playMove(board, tile, move[2], move[3]);
    }


    public int[] findBestMove(Board board) throws IOException {

        int bestVal = Integer.MAX_VALUE;


        ArrayList<int[]> legalMoves = board.getLegalMoves(board);
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        for (int[] move : legalMoves) {

            Board clone = board.cloneBoard(board);

            Tile selectedTile = clone.getTile(move[0], move[1]);
            clone.playMove(clone, selectedTile, move[2], move[3]);

            int value = minimax(clone, 0, false, 5, Integer.MAX_VALUE, Integer.MIN_VALUE);
            System.out.println("\nMOVE: " + Arrays.toString(move) + " VALUE: " + value +"\n");

            if (value < bestVal) {

                bestVal = value;
                possibleMoves = new ArrayList<>();
                possibleMoves.add(move);

            } else if (value == bestVal) {
                possibleMoves.add(move);
            }
        }

        return possibleMoves.get((int)((Math.random() * possibleMoves.size())));

    }


    public int minimax(Board board, int depth, boolean isMaximizing, int maxDepth, int alpha, int beta) throws IOException {
        System.out.println("IS MAXIMIZING: " + isMaximizing + " DEPTH: " + depth);
        int value = getValueOfBoard(board);

        if (value == 100 || value == -100) {
            System.out.println("GAME ENDED CHECKMATE");
            return value;
        }
        if (board.isStalemate()) {
            System.out.println("GAME ENDED STALEMATE");
            return value;
        }

        if (depth >= maxDepth) {
            return value;
        }

        Board clone = board.cloneBoard(board);

        int bestVal = 0;

        ArrayList<int[]> legalMoves = clone.getLegalMoves(clone);

        if (isMaximizing) {
            bestVal = Integer.MIN_VALUE;
            for (int[] legalMove : legalMoves) {

                Board secondClone = clone.cloneBoard(clone);
                Tile selectedTile = secondClone.getTile(legalMove[0], legalMove[1]);
                secondClone.playMove(secondClone, selectedTile, legalMove[2], legalMove[3]);
                int boardValue = minimax(secondClone, depth+1, false, maxDepth, alpha, beta);
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
            for (int[] legalMove : legalMoves) {

                Board secondClone = clone.cloneBoard(clone);
                Tile selectedTile = secondClone.getTile(legalMove[0], legalMove[1]);
                secondClone.playMove(secondClone, selectedTile, legalMove[2], legalMove[3]);
                int boardValue = minimax(secondClone, depth+1, true, maxDepth, alpha, beta);
                bestVal = Math.min(bestVal, boardValue);
                beta = Math.min(beta, bestVal);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestVal;
        }

                 /*
    function minimax(node, depth, isMaximizingPlayer, alpha, beta):

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



    public int getValueOfBoard(Board board) throws IOException {

        if (board.didWhiteWin()) {
            return 100;
        }

        else if (board.didBlackWin()) {
            return -100;
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
