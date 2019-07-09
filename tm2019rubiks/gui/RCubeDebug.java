/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.gui;

import javax.swing.JComponent;
import tm2019rubiks.rcube.RCube;

/**
 *
 * @author estok
 */
public class RCubeDebug extends JComponent{
    
    private RCube cube;
    
    public RCubeDebug(RCube cube){
        super();
        this.cube = cube;
    }
    
}
