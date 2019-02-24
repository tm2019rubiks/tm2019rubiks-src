package tm2019rubiks.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;

/**
 *
 * @author estok
 */

//JComponent showing a cube in its 2d form
//its sides are folded out, like a normal cube
//needs a RCube object, that it can show, and 
//also an int size, to determine the size of the facelets
//When an instance is passed as an argument to a
//RCubeMover, the faces will change according to the keypresses.



public class RCube2D extends JComponent{
    
    
    
    //the cube it shows
    RCube cube;
    //the size in pixels / facelet
    private final int size;
    
    
    //constructor with RCube and size parameters
    public RCube2D(RCube cube, int size){
        
        super();
        this.cube = cube;
        this.size = size;
        
    }

    
    public RCube getRCube() {
        return cube;
    }
    
    
    
    //private method that returns an AWT color
    //based on RGB values from RFace
    private static Color getColor(int index){
        float[] rgb = RFace.COLORS_BY_INDEX[index];
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        Color c = new Color(r, g, b);
        return c;
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        
        RFace[] faces = this.cube.getFaces();
        
        //each face has 3 facelets, so its total size is 3*facelet size
        int faceSize = size*3;
        
        //i = index of the face being drawn
        for(int i = 0; i < 4; i ++){
            
            //y = y index of facelet being drawn in face[i]
            for(int y = 0; y < 3; y ++){
                
                //x = x index of facelet being drawn in face[i][y]
                for(int x = 0; x < 3; x ++){
                    
                    //set color based on the color of the facelet
                    g.setColor(getColor(faces[i].getColors()[y][x]));
                    
                    //calculate position of facelet
                    //(facesize + 5) because there's a little space between different faces
                    int posX = i*(faceSize + 5) + x*size;
                    int posY = 5 +faceSize + size*y;
                    g.fillRect(posX, posY, size, size);
                    
                    //drawing the line
                    g.setColor(Color.white);
                    g.drawRect(posX, posY, size, size);
                }
            }
        }
        
        //the other 2 faces
        //since they're aligned vertically, and not horizontally
        //like the other 4 faces, there's need for a separate for loop
        
        //4 + i = index of the face being drawn
        for(int i = 0; i < 2; i ++){
            
            //y = y index of facelet being drawn in face[4 + i]
            for(int y = 0; y < 3; y ++){
                
                //x = x index of facelet being drawn in face[4 + i][y]
                for(int x = 0; x < 3; x ++){
                    
                    //set color, draw facelet
                    g.setColor(getColor(faces[4+i].getColors()[y][x]));
                    int posY = y * size + 2*i*(faceSize + 5);
                    int posX = x * size;
                    g.fillRect(posX, posY, size, size);
                    
                    //draw line
                    g.setColor(Color.white);
                    g.drawRect(posX, posY, size, size);
                    
                }
            }
        }
        
        
    }
}
