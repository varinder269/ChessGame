# ChessGame

A fully functional Chess Game implementation in Java, demonstrating Low-Level Design (LLD) principles and Object-Oriented Design Patterns. This project showcases clean architecture, scalable design, and complete chess game logic.

## Features

- Complete chess game with all standard rules (check, checkmate, stalemate)
- All chess pieces with proper movement logic (King, Queen, Rook, Bishop, Knight, Pawn)
- Board visualization with standard chess notation
- Player matching system with score-based strategy
- In-game chat system using Mediator pattern
- Game management for handling multiple concurrent matches
- Move validation and game state management

## Design Patterns Implemented

### 1. Mediator Pattern
Used for chat communication between players. The `ChatMediator` interface and `Match` class implement this pattern to facilitate message exchange without direct coupling between players.

### 2. Strategy Pattern
Implemented for player matching. The `MatchingStrategy` interface allows different matching algorithms (e.g., `ScoreBasedMatching`) to be plugged in based on requirements.

### 3. Factory Pattern
Used for creating chess pieces. The piece creation logic is abstracted to allow easy extension and maintenance.

## Tech Stack

- **Java 17** - Core language with modern features
- **Maven** - Dependency management and build tool
- **Lombok** - Reducing boilerplate code

## Project Structure

```
ChessGame/
├── src/main/java/
│   ├── Model/                    # Chess game core models
│   │   ├── Board.java           # Chess board representation
│   │   ├── Piece.java           # Abstract base class for pieces
│   │   ├── King.java            # King piece implementation
│   │   ├── Queen.java           # Queen piece implementation
│   │   ├── Rook.java            # Rook piece implementation
│   │   ├── Bishop.java          # Bishop piece implementation
│   │   ├── Knight.java          # Knight piece implementation
│   │   ├── Pawn.java            # Pawn piece implementation
│   │   ├── Position.java        # Board position representation
│   │   └── Move.java            # Move representation
│   ├── Rules/                    # Chess rules engine
│   │   ├── ChessRules.java      # Rules interface
│   │   └── StandardRules.java   # Standard chess rules implementation
│   ├── ConcreteClasses/          # Core application classes
│   │   ├── User.java            # Player representation
│   │   ├── Match.java           # Game match implementation
│   │   └── Message.java         # Chat message representation
│   ├── MediatorClass/            # Chat mediator pattern
│   │   ├── ChatMediator.java    # Mediator interface
│   │   └── Colleague.java       # Colleague interface
│   ├── MatchingStrategy/         # Player matching strategies
│   │   ├── MatchingStrategy.java # Strategy interface
│   │   └── ScoreBasedMatching.java # Score-based implementation
│   ├── enums/                    # Enumeration types
│   │   ├── Color.java           # Player colors (WHITE, BLACK)
│   │   ├── GameStatus.java      # Game states
│   │   └── PieceType.java       # Piece types
│   ├── ChessDemo.java           # Demo showcasing game features
│   ├── GameManager.java         # Manages multiple matches
│   └── main.java                # Main entry point
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

## How to Build and Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build
```bash
mvn clean compile
```

### Run
```bash
# Run the main demo
mvn exec:java -Dexec.mainClass="main"

# Or run the chess demo specifically
mvn exec:java -Dexec.mainClass="ChessDemo"
```

### Using IDE
Import the project as a Maven project in your preferred IDE (IntelliJ IDEA, Eclipse, VS Code) and run the `main.java` or `ChessDemo.java` classes directly.

## Usage Example

### Basic Game Play
```java
// Create players
User player1 = new User("1", "Alice", 0);
User player2 = new User("2", "Bob", 0);

// Create a match
Match game = new Match("match1", player1, player2);
player1.setChatMediator(game);
player2.setChatMediator(game);

// Display initial board
game.getB().display();

// Make moves
game.makeMove(new Position(6, 4), new Position(4, 4), player1); // e2-e4
game.makeMove(new Position(1, 4), new Position(3, 4), player2); // e7-e5

// Check game status
if (game.getGameStatus() == GameStatus.COMPLETED) {
    System.out.println("Game over!");
}
```

### Player Matching
```java
GameManager gm = new GameManager();

User player1 = new User("1", "Alice", 1200);
User player2 = new User("2", "Bob", 1150);
User player3 = new User("3", "Charlie", 1300);

gm.requestMatch(player1);
gm.requestMatch(player2);
gm.requestMatch(player3);

gm.displayMatches();
```

### Chat System
```java
// Players can send messages through the match mediator
player1.sendMsg(new Message(player1.getId(), "Good game!"));
player2.sendMsg(new Message(player2.getId(), "Well played!"));
```

## Chess Rules Implemented

- **Piece Movement**: Each piece follows standard chess movement rules
- **Check Detection**: Identifies when a king is under threat
- **Checkmate Detection**: Determines when a player has no legal moves to escape check
- **Stalemate Detection**: Identifies when a player has no legal moves but is not in check
- **Move Validation**: Ensures all moves are legal according to chess rules
- **Turn Management**: Enforces alternating turns between white and black

## Sample Output

```
    a    b    c    d    e    f    g    h
 +----+----+----+----+----+-----+----+---+
8 | BR | BN | BB | BQ | BK | BB | BN | BR | 8
 +----+----+----+----+----+-----+----+---+
7 | BP | BP | BP | BP | BP | BP | BP | BP | 7
 +----+----+----+----+----+-----+----+---+
6 |    |    |    |    |    |    |    |    | 6
 +----+----+----+----+----+-----+----+---+
5 |    |    |    |    |    |    |    |    | 5
 +----+----+----+----+----+-----+----+---+
4 |    |    |    |    |    |    |    |    | 4
 +----+----+----+----+----+-----+----+---+
3 |    |    |    |    |    |    |    |    | 3
 +----+----+----+----+----+-----+----+---+
2 | WP | WP | WP | WP | WP | WP | WP | WP | 2
 +----+----+----+----+----+-----+----+---+
1 | WR | WN | WB | WQ | WK | WB | WN | WR | 1
 +----+----+----+----+----+-----+----+---+
    a    b    c    d    e    f    g    h
```

## Learning Outcomes

This project demonstrates:
- Application of SOLID principles in real-world scenarios
- Implementation of design patterns for scalable architecture
- Object-oriented design and encapsulation
- Game state management and rule validation
- Multi-user system design
- Clean code practices and maintainability

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

- **Varinder** - [GitHub Profile](https://github.com/varinder269)

## Acknowledgments

- Chess rules and notation based on standard FIDE regulations
- Design patterns inspired by "Design Patterns: Elements of Reusable Object-Oriented Software" (Gang of Four)

---

**Note**: This is a console-based implementation focused on demonstrating LLD principles. A GUI version could be developed as an extension.