import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App(){
        Test_Automation automation = new Test_Automation();
        FileList fileList = new FileList();
        setTitle("Test Automation");
        add(automation, BorderLayout.CENTER);
        add(fileList, BorderLayout.WEST);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void run(){
        repaint();
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.run();
    }
}
