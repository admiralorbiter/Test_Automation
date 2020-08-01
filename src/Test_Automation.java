import javax.swing.*;
import java.awt.*;

public class Test_Automation extends JPanel{

    public Test_Automation(){
        setSize(1300, 900);
        setPreferredSize(new Dimension(1300, 900));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Color bColor = Color.white;
        setBackground(bColor);
    }
}
