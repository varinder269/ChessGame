import ConcreteClasses.User;

public class main {
    public static void main(String[] args) {
        ChessDemo.demo();

        System.out.println("NOWW GAME MANAGER DEMO");

        GameManager gm = new GameManager();

        User sau = new User("12", "sau", 0);
        User manish = new User("13", "manish", 0);
        User hella = new User("14", "hella", 0);

        System.out.println(sau.toString());
        System.out.println(manish.toString());
        System.out.println(hella.toString());

        gm.requestMatch(sau);
        gm.requestMatch(manish);
        gm.requestMatch(hella);

        gm.displayMatches();

    }
}
