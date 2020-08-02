import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App extends JFrame implements MouseListener {
    Test_Automation automation = new Test_Automation();
    FileParser fileParser = new FileParser();
    FileList fileList = new FileList(fileParser.screenshotlist);

    public App(){
        init();
    }

    private void init(){
        setTitle("Test Automation");
        add(automation, BorderLayout.CENTER);
        add(fileList, BorderLayout.WEST);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
        Screenshot.takeScreenshot("test.jpg", fileParser);
        Screenshot.takeScreenshot("test1.jpg", fileParser);
        Screenshot.takeScreenshot("test2.jpg", fileParser);
        Screenshot.takeScreenshot("test3.jpg", fileParser);
        Screenshot.takeScreenshot("test4.jpg", fileParser);
    }

    public void run(){
        repaint();
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.run();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        //Check to make sure it is within the filelist width
        String filepath=null;
        if(e.getPoint().x<=300)
            filepath=fileList.checkmouseclick(e.getPoint());

        if(filepath!=null){
            System.out.println(filepath+" picked from list.");
            automation.openImage(fileParser.openImage(filepath));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
