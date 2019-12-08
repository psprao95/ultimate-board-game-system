# UltimateBoardGameSystem-UBGS
UltimateBoardGameSystem(UBGS) main repository

Design Patterns for Board Games

Development of an OO board game system. The students easily see the power, utility, flexibility and scalability of the OO Design. The lesson is that generic concepts should lead to generic applicability, not single-use, “throw away” code. The students learn “programming-in-the-large” while studying a system that is still small enough to manage.

For simplicity, we will focus on two-dimensional game boards (Tic-Tac-Toe, Othello, Checkers, Go,...), with only two players.

This project is not about Tic-Tac-Toe nor Othello.  It uses a 2-person game design as a vehicle to learn BIGGER concepts in computing:

- Abstraction
- Design Process
- Fundamental Principles.

In this project, you will be given a big chunk of a 2-person board game framework and asked to write a few of its components, plug them in and obtain a program that can run Tic-Tac-To, Othello, and .. with different types of players, human and/or computer, using a variety of strategies to compute the next move while playing the games.

The given game framework abstracts and decouples the different components in a game and specifies them in terms of interfaces with only pure abstract behaviors.  
For example, the rules of a game is abstracted and encapsulated in an interface called IBoardModel.  ABoardModel is a specific implementation of this interface using the state pattern.  Playing a particular board game is a matter of writing a concrete subclass of ABoardModel and plug it into the framework.  Nothing in the framework is changed!

Among the source files is GameModel.java which is the class that encapsulates:
- the rules of a game,
- the strategies to compute the next move, and
- the management of players.

GameModel does not contain any code specific to Tic-Tac-Toe. It merely moderates the interactions between the board, IBoardModel and the strategy to compute the next move, INextMoveStrategy. GameModel.getRequestor() is the factory method to instantiate the IRequestor for the view. GameModel directly implements the IModelManager interface.

GameModel.java has a method called getPlayers().  In this method, the code to add players playing the strategies. The only available stategy is RandomMoveStrategy implements the INextMoveStrategy. 

For more info please review the following paper:
https://www2.cs.duke.edu/courses/cps108/spring04/notes/zungmvc.pdf

Be sure to document (in javadoc style) all the code you write.
