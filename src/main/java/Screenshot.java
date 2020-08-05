import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Screenshot {

    public static String saveScreenshot(BufferedImage image){
        JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        SaveAs.setCurrentDirectory(new File("C://Test_Automation//"));
        int actionDialog = SaveAs.showOpenDialog(null);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        try {
            ImageIO.write(image, "jpg", new File(SaveAs.getSelectedFile()+".jpg"));
            System.out.println(SaveAs.getSelectedFile().getAbsolutePath());
            return SaveAs.getSelectedFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void takeScreenshot(String name, FileParser fileParser){
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            String path=filePath();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path+name+".jpg"));
            System.out.println("Screenshot saved");
            fileParser.add(path+name);
        }
        catch (AWTException | IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void takeScreenshot(FileParser fileParser){
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            String path=filePath();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            path=saveScreenshot(Image);
            System.out.println("Screenshot saved");
            if(path!=null){
                fileParser.add(path);
            }
        }
        catch (AWTException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void takeScreenshot(String name, Rectangle rectangle, FileParser fileParser){
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            String path=filePath();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(rectangle);
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path+name+".jpg"));
            System.out.println("Screenshot saved");
            fileParser.add(path+name);
        }
        catch (AWTException | IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void takeScreenshot(Rectangle rectangle, FileParser fileParser){
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            String path=filePath();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(rectangle);
            BufferedImage Image = r.createScreenCapture(capture);
            path=saveScreenshot(Image);
            System.out.println("Screenshot saved");
            if(path!=null){
                fileParser.add(path);
            }
        }
        catch (AWTException | InterruptedException ex) {
            System.out.println(ex);
        }
    }


    private static String filePath(){
        // It saves screenshot to desired path
        String path = "C://Test_Automation//";
        File file  = new File(path);
        if(file.exists()){
            file.mkdir();
        }
        return path;
    }
}
