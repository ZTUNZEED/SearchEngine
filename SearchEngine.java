import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileFilter;
import java.math.*;
import java.util.Random;
import java.io.File;
import java.io.FilenameFilter;
import java.io.File.*;



import javax.swing.*;

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
    // constructor - sets up the screen as it first appears when you
    // open it

    BasicGUI (String title)
    {
        super("File Searcher");

        try {

            final int[] numbertocompare = {101};
            final int[] x ={101};
            final int[] guess ={0};
            final String country[] = {"Dick","Dick"};
            int count = 0;
            File f = new File("C:\\Users");
            newfile[0] = f;


            String thefiles2[] = new String[100];
            final int transverse = 0;


            setSize(500,500);
            setLayout(new FlowLayout()); // sets the layout to a flowlayout


            label2 = new JLabel("Make a guess");


            button1 = new JButton("Change Directory");
            button2 = new JButton("Directories are");
            button3 = new JButton("Check your guess");
            button4 = new JButton("Give Up?");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dogo = newfile[thenumber] + "\\" + box1.getSelectedItem().toString();
                    JOptionPane.showMessageDialog(null,"Number generated!");
                    newfile[thenumber+1] = new File(dogo); // FIXME: 15/04/2021
                    System.out.println(newfile[thenumber+1]);
                    thenumber = thenumber + 1;


                }
            });



            // Create a file object

            // Create a FileFilter
            FileFilter filter = new FileFilter() {
                public boolean accept (File f)
                {

                    return f.isDirectory();
                }

            };

            // Get all the names of the files present
            // in the given directory
            // which are text files
            File[] files = newfile[thenumber].listFiles(filter);
            System.out.println("Files are:");

            // Display the names of the files
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
                count = count + 1;

            }
            String flist[] = new String[count];
            for (int i = 0; i < files.length; i++) {

                flist[i] = files[i].getName();


            }
            box1 = new JComboBox(flist);

            textField1 = new JTextField( "10", 20);
            textField2 = new JTextField( "10", 20);
            button3.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent arg0) {
                    numbertocompare[0] = Integer.parseInt(textField1.getText().trim());
                    if (x[0] == 101){
                        JOptionPane.showMessageDialog(null,"Please Generate number first");
                    }
                    else if( numbertocompare[0] >= 0 && numbertocompare[0] <= 100){
                        if(x[0] == numbertocompare[0]){
                            JOptionPane.showMessageDialog(null,"Guess was exactly correct. You guessed "+guess[0]+" times.");
                            guess[0] = 0;
                            x[0] = 101;

                        }
                        else if (x[0] > numbertocompare[0]){
                            JOptionPane.showMessageDialog(null, "Guess was too small!");
                            guess[0]++;
                        }
                        else if (x[0] < numbertocompare[0]){
                            JOptionPane.showMessageDialog(null,"Guess was too high!");
                            guess[0]++;
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Invalid input");
                        }
                    }
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (x[0] == 101){
                        JOptionPane.showMessageDialog(null, "You haven't click generate number");
                    }
                    else if( x[0] >= 0 && x[0] <= 100){
                        JOptionPane.showMessageDialog(null, "Your amount of guesses is "+guess[0]+" and the number was"+ x[0]);
                        guess[0] = 0;
                        x[0] = 101;
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
            panel2.add(textField1);


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



