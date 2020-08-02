import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ScriptingArea extends JTextArea {
    String path=null;

    public ScriptingArea(){
        setFont(new Font("Serif", Font.ITALIC, 16));
        setLineWrap(true);
    }

    public ScriptingArea(String path){
        setFont(new Font("Serif", Font.ITALIC, 16));
        setLineWrap(true);
        this.path=path;
    }

    public void open(){
        JFileChooser open = new JFileChooser();
        open.setApproveButtonText("Open");
        open.setCurrentDirectory(new File("C://Test_Automation//"));
        int actionDialog = open.showOpenDialog(this);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        path=open.getSelectedFile().toString();

        setText("");
        try {
            BufferedReader in = new BufferedReader(new FileReader(open.getSelectedFile()));
            String line = in.readLine();
            while (line != null) {
                append(line + "\n");
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        if(path==null) {
            JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            SaveAs.setCurrentDirectory(new File("C://Test_Automation//"));
            int actionDialog = SaveAs.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }
            path=SaveAs.getSelectedFile() + ".txt";
        }
        //File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAs(){
        JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        SaveAs.setCurrentDirectory(new File("C://Test_Automation//"));
        int actionDialog = SaveAs.showOpenDialog(this);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(SaveAs.getSelectedFile() + ".txt");
            write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
