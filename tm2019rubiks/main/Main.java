package tm2019rubiks.main;

import javax.swing.JButton;
import javax.swing.JFrame;
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
        
        
        
        
        final JFrame frame = new JFrame ("tm2019rubiks");
        final JButton b = new JButton("ok");
        final RCube2D cube2d = new RCube2D(cube, 60);
        frame.addKeyListener(new RCubeMover(cube2d));
        //frame.add(b);
        frame.add(cube2d);
        
        frame.setSize(754, 598);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        
    }
    
    
    
}