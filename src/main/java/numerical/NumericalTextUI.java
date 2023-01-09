package numerical;
import java.util.Scanner;

public class NumericalTextUI{
    private NumericalGame game = new NumericalGame(3,3);

    private Scanner input = new Scanner(System.in);


    public void startGame(){
        game.newGame();
    }

    public int getInputAcross(){
        System.out.println("Turn: " + getPlayer() + "\nPlease enter an across value (1-3): ");
        return input.nextInt();
    }

    public int getInputDown(){
        System.out.println("\nPlease enter a down value (1-3): ");
        return input.nextInt();
    }

    public int getInputValue(){
        System.out.println("Enter your value: ");
        return input.nextInt();
    }

    public int getPlayer(){
        return game.getCurrentPlayer();
    }

     public void printBoard(){
        System.out.println("-------");
        for(int i = 1; i <= 3; i++){
            System.out.print("|");
            for(int j = 1; j <= 3; j++){
                System.out.print(game.getCell(i,j));
                System.out.print("|");
            }
            System.out.print("\n");
            System.out.println("-------");
        }
    }

    public boolean placePiece(){
        return false;
    }

    public boolean isWinner(){
       if(game.isDone()){
            System.out.println("The winner is " + game.getWinner() + "!");
            return true;
       }
       if(game.getDepth() == 9){
            System.out.println("The game was a tie!");
            return true;
       }
       return false;
    }

    public void switchPlayer(){
        game.incrementCurrentPlayer();
    }

    public int arrayLengthEven(){
        return game.arrayLengthEven();
    }

    public int arrayLengthOdd(){
        return game.arrayLengthOdd();
    }

    public int getEvenNum(int i){
        return game.getEvenNum(i);
    }

    public int getOddNum(int i){
        return game.getOddNum(i);
    }

    
    public static void main(String[] args){
        NumericalTextUI text = new NumericalTextUI();
        text.startGame();
        while(true){
            text.printBoard();
            System.out.println("Here are your options: ");
            if(text.getPlayer() == 1){
                for(int i = 0; i < text.arrayLengthOdd(); i++){
                    System.out.print(text.getOddNum(i) + " ");
                }
                System.out.println();
            } else {
                for(int i = 0; i < text.arrayLengthEven(); i++){
                    System.out.print(text.getEvenNum(i) + " ");
                }
                System.out.println();
            }
            if(text.placePiece()){
                if(text.isWinner()){
                    text.printBoard();
                    break;
                }
                text.switchPlayer();
            }
        }
    }
}