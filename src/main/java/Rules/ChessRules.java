package Rules;

import Model.Board;
import enums.Color;
import Model.Move;

public interface ChessRules {

    public boolean isValidMove(Move m, Board b);
    public boolean isInCheck(Color c, Board b);

    public boolean isCheckMate(Color c , Board b);

    public boolean isStaleMate(Color c ,Board b);
    public boolean wouldCauseCheck(Move m, Color c , Board b);
}
