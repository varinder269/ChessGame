package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Enum.Color;

import static Enum.PieceType.KING;

public class Board {
    private Piece[][] board = new Piece[8][8];
    Map<Position,Piece> positionPiecemap = new HashMap<>();

    public Board(){
        for (int i=0; i <8; i++){
            for (int j =0; j <8 ;j++){
                board[i][j] = null;
            }
        }
        initialize();
    }
    private void placePieces(Position position, Piece piece){
        board[position.getRow()][position.getCol()] = piece;
        positionPiecemap.put(position,piece);
    }

    private void initialize(){
        placePieces(new Position(7,0),new Rook(Color.WHITE));
        placePieces(new Position(7,1),new Knight(Color.WHITE));
        placePieces(new Position(7,2),new Bishop(Color.WHITE));
        placePieces(new Position(7,3),new Queen(Color.WHITE));
        placePieces(new Position(7,4),new King(Color.WHITE));
        placePieces(new Position(7,5),new Bishop(Color.WHITE));
        placePieces(new Position(7,6),new Knight(Color.WHITE));
        placePieces(new Position(7,7),new Rook(Color.WHITE));

        placePieces(new Position(0,0),new Rook(Color.BLACK));
        placePieces(new Position(0,1),new Knight(Color.BLACK));
        placePieces(new Position(0,2),new Bishop(Color.BLACK));
        placePieces(new Position(0,3),new Queen(Color.BLACK));
        placePieces(new Position(0,4),new King(Color.BLACK));
        placePieces(new Position(0,5),new Bishop(Color.BLACK));
        placePieces(new Position(0,6),new Knight(Color.BLACK));
        placePieces(new Position(0,7),new Rook(Color.BLACK));

        for (int i=0; i <8; i++){
            placePieces(new Position(6,i),new Rook(Color.WHITE));
        }
        for (int i=0; i <8; i++){
            placePieces(new Position(1,i),new Rook(Color.BLACK));
        }
    }

    public void removePiece(Position position){
        board[position.row][position.col] = null;
        positionPiecemap.remove(position);
    }
    public Piece getPiece(Position position){
        return board[position.row][position.col];
    }
    public boolean isPiece(Position position){
        return board[position.row][position.col] != null;
    }
    public boolean isSameColorPiece(Color color,Position position){
        if (getPiece(position)!=null){
            return getPiece(position).getColor()==color;
        }
        return false;
    }
    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        Piece piece1 = getPiece(to);
        if (piece != null) {
            if (piece1 != null) {
                positionPiecemap.remove(piece1);
            }
            board[to.row][to.col] = piece;
            board[from.row][from.col] = null;
            positionPiecemap.put(to, piece);
            positionPiecemap.remove(from);
            piece.hasMoved = true;
        }
    }

    public Position findKing(Color color) {

        for (Map.Entry<Position, Piece> entry: positionPiecemap.entrySet()){
            Piece p = entry.getValue();
            if (p.getColor()==color && p.getPieceType()==KING){
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Position> getAllPiecesColor(Color color){
        List<Position> ans = new ArrayList<>();
        for (Map.Entry<Position,Piece> entry: positionPiecemap.entrySet()){
            if(entry.getValue().getColor()==color){
                ans.add(entry.getKey());
            }
        }
        return  ans;
    }

    public void display() {
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println("  +---+---+---+---+---+---+---+---+");

        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " |");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece == null) {
                    System.out.print("   |");
                } else {
                    System.out.print(" " + piece.toString() + " |");
                }
            }
            System.out.println(" " + (8 - row));
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
    }

}
