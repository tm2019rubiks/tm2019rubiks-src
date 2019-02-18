/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;

/**
 *
 * @author estok
 */
public class RCubeMover implements KeyListener, ActionListener{
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
            case 'S':
                cube.scramble(1000 );
        }
        if(this.cube2d != null){
            this.cube2d.repaint();
        }
        
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        this.processEvent(e.getKeyChar());
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char c = e.getActionCommand().charAt(0);
        this.processEvent(c);
    }
    
    
}
