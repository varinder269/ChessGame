package Rules;

import enums.Color;
import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Position;

import java.util.List;

public class StandardRules implements ChessRules {

    public boolean isValidMove(Move m, Board b) {

        Piece piece = m.getPiece();
        List<Position> positionList = piece.getMoves(m.getFrom(), b);

        boolean isPossible = false;
        for (Position p : positionList) {
            if (p.equals(m.getTo())) {
                isPossible = true;
                break;
            }
        }

        if (!isPossible) {
            return false;
        }

        return !wouldCauseCheck(m, piece.getColor(), b);

    }

    public boolean isInCheck(Color c, Board b) {
        Position position = b.findKing(c);
        if (position.getRow() == -1) return false;
        Color opponentColor = c == Color.WHITE ? Color.BLACK : Color.WHITE;

        List<Position> positionList = b.getAllPiecesColor(opponentColor);

        for (Position p : positionList) {
            List<Position> positionList1 = b.getPiece(p).getMoves(p, b);
            for (Position p2 : positionList1) {
                if (p2.equals(b.findKing(c))) {
                    return true;
                }
            }
        }


        return false;
    }

    public boolean isCheckMate(Color c, Board b) {

        if (!isInCheck(c, b)) return false;
        List<Position> positionList = b.getAllPiecesColor(c);

        for (Position p : positionList) {
            List<Position> positionList1 = b.getPiece(p).getMoves(p, b);
            for (Position target : positionList1) {
                Move move = new Move(p, target, b.getPiece(p), b.getPiece(target));
                if (isValidMove(move, b)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStaleMate(Color c, Board b) {

        if (isInCheck(c, b)) return false;
        List<Position> positionList = b.getAllPiecesColor(c);

        for (Position p : positionList) {
            List<Position> positionList1 = b.getPiece(p).getMoves(p, b);
            for (Position target : positionList1) {
                Move move = new Move(p, target, b.getPiece(p), b.getPiece(target));
                if (isValidMove(move, b)) {
                    return false;
                }
            }
        }

        return true;

    }

    public boolean wouldCauseCheck(Move m, Color c, Board b) {

        Piece piece = m.getPiece();
        Piece captured = m.getCaptured();

        if (piece == null) {
            return true;
        }

        b.removePiece(m.getFrom());

        if (captured != null) {
            b.removePiece(m.getTo());
        }

        b.placePieces(m.getTo(), piece);

        boolean inCheck = isInCheck(c, b);

        b.removePiece(m.getTo());
        b.placePieces(m.getFrom(), piece);
        if (captured != null) b.placePieces(m.getTo(), captured);

        return inCheck;

    }
}
