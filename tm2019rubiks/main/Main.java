package tm2019rubiks.main;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tm2019rubiks.gui.RCube2D;
import tm2019rubiks.gui.RCube3D;
import tm2019rubiks.gui.RCubeMover;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.solve.Solver;
import tm2019rubiks.tables.g3.GenG2;
import tm2019rubiks.tables.g4.GenG3;
import tm2019rubiks.utils.Utils;

//Main program
public class Main  {
    
    //there must be only one instance of this class
    public static Solver solver;
    
    public static HashMap<String, String> m, m2;
    
    //the cube being displayed and manipulated
    private static RCube cube;
    
    //variable that determines of the 3d view is on or off
    private static boolean on3d;
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        
        solver = new Solver();
        
        
        //getting solved cube
        cube = new RCube();//RCube.EDGE_FLIP);
        on3d = false;
        
        //layout for the whole frame
        GridBagLayout g = new GridBagLayout();
        
        //create window
        final JFrame frame = new JFrame ("tm2019rubiks");
        
        //try setting system l&f
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //panel that will contain all the JComponents
        final JPanel panel = new JPanel(g);
        GridBagConstraints c = new GridBagConstraints();
        
        //panel with cardLayout to be able to switch between 3d and 2d view
        CardLayout cardLayout = new CardLayout();
        final JPanel rcubeGUI = new JPanel(cardLayout);
        
        //set panel size and add it to the window
        panel.setSize(800,600);
        frame.add(panel);
        
        //adding buttons
        final JButton buttonF = new JButton("F");
        final JButton buttonFP = new JButton("F'");
        final JButton buttonR = new JButton("R");
        final JButton buttonRP = new JButton("R'");
        final JButton buttonB = new JButton("B");
        final JButton buttonBP = new JButton("B'");
        final JButton buttonL = new JButton("L");
        final JButton buttonLP = new JButton("L'");
        final JButton buttonU = new JButton("U");
        final JButton buttonUP = new JButton("U'");
        final JButton buttonD = new JButton("D");
        final JButton buttonDP = new JButton("D'");
        final JButton button3D = new JButton("3D");
        
        //making them unfocusable, since only the focused object can handle keyevents
        //so i have to make sure that the actual component with the keylistener
        //never loses focus
        buttonF.setFocusable(false);
        buttonFP.setFocusable(false);
        buttonR.setFocusable(false);
        buttonRP.setFocusable(false);
        buttonB.setFocusable(false);
        buttonBP.setFocusable(false);
        buttonL.setFocusable(false);
        buttonLP.setFocusable(false);
        buttonU.setFocusable(false);
        buttonUP.setFocusable(false);
        buttonD.setFocusable(false);
        buttonDP.setFocusable(false);
        button3D.setFocusable(false);
        panel.setFocusable(false);
        //textField.setFocusable(false);
        
        //setting the same action commands as the keys that you have to press
        //for a move, so that the keyChars and ActionCommand can be processed
        //in the same function
        buttonF.setActionCommand("f");
        buttonFP.setActionCommand("F");
        buttonR.setActionCommand("r");
        buttonRP.setActionCommand("R");
        buttonB.setActionCommand("b");
        buttonBP.setActionCommand("B");
        buttonL.setActionCommand("l");
        buttonLP.setActionCommand("L");
        buttonU.setActionCommand("u");
        buttonUP.setActionCommand("U");
        buttonD.setActionCommand("d");
        buttonDP.setActionCommand("D");
        
        
        
        //try initializing the RCube3D component.
        //if it fails, the 3d/2d button will be hidden
        RCube3D cube3d = null;
        
        try {
            cube3d = new RCube3D(cube);
        }
        catch(InternalError | UnsatisfiedLinkError e2){
            button3D.hide();
        }
        final RCube2D cube2d = new RCube2D(cube, 60);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=7;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(rcubeGUI, c);
        
        rcubeGUI.add(cube2d, "2D");
        if(cube3d != null){
            rcubeGUI.add(cube3d, "3D");
        }
        
        
        
        
        
        c.weightx = 0.05;
        c.weighty = 0.05;
        c.gridheight=1;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(buttonF, c);
        
        c.gridx = 2;
        panel.add(buttonFP, c);
        
        c.gridy = 1;
        c.gridx = 1;
        panel.add(buttonR, c);
        
        c.gridx = 2;
        panel.add(buttonRP, c);
        
        c.gridy = 2;
        c.gridx = 1;
        panel.add(buttonB, c);
        
        c.gridx = 2;
        panel.add(buttonBP, c);
        
        c.gridy = 3;
        c.gridx = 1;
        panel.add(buttonL, c);
        
        c.gridx = 2;
        panel.add(buttonLP, c);
        
        c.gridy = 4;
        c.gridx = 1;
        panel.add(buttonU, c);
        
        c.gridx = 2;
        panel.add(buttonUP, c);
        
        c.gridy = 5;
        c.gridx = 1;
        panel.add(buttonD, c);
        
        c.gridx = 2;
        panel.add(buttonDP, c);
        
        c.gridy = 6;
        c.gridx = 1;
        c.gridwidth = 2;
        c.weighty = 0.015;
        panel.add(button3D, c);
        
        
        cube2d.setFocusable(false);
        if(cube3d != null){
            cube3d.setFocusable(false);
        }
        
        //create listener and add it to the buttons
        RCubeMover listener = new RCubeMover(cube2d, cube3d);
        
        //since each has a different actionCommand, they'll behave differently
        buttonF.addActionListener(listener);
        buttonFP.addActionListener(listener);
        buttonR.addActionListener(listener);
        buttonRP.addActionListener(listener);
        buttonB.addActionListener(listener);
        buttonBP.addActionListener(listener);
        buttonL.addActionListener(listener);
        buttonLP.addActionListener(listener);
        buttonU.addActionListener(listener);
        buttonUP.addActionListener(listener);
        buttonD.addActionListener(listener);
        buttonDP.addActionListener(listener);
        
        //set behaviour of 3d button
        button3D.addActionListener((ActionEvent e) -> {
            if(!on3d){
                CardLayout cl = (CardLayout) rcubeGUI.getLayout();
                cl.show(rcubeGUI, "3D");
                button3D.setText("2D");
                
            }
            else{
                CardLayout cl = (CardLayout) rcubeGUI.getLayout();
                cl.show(rcubeGUI, "2D");
                button3D.setText("3D");
            }
            on3d = !on3d;
        });
        
        //make it so that only the component containing the RCube2D and RCube3D
        //views can be focused, because it's the component that has the keylistener
        rcubeGUI.setFocusable(true);
        rcubeGUI.requestFocus();
        rcubeGUI.addKeyListener(new RCubeMover(cube2d, cube3d));
        
        //show window
        frame.setSize(845,590);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //Test.test(cube);
        //GenG1.gen();
        
        
        //m = GenG2.treeGen();
        //m2 = GenG3.treeGen();
        System.out.println("starting thistletest");
        Utils.thistleTest();
        
        
        
    }
    
    
    
}