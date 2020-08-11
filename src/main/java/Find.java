import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Find extends JFrame implements ActionListener {
    private JTextArea text = null;
    private JLabel labelFind, labelReplace;
    private JTextField fieldFind, fieldReplace;
    private JButton buttonFind, buttonNext, buttonReplaceAll;
    private int findIndex=0;

    public Find(JTextArea text){
        setLayout(null);
        this.text=text;
        labelFind = new JLabel("Find: ");
        labelReplace = new JLabel("Replace: ");
        fieldFind = new JTextField(40);
        fieldReplace = new JTextField(40);

        buttonFind = new JButton("Find");
        buttonNext = new JButton("Next");
        buttonReplaceAll = new JButton("Replace All");

        labelFind.setBounds(10, 10, 75, 25);
        labelReplace.setBounds(10, 20+25, 75, 25);
        fieldFind.setBounds(10+75, 10, 100, 25);
        fieldReplace.setBounds(10+75, 20+25, 100, 25);
        buttonFind.setBounds(200, 10, 100, 25);
        buttonNext.setBounds(200, 10+30, 100, 25);
        buttonReplaceAll.setBounds(200, 10+60, 100, 25);

        add(labelFind);
        add(labelReplace);
        add(fieldFind);
        add(fieldReplace);
        add(buttonFind);
        add(buttonNext);
        add(buttonReplaceAll);

        buttonFind.addActionListener(this);
        buttonNext.addActionListener(this);
        buttonReplaceAll.addActionListener(this);

        setSize(325, 150);
        setLocationRelativeTo(text);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public void find(){
        int startIndex=text.getText().toLowerCase().indexOf(fieldFind.getText().toLowerCase());
        if(startIndex<0 || startIndex>=text.getText().length()){
            System.out.println("Could not find "+fieldFind.getText());
            return;
        }

        int endIndex=startIndex+fieldFind.getText().length();
        text.select(startIndex, endIndex);
    }

    public void replace(){
        text.setText(text.getText().toLowerCase().replaceAll(fieldFind.getText().toLowerCase(), fieldReplace.getText()));
    }

    public void next(){
        int startIndex=text.getText().toLowerCase().indexOf(fieldFind.getText().toLowerCase(), findIndex);
        if(startIndex<0 || startIndex>=text.getText().length()){
            System.out.println("End of File"+fieldFind.getText());
            findIndex=0;
            return;
        }
        int endIndex=startIndex+fieldFind.getText().length();
        text.select(startIndex, endIndex);
        findIndex=endIndex+1;
        System.out.println(findIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonFind){
            find();
        }else if(e.getSource()==buttonReplaceAll){
            replace();
        }else if(e.getSource()==buttonNext){
            next();
        }
    }
}
