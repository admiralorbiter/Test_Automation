import javax.swing.*;
import java.awt.*;

public class Test_Automation extends JPanel{

    Image screenshot = null;

    public Test_Automation(){
        setSize(1300, 900);
        setPreferredSize(new Dimension(1300, 900));
    }

    public void openImage(Image image){
        screenshot=image;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Color bColor = Color.white;
        setBackground(bColor);
        if(screenshot!=null){
            g.drawImage(screenshot, 0, 0, 1300, 900, null);
        }
    }
}
