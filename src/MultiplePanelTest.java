import java.awt.*;
import javax.swing.*;
public class MultiplePanelTest extends JFrame {
    private JPanel mainPanel, subPanel1, subPanel2;
    public MultiplePanelTest() {
        setTitle("MultiPanel Test");
        mainPanel = new JPanel(); // main panel
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(new JLabel("Main Panel", SwingConstants.CENTER));
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        subPanel1 = new JPanel(); // sub-panel 1
        subPanel1.add(new JLabel("Panel One", SwingConstants.CENTER));
        subPanel1.setBackground(Color.red);
        subPanel2 = new JPanel(); // sub-panel 2
        subPanel2.setBackground(Color.blue);
        subPanel2.add(new JLabel("Panel Two", SwingConstants.CENTER));
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);
        add(mainPanel);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MultiplePanelTest();
    }
}
