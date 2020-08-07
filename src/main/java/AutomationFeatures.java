public final class AutomationFeatures {
    public static boolean findScreenshot(){return false;}
    public static boolean findText(){return false;}
    public static boolean click(int x, int y){
        System.out.println(x+", "+y);
        return false;
    }
}
