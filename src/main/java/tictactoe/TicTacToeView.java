package tictactoe;
/**
 *  an example GUI view for an NxM game
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import boardgame.ui.PositionAwareButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import game.GameUI;
import java.io.File;
//import boardgame.FileHandler;

/**
* 
*/
public class TicTacToeView extends JPanel{

    private JLabel messageLabel;
    private JLabel turnTracker;
    private TicTacToeGame game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    private GameUI root;
    private JMenuBar menuBar;
    private JButton loadFileButton;
    private JTextArea outputArea;
    private JFileChooser fileChooser;
    //private FileHandler handler;

    public TicTacToeView(int wide, int tall, GameUI gameFrame){

        super();    
        setLayout(new BorderLayout());
        root = gameFrame;

        setGameController(new TicTacToeGame(wide,tall));   

        messageLabel = new JLabel("Welcome to TicTacToe!");
        add(messageLabel, BorderLayout.NORTH); 
        fileChooser = new JFileChooser();
        add(makeNewGameMenu(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
        turnTracker = new JLabel("Turn:" + game.getPiece());
        add(turnTracker, BorderLayout.SOUTH);
        newGame();
    }

    public void setGameController(TicTacToeGame controller){
        this.game = controller;
        }

    private JMenuBar makeNewGameMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("New Puzzle");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem load = new JMenuItem("Load");
        menu.add(save);
        menu.add(quit);
        menu.add(restart);
        menu.add(load);
        menuBar.add(menu);
        save.addActionListener(e->saveGame());
        quit.addActionListener(e->quitGame());
        restart.addActionListener(e->restartGame());
        load.addActionListener(e->loadGame());
        return menuBar;
    }

    /**
    This method makes the clickable button grid
    @param none
    @return the buttons
     */
    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
                for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ 
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1);
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        placePiece(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    /**
    This method checks the current game state and sets the dialogue box
    @param none
    @return none
    */
    private void checkGameState(){
        int selection= 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
            if(game.getWinner() == 1){
                root.setPlayer2Wins();
            } else if (game.getWinner() == 2){
                root.setPlayer1Wins();
            } else {
                root.setTies();
            }
            root.setGamesPlayed();
            root.statsUpdater();
            selection = gameOver.showConfirmDialog(null,
            game.getGameStateMessage(), "Play again?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }
        turnTracker.setText("Turn: " + game.getPiece());
    }   

    /**
    This method updates the clickable grid when one is clicked
    @param none
    @return none
     */
    protected void updateView(){
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }

    }

    /**
    This method starts a new game
    @param none
    @return none
     */
    protected void newGame(){
        game.newGame();
        updateView();
    }

    /**
    This method places a piece on the board when click
    @param none
    @return none
     */
    private void placePiece(ActionEvent e){
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        try {
            game.validateInput(clicked.getAcross(), clicked.getDown());
        } catch (Exception e1){
            JOptionPane.showMessageDialog(null, "This place is already taken!");
            return;
        }

        if(game.takeTurn(clicked.getAcross(), clicked.getDown(),game.getPiece())){
            clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
            game.incrementCurrentPlayer();
        }
    }

    /**
    This method allows a user to save a game
    @param none
    @return none
     */
    private void saveGame(){
        int selection = 0;
        String filename = JOptionPane.showInputDialog("Please enter a filename");
        TicTacToeSave.save(game, filename + ".csv", "assets");
        selection = JOptionPane.showConfirmDialog(null,
            "Would you like to start a new game?", "", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
    }

    /**
    This method allows the user to quit the game
    @param none
    @return none
     */
    private void quitGame(){
        int selection = 0;
        selection = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?", "", JOptionPane.YES_NO_OPTION);
        if(selection == JOptionPane.NO_OPTION){
            return;
        } else {
            root.start();
        }
    }

    /**
    This method allows a user to restart the game
    @param none
    @return none
     */
    private void restartGame(){
        int selection = 0;
        selection = JOptionPane.showConfirmDialog(
            null,"Are you sure you want to restart?", "", JOptionPane.YES_NO_OPTION);
        if(selection == JOptionPane.NO_OPTION){
            return;
        } else {
            newGame();
        }
    }

    /**
    This method allows a user to load a game
    @param none
    @return none
     */
    private void loadGame(){
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if(fileChooser.getSelectedFile().canRead()) {
                try {
                    TicTacToeSave.load(game, fileChooser.getSelectedFile().getName(), "assets");
                    updateView();
                } catch (Exception ex) {
                    outputArea.append("\n" + ex.getMessage());
                }
            }
        }
    }
}
