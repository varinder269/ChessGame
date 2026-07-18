package Model;

//import javax.swing.text.Position;

import static enums.PieceType.BISHOP;
import enums.Color;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    Bishop(Color color){
        super(color, BISHOP, false);
    }

    @Override
    public List<Position> getMoves(Position currPos, Board b) {
        List<Position> moves = new ArrayList<>();

        int dir[][] = {{-1, -1}, {-1, 1},{1, -1},{1, 1}};

        for (int i =0; i <4; i++){

            for (int j =1; j <8; j++) {
                Position position = new Model.Position(currPos.getRow() + (dir[i][0]*j), currPos.getRow() + (dir[i][1]*j));
                if (position.isValid()== false) {
                    break;
                }
                if (!b.isSameColorPiece(this.getColor(), position)){
                    break;
                }
                moves.add(position);
                if (b.isPiece(position)) break;
            }
        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
