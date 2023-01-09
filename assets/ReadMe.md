# Swing Game Suite

This is a simple swing GUI where the user has the choice to play either TicTacToe or NumberScrabble, as well as other features such as saving and loading

# Description

My project uses Swing to create the GUI but also has a TextUI available for use. When the program first starts, you'll be greeted by a welcome message and 4 buttons. Two of the buttons can be used to starts a new game, either TicTacToe or Number Scrabble. Pressing one of these allows you to jump right into a game. Each player can select a square and make their move. The game keeps track of who's turn it is. From the game window, clicking "New Game" allows you to quit, save, restart or load an existing game. The other two buttons allow you to save / load player profiles. Each player profile consists of player 1 wins, player 2 wins, ties, and games played. You can save as many of these as you wish and load any of them at any time. The file format I chose to save these was just a simple space delimited .txt file.

## Getting Started

### Dependencies

This program will work on any operating system given that you have a java interpreter and gralde installed. All needed Java libraries will be imported for you upon program startup.

### Executing program

Navigate to the A3 directory where the src file is located.

If you want to play the TicTacToe TextUI:

1. Type:
   `gradle build` in the scioer shell. You should see BUILD SUCCESSFUL in all green letters

2. Now type:
   `java -cp build/classes/java/main tictactoe.TicTacToeTextUI`

3. Follow the insutrctions and have fun!

If you want to use the Swing GUI:

1. Type:
   `gradle build`
   You should see BUILD SUCCESSFUL in all green letters

2. Open up a terminal on your ---LOCAL SYSTEM--- and navigate to the A3 directory. The GUI will not work on the scioer shell!

3. Type:
   `java -jar build/libs/A3.jar`
   The GUI should now load up

4. Play some games!

##

Most things are finished, however not as cleanly as I'd like. My saving and loading player profiles doesn't use the saveable interface as I couldn't figure out how to make it work. I also don't check very thouroughly to ensure files loaded are properly formatted.

However, everything in the assignment outline ~should~ be included.

## Author Information

Jordan Fulawka
jfulawka@uoguelph.ca

## Development History

- 0.7
  - finished checkstyle and javadocs
    [2765443661854f094e8ab79ad551e556bdb6c40d]

* 0.6
  - finished implementing the saving/loading profiles
    [f5329c4fd173b8ef4cb0dffdf1241ff3c294b92a]

- 0.5
  - started implementing the player profiles function
    [f5329c4fd173b8ef4cb0dffdf1241ff3c294b92a]

* 0.4
  - finished implementing my saving/loading and fixed some other things
    [fefa8727a8dd2b91402036be974dcb5c62f428ce]

- 0.3
  - Improved on my GUI and started implement saving / loading games
    [e887810342fce3c937296300da4d350ab7e461e6]

* 0.2
  - Started GUI development
    [e887810342fce3c937296300da4d350ab7e461e6]

- 0.1
  - Started working on refactoring TicTacToe game
    [e887810342fce3c937296300da4d350ab7e461e6]

## Acknowledgments

Inspiration, code snippets, etc.

- [awesome-readme](https://github.com/matiassingers/awesome-readme)
- [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
