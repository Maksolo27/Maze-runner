# Maze runner
## Game Overview

This game involves controlling a character on a gaming grid using keyboard inputs. The gaming grid is represented as a table (JTable), with each cell portraying a specific object (AbstractFigur). The game grid is loaded based on different difficulty levels defined in DifficultyLoader.

### Key Classes and Components

- **ArrayCollection**: The core class responsible for storing game data and its processing. It holds the game grid, a list of moving objects, a list of bullets, and the player. It facilitates object movement, shooting, collision handling, and character actions.

- **GameMap**: The primary interface of the game, displaying the gaming grid, score, steps, time information, and game status. It also handles user keyboard input for controlling the player.

### Gameplay Flow

- **Game Display**: GameMap creates a graphical interface displaying the gaming grid, score, steps, time, and game status.

- **Loading the Game Grid**: ArrayCollection loads the gaming grid from the map loader factory (MapLoaderFactory). The field comprises various objects, including the player, moving entities, and bullets.

- **Player Control**: The player can navigate the gaming grid using directional keys (VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT) and shoot (VK_L).

- **Data and Interface Update**: Upon each update of the game state, GameMap refreshes the data and game display based on the current state of the game collection.

### Key Player Actions (Player)

- **Movement**: The player can move up, down, left, and right across the gaming grid.
- **Shooting**: The player can fire bullets in a specified direction using the VK_L key.

### Game Objects

- **Player**: The main character controlled by the user.
- **Bullet**: Objects fired by the player upon shooting.
- **Emptiness**: Empty cells within the gaming grid.
- **AbstractMovingFigur**: An abstract class for all moving entities in the game.

### Key Actions and Processing

- **Collisions**: Upon collision between bullets and other objects, their processing takes place. For instance, a bullet might destroy an object or alter the game state.
- **Action Execution**: Player or other object actions are handled in corresponding methods, checking the feasibility of the action and its consequences.

### Multithreading

- Separate threads (GameThread, BulletThread) are employed to ensure parallel execution of the game logic and bullet movement.

### Dependencies

- **Apache Commons Lang 3**:
    - A library providing utilities for common tasks in Java, offering functionalities such as string manipulation, object handling, and more.

- **Lombok**:
    - A library that helps reduce boilerplate code in Java by automatically generating getters, setters, constructors, and other commonly used code blocks during compilation. It enhances code readability and maintainability.

- **Spring Boot Starter Test**:
    - A Spring Boot starter that includes various dependencies for testing Spring applications, facilitating unit and integration testing using Spring's testing framework.

### Technologies Used in the Game

- **Java**:
    - The primary programming language used to develop the game, offering platform independence, strong object-oriented principles, and a vast ecosystem of libraries and tools.

- **Swing (Java GUI)**:
    - Utilized for creating the game's graphical user interface (GUI). A Java-based toolkit for building graphical user interfaces providing components like tables, labels, and panels used for rendering the game's visuals.

- **Multithreading**:
    - Employed to handle concurrent execution, ensuring smoother gameplay. Separate threads (GameThread, BulletThread) manage game logic and bullet movement concurrently.

- **Apache Commons Lang**:
    - Utilized for various common tasks such as string manipulation, object handling, and other utility functionalities required during gameplay.

- **Lombok**:
    - Streamlines the Java codebase by automatically generating common boilerplate code, enhancing code readability and maintainability.

- **Spring Boot (for Testing)**:
    - Used for testing Spring application components within the game, enabling unit and integration testing with the Spring testing framework.

# Authors
KOVAL MAKSYM - https://github.com/Maksolo27

PAVEL BELOLIPETSKIY - https://github.com/Pashhaa
# Licence
https://github.com/Maksolo27/Project/blob/main/LICENCE
