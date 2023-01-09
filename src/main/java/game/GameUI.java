package game;
/**
 * 
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import tictactoe.TicTacToeView;
import numerical.NumericalView;

/**
* 
*/
public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    private JLabel stats;
    private JFileChooser fileChooser;

    private int player1Wins = 0;
    private int player2Wins = 0;
    private int ties = 0;
    private int gamesPlayed = 0;


    public GameUI(String title){
        super();    
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(),BorderLayout.EAST);
        add(makeStatsTracker(),BorderLayout.SOUTH);
        start();
    }
    /**
    This method creates and returns the button panel 
    @param none
    @return the button panel
     */
    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTicTacToeButton());
        buttonPanel.add(makeNumericalGameButton());
        buttonPanel.add(makeLoadPlayerButton());
        buttonPanel.add(makeSavePlayerButton());
        return buttonPanel;
    }

    /**
    This creates the JPanel welcome message
    @param none
    @return JPanel containing startup message
     */
    private JPanel startupMessage(){
        JPanel msg = new JPanel();
        msg.add(new JLabel("Welcome to the Game Suite! Click a game to begin, or load a player profile!"));
        return msg;
    }

    /**
    This method creates the JLabel that contains the player stats
    @param none
    @return player stats JLabel
     */
    private JLabel makeStatsTracker(){
        stats = new JLabel("Player 1 Wins: " 
        + player1Wins 
        + "       " 
        + "Player 2 Wins: " 
        + player2Wins 
        + "       " 
        + "Ties: " 
        + ties
        + "       "
        + "Games Played: "
        + gamesPlayed);
        return stats;
    }

    /**
    This method updates the stats JLabel after each game finishes
    @param none
    @return none
     */
    public void statsUpdater(){
        stats.setText("Player 1 Wins: " 
        + player1Wins 
        + "         " 
        + "Player 2 Wins: " 
        + player2Wins
        + "         " 
        + "Ties: " 
        + ties
        + "       "
        + "Games Played: "
        + gamesPlayed);
    }

    /**
    This method creates the JButton that starts a tictactoe game
    @param none
    @return the JButton
     */
    private JButton makeTicTacToeButton(){
        JButton button = new JButton("Play TicTacToe");
        button.addActionListener(e->ticTacToe());
        return button;
    }

    /**
    This method creates the JButton that starts a number scrabble game
    @param none
    @return the JButton
     */
    private JButton makeNumericalGameButton(){
        JButton button = new JButton("Play Number Scrabble");
        button.addActionListener(e->numberScrabble());
        return button;
    }

    /**
    This method creates the JButton responsible for loading a saved player profile
    @param none
    @return the JButton
     */
    private JButton makeLoadPlayerButton(){
        JButton button = new JButton("Load Player Profile");
        button.addActionListener(e->loadPlayer());
        return button;
    }

    /**
    This method creates the JButton responsible for saving a player profile
    @param none
    @return the JButton
     */
    private JButton makeSavePlayerButton(){
        JButton button = new JButton("Save Player Profile");
        button.addActionListener(e->savePlayer());
        return button;
    }

    /**
    This method initiates the start of the game by clearing the GUI, and displaying the startup message
    @param none
    @return none
     */
    public void start(){
        gameContainer.removeAll();
        gameContainer.add(startupMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    
    /**
    This method initializs the tictactoe constructor and starts the game
    @param none
    @return none
     */
    protected void ticTacToe(){
        gameContainer.removeAll();
        TicTacToeView game = new TicTacToeView(3,3,this);
        gameContainer.add(new TicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
    This method initializes the number scrabble constructor and starts the game
    @param none
    @return none
     */
    protected void numberScrabble(){
        gameContainer.removeAll();
        gameContainer.add(new NumericalView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
    This method saves the player statistics
    @param none
    @return none
     */
    protected void savePlayer(){
        String saveString = player1Wins + " " + player2Wins + " " + ties + " " + gamesPlayed;
        String filename = JOptionPane.showInputDialog("Please enter a username: ");
        StringBuilder sb = new StringBuilder();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".csv"));
            sb.append(saveString);
            writer.write(sb.toString());
            writer.close();
        } catch (Exception ex) {
        }
    }

    /**
    This method loads the player stats from a file chosen by the user
    @param none
    @return none
     */
    protected void loadPlayer(){
        int selection = 0;
        String filename = JOptionPane.showInputDialog("Please enter the username to load: ");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename + ".csv"));
            String temp;
            String[] splitStr;
            temp = reader.readLine();
            splitStr = temp.split(" ");
            player1Wins = Integer.valueOf(splitStr[0]);
            player2Wins = Integer.valueOf(splitStr[1]);
            ties = Integer.valueOf(splitStr[2]);
            gamesPlayed = Integer.valueOf(splitStr[3]);
            statsUpdater();
            reader.close();
        } catch (Exception ex){
        }
    }

    /**
    This method increments the player1Wins variable when player 1 wins
    @param none
    @return none
     */
    public void setPlayer1Wins(){
        player1Wins++;
    }

    /**
    This method increments the player2Wins variable when player 2 wins
    @param none
    @return none
     */
    public void setPlayer2Wins(){
        player2Wins++;
    }

    /**
    This method increments the ties variable when theres a tir
    @param none
    @return none
     */
    public void setTies(){
        ties++;
    }

    /**
    This method increments the gamesPlayed variable after each game
    @param none
    @return none
     */
    public void setGamesPlayed(){
        gamesPlayed++;
    }
    
    public static void main(String[] args){
        GameUI example = new GameUI("Game Suite");
        example.setVisible(true);
    }
} 
