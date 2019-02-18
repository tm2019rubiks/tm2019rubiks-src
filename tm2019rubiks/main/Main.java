package tm2019rubiks.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tm2019rubiks.gui.RCube2D;
import tm2019rubiks.gui.RCubeMover;
import tm2019rubiks.rcube.RCube;



public class Main  {
    private static RCube cube;
    static int rotIters = 0;
    private static int frames;
    private static boolean start = false;
    public static float currRotY, currRotX, rotIter;
    
    
    
    
    public static void main(String[] args) {
        frames = 0;
        
        cube = RCube.BASE;
        
        
        
        GridBagLayout g = new GridBagLayout();
        
        final JFrame frame = new JFrame ("tm2019rubiks");
        final JPanel panel = new JPanel(g);
        GridBagConstraints c = new GridBagConstraints();
        
        panel.setSize(800,600);
        frame.add(panel);
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
        
        
        
        
        final RCube2D cube2d = new RCube2D(cube, 60);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=6;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(cube2d, c);
        
        
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
        
        cube2d.setFocusable(true);
        
        RCubeMover listener = new RCubeMover(cube2d);
        
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
        buttonR.addActionListener(listener);
        buttonRP.addActionListener(listener);
        buttonD.addActionListener(listener);
        buttonDP.addActionListener(listener);
        
        cube2d.addKeyListener(new RCubeMover(cube2d));
        frame.setSize(1024,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        
    }
    
    
    
}