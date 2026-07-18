package Factory;

import Model.*;
import enums.Color;
import enums.PieceType;

public class PieceFactory {

    public Piece createPiece(PieceType type, Color color) {

        switch (type) {
            case KING:
                return new King(color);
            case QUEEN:
                return new Queen(color);
            case ROOK:
                return new Rook(color);
            case BISHOP:
                return new Bishop(color);
            case KNIGHT:
                return new Knight(color);
            case PAWN:
                return new Pawn(color);
            default:
                throw new IllegalArgumentException("unknown piece");

        }
    }
}
