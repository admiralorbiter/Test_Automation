import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileList extends JPanel {

    ArrayList<String> fileList = new ArrayList<>();

    public FileList()
    {
        setSize(300, 900);
        setPreferredSize(new Dimension(300, 900));
        fileList.add("Test1");
        fileList.add("Test2");
        fileList.add("Test3");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.gray);
        g.setColor(Color.white);
        for(int i=0; i<fileList.size(); i++) {
            g.drawString(fileList.get(i), 0, 15+i*15);
        }
    }
}
