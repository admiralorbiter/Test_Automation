import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class FileParser {
    ArrayList<String> screenshotlist = new ArrayList<>();
    String parentDirectory;

    public FileParser(){
        parentDirectory ="C://Test_Automation//";
        File file  = new File(parentDirectory);
        if(file.exists()){
            file.mkdir();
        }
        openfilelist();
    }

    public FileParser(String parentDirectory){
        this.parentDirectory = parentDirectory;
        File file  = new File(parentDirectory);
        if(file.exists()){
            file.mkdir();
        }
        openfilelist();
    }

    public void add(String file){
        if(!screenshotlist.contains(file)){
            screenshotlist.add(file);
            save();
            System.out.println("Saved "+file);
        }else{
            System.out.println("File Already In File List");
        }

    }


    private void openfilelist(){
        File file  = new File(parentDirectory +"screenshotlist.txt");
        if(file.exists()){
            try {
                FileInputStream fis = new FileInputStream(parentDirectory +"screenshotlist.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                screenshotlist = (ArrayList<String>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        printList();
    }

    public Image openImage(String filepath){
        filepath=  filepath.replace("\\", "/");
        File file = new File(filepath+".jpg");
        Image image = null;
        if(file.exists()) {
            try {
                image = ImageIO.read(file);
                System.out.println(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    private void printList(){
        for(int i=0; i<screenshotlist.size(); i++){
            System.out.println(screenshotlist.get(i));
        }
    }

    private void save(){
        try {
            FileOutputStream fos = new FileOutputStream(parentDirectory +"screenshotlist.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(screenshotlist);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
