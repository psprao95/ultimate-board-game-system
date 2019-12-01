package main.java.model.board;

import main.java.model.move.IBoardLambada;
import main.java.model.move.IBoardStatusVisitor;

public class NonTerminalState implements IBoardState
{
    public static NonTerminalState Singleton;
    
    protected NonTerminalState() {
    }
    
    public void map(final int n, final IBoardLambada boardLambda, final Object o, final IBoardModel boardModel) {
        boolean b = true;
        final int[][] cells = boardModel.getCells();
        for (int i = 0; i < cells.length; ++i) {
            for (int j = 0; j < cells[i].length; ++j) {
                if (boardModel.isValidMove(n, i, j)) {
                    b = false;
                    if (!boardLambda.apply(n, boardModel, o, i, j, cells[i][j])) {
                        return;
                    }
                }
            }
        }
        if (b) {
            boardLambda.noApply(n, boardModel, o);
        }
    }
    
    public Object execute(final IBoardStatusVisitor boardStatusVisitor, final Object o, final IBoardModel boardModel) {
        return boardStatusVisitor.noWinnerCase(boardModel, o);
    }
    
    static {
        NonTerminalState.Singleton = new NonTerminalState();
    }
}
