package numerical;


import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.IOException;
import boardgame.Saveable;

public class NumericalSave{
    /**
    This method saves the game in the form of a string to a file
    @param none
    @return nothing
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
    This method loads a game from a file
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