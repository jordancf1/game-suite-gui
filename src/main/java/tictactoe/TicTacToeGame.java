package tictactoe;

public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    //TTTTextUI input = new TTTTextUI();
    private int currentPlayer = 1;
    private int depth = 0;
    

    public TicTacToeGame(int wide, int high){
        super(wide,high);
        setGrid(new TicTacToeGrid(wide,high));
    }

    /**
    This method allows a user to place a piece on the board
    @param none
    @return true or false depending if the piece was placed
     */
    @Override
    public boolean takeTurn(int across, int down, String input){
            setValue(across,down,input);
            depth++;
            return true;
    }

    /**
    This method allows a user to place a piece on the board
    @param none
    @return true or false depending if the piece was placed
     */
    @Override
    public boolean takeTurn(int across, int down, int input){
            setValue(across,down,String.valueOf(input));
            depth++;
            return true;
    }

    /**
    This method starts a new game
    @param none
    @return none
     */
    @Override
    public void newGame(){
        super.newGame();
        currentPlayer = 1;
        depth = 0;
    }


    /**
    This method retrieves the cell at the index
    @param across value, down value
    @return the character stored at the specified cell
     */
    @Override
    public String getCell(int across, int down){
        return super.getCell(across,down);
    }

    /**
    This method checks to see if the game is done
    @param none
    @return true is game is done, false if not
     */
    @Override
    public boolean isDone(){
        for(int i = 1; i < 4; i++){
            if(getCell(i,1) == getCell(i,2) && getCell(i,2) == getCell(i,3) && getCell(i,1) != " "){
                return true;
            } else if(getCell(1,i) == getCell(2,i) && getCell(2,i) == getCell(3,i) && getCell(1,i) != " "){
                return true;
            } else if(getCell(1,1) == getCell(2,2) && getCell(2,2) == getCell(3,3) && getCell(1,1) != " "){
                return true;
            } else if(getCell(3,1) == getCell(2,2) && getCell(2,2) == getCell(1,3) && getCell(3,1) != " "){
                return true;
            } else if(depth == 9){
                return true;
            }
        }
        return false;
    }

    /**
    This method returns the result of the game
    @param none
    @return the player who won, or 0 if tie
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
    This method returns the depth of the game
    @param none
    @return the depth
    */
    public int getDepth(){
        return depth;
    }

    /**
    This method switchs the players
    @param none
    @return none
     */
    public void incrementCurrentPlayer(){
        if(currentPlayer == 1){
            currentPlayer++;
        } else {
            currentPlayer = 1;
        }
    }

    /**
    This method returns the state of the game
    @param none
    @return gamestate
     */
    @Override
    public String getGameStateMessage(){
        String gamestate;
        int winner = getWinner();
        if(winner == 1){
            gamestate = "Nice! Player 2 Wins!";
            return gamestate;
        } else if (winner == 2){
            gamestate = "Woohoo! Player 1 Wins!";
            return gamestate;
        } else {
            gamestate = "The game was a tie!";
            return gamestate;
        }
    }

    /**
    This method saves the game
    @param none
    @return save string
     */
    @Override
    public String getStringToSave(){
        String boardString = getPiece() + "\n";
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
    This method saves the game
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
    This method loads the current players turn
    @param none
    @return none
     */
    public void loadPlayerTurn(String player){
        if(player == "X"){
            currentPlayer = 1;
        } else {
            currentPlayer = 0;
        }
    }

    /**
    This method gets the current players piece
    @param none
    @return the current piece
     */
    public String getPiece(){
        if(currentPlayer == 1){
            return "X";
        } else {
            return "O";
        }
    }

    /**
    This method checks if a piece is valid or not
    @param none
    @return true or false depending if the piece is valid
     */
    public boolean validateInput(int across, int down) throws Exception{
        if(getCell(across,down) == " "
        && (across >= 1 && across <= 3) && (down >= 1 && down <= 3)){
            return true;
        } else {
            throw new Exception();
        }
    }
}
