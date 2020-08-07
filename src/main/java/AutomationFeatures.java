import java.awt.*;
import java.awt.event.InputEvent;

public final class AutomationFeatures {
    public static boolean findScreenshot(){return false;}
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
