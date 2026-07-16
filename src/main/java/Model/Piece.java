package Model;
import Enum.PieceType;
import Enum.Color;

import java.util.List;

import static Enum.Color.WHITE;

public  abstract  class Piece {

    private Color color;
    private PieceType pieceType;
    boolean hasMoved;


    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Color getColor() {
        return color;
    }

    public void setC(Color c) {
        this.color = c;
    }


    public Piece(Color c, PieceType pieceType, boolean hasMoved) {
        this.color = c;
        this.pieceType = pieceType;
        this.hasMoved = hasMoved;
    }

    public abstract List<Position> getMoves(Position currPos, Board b);
    public abstract String getSymbol();

    @Override
    public String toString(){
        String colorStr = color==WHITE?"W":"B";
        return colorStr+getSymbol();
    }

}
