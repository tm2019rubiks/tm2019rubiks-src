/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;

/**
 *
 * @author estok
 */
public class RCubeMover implements KeyListener {
    final private RCube3D cube3d;
    final private RCube2D cube2d;
    final private RCube cube;


    public RCubeMover(RCube3D toMove) {
        this.cube3d = toMove;
        this.cube2d = null;
        this.cube = cube3d.getRCube();
    }
    public RCubeMover(RCube2D toMove){
        this.cube3d = null;
        this.cube2d = toMove;
        this.cube = toMove.getRCube();
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
        if(e.getKeyChar() == KeyEvent.VK_SPACE){
            
        }
        System.out.println(e.getKeyChar());
        switch(e.getKeyChar()){
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
        }
        if(this.cube2d != null){
            this.cube2d.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
