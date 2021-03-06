import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class App extends JFrame implements MouseListener, KeyListener {
    Test_Automation automation = new Test_Automation();
    FileParser fileParser = new FileParser();
    FileList fileList = new FileList(fileParser.screenshotlist);
    TopMenu topMenu = new TopMenu();
    ScriptingArea scriptingArea = new ScriptingArea();
    FeatureBar featureBar;
    JPanel centerPane = new JPanel();
    CardLayout layout = new CardLayout();
    TransparentPanel transparentPanel = new TransparentPanel();

    private int clicks=0;
    private Rectangle screenshotArea=new Rectangle();

    public App(boolean transparent){
        if(!transparent)
            init();
        else
            initTransparent(null);
    }

    public App(boolean transparent, String feature){
        if(!transparent)
            initBackground(feature);
        else
            initTransparent(feature);
    }

    public App(String feature, ScriptingArea scriptingArea){
        this.scriptingArea=scriptingArea;
        initBackground(feature);
    }


    public void initTransparent(String feature){
        setUndecorated(true);
        featureBar = new FeatureBar();
        featureBar.setCurrentFeatureSelection(feature);

        add(transparentPanel);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        setOpacity(.01f);
    }

    public void initBackground(String feature){
        setUndecorated(true);
        featureBar = new FeatureBar();
        featureBar.setCurrentFeatureSelection(feature);

        add(new JPanel());
        setSize(1, 1);
        System.out.println(getSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
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
        featureBar.addActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("scripting")){
                    layout.last(centerPane);
                }else if(e.getActionCommand().equals("taking_screenshot")){
                    featureBar.setCurrentFeatureSelection("screenshot_mode");
                }else if(e.getActionCommand().equals("taking_fullscreenshot")){
                    featureBar.setCurrentFeatureSelection("fullscreenshot_mode");
                }else if(e.getActionCommand().equals("run_script")){
                    featureBar.setCurrentFeatureSelection("run_script");
                }
            }
        });
        add(fileList, BorderLayout.WEST);
        add(featureBar, BorderLayout.NORTH);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
    }

    public String run(){
        topMenu.update(scriptingArea);
        String feature=featureBar.getCurrentFeatureSelection();
      if(feature.equals("screenshot_mode")||feature.equals("fullscreenshot_mode")||feature.equals("run_script")){
        if(isUndecorated()){
            if(feature.equals("fullscreenshot_mode")){
                Screenshot.takeScreenshot(fileParser);
                feature="program_mode";
            }
            else if(feature.equals("screenshot_mode"))
                feature="taking_screenshot";
            else
                feature="running_script";
            featureBar.setCurrentFeatureSelection(feature);
            System.out.println("Setting Opacity");
        }
      }else if(feature.equals("switch_viewing_screenshot")){
          layout.first(centerPane);
      }else if(feature.equals("running_script")){
          System.out.println("Processing: "+scriptingArea.getText());
          Interpreter interpreter = new Interpreter(fileParser, scriptingArea.getText());
          interpreter.run();
          feature="program_mode";
          featureBar.setCurrentFeatureSelection(feature);
      }
        repaint();
        return feature;
    }

    public static void main(String[] args)
    {
        App app = new App(false);

        while(true){
            String feature=app.run();
            //TODO: Add flag on feature that is called with these three to dispose so I don't have to add it here everytime
            if(feature.equals("screenshot_mode")||feature.equals("fullscreenshot_mode")){
                app.dispose();
                app = new App(true, feature);
            }else if(feature.equals("program_mode")){
                app.dispose();
                app = new App(false);
            }else if(feature.equals("run_script")){
                app.dispose();
                app = new App(feature, app.scriptingArea);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Interpreter interpreter = new Interpreter("Click 10 11");
        //interpreter.eatToken(interpreter.getNextToken());

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
                    Screenshot.takeScreenshot(screenshotArea, fileParser);
                }else{
                    System.out.println("Error - x and/or y not greater than first point, try again");
                }
                featureBar.setCurrentFeatureSelection("program_mode");
                clicks=0;
            }
        }else{
            if(e.getPoint().x<=300)
                filepath=fileList.checkmouseclick(e.getPoint());

            if(filepath!=null){
                System.out.println(filepath+" picked from list.");
                automation.openImage(fileParser.openImage(filepath));
                layout.first(centerPane);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
