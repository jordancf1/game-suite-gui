package numerical;
import java.util.ArrayList;


public class NumericalGame extends boardgame.BoardGame implements boardgame.Saveable{

    private int currentPlayer = 1;
    private int depth = 0;

    private ArrayList<Integer> evenNumbers = new ArrayList<Integer>();

    private ArrayList<Integer> oddNumbers = new ArrayList<Integer>();

    public NumericalGame(int wide, int high){
        super(wide,high);
        setGrid(new NumericalGrid(wide,high));
    }

    /**
    This method places a piece on the board
    @param across value, down value, input
    @return true of false depending on whether the piece was correctly placed
     */
    @Override
    public boolean takeTurn(int across, int down, String input){
        if(currentPlayer == 1){
            oddNumbers.remove(Integer.valueOf(input));
        } else {
            evenNumbers.remove(Integer.valueOf(input));
        }
        setValue(across,down,input);
        depth++;
        return true;
    }

    /**
    This method places a piece on the board
    @param across value, down value, input
    @return true of false depending on whether the piece was correctly placed
     */
    @Override
    public boolean takeTurn(int across, int down, int input){
            if(currentPlayer == 1){
                oddNumbers.remove(Integer.valueOf(input));
            } else {
                evenNumbers.remove(Integer.valueOf(input));
            }
            setValue(across,down,String.valueOf(input));
            depth++;
            return true;
    }

