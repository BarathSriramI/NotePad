import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener
{
    JFrame f;
    JTextArea t;
    JMenuBar mb;
    note()
    {
        f= new JFrame("Notepad");
        t= new JTextArea();
        mb=new JMenuBar();
        JMenu file= new JMenu("File");
        JMenuItem f1= new JMenuItem("New");
        JMenuItem f2= new JMenuItem("save");
        JMenuItem f3= new JMenuItem("Open");
        JMenuItem f4= new JMenuItem("print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        // for edit menu
        JMenu edit= new JMenu("Edit");
        JMenuItem e1= new JMenuItem("Cut");
        JMenuItem e2= new JMenuItem("Copy");
        JMenuItem e3= new JMenuItem("Paste");

        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        // create close as jmenuitem
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        // adding the menus to the mb
        mb.add(file);
        mb.add(edit);
        mb.add(close);

        // adding the testarea and menubar to the frame
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(3000,3000);
        f.setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        String s= e.getActionCommand();
        switch(s)
        {
            case "New":
                t.setText("");
                break;

            case "save":
                JFileChooser j= new JFileChooser("D:");

                int r=j.showSaveDialog(null);
                if(r==0)
                {
                    File fi= new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw= new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(f,"the user has cancelled the operation");
                }
                break;

            case "Open":
                JFileChooser jo= new JFileChooser("D:");

                int ro=jo.showSaveDialog(null);
                if(ro==0)
                {
                    File fi= new File(jo.getSelectedFile().getAbsolutePath());
                    try {
                       FileReader fw= new FileReader(fi);
                       BufferedReader bw= new BufferedReader(fw);
                       String s1="",s2="";
                       s1=bw.readLine();
                       while((s2=bw.readLine())!=null)
                       {
                           s1=s1  +"\n"+ s2;
                       }
                       t.setText(s1);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(f, "the user has cancelled the operation");
                }
                break;
                    case "print" :
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
        }
    }
    public static void main(String[] args) {
        note n= new note();

    }
}


