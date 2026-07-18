package MediatorClass;

import ConcreteClasses.Message;
import ConcreteClasses.User;

public interface ChatMediator {

    public void sendMessage(Message message, User user);
    public void addUser( User user);
    public void removeUser(User user);

}
