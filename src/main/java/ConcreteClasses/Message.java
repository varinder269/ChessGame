package ConcreteClasses;

import lombok.Data;

import java.sql.Time;

@Data
public class Message {

    private String senderId;
    private String content;
    private Time timestamp;

    public Message(String sId, String msg, Time timestamp){
        this.senderId = sId;
        this.content = msg;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ConcreteClasses.Message{" +
                "senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
