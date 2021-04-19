

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.math.*;
import java.util.*;
import java.io.File.*;


import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.Timer;

// this class adds to buttons to a screen - and makes one of them clicable

public class BasicGUI extends JFrame implements ActionListener, ActiveEvent
{


    // attributes
    JButton button1, button2, button3, button4;
    JPanel panel, panel2, panel3,panel4;
    JLabel label1, label2, label3;
    JTextField textField1, textField2;
    Timer time1;
    JComboBox box1;


    public int thenumber =0  ;
    public File[] newfile = new File[200];
    public String dogo = "dog";
    public int count = 0;
    public String flist[] = new String[count];
    public String country[] = {"Dick","Dick"};
    public File f = new File("C:\\");
    public File[] files1;
    public String feckery = "feck";
    public int filewordcounter = 0;

    // constructor - sets up the screen as it first appears when you
    // open it

    BasicGUI (String title)
    {
        super("File Searcher");

        try {

            final int[] numbertocompare = {101};
            final int[] x = {101};
            final int[] guess = {0};
            newfile[0] = f;


            String thefiles2[] = new String[100];
            final int transverse = 0;


            setSize(500, 500);
            setLayout(new FlowLayout()); // sets the layout to a flowlayout


            label2 = new JLabel("Make a guess");
            label3 = new JLabel("Search for textfiles");


            button1 = new JButton("Change Directory");
            button2 = new JButton("Directories are");
            button3 = new JButton("Go Back to Home Drive");
            button4 = new JButton("Search for textfiles");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dogo = box1.getSelectedItem().toString();
                    JOptionPane.showMessageDialog(null, "Number generated!");
                    newfile[thenumber + 1] = new File(dogo); // FIXME: 15/04/2021
                    System.out.println(newfile[thenumber + 1]);
                    thenumber = thenumber + 1;
                    File duck = new File(dogo);
                    box1.removeAllItems();

                    FileFilter filter = new FileFilter() {
                        public boolean accept(File f) {

                            return f.isDirectory();
                        }

                    };
                    File[] files = duck.listFiles(filter);

                    System.out.println("Files are:");

                    // Display the names of the files
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i].getName());
                        box1.addItem(files[i]);
                        count = count + 1;
                    }







                }

            });
            FileFilter filter = new FileFilter() {
                public boolean accept(File f) {

                    return f.isDirectory();
                }

            };

            File[] files = newfile[0].listFiles(filter);

            if (thenumber == 0){
                System.out.println("Files are:");

            // Display the names of the files
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i].getName());
                    count = count + 1;
                }
                box1 = new JComboBox(files);
            }



            textField1 = new JTextField( "10", 20);
            textField2 = new JTextField( "10", 20);
            button3.addActionListener(new ActionListener() {  // BUTTON 3

                @Override
                public void actionPerformed(java.awt.event.ActionEvent arg0) {
                    box1.removeAllItems();
                    File[] files = newfile[0].listFiles(filter);
                    FileFilter filter = new FileFilter() {
                        public boolean accept(File f) {

                            return f.isDirectory();
                        }

                    };


                        System.out.println("Files are:");

                        // Display the names of the files
                        for (int i = 0; i < files.length; i++) {
                            System.out.println(files[i].getName());
                            box1.addItem(files[i]);
                            count = count + 1;
                        }


                }
            });

            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) { // BUTTON 4
                    filewordcounter = 0;
                    boolean flag = false;


                    FileInputStream fin[] = new FileInputStream[100];
                    dogo = box1.getSelectedItem().toString();
;

                    File duck = new File(dogo);

                    FileFilter filter = new FileFilter() {
                        public boolean accept(File f) {

                            return f.getName().endsWith("txt");
                        }

                    };
                    File[] files = duck.listFiles(filter);
                    File folder = new File("/path/to/files");
                    File[] listOfFiles = folder.listFiles();
                    System.out.println("Files are:");
                    String word = "dog";
                    String[] words=null;  //Intialize the word Array
                    // Display the names of the files
                    for (int i = 0; i < files.length; i++) {
                        filewordcounter = 0;
                        box1.addItem(files[i]);
                        feckery = dogo + "\\" + files[i].getName();

                        Scanner scanner = new Scanner(feckery);
                        System.out.println("Contents of the line");
                        //Reading the contents of the file
                        Scanner sc2 = null;
                        try {
                            sc2 = new Scanner(new FileInputStream(feckery));
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        while(sc2.hasNextLine()) {
                            String line = sc2.nextLine();
                            if(line.indexOf(word)!=-1) {
                                flag = true;
                                filewordcounter = filewordcounter+1;
                            }
                        }
                        if(flag) {
                            System.out.println( files[i]+" contains the specified word");
                            System.out.println("Number of occurrences is: "+filewordcounter);
                        }


                    }

                }




            });



            panel = new JPanel(new FlowLayout());
            panel2 = new JPanel(new FlowLayout());
            panel3 = new JPanel(new FlowLayout());
            panel4 = new JPanel(new FlowLayout());


            panel.add(box1);
            panel.add(button1);

            panel2.add(label2);
            panel2.add(button3);

            panel3.add(label3);
            panel3.add(textField1);
            panel3.add(button4);


            add(panel);
            add(panel2);
            add(panel3);
            add(panel4);


            setVisible(true);



        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }



    }



    public static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }


    @Override
    public void dispatch() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}