    /**
    This method pstarts new game
    @param none
    @return none
     */
    public void newGame(){
        super.newGame();
        currentPlayer = 1;
        depth = 0;
        evenNumbers.clear();
        oddNumbers.clear();
        evenNumbers.add(0);
        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);
        evenNumbers.add(8);
        oddNumbers.add(1);
        oddNumbers.add(3);
        oddNumbers.add(5);
        oddNumbers.add(7);
        oddNumbers.add(9);
    }

    /**
    This method returns a specific cell on the board
    @param across value, down value
    @return the contents of the cell at the specified index
     */
    @Override
    public String getCell(int across, int down){
        return super.getCell(across,down);
    }

    /**
    This method returns the length of the evenNumbers array
    @param none
    @return length of the array
     */
    public int arrayLengthEven(){
        return evenNumbers.size();
    }

    /**
    This method returns the length of the oddNumbers array
    @param none
    @return length of the array
     */
    public int arrayLengthOdd(){
        return oddNumbers.size();
    }

    /**
    This method returns the element in the evenNumbers array at the specified index
    @param none
    @return integer from the evenNumbers array
     */
    public int getEvenNum(int i){
        return evenNumbers.get(i);
    }

    /**
    This method returns the element in the oddNumbers array at the specified index
    @param none
    @return integer from the oddNumbers array
     */
    public int getOddNum(int i){
        return oddNumbers.get(i);
    }

    /**
    This method removes an element from the oddNumbers array
    @param none
    @return none
     */
    public void removeOdd(int number){
        oddNumbers.remove(Integer.valueOf(number));
    }

    /**
    This method removes an element from the evenNumbers array
    @param none
    @return none
     */
    public void removeEven(int number){
        evenNumbers.remove(Integer.valueOf(number));
    }

    /**
    This method returns a character based on whos turn it is
    @param none
    @return either "E" or "O"
     */
    public String getParity(){
        if(currentPlayer == 1){
            return "Odd";
        } else {
            return "Even";
        }
    }

    /**
    This method checks if an element is in the evenNumbers array
    @param none
    @return true or false depending if the element is contained in the array
     */
    public boolean checkEven(int number){
        return evenNumbers.contains(Integer.valueOf(number));
    }

    /**
    This method checks if an element is in the oddNumbers array
    @param none
    @return true or false depending if the element is contained in the array
     */
    public boolean checkOdd(int number){
        return oddNumbers.contains(Integer.valueOf(number));
    }

    /**
    This method checks if the game is done
    @param none
    @return true or false depending on if the game is done or not
     */
    @Override
    public boolean isDone(){
        for(int i = 1; i < 4; i++){
            if(getCell(i,1) != " " && getCell(i,2) != " " && getCell(i,3) != " " 
            && (Integer.valueOf(getCell(i,1)) + Integer.valueOf(getCell(i,2)) + Integer.valueOf(getCell(i,3))) == 15){
                return true;
            } else if(getCell(1,i) != " " && getCell(2,i) != " " && getCell(3,1) != " " 
            && (Integer.valueOf(getCell(1,i)) + Integer.valueOf(getCell(2,i)) + Integer.valueOf(getCell(3,i))) == 15){
                return true;
            } else if(getCell(1,1) != " " && getCell(2,2) != " " && getCell(3,3) != " " 
            && (Integer.valueOf(getCell(1,1)) + Integer.valueOf(getCell(2,2)) + Integer.valueOf(getCell(3,3))) == 15){
                return true;
            } else if(getCell(3,1) != " " && getCell(2,2) != " " && getCell(1,3) != " " 
            && (Integer.valueOf(getCell(3,1)) + Integer.valueOf(getCell(2,2)) + Integer.valueOf(getCell(1,3))) == 15){
                return true;
            }
        }
        return false;
    }

    /**
    This method returns the winner or 0 is the game is tired
    @param none
    @return 1, 2 or 0
     */
    @Override
    public int getWinner(){
        if(depth != 9){
            return currentPlayer;
        } else {
            return 0;
        }
    }

    /**
    This method returns the current player
    @param none
    @return current player
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    /**
    This method returns how many turns in the game have happened
    @param none
    @return depth of the game
     */
    public int getDepth(){
        return depth;
    }

    /**
    This method checks if an element is in the evenNumbers array
    @param none
    @return true or false depending if the element is contained in the array
     */
    public void incrementCurrentPlayer(){
        if(currentPlayer == 1){
            currentPlayer++;
        } else {
            currentPlayer = 1;
        }
    }

    /**
    This method returns the current state of the game
    @param none
    @return the appropiate string
     */
    @Override
    public String getGameStateMessage(){
        String gamestate;
        if(!isDone()){
            if(getCurrentPlayer() == 1){
                gamestate = "Player 1: Choose one of these numbers: [";
                for(int i = 0; i < oddNumbers.size(); i++){
                    gamestate = gamestate + oddNumbers.get(i) + ", ";
                }
                gamestate = gamestate + "]";
                return gamestate;
            } else {
                gamestate = "Player 2: Choose one of these numbers: [";
                for(int i = 0; i < evenNumbers.size(); i++){
                    gamestate = gamestate + evenNumbers.get(i) + ", ";
                }
                gamestate = gamestate + "]";
                return gamestate;
            }
        } else {
            int winner = getWinner();
            if(winner == 1){
                return "Nice! Player 2 Wins!";
            } else if (winner == 2){
                return "Woohoo! Player 1 Wins!";
            } else{
                return "The game was a tie!";
            }
        }
    }

    /**
    This method checks a specified place is taken or not
    @param none
    @return true or false depending if the space is taken
     */
    public boolean validatePlace(int across, int down) throws Exception{
        if(getCell(across,down) == " "){
            return true;
        } else {
            throw new Exception();
        }
    }

    /**
    This method checks if a place is within the range of the grid
    @param none
    @return true or false depending if the element is in the range
     */
    public boolean validateInput(int input) throws Exception{
        if(currentPlayer == 1 && oddNumbers.contains(input)){
            return true;
        } else if(currentPlayer == 2 && evenNumbers.contains(input)){
            return true;
        } else {
            throw new Exception();
        }
    }

    /**
    This method condenses the information needed into a single string to save it
    @param none
    @return the string 
     */
    @Override
    public String getStringToSave(){
        String boardString = getPlayerParity() + "\n";
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                if(getCell(j,i) == " "){
                    if(j == 3){
                        continue;
                    } else {
                        boardString += ",";
                    }
                } else {
                    if(j != 3){
                    boardString += getCell(j,i) + ",";
                    } else {
                        boardString += getCell(j,i);
                    }
                }
            }
            boardString += "\n";
        }
        return boardString;
    }

    /**
    This method loads a previously saved game into the board
    @param none
    @return none
     */
    @Override
    public void loadSavedString(String load){
        String[] mainString = load.split("\n",4);
        loadPlayerTurn(mainString[0]);
        String[] line1 = mainString[1].split(",",3);
        for(int i = 1; i <= 3; i++){
            if(line1[i-1].isEmpty()){
                setValue(i,1," ");
            } else {
                setValue(i,1,line1[i-1]);
            }
        }
        String[] line2 = mainString[2].split(",",3);
        for(int i = 1; i <= 3; i++){
            if(line2[i-1].isEmpty()){
                setValue(i,2," ");
            } else {
                setValue(i,2,line2[i-1]);
            }
        }
        String[] line3 = mainString[3].split(",",3);
        for(int i = 1; i <= 3; i++){
            if(line3[i-1].isEmpty()){
                setValue(i,3," ");
            } else {
                setValue(i,3,line3[i-1]);
            }
        }
    }

    /**
    This method sets the current player based on the loaded game
    @param none
    @return none
     */
    public void loadPlayerTurn(String player){
        if(player == "O"){
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }
    }

    /**
    This method return E or O depending if its even or odds turn
    @param none
    @return E or O
     */
    public String getPlayerParity(){
        if(currentPlayer == 1){
            return "O";
        } else {
            return "E";
        }
    }
}