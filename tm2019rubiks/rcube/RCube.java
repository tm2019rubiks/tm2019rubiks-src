package tm2019rubiks.rcube;

import tm2019rubiks.utils.Utils;


/**
 *
 * @author estok
 */
public class RCube {
    
    
    
    
    private RFace[] faces;
    
    public RCube(RFace[] faces){
        this.faces = faces;
        
    }
    public RCube(RFace front, RFace right, RFace back, RFace left, RFace up, RFace down){
        
        this.faces = new RFace[]{front, right, back, left, up, down};
    }
    //ex : D2 b, D', U , 
    public void applyMove(Move move) throws Exception{
        
        int[] moveParams = move.getMoveParams();
        int rotatedFaceIndex = moveParams[0];
        int direction = moveParams[1];
        int turns = moveParams[2];
        
        RFace toRotate = this.faces[rotatedFaceIndex];
        int[][] colors = toRotate.getColors();
        int[][] rotatedColors = Utils.copyOf(colors);
        for(int i = 0; i < turns; i ++){
            rotatedColors = Utils.rotate(rotatedColors, direction);
        }
                
        RFace rotatedFace = new RFace(rotatedColors);
        this.faces[rotatedFaceIndex] = rotatedFace;
         
        //TODO: other faces whose parts also get rotated
        
        
    }
    
}
