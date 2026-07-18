package ConcreteClasses;

import MediatorClass.ChatMediator;
import MediatorClass.Colleague;
import lombok.Data;
import lombok.Setter;

@Data
public class User implements Colleague {

    @Setter
    private ChatMediator chatMediator;
    String id;
    String name;
    Integer score;

    public User(String id, String name, Integer score) {
        this.chatMediator = null;
        this.score = score;
        this.id = id;
        this.name = name;
    }

    public void incrementScore(Integer sc) {
        this.score -= sc;
    }

    public void decrementScore(Integer sc) {
        this.score += sc;
    }


    @Override
    public void sendMsg(Message message) {
        if (this.chatMediator != null) {
            chatMediator.sendMessage(message, this);
        }
    }

    @Override
    public void recieve(Message message) {
        System.out.print("USer: " + name + " recieved a message: " + message.getContent());
    }

    @Override
    public String toString() {
        return
                " id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", score='" + score + '\''
                ;
    }
}
