package MediatorClass;

import ConcreteClasses.Message;

public interface Colleague {


    public void sendMsg(Message message);
    public void recieve (Message message);
}
