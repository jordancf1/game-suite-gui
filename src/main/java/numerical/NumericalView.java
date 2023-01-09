package numerical;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import boardgame.ui.PositionAwareButton;
import game.GameUI;

/**
* 
*/
public class NumericalView extends JPanel{

    private JLabel messageLabel;
    private JLabel turnTracker;
    private NumericalGame game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    private GameUI root;
    private JMenuBar menuBar;
    private JButton loadFileButton;
    private JTextArea outputArea;
    private JFileChooser fileChooser;

    

    public NumericalView(int wide, int tall, GameUI gameFrame){
        super();    
        setLayout(new BorderLayout());
        root = gameFrame;

        setGameController(new NumericalGame(wide,tall));   


        messageLabel = new JLabel("Welcome to Numerical Scrabble!");
        fileChooser = new JFileChooser();
        add(messageLabel, BorderLayout.NORTH);  
        add(makeNewGameMenu(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
        turnTracker = new JLabel("Turn: " + game.getParity());
        add(turnTracker, BorderLayout.SOUTH);
        newGame();
    }

    public void setGameController(NumericalGame controller){
        this.game = controller;
    }

    /**
    This method makes the menu bar that contains all buttons about the game
    @param none
    @return the JMenuBar
     */
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
    This method creates the clickable buttons for the number scrabble grid
    @param none
    @return a JPanel
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
                                        enterNumber(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    /**
    This method checks if the game is done and displays the appropiate dialogue window
    @param none
    @return none
     */
    private void checkGameState(){
        int selection = 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
            if(game.getWinner() == 1){
                root.setPlayer2Wins();
                root.statsUpdater();
            } else if (game.getWinner() == 2){
                root.setPlayer1Wins();
                root.statsUpdater();
            } else {
                root.setTies();
                root.statsUpdater();
            }
            root.setGamesPlayed();
            selection = gameOver.showConfirmDialog(null,
            game.getGameStateMessage(), "Play Again?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }
        turnTracker.setText("Turn: " + game.getParity());
    }   

    /**
    This method updates the buttons when one is pressed
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
    This method allows a user to enter a number and have it appear in the grid
    @param none
    @return none
     */
    private void enterNumber(ActionEvent e){
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        try{
            game.validatePlace(clicked.getAcross(),clicked.getDown());
        } catch (Exception e1){
            JOptionPane.showMessageDialog(root, "This place is already taken!");
            return;
        }
        String num = JOptionPane.showInputDialog(game.getGameStateMessage()); 
        try{
            game.validateInput(Integer.valueOf(num));
        } catch (Exception e2){
            JOptionPane.showMessageDialog(null, "This is not an availabe integer!");
            return;
        }
        if(game.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
            clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
            game.incrementCurrentPlayer();
        }
    }

    /**
    This method allows the user to save an inprogess game
    @param none
    @return none
     */
    private void saveGame(){
        int selection = 0;
        String filename = JOptionPane.showInputDialog("Please enter a filename");
        NumericalSave.save(game, filename + ".txt", "assets");
        selection = JOptionPane.showConfirmDialog(null,
            "Would you like to start a new game?", "", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
    }

    /**
    This method allows a user to quit an inprogress game
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
    This method allows a user to restart a game
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
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if(fileChooser.getSelectedFile().canRead()) {
                try {
                    NumericalSave.load(game,fileChooser.getSelectedFile().getName(), "assets");
                    updateView();
                } catch (Exception ex) {
                    outputArea.append("\n" + ex.getMessage());
                }
            }
        }
    }
}
