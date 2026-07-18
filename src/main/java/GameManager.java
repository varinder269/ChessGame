import ConcreteClasses.Match;
import ConcreteClasses.Message;
import ConcreteClasses.User;
import MatchingStrategy.MatchingStrategy;
import MatchingStrategy.ScoreBasedMatching;
import Model.Position;
import enums.GameStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    Map<String, Match> matchMap;

    @Getter
    static GameManager instance = new GameManager();
    List<User> waitingUsers;
    MatchingStrategy matchingStrategy;
    int matchCount;

    public GameManager(){
        matchMap = new HashMap<>();
        waitingUsers = new ArrayList<>();
        matchingStrategy = new ScoreBasedMatching();
        matchCount = 0;
    }

    public void requestMatch(User user){

        User opponent = matchingStrategy.findMatch(user, waitingUsers);

        if (opponent!=null){

            waitingUsers.remove(opponent);
            String matchId = "MATCH_"+matchCount++;
            Match match = new Match(matchId, user,opponent);
            matchMap.put(matchId, match);

            System.out.println("MATCH FOUND: " +user.getName() + " VS "+ opponent.getName());

            match.getB().display();
        }
        else {
            waitingUsers.add(user);
            System.out.println("NO MATCH FOUND");
        }
    }

    public void makeMove(String matchId, Position from , Position to, User user){

        matchMap.get(matchId).makeMove(from ,to, user);

        if (matchMap.get(matchId).getGameStatus()== GameStatus.COMPLETED){
            matchMap.remove(matchId);
            System.out.println("MATCH COMPELTED");
        }
    }

    public void quitGame(String matchId, User user){
        matchMap.get(matchId).quitGame(user);
    }

    public void sendMessage(String matchId, User user, String msg) {
        matchMap.get(matchId).sendMessage(new Message("0", msg ) , user);
    }

    public Match getMatch(String matchId){
        return matchMap.get(matchId);
    }

    public void displayMatches(){
        System.out.println("ACTIVE MATCHES____");

        for (Map.Entry<String ,Match> entry: matchMap.entrySet()){

            System.out.print("Match id "+entry.getKey());
            System.out.print("Player 1 " + entry.getValue().getBlackPlayer().getName());
            System.out.println("Player 2 " + entry.getValue().getWhitePlayer().getName());

        }
        System.out.println("total active matches "+ matchMap.size());
        System.out.println("users waiting: "+waitingUsers.size());
    }


}
