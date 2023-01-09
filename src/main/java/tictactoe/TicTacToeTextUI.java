package tictactoe;
import java.util.Scanner;
//import java.lang.Thread;

public class TicTacToeTextUI {

private Scanner scan = new Scanner(System.in);

private TicTacToeGame game = new TicTacToeGame(3,3);
private String filename;

    /**
    This method starts the game and prints a welcome message
    @param none
    @return none
     */
    public void startGame(){
        System.out.println(
            "Welcome to TicTacToe! Player 1 will play X, and player 2 will play O! First person so get 3 in a row wins!"
            );
        game.newGame();
    }

    /**
    This method gets input for the across value
    @param none
    @return the specified value
     */
    public int getInputAcross(){
        System.out.println("Player: " + game.getCurrentPlayer() + "\nPlease enter an across value (1-3) or 0 to exit: "
        );
        return scan.nextInt();
    }

    /**
    This method gets input for the down 
    @param none
    @return the specified value
     */
    public int getInputDown(){
        System.out.println("\nPlease enter a down value (1-3): ");
        return scan.nextInt();
    }

    /**
    This method returns the current player
    @param none
    @return current player
     */
    public String getPlayer(){
        if(game.getCurrentPlayer() == 1){
            return "X";
        } else {
            return "O";
        }
    }

    /**
    This method prints the board
    @param none
    @return none
     */
    public void printBoard(){
        System.out.println("-------");
        for(int i = 1; i <= 3; i++){
            System.out.print("|");
            for(int j = 1; j <= 3; j++){
                System.out.print(game.getCell(j,i));
                System.out.print("|");
            }
            System.out.print("\n");
            System.out.println("-------");
        }
    }

    /**
    This method allows a user to place a piece on the board
    @param none
    @return true or false depending if the piece was placed
     */
    public boolean placePiece(){
        int across;
        int down;
        try {
            across = getInputAcross();
            if(across == 0){
                System.out.println("Would you like to save your game? Y for yes or anything else for no");
                char saveAnswer = scan.next().charAt(0);
                if(saveAnswer == 'Y' || saveAnswer == 'y'){
                setFileName();
            } else {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            } 
            down = getInputDown();
        } catch (Exception e) {
            System.out.println("Please ensure you're entering valid intergers!");
            consumeInvalid();
            return false;
        } 
        try{
            game.validateInput(across,down);
            } catch (Exception e1){
                System.out.println("This is not a valid place. Please try again!");
                return false;
            }
            game.takeTurn(across,down,getPlayer());
            return true;
    }

    /**
    This method switches the player
    @param none
    @return none
     */
    public void switchPlayer(){
        game.incrementCurrentPlayer();
    }

    /**
    This method prints the winner message
    @param none
    @return if the game is done or not
     */
    public boolean isWinner(){
       if(game.isDone()){
            if(game.getWinner() == 1){
                System.out.println("Player 1 wins!");
                return true;
            } else if(game.getWinner() == 2){
                System.out.println("Player 2 wins!");
                return true;
            } else if(game.getWinner() == 0){
                System.out.println("The game was a tie!");
                return true;
            }
       }
       return false;
    }

    /**
    This method consumes an invalid character to prevent infinite loops with the scanner
    @param none
    @return none
     */
    public void consumeInvalid(){
        scan.next();
    }

    public static void main(String[] args){
        TicTacToeTextUI text = new TicTacToeTextUI();
        text.startGame();
        while(true) {
            text.printBoard();
            if(text.placePiece()){
                if(text.isWinner()){
                    text.printBoard();
                    break;
            }
            text.switchPlayer();
            }
        }
    }

    public void setFileName(){
        System.out.println("Enter a file name: ");
        filename = scan.next();
        try{
        System.out.print("Saving");
        Thread.sleep(700);
        System.out.print(".");
        Thread.sleep(800);
        System.out.print(".");
        Thread.sleep(400);
        System.out.println("Saving complete! Thanks for playing!");
        TicTacToeSave.save(game, filename + ".csv", "assets");
        System.exit(0);
        } catch (Exception e){
            System.out.println("Saving complete! Thanks for playing!");
            System.exit(0);
        }
    }
}