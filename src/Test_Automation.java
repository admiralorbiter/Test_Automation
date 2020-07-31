import javax.swing.*;
import java.awt.*;

public class Test_Automation extends JPanel{
    public static void main(String[] args)
    {
        Test_Automation automation = new Test_Automation();
        JFrame app = new JFrame("Test Automation");
        app.add(automation, BorderLayout.CENTER);
        app.setSize(1600, 900);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Color bColor = Color.WHITE;

        g.setColor(bColor);
        int offset = 0;
        g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

        super.paintComponent(g);
    }

    //public int getWidth(){return 400;}
    //public int getHeight(){return 400;}
}
