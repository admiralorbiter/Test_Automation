import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {

    Point one = null;
    Point two = null;

    public TransparentPanel(){

    }

    public void update(Point one, Point two){
        if(one!=null){
            this.one=one;
        }

        if(two!=null){
            this.two=two;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        setOpaque(false);
        g.setColor(Color.blue);
        g.drawRect(10, 10, 500, 500);
        if(one!=null && two!=null){
            g.setColor(Color.blue);
            g.drawRect(one.x, one.y, two.x, two.y);
        }
    }
}
