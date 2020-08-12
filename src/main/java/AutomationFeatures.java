import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public final class AutomationFeatures {
    public static boolean findScreenshot(String filePath){
        System.out.println("Finding Screenshot");
        String parentDirectory ="C://Test_Automation//";

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat template=null;
        Mat source = null;
        BufferedImage image=null;

        try {
            Robot r = new Robot();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = r.createScreenCapture(capture);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        /*
        Mat source = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        source.put(0, 0, data);
         */

        //template=Imgcodecs.imread(parentDirectory+filePath);
        template=Imgcodecs.imread(parentDirectory+"ide.jpg");
        source = Imgcodecs.imread(parentDirectory+"fullscreen.jpg");
        Mat outputImage=new Mat();
        int machMethod= Imgproc.TM_CCOEFF;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc=mmr.maxLoc;
        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(0, 0, 255), 5);
        System.out.println(matchLoc);
        Imgcodecs.imwrite(parentDirectory+"test.jpg", source);
        System.out.println("Matched.");


        return false;}
    public static boolean findText(){return false;}
    public static boolean wait(int seconds){
        System.out.println("WaitFor: "+seconds);
        try{
            Robot bot = new Robot();
            bot.delay(1000*seconds);
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean click(int x, int y){
        System.out.println(x+", "+y);
        try {
            Robot bot = new Robot();
            bot.mouseMove(x, y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
