package MatchingStrategy;

import ConcreteClasses.User;

import java.util.List;

public interface MatchingStrategy {
    User findMatch(User user , List<User> Users);
}
