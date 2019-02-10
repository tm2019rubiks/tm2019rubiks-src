package tm2019rubiks.rcube;

import tm2019rubiks.utils.Utils;


/**
 *
 * @author estok
 */
public class RCube {
    
    
    
    
    private RFace[] faces;
    public static final RCube BASE;
    
    static {
        RFace red, green, orange, blue, yellow, black;
        red = new RFace(Utils.fill(RFace.INDEX_FACE_RED));
        green = new RFace(Utils.fill(RFace.INDEX_FACE_GREEN));
        orange = new RFace(Utils.fill(RFace.INDEX_FACE_ORANGE));
        blue = new RFace(Utils.fill(RFace.INDEX_FACE_BLUE));
        yellow = new RFace(Utils.fill(RFace.INDEX_FACE_YELLOW));
        black = new RFace(Utils.fill(RFace.INDEX_FACE_BLACK));
        BASE = new RCube(red, green, orange, blue, yellow, black);
    }
    
    public RCube(RFace[] faces){
        this.faces = faces;
        
    }
    public RCube(RFace front, RFace right, RFace back, RFace left, RFace up, RFace down){
        
        this.faces = new RFace[]{front, right, back, left, up, down};
    }
    
    //you have to pass it a Move() object, and the rcube makes modifications on itself
    //based on the move
    //the faces change
    public void applyMove(Move move) {
        
        int[] moveParams = move.getMoveParams();
        int rotatedFaceIndex = moveParams[0];
        int direction = moveParams[1];
        int turns = moveParams[2];
        
        RFace toRotate = this.faces[rotatedFaceIndex];
        toRotate.twist(direction, turns);
        
        
         
        //TODO: other faces whose parts also get rotated
        //one for suffices since we only have 1 parameter that has to be iterated over
        //the other one (index of the face) is not a value that cahnges regularly
        //so a for loop isn't needed
        
        
        //rotating ccw is the same as 3 cw rotations
        
        //consider ternary
        int rotations;
        if(direction == 1){
            rotations = 1;
        }
        else{
            rotations = 3;
        }
        
        //for the 4 faces that get rotated:
        //change each element with the next face
        //TODO: data struct that lets you easily define which parts of which faces get
        //rotated
        //tip; try defining how the faces of the cube(matrixes) link up with each other
        //and then maybe its possible to autogen the rotated parts
        //U:
        /*
        up rotated, every element from face[0] from 0 to 3 goes to face face[0][i+1]
         */
        //D:
        /*
        down ratated, same thing as for up ,except zts face[2]
         */
        // R
        /*
        R gets rotated
        D-> F -> U -//> B ->> D
         */
        
        
        
        
        
        //the case when the red face gets rotated
        switch (rotatedFaceIndex) {
            case RFace.INDEX_FACE_RED:
                {
                    RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_YELLOW],
                        this.faces[RFace.INDEX_FACE_GREEN],
                        this.faces[RFace.INDEX_FACE_BLACK],
                        this.faces[RFace.INDEX_FACE_BLUE]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[i][0];
                            facesRotated[1].getColors()[i][0] = facesRotated[0].getColors()[2][i];
                            facesRotated[0].getColors()[2][i] = facesRotated[3].getColors()[2-i][2];
                            facesRotated[3].getColors()[2-i][2] = facesRotated[2].getColors()[0][2-i];
                            facesRotated[2].getColors()[0][2-i] = temp;
                        }
                    }       break;
                }
            case RFace.INDEX_FACE_GREEN:
                {
                    RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_YELLOW],
                        this.faces[RFace.INDEX_FACE_ORANGE],
                        this.faces[RFace.INDEX_FACE_BLACK],
                        this.faces[RFace.INDEX_FACE_RED]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[i][0];
                            facesRotated[1].getColors()[i][0] = facesRotated[0].getColors()[2-i][2];
                            facesRotated[0].getColors()[2-i][2] = facesRotated[3].getColors()[2-i][2];
                            facesRotated[3].getColors()[2-i][2] = facesRotated[2].getColors()[2-i][2];
                            facesRotated[2].getColors()[2-i][2] = temp;
                        }
                    }       break;
                }
            case RFace.INDEX_FACE_ORANGE:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_YELLOW],
                        this.faces[RFace.INDEX_FACE_BLUE],
                        this.faces[RFace.INDEX_FACE_BLACK],
                        this.faces[RFace.INDEX_FACE_GREEN ]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[i][0];
                            facesRotated[1].getColors()[i][0] = facesRotated[0].getColors()[0][2-i];
                            facesRotated[0].getColors()[0][2-i] = facesRotated[3].getColors()[2-i][2];
                            facesRotated[3].getColors()[2-i][2] = facesRotated[2].getColors()[2][i];
                            facesRotated[2].getColors()[2][i] = temp;
                        }
                    }       break; 
                }
            case RFace.INDEX_FACE_BLUE:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_YELLOW],
                        this.faces[RFace.INDEX_FACE_RED],
                        this.faces[RFace.INDEX_FACE_BLACK],
                        this.faces[RFace.INDEX_FACE_ORANGE ]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[i][0];
                            facesRotated[1].getColors()[i][0] = facesRotated[0].getColors()[i][0];
                            facesRotated[0].getColors()[i][0] = facesRotated[3].getColors()[2-i][2];
                            facesRotated[3].getColors()[2-i][2] = facesRotated[2].getColors()[i][0];
                            facesRotated[2].getColors()[i][0] = temp;
                        }
                    }       break; 
                }
            case RFace.INDEX_FACE_YELLOW:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_ORANGE],
                        this.faces[RFace.INDEX_FACE_GREEN],
                        this.faces[RFace.INDEX_FACE_RED],
                        this.faces[RFace.INDEX_FACE_BLUE]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[0][2-i];
                            facesRotated[1].getColors()[0][2-i] = facesRotated[0].getColors()[0][2-i];
                            facesRotated[0].getColors()[0][2-i] = facesRotated[3].getColors()[0][2-i];
                            facesRotated[3].getColors()[0][2-i] = facesRotated[2].getColors()[0][2-i];
                            facesRotated[2].getColors()[0][2-i] = temp;
                        }
                    }       break; 
                }
            case RFace.INDEX_FACE_BLACK:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_RED],
                        this.faces[RFace.INDEX_FACE_GREEN],
                        this.faces[RFace.INDEX_FACE_ORANGE],
                        this.faces[RFace.INDEX_FACE_BLUE]};
                    for(int currentRotation = 0; currentRotation < rotations; currentRotation ++){
                        for(int i = 0; i < 3; i ++){
                            int temp = facesRotated[1].getColors()[2][i];
                            facesRotated[1].getColors()[2][i] = facesRotated[0].getColors()[2][i];
                            facesRotated[0].getColors()[2][i] = facesRotated[3].getColors()[2][i];
                            facesRotated[3].getColors()[2][i] = facesRotated[2].getColors()[2][i];
                            facesRotated[2].getColors()[2][i] = temp;
                        }
                    }       break; 
                }   
            default:
                break;
        }
        
        
    }

    @Override
    public String toString() {
        String f = "";
        for(RFace r : faces){
            
            for(int[] line : r.getColors()){
                for(int col : line){
                    char colorChar = 'N';
                    switch(col){
                        case 0:
                            colorChar = 'R';
                            break;
                        case 1:
                            colorChar = 'G';
                            break;
                        case 2:
                            colorChar = 'O';
                            break;
                        case 3:
                            colorChar = 'B';
                            break;
                        case 4:
                            colorChar = 'Y';
                            break;
                        case 5:
                            colorChar = 'b';
                            break;
                        
                    }
                    f += String.valueOf(colorChar) + " ";
                }
                f += "\n";
            }
            f += "\n";
            f += "\n";
        }
        return f;
    }

    public RFace[] getFaces() {
        return faces;
    }
    
}
