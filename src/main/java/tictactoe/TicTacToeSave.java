package tictactoe;


import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;
import boardgame.Saveable;

public class TicTacToeSave{
    /**
    This method allows a user to save their game
    @param none
    @return none
     */
    public static void save(Saveable toSave, String filename, String relativePath){
        Path path = FileSystems.getDefault().getPath(relativePath,filename);
        try{
            Files.writeString(path, toSave.getStringToSave());
        } catch (IOException e){
            throw(new RuntimeException(e.getMessage()));
        }
    }


    /**
    This method allows a user to load a saved game
    @param none
    @return none
     */
    public static void load(Saveable toLoad, String filename, String relativePath){
        Path path = FileSystems.getDefault().getPath(relativePath,filename);
        try {
            String fileLines = String.join("\n", Files.readAllLines(path));
            toLoad.loadSavedString(fileLines);
        } catch (IOException e){
            //jfdiognidng
        }
    }
}