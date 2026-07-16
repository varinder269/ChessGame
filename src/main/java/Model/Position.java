package Model;

import java.util.Objects;

public class Position {
    int row;
    int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean isValid(){
        if (row>=0 && col>=0 && row<8 && col<8){
            return true;
        }
        return false;
    }


    public boolean equals(Object o) {

        if (o == null ) return false;
        Position curr = (Position) o;
        return row == curr.row && col == curr.col;
    }
    public boolean lessThan(Position o) {

        if (o == null ) return false;

        return row != o.row ? row<o.row : col < o.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public String chessNotation(){
        Character file = (char) (col+'a');
        Character rank = (char) (8-row);
        return String.valueOf(rank+file);
    }
}
