import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private String path;

    /**
     * Agrega el path para crear el archivo
     * @param filePath
     */
    public FileHandler(String filePath){
        setPath(filePath);
    }

    public boolean createFile()  {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void writeToFile(String textLine){
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(textLine);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
