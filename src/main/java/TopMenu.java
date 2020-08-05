import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenu extends JMenuBar implements ActionListener {

    JMenu menu = new JMenu("File");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem saveAs = new JMenuItem("Save As");
    ScriptingArea scriptingArea = null;
    public TopMenu(){
        open.addActionListener(this);
        save.addActionListener(this);
        menu.add(open);
        menu.add(save);
        menu.add(saveAs);
        add(menu);
    }

    public void update(ScriptingArea scriptingArea){
        this.scriptingArea=scriptingArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==open) {
            if(scriptingArea!=null){
                scriptingArea.open();
            }
        }else if(e.getSource()==save){
            if(scriptingArea!=null){
                scriptingArea.save();
            }
        }else if(e.getSource()==saveAs){
            if(scriptingArea!=null){
                scriptingArea.saveAs();
            }
        }
    }
}
