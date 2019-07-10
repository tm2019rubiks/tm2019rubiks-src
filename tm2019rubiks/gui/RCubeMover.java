package tm2019rubiks.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import tm2019rubiks.main.Main;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.solve.Solver;
import tm2019rubiks.tables.g4.GenG3;
import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */

//RCubeMover is a Key- and ActionListener
//used to make a RCube, that's in an RCube2D or RCube3D move.


public class RCubeMover implements KeyListener, ActionListener{
    
    
    
    
    final private RCube3D cube3d;
    final private RCube2D cube2d;
    final private RCube cube;
    final private Solver solv = Main.solver;
    
    int currIt = 0;
    ArrayList<Move> m = new ArrayList<>();
    
    HashMap<String, String> moveTransmitStrings;

    //constructor when using only RCube3D
    public RCubeMover(RCube3D toMove) {
        this.cube3d = toMove;
        this.cube2d = null;
        this.cube = cube3d.getRCube();
    }
    
    //only RCube2D
    public RCubeMover(RCube2D toMove){
        this.cube3d = null;
        this.cube2d = toMove;
        this.cube = toMove.getRCube();
    }
    
    //both used at the same time
    public RCubeMover(RCube2D cube2d, RCube3D cube3d) {
        this.cube3d = cube3d;
        this.cube2d = cube2d;
        this.cube = cube2d.getRCube();
        moveTransmitStrings = new HashMap<String, String>(){{
            put("f", "/00/");
            put("F", "/02/");
            put("r", "/10/");
            put("R", "/12/");
            put("b", "/20/");
            put("B", "/22/");
            put("l", "/30/");
            put("L", "/32/");
            put("u", "/40/");
            put("U", "/42/");
            put("d", "/50/");
            put("D", "/50/");
        }};
    }
    
    //This function processes keyStrokes, or actionCommands that are passed
    //to it when a key or a button is pressed
    private void processEvent(char c){
        
        
        
        
        
        switch(c){
            case 'r':
                cube.applyMove(Move.R);
                break;
            case 'R':
                cube.applyMove(Move.RP);
                break;
            case 'u':
                cube.applyMove(Move.U);
                break;
            case 'U':
                cube.applyMove(Move.UP);
                break;
            case 'f':
                cube.applyMove(Move.F);
                break;
            case 'F':
                cube.applyMove(Move.FP);
                break;
            case 'b':
                cube.applyMove(Move.B);
                break;
            case 'B':
                cube.applyMove(Move.BP);
                break;
            case 'l':
                cube.applyMove(Move.L);
                break;
            case 'L':
                cube.applyMove(Move.LP);
                break;
            case 'd':
                cube.applyMove(Move.D);
                break;
            case 'D':
                cube.applyMove(Move.DP);
                break;
            //scrambles the cube
            case 'S':
                cube.scramble(1000);
                break;
            //solves the cube
            case 's':
                
                currIt = 0;
                RCube copy = cube.copy();
                m = solv.thistleSolution(copy);
                
                
                break;
            //applies solution step by step
            case 'M':
                if(currIt < m.size()){
                    cube.applyMove(m.get(currIt));
                    System.out.println(m.get(currIt).toString());
                    currIt += 1;
                    
                }
                break;
            //debugging
            case 'g':
                this.cube3d.setShotAngle();
                Main.motor.send(Main.solver.thistleSolution(cube));
                break;
            
            
        }
        if(this.cube2d != null){
            this.cube2d.repaint();
        }
        
        //set the solution on the textfield.
        String solution = "";
        for(Move m : Main.solver.thistleSolution(cube)) solution += m.toString();
        Main.solutionText.setText(solution);
        
        if(moveTransmitStrings.containsKey(String.valueOf(c))){
            Main.motor.sendString(moveTransmitStrings.get(String.valueOf(c)));
        }
        
    }
    
    
    @Override
    
    public void keyTyped(KeyEvent e) {
        //passes its keyChar to the processEvent function
        this.processEvent(e.getKeyChar());
        
    }

    @Override
    //this function is devoted to the 3d rotations
    //since a RCube3D updates every frame, we have to set an internal variable in
    //it to true, to make it turn
    //this way, there's no need to constantly change the rotation value in RCube3D
    //becuase it will do it itself, 60 * /s
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(this.cube3d != null){
                    this.cube3d.setTurningRight(false);
                    this.cube3d.setTurningLeft(true);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(this.cube3d != null){
                    this.cube3d.setTurningRight(true);
                    this.cube3d.setTurningLeft(false);
                }
                break;
            case KeyEvent.VK_UP:
                if(this.cube3d != null){
                    this.cube3d.setTurningUp(true);
                    this.cube3d.setTurningDown(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(this.cube3d != null){
                    this.cube3d.setTurningDown(true);
                    this.cube3d.setTurningUp(false);
                }
                break;
            case KeyEvent.VK_SPACE:
                if(this.cube3d != null){
                    
                    this.cube3d.setSetPersp(true);
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(this.cube3d != null){
                    this.cube3d.setTurningLeft(false);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(this.cube3d != null){
                    this.cube3d.setTurningRight(false);
                }
                break;
            case KeyEvent.VK_UP:
                if(this.cube3d != null){
                    this.cube3d.setTurningUp(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(this.cube3d != null){
                    this.cube3d.setTurningDown(false);
                }
        }
    }

    @Override
    //when buttons are clicked, the actionCommands are passed to processEvent()
    public void actionPerformed(ActionEvent e) {
        char c = e.getActionCommand().charAt(0);
        this.processEvent(c);
    }
    
    
}
