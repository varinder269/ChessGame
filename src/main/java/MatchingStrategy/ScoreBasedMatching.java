package MatchingStrategy;

import ConcreteClasses.User;

import java.util.List;

import static java.lang.Math.abs;

public class ScoreBasedMatching implements MatchingStrategy{
    @Override
    public User findMatch(User user, List<User> users) {
        
        User opponent = null;
        int diff = Integer.MAX_VALUE;
        
        for (int i = 0 ; i <users.size(); i++){
            
            User user1 = users.get(i);
            
            if ( user1!= user && abs(user1.getScore() - user.getScore()) < diff){
                opponent = user1;
                diff = abs(user1.getScore() - user.getScore());
            }
        }
        return opponent;
    }
}
