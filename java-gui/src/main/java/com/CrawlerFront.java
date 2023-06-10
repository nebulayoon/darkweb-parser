package com;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

import com.mongodb.config.MongoConfig;

public class CrawlerFront extends JFrame {
    private JTextField filename = new JTextField(), dir = new JTextField();

    private JButton crawling = new JButton("Crawling"), save = new JButton("Save"), fileSelectB = new JButton("file choose");

    private File file = null;


    private LayoutManager Layout_Manager = new FlowLayout();
    private JLabel J_Label = new JLabel(), errorLabel = new JLabel();


    public CrawlerFront() {
        JPanel p = new JPanel();

        p.add(J_Label);
        fileSelectB.addActionListener(new CSVFileL());
        p.add(fileSelectB);

        crawling.addActionListener(new CrawlingL());
        p.add(crawling);
        save.addActionListener(new SaveL());
        p.add(save);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);

        p.add(errorLabel);

        cp.add(p, BorderLayout.CENTER);
    }

    class CrawlingL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ParserMain parser = new ParserMain();
            if(file == null){
                errorLabel.setText("no selected file");
            } else {
                try{
                    parser.run(file.getCanonicalPath());
                } catch(IOException err){
                    err.printStackTrace();
                }
            }
        }
    }

    class CSVFileL implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JFileChooser J_File_Chooser = new JFileChooser();

            J_File_Chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int option = J_File_Chooser.showOpenDialog(CrawlerFront.this);
            if(option == JFileChooser.APPROVE_OPTION){
                file = J_File_Chooser.getSelectedFile();
                J_Label.setText("Selected: " + file.getName());
            }
            else{
                J_Label.setText("Command Canceled");
            }
        }
    }

    class SaveL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(CrawlerFront.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                String FileName = filename.getText();
                String DirAddr = dir.getText();
                try{
                    FileWriter fw = new FileWriter(DirAddr + "\\" + FileName, true);
                    MongoConfig mongo = new MongoConfig();
                    ArrayList asd = mongo.saveListData();

                    for (int i = 0; i < asd.size(); i ++) {
                        fw.append(((CharSequence) asd.get(i)));
                    }
                    JOptionPane.showMessageDialog(null, "Data Saved Successfully");
                    fw.close();
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null, "There was an error while writing the data");
                }
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }


        }
    }

    public static void main(String[] args) {
        run(new CrawlerFront(), 500, 200);
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
