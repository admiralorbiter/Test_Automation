import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Screenshot {

    public static void takeScreenshot(String name, FileParser fileParser){
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            String path=filePath();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path+name));
            System.out.println("Screenshot saved");
            fileParser.add(path+name);
        }
        catch (AWTException | IOException | InterruptedException ex) {
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
            ImageIO.write(Image, "jpg", new File(path+name));
            System.out.println("Screenshot saved");
            fileParser.add(path+name);
        }
        catch (AWTException | IOException | InterruptedException ex) {
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
