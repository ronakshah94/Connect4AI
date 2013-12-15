package models.ai;

import java.util.List;
import java.util.ArrayList;

public class AlphaBetaMinimax {
    Double value = null;
    Object move = null;
    public Object getMove() {
        return this.move;
    }

    public AlphaBetaMinimax(Evaluator eval, Evaluatable e, int depth, double alpha, double beta, boolean isMax) {
        if (depth == 0 || e.possibleMoves().size() == 0) {
            this.value = eval.evaluate(e);
            // System.out.println("Value: "+this.value);
            // System.out.println("Evaluating leaf...");
            // System.out.println("Value: "+this.value + " Depth: "+depth+" Move:"+ this.move + "\n" + e.toString()+"\n\n");
        } else {
            if (isMax) {
                double minOrMax = alpha;
                Object bestMove = null;
                for (Object m : e.possibleMoves()) {
                    // System.out.println("Calling child from maxnode with move " +m+"...");
                    AlphaBetaMinimax child = new AlphaBetaMinimax(eval, e.playMoveImmutable(m), depth - 1, minOrMax, beta, false);
                    // System.out.println("Leaf value received from maxnode: "+child.value+" minOrmax: "+minOrMax);
                    if (child.value > minOrMax) {

                        minOrMax = child.value;
                        bestMove = m;
                        // System.out.println("New maxnode best move is now: "+bestMove);
                    }
                    // System.out.println("Child val: "+child.value+" minormax"+minOrMax+" move:"+m+" bestmove: "+bestMove);
                    if (beta <= minOrMax) {
                        break;
                    }
                }

                this.value = minOrMax;
                this.move = bestMove;
                // System.out.println("Value: "+this.value  ng());
            } else {
                double minOrMax = beta;
                Object bestMove = null;
                for (Object m : e.possibleMoves()) {
                    // System.out.println("Calling child from minnode...");
                    AlphaBetaMinimax child = new AlphaBetaMinimax(eval, e.playMoveImmutable(m), depth - 1, alpha, minOrMax, true);
                    // System.out.println("Leaf value received from minnode: "+child.value+" minOrmax: "+minOrMax);
                    if (child.value < minOrMax) {
                        minOrMax = child.value;
                        bestMove = m;
                        // System.out.println("New minnode best move is now: "+bestMove);
                    }
                    if (minOrMax <= alpha) {
                        break;
                    }
                }
                this.value = minOrMax;
                this.move = bestMove;
            }

        }
    }
}
