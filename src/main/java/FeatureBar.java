import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeatureBar extends JPanel implements ActionListener {
    JButton scripting_area = new JButton("Scripting Area");
    private String currentFeatureSelection="scripting";
    public FeatureBar(){
        scripting_area.setVerticalTextPosition(AbstractButton.CENTER);
        scripting_area.setHorizontalTextPosition(AbstractButton.LEADING);
        scripting_area.setActionCommand("scripting");
        scripting_area.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(scripting_area);
    }

    public String getCurrentFeatureSelection(){
        return currentFeatureSelection;
    }

    public void setCurrentFeatureSelection(String featureSelection){
        this.currentFeatureSelection=featureSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("scripting")){
            currentFeatureSelection="scripting";
        }
    }
}