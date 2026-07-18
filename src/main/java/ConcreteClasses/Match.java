package ConcreteClasses;

import MediatorClass.ChatMediator;
import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Position;
import Rules.ChessRules;
import Rules.StandardRules;
import enums.Color;
import enums.GameStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static enums.Color.BLACK;
import static enums.Color.WHITE;
import static enums.GameStatus.COMPLETED;
import static enums.GameStatus.IN_PROGRESS;

@Data
public class Match implements ChatMediator {

    String id;
    Color turn;
    Board b;
    ChessRules chessRules;
    GameStatus gameStatus;
    List<Move> moveList;
    List<Message> messages;
    User whitePlayer;
    User blackPlayer;


    List<User> users = new ArrayList<>();

    public Match(String id, User blackPlayer, User whitePlayer) {
        this.id = id;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;


        this.b =  new Board();
        this.chessRules = new StandardRules();
        this.gameStatus = IN_PROGRESS;
        this.moveList = new ArrayList<>();
        this.messages = new ArrayList<>();
        turn = WHITE;

        System.out.println("match started between "+whitePlayer.getName() + " (white) and "+ blackPlayer.getName() +" (black).");
    }



    @Override
    public void sendMessage(Message message, User user) {
        messages.add(message);
        User opponentPlayer = blackPlayer==user ? whitePlayer: blackPlayer;
        opponentPlayer.recieve(message);
        System.out.println("chat in match: "+ message.getContent());
    }

    @Override
    public void addUser(User user) {
//        users.add(user);
        throw new RuntimeException();
    }

    @Override
    public void removeUser(User user) {
        quitGame(user);
    }

    public boolean makeMove(Position from , Position to, User curr){
        if (gameStatus!=IN_PROGRESS){
            System.out.println("not in progress");
            return false;
        }
        if (turn!=getColor(curr)){
            System.out.println("wrong color ");
            return false;
        }

        Piece piece = b.getPiece(from);
        Piece captured = b.getPiece(to);

        if (piece==null || piece.getColor()!=getColor(curr)){
            System.out.println("not piece or wrong color of the piece");
            return false;
        }

        Move move = new Move(from, to,piece, captured);

        if (!chessRules.isValidMove(move, b)){
            System.out.println("invalid move");
            return false;
        }

        // execute move
        moveList.add(move);

        b.movePiece(from, to);

        System.out.println("player: "+curr.getName() +" executed a move. and moved "+piece.getSymbol()+" form "+ from.chessNotation() + " to "+to.chessNotation());
        b.display();
        Color opponentColor =  turn==WHITE? BLACK : WHITE;

        if (chessRules.isCheckMate(opponentColor,b)){
            endGame(curr, "checkmate");
            return true;
        }

        else if (chessRules.isStaleMate(opponentColor,b)){
            endGame(null, "stalemate");
            return true;
        }

        else {
            turn = opponentColor;

            if (chessRules.isInCheck(opponentColor,b)){
                System.out.println(getPlayer(opponentColor).getName()+" is in check.");
            }
        }

        return true;
    }

    Color getColor(User user){
        return user==whitePlayer?WHITE:BLACK;
    }

    User getPlayer(Color color){
        return color==WHITE? whitePlayer: blackPlayer;
    }

    public void quitGame(User user){
        User opponentPlayer = blackPlayer==user ? whitePlayer: blackPlayer;

        endGame(opponentPlayer, "quit");
        user.decrementScore(-20);
        System.out.println("playere: "+user.getName() +" quits.");

    }

    public void endGame(User winner, String reason){
        gameStatus = COMPLETED;

        if (winner!=null){

            User opponentPlayer = blackPlayer==winner ? whitePlayer: blackPlayer;
            winner.incrementScore(30);
            opponentPlayer.decrementScore(20);
            System.out.println("winner is : "+ winner.getName() + " reason: "+ reason);
            System.out.println("new socre: "+ winner.getName() + ": "+winner.getScore());
            System.out.println(opponentPlayer.getName() + ": "+opponentPlayer.getScore());
        }
        else {
            System.out.println("game ended in draw. reason is: "+ reason);
        }
    }


}
