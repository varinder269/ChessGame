package Model;

//import javax.swing.text.Position;
import Enum.Color;
import Model.Position;
import java.util.ArrayList;
import java.util.List;

import static Enum.PieceType.KING;

public class King extends Piece {

    King(Color color){
        super(color, KING, false);
    }

    @Override
    public List<Position> getMoves(Position currPos, Board b) {

        List<Position> moves = new ArrayList<>();

        int dir[][] = {  {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},  {0, 1},{1, -1},  {1, 0},{1, 1}};

        for (int i =0; i <8; i++){

            Position position = new Model.Position(currPos.getRow()+ dir[i][0] , currPos.getRow()+ dir[i][1]);
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
