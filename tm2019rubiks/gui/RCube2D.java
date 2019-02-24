package tm2019rubiks.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JPanel;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;

/**
 *
 * @author estok
 */
public class RCube2D extends JPanel{
    
    RCube cube;
    private int size;
    
    public RCube2D(RCube cube, int size){
        
        super();
        this.cube = cube;
        this.size = size;
        
    }

    public RCube getRCube() {
        return cube;
    }
    
    
    
    
    private Color getColor(int index){
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
        RFace[] sidefaces = Arrays.copyOfRange(faces, 0, 4);
        int faceSize = size*3;
        for(int i = 0; i < 4; i ++){
            for(int y = 0; y < 3; y ++){
                for(int x = 0; x < 3; x ++){
                    g.setColor(this.getColor(sidefaces[i].getColors()[y][x]));
                    int posX = i*(faceSize + 5) + x*size;
                    int posY = 5 +faceSize + size*y;
                    g.fillRect(posX, posY, size, size);
                    g.setColor(Color.white);
                    g.drawRect(posX, posY, size, size);
                }
            }
        }
        for(int y = 0; y < 3; y ++){
            for(int x = 0; x < 3; x ++){
                
                //yellow face
                g.setColor(this.getColor(faces[4].getColors()[y][x]));
                int posY = y * size;
                int posX = x * size;
                g.fillRect(posX, posY, size, size);
                g.setColor(Color.white);
                g.drawRect(posX, posY, size, size);
                
                //black face
                g.setColor(this.getColor(faces[5].getColors()[y][x]));
                posY += 2 * (faceSize + 5);
                g.fillRect(posX, posY, size, size);
                g.setColor(Color.white);
                g.drawRect(posX, posY, size, size);
                
                
            }
        }
        
        
    }
}
