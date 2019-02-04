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
    
    //you have to pass it a Move() object, and the rcube makes modifications on itself
    //based on the move
    //the faces change
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
        //one for suffices since we only have 1 parameter that has to be iterated over
        //the other one (index of the face) is not a value that cahnges regularly
        //so a for loop isn't needed
        
        for(int i = 0; i < 3; i ++){
           //for the 4 faces that get rotated:
           //change each element with the next face
           
           //TODO: data struct that lets you easily define which parts of which faces get
           //rotated
           //tip; try defining how the faces of the cube(matrixes) link up with each other 
           //and then maybe its possible to autogen the rotated parts
        }
        
    }
    
}
