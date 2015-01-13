package base;

import java.util.*;

/**
 * This class contains the methods for a board game
 * Like a chess board it has two dimensions, width and depth, which contain pieces
 * Pieces contain a color and shape attribute
 * Pieces are automatically randomly placed on the board
 * The user wins if the number of pieces on the board is equal to or exceeds winCount
 *
 * Created by wireed on 9/25/2014.
 */
public class Game {
    private boolean debug = true;
    private Random rnd = new Random();

    Game(boolean dbug) {
        debug = dbug;
    }

    private enum Color {
        red,
        green,
        blue,
        yellow
    }

    private enum Shape {
        square,
        circle,
        triangle,
        star
    }

    private class Piece {
        Color color;
        Shape shape;
    }

    private List<Piece> board;
    private int depth;
    private int width;

    /**
     * This method produces random pieces to populate the board
     *
     * @param boardDepth
     * @param boardWidth
     */
    public void initializeBoard(int boardDepth, int boardWidth) {
        board = new ArrayList<Piece>();
        depth = boardDepth;
        width = boardWidth;
        if (debug)
            System.out.print("     add: ");
        for (int w = 0; w < depth; w++)
            for (int d = 0; d < width; d++) {
                Piece p = new Piece();
                p.color = Color.values()[rnd.nextInt(4)];
                p.shape = Shape.values()[rnd.nextInt(4)];
                board.add(p);
                if (debug)
                    System.out.print(p.color.toString() + p.shape.toString() + ",  ");
            }
        if (debug)
            System.out.println(" ");
    }

    /**
     * This method inspects the pieces on the board, to determine if there are winCount number of identical pieces
     *
     * @param winCount
     * @return
     */
    public boolean isWinner(int winCount) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        Iterator itr = board.iterator();
        if (debug)
            System.out.print("     anl: ");
        while (itr.hasNext()) {
            Piece p = (Piece) itr.next();
            String k = p.color.toString() + p.shape.toString();
            if (hm.containsKey(k)) {
                int count = ((int) hm.get(k)) + 1;
                if (debug)
                    System.out.print(k + count + ", ");
                if (count == winCount)
                    return true;
                else
                    hm.put(k, count);
            } else {
                hm.put(k, 1);
                if (debug)
                    System.out.print(k + "1, ");
            }
        }
        if (debug)
            System.out.println(" ");
        return false;
    }

    /**
     * This method loops until the game has a winner
     * each loop initializes the board, and determines if the number of identical pieces produces a winner
     */
    public void testGame() {
        if (debug)
            System.out.println("Board Game:");
        boolean winnerFound = false;

        while (!winnerFound) {
            initializeBoard(4, 4);
            winnerFound = isWinner(3);
        }
        if (debug) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}