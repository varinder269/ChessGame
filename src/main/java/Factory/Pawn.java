package Factory;

//import javax.swing.text.Position;

import Model.Board;
import Model.Piece;
import Model.Position;
import enums.Color;

import java.util.ArrayList;
import java.util.List;

import static enums.PieceType.PAWN;

public class Pawn extends Piece {

    public  Pawn(Color color) {
        super(color, PAWN, false);
    }

    @Override
    public List<Position> getMoves(Position currPos, Board b) {
        List<Position> moves = new ArrayList<>();
        int direction = 0;
        boolean dir = (this.getColor() == Color.WHITE) ? false : true;
        if (dir) {
            direction = 1;
        } else {
            direction = -1;
        }

        Position oneStep = new Position(currPos.getRow() + direction, currPos.getCol());
        if (oneStep.isValid() && !b.isSameColorPiece(this.getColor(), oneStep)) {
            moves.add(oneStep);

            if (!this.hasMoved) {
                Position position2 = new Position(currPos.getRow() + (direction * 2), currPos.getCol());
                if (position2.isValid() && !b.isSameColorPiece(this.getColor(), position2)) {
                    moves.add(position2);
                }
            }
        }

        Position leftCap = new Position(currPos.getRow() + direction, currPos.getCol() - 1);
        Position rightCap = new Position(currPos.getRow() + direction, currPos.getCol() + 1);


        if (leftCap.isValid() && b.isPiece(leftCap) && !b.isSameColorPiece(this.getColor(), leftCap)) {
            moves.add(leftCap);
        }

        if ( rightCap.isValid() && b.isPiece(rightCap) && !b.isSameColorPiece(this.getColor(), rightCap)) {
            moves.add(rightCap);
        }

        return moves;

    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
