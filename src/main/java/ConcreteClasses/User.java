package ConcreteClasses;

import MediatorClass.ChatMediator;
import MediatorClass.Colleague;
import lombok.Data;

@Data
public class User implements Colleague {

    private ChatMediator chatMediator;
    String id;
    String name ;
    Integer score;
    public User(String id , String name, Integer score){
        this.chatMediator = null;
        this.score = score;
        this.id = id;
        this.name = name;
    }

    public void incrementScore (Integer sc){
        this.score -= sc;
    }

    public void decrementScore (Integer sc){
        this.score+=sc;
    }


    public void setChatMediator(ChatMediator mediator){
        this.chatMediator = mediator;
    }

    @Override
    public void sendMsg(Message message) {
        if (this.chatMediator!=null){
            chatMediator.sendMessage(message, this);
        }
    }

    @Override
    public void recieve(Message message) {
        System.out.print("USer: "+ name+" recieved a message: "+ message.getContent());
    }

    @Override
    public String toString() {
        return "ConcreteClasses.User{" +
                "chatMediator=" + chatMediator +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
