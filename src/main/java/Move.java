import Model.Piece;
import Model.Position;

public class Move {
    private Position from;
    private Position to;

    private Piece piece;
    private Piece captured;

    public Move(){
        from = null;
        to = null;
        piece = null;
        captured =null;
    }

    public Move(Position from, Position to, Piece piece, Piece captured) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.captured = captured;
    }
}
