# Chess Game Design Patterns Implementation

This project implements a console-based chess game that demonstrates several design patterns. Here's how and why each pattern is used:

![bLLDRoCt3BthLmZkeVOsDfhzw66m27OJeCLYFvecHIz13hA6jGNFI0E9aoPDyz-haSIHhGmsyCb3Fb9yf1xb3mw5nRvjcDiftGahMYuRuTn7HAlMFS8dQVIr-XVuptiX3XyQuCw7o93CEY5tOWDy9hVWt8m_CXxFuHU3upVHGl1nlkHh8snTHU1y-4v-xAsk-3LQfJV9PTOEx1rOL_4l-1](https://github.com/user-attachments/assets/396d03c0-6d54-4c44-b4ef-9cd4f3179fe0)


## Creational Patterns

### Factory Method Pattern

**Where**: `PieceFactory` class

**Why**:

- Encapsulates piece creation logic
- Provides a single point of control for creating different chess pieces
- Makes adding new piece types easier without modifying existing code

### Builder Pattern

**Where**: `ChessBoardBuilder` class

**Why**:

- Simplifies the complex process of creating a chess board
- Allows step-by-step construction of the board
- Makes it easy to create different board configurations
- Separates the construction of a chess board from its representation

## Structural Patterns

### Adapter Pattern

**Where**: `MoveValidatorAdapter` class

**Why**:

- Provides a unified interface for move validation
- Makes it easier to modify or extend move validation rules
- Decouples move validation logic from piece implementation

### Composite Pattern

**Where**: `ChessTeam` class

**Why**:

- Groups pieces into teams (White/Black)
- Allows treating individual pieces and groups of pieces uniformly
- Simplifies team-based operations and checks

## Behavioral Patterns

### Strategy Pattern

**Where**: `MoveStrategy` interface and `RandomMoveStrategy` class

**Why**:

- Enables different AI move algorithms to be implemented
- Makes it easy to switch between different move strategies
- Allows for future addition of more sophisticated AI strategies

### Observer Pattern

**Where**: `Observer` interface and `GameLogger` class

**Why**:

- Provides a way to track and log game moves
- Allows multiple observers to monitor the game
- Makes it easy to add new types of observers (e.g., move history, game analysis)

## Usage Example

```java

ChessGamegame = newChessGame();

game.addObserver(newGameLogger());  // Add move logging

game.makeMove(1, 1, 1, 2);  // Make a move

```

These patterns work together to create a flexible and maintainable foundation for a console chess game that can be easily extended with new features.
