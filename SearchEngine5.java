import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class BasicGUI extends JFrame implements ActionListener, ActiveEvent
{


    // attributes
    JButton button1, button2, button3;
    JPanel panel, panel2, panel3,panel4;
    JLabel label1, label2;
    JTextField textField1;
    JComboBox box1;

    public int filenumber =0;  // This is used to iterate for newfile
    public File[] newfile = new File[200];  // This is used to store addresses
    public String selectedaddress = "dog";  //  This is used to convert the current search space to a string
    public int count = 0;  // This is used for counting
    public File f = new File("C:\\");  // This is used to go back to the
    public String wordtosearch;  // This is used for the word to be searched
    public int filewordcounter = 0;  // This is ued to counte the word that is given


    BasicGUI (String title)
    {
        super("File Searcher");

        try {


            newfile[0] = f; //  For button 2 to go back to the home drive
            setSize(500, 500);
            setLayout(new FlowLayout()); // sets the layout to a flowlayout


            //  Where the labels, buttons and textfields are.
            label1 = new JLabel("Current Search Space");
            label2 = new JLabel("Search for textfiles");
            button1 = new JButton("Change Directory");
            button2 = new JButton("Go Back to Home Drive");
            button3 = new JButton("Search for textfiles");
            textField1 = new JTextField( "10", 20);


            // This is used for the directories, the first items you see in the dropdown menu
            FileFilter filter = new FileFilter() {  // This helps filter the directory to only directories
                public boolean accept(File f) {

                    return f.isDirectory();
                }

            };

            File[] files = newfile[0].listFiles(filter);  // This calls to filter above

            if (filenumber == 0){  // Helps reset
                System.out.println("Files are:");

                // Display the names of the files
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i].getName());
                    count = count + 1;
                }
                box1 = new JComboBox(files);  // Fills the combobox
            }

            // This is used to change directory
            button1.addActionListener(new ActionListener() {  // Button 1
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    selectedaddress = box1.getSelectedItem().toString();  // this is used to get the filepath to a string
                    JOptionPane.showMessageDialog(null, "Search Space Changed");  // Indicate search space has changed
                    newfile[filenumber + 1] = new File(selectedaddress);  // Had to use a file array because 'File' values weren't mutable
                    System.out.println(newfile[filenumber + 1]);  // This is used to check which address you are on
                    filenumber = filenumber + 1;  // This allows the user to go through address multiple times
                    File newaddress = new File(selectedaddress);  // newaddress converts selectedaddress to File
                    box1.removeAllItems();  // To change the items in the combobox had to remove everthing first

                    FileFilter filter = new FileFilter() {  // Check if it's a directory
                        public boolean accept(File f) {

                            return f.isDirectory();
                        }

                    };
                    File[] files = newaddress.listFiles(filter);  // This calls to filter above, iterates through the new address

                    System.out.println("Files are:");

                    // Display the names of the files
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i].getName());
                        box1.addItem(files[i]);  // This gets the filtered items and names them
                        count = count + 1;
                    }
                }
            });
            
            // This will make the user go back to the home drive
            button2.addActionListener(new ActionListener() {  // BUTTON 2

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

            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { // BUTTON 3
                    filewordcounter = 0;
                    selectedaddress = box1.getSelectedItem().toString();


                    File duck = new File(selectedaddress);

                    FileFilter filter = new FileFilter() {
                        public boolean accept(File f) {
                            return f.getName().endsWith("txt");
                        }

                    };
                    File[] files = duck.listFiles(filter);
                    System.out.println("Files are:");

                    for (int i = 0; i < files.length; i++) {
                        filewordcounter = 0;
                        box1.addItem(files[i]);
                        wordtosearch = selectedaddress + "\\" + files[i].getName();

                        try {
                            File f1=new File(wordtosearch); //Creation of File Descriptor for input file
                            String[] words= null;  //Intialize the word Array
                            FileReader fr = new FileReader(f1);  //Creation of File Reader object
                            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
                            String s;

                            String input=textField1.getText();   // Input word to be searched
                            int count=0;   //Intialize the word to zero
                            while((s=br.readLine())!=null)   //Reading Content from the file
                            {

                                words=s.split("[, ?.@!]+");  //Split the word using space


                                for (String word : words)
                                {


                                    if (word.equalsIgnoreCase(input))   //Search for the given word
                                    {
                                        count++;    //If Present increase the count by one
                                    }
                                }
                            }
                            if(count!=0)  //Check for count not equal to zero
                            {
                                System.out.println("The word '" + input +"' is present for "+count+ " Times in the file " + files[i].getName());
                            }


                            fr.close();
                        } catch (IOException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
            });



            panel = new JPanel(new FlowLayout());
            panel2 = new JPanel(new FlowLayout());
            panel3 = new JPanel(new FlowLayout());
            panel4 = new JPanel(new FlowLayout());

            panel.add(label1);  // Panel 1
            panel.add(box1);
            panel.add(button1);


            panel2.add(button2);  // Panel 2

            panel3.add(label2);  // Panel 3
            panel3.add(textField1);
            panel3.add(button3);


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


    @Override
    public void dispatch() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}



