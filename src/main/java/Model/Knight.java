package Model;

//import javax.swing.text.Position;

import static Enum.PieceType.KNIGHT;
import Enum.Color;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    Knight(Color color){
        super(color, KNIGHT, false);
    }

    @Override
    public List<Position> getMoves(Position currPos, Board b) {
        List<Position> moves = new ArrayList<>();

        int dir[][] = {  {-1, -2}, {-1, 2}, {1, 2 }, {1, -2},  {2, 1},{2, -1},  {-2, -1},{-2, 1}};

        for (int i =0; i <8; i++){

            Position position = new Model.Position(currPos.getRow() + dir[i][0], currPos.getRow() +dir[i][1]);
            if (position.isValid() && !b.isSameColorPiece(this.getColor() , position)) {
                moves.add(position);
            }

        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
