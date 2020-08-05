import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeatureBar extends JPanel {
    JButton scripting_area = new JButton("Scripting Area");
    JButton screenshot_button = new JButton("Screenshot Area");
    JButton fullscreen_screenshot_button = new JButton("Fullscreen Shot");


    private String currentFeatureSelection="scripting";

    public FeatureBar(){
        scripting_area.setVerticalTextPosition(AbstractButton.CENTER);
        scripting_area.setHorizontalTextPosition(AbstractButton.LEADING);
        scripting_area.setActionCommand("scripting");

        screenshot_button.setVerticalTextPosition(AbstractButton.CENTER);
        screenshot_button.setHorizontalTextPosition(AbstractButton.LEADING);
        screenshot_button.setActionCommand("taking_screenshot");

        fullscreen_screenshot_button.setVerticalTextPosition(AbstractButton.CENTER);
        fullscreen_screenshot_button.setHorizontalTextPosition(AbstractButton.LEADING);
        fullscreen_screenshot_button.setActionCommand("taking_fullscreenshot");

        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(scripting_area);
        add(screenshot_button);
        add(fullscreen_screenshot_button);
    }

    public void addActionListeners(ActionListener actionListener){
        screenshot_button.addActionListener(actionListener);
        scripting_area.addActionListener(actionListener);
        fullscreen_screenshot_button.addActionListener(actionListener);
    }

    public String getCurrentFeatureSelection(){
        return currentFeatureSelection;
    }

    public void setCurrentFeatureSelection(String featureSelection){
        if(featureSelection!=currentFeatureSelection)
            System.out.println("Feature change to "+featureSelection);
        this.currentFeatureSelection=featureSelection;
    }
}
