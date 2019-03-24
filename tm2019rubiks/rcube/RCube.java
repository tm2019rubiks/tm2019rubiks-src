package tm2019rubiks.rcube;

import java.util.Random;
import tm2019rubiks.utils.Utils;


/**
 *
 * @author estok
 */
public class RCube {
    
    public static final int EDGE_FLIP = 1, CORNER_TWIST = 2, SQUARES_GROUP = 3;
    
    
    
    
    
    //RCube is a representation of a rubiks cube.
    //Each of its six faces (RFace objects) has 9 facelet colors.
    //The cube can be moved via the applyMove(Move m) function
    
    
    //array of its faces
    private RFace[] faces;
    
    //the solved state of the cube
    public RCube(){
        RFace red, green, orange, blue, yellow, black;
        red = new RFace(Utils.fill(RFace.INDEX_FACE_FRONT));
        green = new RFace(Utils.fill(RFace.INDEX_FACE_RIGHT));
        orange = new RFace(Utils.fill(RFace.INDEX_FACE_BACK));
        blue = new RFace(Utils.fill(RFace.INDEX_FACE_LEFT));
        yellow = new RFace(Utils.fill(RFace.INDEX_FACE_UP));
        black = new RFace(Utils.fill(RFace.INDEX_FACE_DOWN));
        this.faces = new RFace[]{red, green, orange, blue, yellow, black};
    }
    public RCube(int stage){
        if(stage == RCube.EDGE_FLIP){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new int[][]{{6, 6, 6},{4, 0, 4},{6, 6, 6}});
            green = new RFace(new int[][]{{6, 6, 6},{6, 1, 6},{6, 6, 6}});
            orange = new RFace(new int[][]{{6, 6, 6},{4, 2, 4},{6, 6, 6}});
            blue = new RFace(new int[][]{{6, 6, 6},{6, 3, 6},{6, 6, 6}});
            yellow = new RFace(new int[][]{{6, 4, 6},{4, 4, 4},{6, 4, 6}});
            black = new RFace(new int[][]{{6, 4, 6},{4, 5, 4},{6, 4, 6}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
        }
        if(stage == RCube.CORNER_TWIST){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new int[][]{{6, 0, 6},{0, 0, 0},{6, 0, 6}});
            green = new RFace(new int[][]{{6, 1, 6},{1, 1, 1},{6, 1, 6}});
            orange = new RFace(new int[][]{{6, 2, 6},{2, 2, 2},{6, 2, 6}});
            blue = new RFace(new int[][]{{6, 3, 6},{3, 3, 3},{6, 3, 6}});
            yellow = new RFace(new int[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new int[][]{{5, 5, 5},{5, 5, 5},{5, 5, 5}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
        }
        if(stage == RCube.SQUARES_GROUP){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new int[][]{{0, 0, 0},{0, 0, 0},{0, 0, 0}});
            green = new RFace(new int[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}});
            orange = new RFace(new int[][]{{0, 0, 0},{0, 0, 0},{0, 0, 0}});
            blue = new RFace(new int[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}});
            yellow = new RFace(new int[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new int[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
            
            
        }
        
        if(stage == 6){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new int[][]{{6, 0, 6},{0, 0, 0},{6, 0, 6}});
            green = new RFace(new int[][]{{6, 1, 6},{1, 1, 1},{6, 1, 6}});
            orange = new RFace(new int[][]{{6, 2, 6},{2, 2, 2},{6, 2, 6}});
            blue = new RFace(new int[][]{{3, 3, 3},{3, 3, 3},{3, 3, 3}});
            yellow = new RFace(new int[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new int[][]{{5, 5, 5},{5, 5, 5},{5, 5, 5}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
            
            
        }
    }
    
    
    public RCube(RFace[] faces){
        this.faces = faces;
        
    }
    public RCube(RFace front, RFace right, RFace back, RFace left, RFace up, RFace down){
        
        this.faces = new RFace[]{front, right, back, left, up, down};
    }
    
    //you have to pass it a Move object, and the rcube makes modifications on itself
    //based on the move
    public void applyMove(Move move) {
        
        //parameter of the move, including which face is turned
        //the direction (1 = CW, -1 = CCW)
        //and the number of turns
        int[] moveParams = move.getMoveParams();
        int rotatedFaceIndex = moveParams[0];
        int direction = moveParams[1];
        int turns = moveParams[2];
        
        //the twisted face is turned with the number of turns
        RFace toRotate = this.faces[rotatedFaceIndex];
        toRotate.twist(direction, turns);
        
        
         
        
        
        
        //translate move to a number of CW turns
        //if it's an X2 move, the numebr of CW turns is 2
        //if it's an X move, then its 1
        //if it's an X' move, it's 3, since 3CW = 1CCW
        int rotations;
        if(turns != 2){
            if(direction == 1){
                rotations = 1;
            }
            else{
                rotations = 3;
            }
        }
        else{
            rotations = 2;
        }
        
        
        
        
        
        
        
        
        //the case when the red face gets rotated
        switch (rotatedFaceIndex) {
            case RFace.INDEX_FACE_FRONT:
                {
                    //the list of faces whose cubies change in order
                    RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_UP],
                        this.faces[RFace.INDEX_FACE_RIGHT],
                        this.faces[RFace.INDEX_FACE_DOWN],
                        this.faces[RFace.INDEX_FACE_LEFT]};
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
            case RFace.INDEX_FACE_RIGHT:
                {
                    RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_UP],
                        this.faces[RFace.INDEX_FACE_BACK],
                        this.faces[RFace.INDEX_FACE_DOWN],
                        this.faces[RFace.INDEX_FACE_FRONT]};
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
            case RFace.INDEX_FACE_BACK:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_UP],
                        this.faces[RFace.INDEX_FACE_LEFT],
                        this.faces[RFace.INDEX_FACE_DOWN],
                        this.faces[RFace.INDEX_FACE_RIGHT ]};
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
            case RFace.INDEX_FACE_LEFT:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_UP],
                        this.faces[RFace.INDEX_FACE_FRONT],
                        this.faces[RFace.INDEX_FACE_DOWN],
                        this.faces[RFace.INDEX_FACE_BACK ]};
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
            case RFace.INDEX_FACE_UP:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_BACK],
                        this.faces[RFace.INDEX_FACE_RIGHT],
                        this.faces[RFace.INDEX_FACE_FRONT],
                        this.faces[RFace.INDEX_FACE_LEFT]};
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
            case RFace.INDEX_FACE_DOWN:
                {
                   RFace[] facesRotated = {this.faces[RFace.INDEX_FACE_FRONT],
                        this.faces[RFace.INDEX_FACE_RIGHT],
                        this.faces[RFace.INDEX_FACE_BACK],
                        this.faces[RFace.INDEX_FACE_LEFT]};
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
    //depth is the number of random moves applied to the cube
    public void scramble(int depth){
        Random rand = new Random();
        int len = Move.MOVES.length;
        for(int i = 0; i < depth; i ++){
            int index = rand.nextInt(len);
            this.applyMove(Move.MOVES[index]);
        }
        
    }
    @Override
    public String toString() {
        String f = "";
        for(RFace r : faces){
            
            for(int[] line : r.getColors()){
                for(int col : line){
                    char colorChar = '?';
                    switch(col){
                        case 0:
                            colorChar = 'F';
                            break;
                        case 1:
                            colorChar = 'R';
                            break;
                        case 2:
                            colorChar = 'B';
                            break;
                        case 3:
                            colorChar = 'L';
                            break;
                        case 4:
                            colorChar = 'U';
                            break;
                        case 5:
                            colorChar = 'D';
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
    public void setFaces(RFace[] faces){
        this.faces = faces;
    }
    
    //todo: return 2d array of edges with same index as int index:
    //{face, x, y}
    public int[][] getEdges(int index){
        final int[][] edgeCoords = {{1, 0}, {0, 1}, {1, 2}, {2, 1}};
        int[][] edges = new int[4][3];
        int found = 0;
        for(int i = 0; i < 6; i ++){
            RFace face = this.faces[i];
            for(int[] coord : edgeCoords){
                int coordY = coord[0];
                int coordX = coord[1];
                if(face.getColors()[coordY][coordX] == index){
                    edges[found] = new int[]{i, coordY, coordX};
                    found ++;
                }
                     
            }
        }
        return edges;
    }
    public RCube copy(){
        RFace[] newFaces = new RFace[6];
        for(int i = 0; i < 6; i ++){
            RFace face = this.faces[i];
            int[][] colors = face.getColors();
            int[][] newColors = new int[3][3];
            for(int y = 0; y < 3; y ++){
                for(int x = 0; x < 3; x ++){
                    newColors[y][x] = colors[y][x];
                }
            }
            newFaces[i] = new RFace(newColors);
            
            
            
        }
        return new RCube(newFaces);
    }

    public RFace getFace(int i) {
        
        return this.faces[i];

    }
}
