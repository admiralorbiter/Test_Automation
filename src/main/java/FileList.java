import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileList extends JPanel {

    ArrayList<String> fileList;

    public FileList(ArrayList<String> fileList)
    {
        setSize(300, 900);
        setPreferredSize(new Dimension(300, 900));
        this.fileList=fileList;
    }

    public String checkmouseclick(Point mouseclick){

        int topOffset=90;

        //First check to make sure it is within the height of the list
        if(mouseclick.y<topOffset+fileList.size()*20){
            return fileList.get((int)((mouseclick.y-topOffset)/20));
        }

        return null;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.gray);
        for(int i=0; i<fileList.size(); i++) {
            g.setColor(Color.white);
            g.drawRect(0, 20+(i-1)*20, 300, 20);
            g.setColor(Color.black);
            g.fillRect(1, 20+(i-1)*20+1, 300-2, 20-2);
            g.setColor(Color.white);
            g.drawString(fileList.get(i), 0, 15+i*20);
        }
    }
}
