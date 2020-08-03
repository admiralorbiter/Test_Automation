import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class App extends JFrame implements MouseListener {
    Test_Automation automation = new Test_Automation();
    FileParser fileParser = new FileParser();
    FileList fileList = new FileList(fileParser.screenshotlist);
    TopMenu topMenu = new TopMenu();
    ScriptingArea scriptingArea = new ScriptingArea();
    FeatureBar featureBar;
    JPanel centerPane = new JPanel();
    CardLayout layout = new CardLayout();

    private int clicks=0;
    private Rectangle screenshotArea=new Rectangle();

    public App(boolean transparent){
        if(!transparent)
            init();
        else
            initTransparent();
    }

    public void initTransparent(){
        setUndecorated(true);
        featureBar = new FeatureBar();
        featureBar.setCurrentFeatureSelection("screenshotMode");

        add(new JPanel());
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
        setOpacity(.1f);
    }

    private void init(){
        setTitle("Test Automation");
        setJMenuBar(topMenu);
        centerPane.setLayout(layout);
        centerPane.setPreferredSize(new Dimension(1300, 900));
        centerPane.setSize(new Dimension(1300, 900));
        centerPane.add(automation);
        centerPane.add(scriptingArea);
        add(centerPane, BorderLayout.CENTER);
        centerPane.setVisible(true);
        layout.last(centerPane);

        featureBar = new FeatureBar();
        add(fileList, BorderLayout.WEST);
        add(featureBar, BorderLayout.NORTH);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
    }

    public String run(){
        topMenu.update(scriptingArea);
        String feature=featureBar.getCurrentFeatureSelection();
        //System.out.print(feature);
        if(feature.equals("switch_scripting")){
            //scriptingArea.setVisible(true);
            //automation.setVisible(false);
            layout.last(centerPane);
            feature="scripting";
            featureBar.setCurrentFeatureSelection(feature);
        }else if(feature.equals("screenshotMode")){
            if(isUndecorated()){
                feature="taking_screenshot";
                featureBar.setCurrentFeatureSelection(feature);
                System.out.println("Setting Opacity");
            }
        }else if(feature.equals("taking_screenshot")){

        }else if(feature.equals("switch_viewing_screenshot")){
            layout.first(centerPane);
            feature="viewing_screenshot";
            featureBar.setCurrentFeatureSelection(feature);
        } else if(feature.equals("viewing_screenshot")){

        }
        repaint();
        return feature;
    }

    public static void main(String[] args)
    {
        App app = new App(false);

        while(true){
            String feature=app.run();
            if(feature.equals("screenshotMode")){
                app.dispose();
                app = new App(true);
            }else if(feature.equals("programMode")){
                app.dispose();
                app = new App(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        //Check to make sure it is within the filelist width
        String filepath=null;

        if(featureBar.getCurrentFeatureSelection().equals("taking_screenshot")){
            if(clicks==0){
                screenshotArea=new Rectangle();
                screenshotArea.x=e.getPoint().x;
                screenshotArea.y=e.getPoint().y;
                clicks++;
            }else{
                if(e.getPoint().x>=screenshotArea.x && e.getPoint().y>=screenshotArea.y){
                    screenshotArea.width=e.getPoint().x-screenshotArea.x;
                    screenshotArea.height=e.getPoint().y-screenshotArea.y;
                    Screenshot.takeScreenshot("test_button", screenshotArea, fileParser);
                }else{
                    System.out.println("Error - x and/or y not greater than first point, try again");
                }
                featureBar.setCurrentFeatureSelection("programMode");
                clicks=0;
            }
        }else{
            if(e.getPoint().x<=300)
                filepath=fileList.checkmouseclick(e.getPoint());

            if(filepath!=null){
                System.out.println(filepath+" picked from list.");
                automation.openImage(fileParser.openImage(filepath));
                featureBar.setCurrentFeatureSelection("switch_viewing_screenshot");
            }
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
