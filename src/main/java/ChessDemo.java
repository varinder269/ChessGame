import ConcreteClasses.Match;
import ConcreteClasses.Message;
import ConcreteClasses.User;
import Model.Position;
import enums.GameStatus;

public class ChessDemo {
    public  static  void demo() {
        User aditya = new User("1", "aditya", 0);
        User suraj = new User("2", "suraj", 0);

        Match demoMatch = new Match("1", aditya, suraj);
        aditya.setChatMediator(demoMatch);
        suraj.setChatMediator(demoMatch);

        demoMatch.getB().display();

        System.out.println("m1: white e2-e4");
        demoMatch.makeMove(new Position(6,4) , new Position(4,4),  aditya );

//        demoMatch.getB().display();

        System.out.println("m1: black e7-e5");
        demoMatch.makeMove(new Position(1,4) , new Position(3,4),  suraj );

//        demoMatch.getB().display();


        System.out.println("m2: white Bf1-c4 (targeting f7)");
        demoMatch.makeMove(new Position(7,5) , new Position(4,2),  aditya );

//        demoMatch.getB().display();


        System.out.println("m2:black Nb8-c6 (developing)");
        demoMatch.makeMove(new Position(0,1) , new Position(2,2),  suraj );

//        demoMatch.getB().display();

        System.out.println("m3: white Qd1-h5 (attacking f7 and h7)");
        demoMatch.makeMove(new Position(7,3) , new Position(3,7),  aditya );

//        demoMatch.getB().display();

        System.out.println("m3:black Ng8-f6 ?? (defending h7 but expsoing f7)");
        demoMatch.makeMove(new Position(0,6) , new Position(2,5),  suraj );

//        demoMatch.getB().display();

        System.out.println("m4: white Qx5-f7 (checkmate)");
        boolean endGame = demoMatch.makeMove(new Position(3,7) , new Position(1,5),  aditya );

//        demoMatch.getB().display();

        if (demoMatch.getGameStatus()== GameStatus.COMPLETED){
            System.out.println("checkmate detected:");
        }



        System.out.println("testing chat");

        aditya.sendMsg(new Message(aditya.getId(), "good game !!"));
        suraj.sendMsg(new Message(suraj.getId(), "thanks game  was good !!"));


    }


}
