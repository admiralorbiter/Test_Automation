import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileList extends JPanel {

    ArrayList<String> fileList = new ArrayList<>();

    public FileList()
    {
        fileList.add("Test1");
        fileList.add("Test2");
        fileList.add("Test3");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Color bColor = Color.GRAY;

        g.setColor(bColor);
        int offset = 0;
        g.fillRect(offset, 0, getWidth()/3 - 1 - offset, getHeight() - 1);
        for(int i=0; i<fileList.size(); i++)
            g.drawString(fileList.get(i), getHeight()-10, 10);

        super.paintComponent(g);
    }
}
