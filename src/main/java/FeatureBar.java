import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeatureBar extends JPanel implements ActionListener {
    JButton scripting_area = new JButton("Scripting Area");
    JButton screenshot_button = new JButton("screenshot_button");
    private String currentFeatureSelection="scripting";
    public FeatureBar(){
        scripting_area.setVerticalTextPosition(AbstractButton.CENTER);
        scripting_area.setHorizontalTextPosition(AbstractButton.LEADING);
        scripting_area.setActionCommand("scripting");
        scripting_area.addActionListener(this);
        screenshot_button.setVerticalTextPosition(AbstractButton.CENTER);
        screenshot_button.setHorizontalTextPosition(AbstractButton.LEADING);
        screenshot_button.setActionCommand("taking_screenshot");
        screenshot_button.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(scripting_area);
        add(screenshot_button);
    }

    public String getCurrentFeatureSelection(){
        return currentFeatureSelection;
    }

    public void setCurrentFeatureSelection(String featureSelection){
        if(featureSelection!=currentFeatureSelection)
            System.out.println("Feature change to "+featureSelection);
        this.currentFeatureSelection=featureSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("scripting")){
            currentFeatureSelection="scripting";
        }else if(e.getActionCommand().equals("taking_screenshot")){
            currentFeatureSelection="screenshotMode";
            System.out.println(currentFeatureSelection);
        }
    }
}
