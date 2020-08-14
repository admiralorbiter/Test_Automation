import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;

public final class AutomationFeatures {
    public static boolean takeScreenshot(FileParser fileParser, String filename){
        System.out.println("Take Screenshot");
        String parentDirectory ="C://Test_Automation//";

        Screenshot.takeScreenshot(filename, fileParser);

        return true;}
    public static boolean takeScreenshot(FileParser fileParser, String filename, Rectangle bounds){
        System.out.println("Take Screenshot");
        String parentDirectory ="C://Test_Automation//";

        Screenshot.takeScreenshot(filename, bounds, fileParser);

        return true;
    }
    public static boolean findScreenshot(String filePath){
        System.out.println("Finding Screenshot");
        String parentDirectory ="C://Test_Automation//";

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat template=null;
        Mat source = null;
        BufferedImage image=null;

        try {
            Robot r = new Robot();
            Thread.sleep(1000);
            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = r.createScreenCapture(capture);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        /*
        Mat source = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        source.put(0, 0, data);
         */



        //template=Imgcodecs.imread(parentDirectory+filePath);
        template=Imgcodecs.imread(parentDirectory+filePath);
        //source = Imgcodecs.imread(parentDirectory+"fullscreen.jpg");
        source = img2Mat(image);
        //Imgcodecs.imwrite(parentDirectory+"source.jpg", source);
        //Screenshot.saveScreenshot(image);
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
        Imgcodecs.imwrite(parentDirectory+"fullscreen.jpg", template);
        System.out.println("Matched.");

    return true;}

    public static Mat img2Mat(BufferedImage in) {
        Mat out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
        byte[] data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
        int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
        for (int i = 0; i < dataBuff.length; i++) {
            data[i * 3] = (byte) ((dataBuff[i]));
            data[i * 3 + 1] = (byte) ((dataBuff[i]));
            data[i * 3 + 2] = (byte) ((dataBuff[i]));
        }
        out.put(0, 0, data);
        return out;
    }


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
