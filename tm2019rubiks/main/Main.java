package tm2019rubiks.main;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import tm2019rubiks.rcube.RCube;



public class Main  {
    private static RCube cube;
    private static boolean on3d;
    
    
    
    
    public static void main(String[] args) {
        
        cube = RCube.BASE;
        on3d = true;
        
        
        GridBagLayout g = new GridBagLayout();
        
        final JFrame frame = new JFrame ("tm2019rubiks");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        final JPanel panel = new JPanel(g);
        GridBagConstraints c = new GridBagConstraints();
        
        CardLayout cardLayout = new CardLayout();
        final JPanel rcubeGUI = new JPanel(cardLayout);
        
        
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
        final JButton button3D = new JButton("3D/2D");
        
        
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
        
        
        
        
        final RCube3D cube3d = new RCube3D(cube);
        final RCube2D cube2d = new RCube2D(cube, 60);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=6;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(rcubeGUI, c);
        
        rcubeGUI.add(cube2d, "2D");
        rcubeGUI.add(cube3d, "3D");
        
        
        
        
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
        panel.add(button3D, c);
        
        cube2d.setFocusable(false);
        cube3d.setFocusable(false);
        
        RCubeMover listener = new RCubeMover(cube2d, cube3d);
        
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
        
        button3D.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(on3d){
                    CardLayout cl = (CardLayout) rcubeGUI.getLayout();
                    cl.show(rcubeGUI, "3D");
                    
                }
                else{
                    CardLayout cl = (CardLayout) rcubeGUI.getLayout();
                    cl.show(rcubeGUI, "2D");
                }
                on3d = !on3d;
                
            }
        });
        
        
        rcubeGUI.setFocusable(true);
        rcubeGUI.requestFocus();
        rcubeGUI.addKeyListener(new RCubeMover(cube2d, cube3d));
        frame.setSize(1024,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(frame.getFocusOwner());
        
        
        
        
        
    }
    
    
    
}