package tm2019rubiks.rcube;

import java.util.Arrays;
import java.util.Random;
import tm2019rubiks.utils.Utils;


/**
 *
 * @author estok
 */
public class RCube {
    
    private static final byte[][][] EDGE_POSITIONS = {{{0, 0, 1},{4, 2, 1}},{{0, 2, 1},{5, 0, 1}},
        {{2, 0, 1},{4, 0, 1}},{{2, 2, 1},{5, 2, 1}},
        {{0, 1, 0},{3, 1, 2}},{{0, 1, 2},{1, 1, 0}},
        {{2, 1, 0},{1, 1, 2}},{{2, 1, 2},{3, 1, 0}},
        {{4, 1, 2},{1, 0, 1}},{{5, 1, 2},{1, 2, 1}},
        {{5, 1, 0},{3, 2, 1}},{{4, 1, 0},{3, 0, 1}}};
    private static final byte[][][] CORNER_POSITIONS = {{{3, 0, 2},{0, 0, 0},{4, 2, 0}},
        {{1, 2, 0},{0, 2, 2},{5, 0, 2}},
        {{3, 2, 0},{2, 2, 2},{5, 2, 0}},
        {{1, 0, 2},{2, 0, 0},{4, 0, 2}},
        {{1, 0, 0},{4, 2, 2},{0, 0, 2}},
        {{3, 2, 2},{5, 0, 0},{0, 2, 0}},
        {{1, 2, 2},{5, 2, 2},{2, 2, 0}},
        {{3, 0, 0},{4, 0, 0},{2, 0, 2}}};
    
    
    
    
    public static final byte EDGE_FLIP = 1, CORNER_TWIST = 2, SQUARES_GROUP = 3;
    public static final byte CENTERS_ONLY = 8, EDGES_ONLY = 9, CORNERS_ONLY = 10;
    
    
    
    
    
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
            red = new RFace(new byte[][]{{6, 6, 6},{4, 6, 4},{6, 6, 6}});
            green = new RFace(new byte[][]{{6, 6, 6},{6, 6, 6},{6, 6, 6}});
            orange = new RFace(new byte[][]{{6, 6, 6},{4, 6, 4},{6, 6, 6}});
            blue = new RFace(new byte[][]{{6, 6, 6},{6, 6, 6},{6, 6, 6}});
            yellow = new RFace(new byte[][]{{6, 4, 6},{4, 6, 4},{6, 4, 6}});
            black = new RFace(new byte[][]{{6, 4, 6},{4, 6, 4},{6, 4, 6}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
        }
        if(stage == RCube.CORNER_TWIST){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{6, 0, 6},{0, 0, 0},{6, 0, 6}});
            green = new RFace(new byte[][]{{6, 1, 6},{1, 1, 1},{6, 1, 6}});
            orange = new RFace(new byte[][]{{6, 2, 6},{2, 2, 2},{6, 2, 6}});
            blue = new RFace(new byte[][]{{6, 3, 6},{3, 3, 3},{6, 3, 6}});
            yellow = new RFace(new byte[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new byte[][]{{5, 5, 5},{5, 5, 5},{5, 5, 5}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
        }
        if(stage == RCube.SQUARES_GROUP){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{0, 0, 0},{0, 0, 0},{0, 0, 0}});
            green = new RFace(new byte[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}});
            orange = new RFace(new byte[][]{{0, 0, 0},{0, 0, 0},{0, 0, 0}});
            blue = new RFace(new byte[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}});
            yellow = new RFace(new byte[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new byte[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
            
            
        }
        
        if(stage == 6){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{6, 0, 6},{0, 0, 0},{6, 0, 6}});
            green = new RFace(new byte[][]{{6, 1, 6},{1, 1, 1},{6, 1, 6}});
            orange = new RFace(new byte[][]{{6, 2, 6},{2, 2, 2},{6, 2, 6}});
            blue = new RFace(new byte[][]{{3, 3, 3},{3, 3, 3},{3, 3, 3}});
            yellow = new RFace(new byte[][]{{4, 4, 4},{4, 4, 4},{4, 4, 4}});
            black = new RFace(new byte[][]{{5, 5, 5},{5, 5, 5},{5, 5, 5}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
            
            
            
        }
        if(stage == RCube.CENTERS_ONLY){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{6, 6, 6},{6, 0, 6},{6, 6, 6}});
            green = new RFace(new byte[][]{{7, 7, 7},{7, 1, 7},{7, 7, 7}});
            orange = new RFace(new byte[][]{{6, 6, 6},{6, 2, 6},{6, 6, 6}});
            blue = new RFace(new byte[][]{{7, 7, 7},{7, 3, 7},{7, 7, 7}});
            yellow = new RFace(new byte[][]{{8, 8, 8},{8, 4, 8},{8, 8, 8}});
            black = new RFace(new byte[][]{{8, 8, 8},{8, 5, 8},{8, 8, 8}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
        }
        if(stage == RCube.CORNERS_ONLY){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{0, 6, 0},{6, 6, 6},{0, 6, 0}});
            green = new RFace(new byte[][]{{1, 7, 1},{7, 7, 7},{1, 7, 1}});
            orange = new RFace(new byte[][]{{2, 6, 2},{6, 6, 6},{2, 6, 2}});
            blue = new RFace(new byte[][]{{3, 7, 3},{7, 7, 7},{3, 7, 3}});
            yellow = new RFace(new byte[][]{{4, 8, 4},{8, 8, 8},{4, 8, 4}});
            black = new RFace(new byte[][]{{5, 8, 5},{8, 8, 8},{5, 8, 5}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
        }
        if(stage == RCube.EDGES_ONLY){
            RFace red, green, orange, blue, yellow, black;
            red = new RFace(new byte[][]{{6, 0, 6},{0, 6, 0},{6, 0, 6}});
            green = new RFace(new byte[][]{{7, 1, 7},{1, 7, 1},{7, 1, 7}});
            orange = new RFace(new byte[][]{{6, 2, 6},{2, 6, 2},{6, 2, 6}});
            blue = new RFace(new byte[][]{{7, 3, 7},{3, 7, 3},{7, 3, 7}});
            yellow = new RFace(new byte[][]{{8, 4, 8},{4, 8, 4},{8, 4, 8}});
            black = new RFace(new byte[][]{{8, 5, 8},{5, 8, 5},{8, 5, 8}});
            this.faces = new RFace[]{red, green, orange, blue, yellow, black};
        }
        
    }
    
    public RCube(RCube base, byte stage){
        RCube copy = base.copy();
        if(stage == RCube.SQUARES_GROUP){
            for(RFace face : copy.faces){
                for(byte[] row : face.getColors()){
                    for(int i = 0; i < 3; i ++){
                        if(row[i] == RFace.INDEX_FACE_BACK){
                            row[i] = RFace.INDEX_FACE_FRONT;
                        }
                        if(row[i] == RFace.INDEX_FACE_LEFT){
                            row[i] = RFace.INDEX_FACE_RIGHT;
                        }
                        if(row[i] == RFace.INDEX_FACE_DOWN){
                            row[i] = RFace.INDEX_FACE_UP;
                        }
                    }
                }
            }
            
        }
        this.faces = copy.faces;
        
        
        
    }
    
    
    public RCube(RFace[] faces){
        this.faces = faces;
        
    }
    public RCube(RFace front, RFace right, RFace back, RFace left, RFace up, RFace down){
        
        this.faces = new RFace[]{front, right, back, left, up, down};
    }
    public RCube(String repr){
        byte[][][] colorMatrix = new byte[6][3][3];
        byte face = 0, row = 0, column = 0;
        for(byte i = 0; i < 54; i ++){
            face = (byte) (i / 9);
            row = (byte) ((i % 9) / 3);
            column = (byte)((i % 9) % 3);
            colorMatrix[face][row][column] = Byte.valueOf(String.valueOf(repr.toCharArray()[i]));
        }
        RFace[] faces = new RFace[6];
        for(byte i = 0; i < 6; i ++){
            faces[i] = new RFace(colorMatrix[i]);
        }
        this.faces = faces;
        
    }
    
    //you have to pass it a Move object, and the rcube makes modifications on itself
    //based on the move
    public void applyMove(Move move) {
        
        
        //parameter of the move, including which face is turned
        //the direction (1 = CW, -1 = CCW)
        //and the number of turns
        byte[] moveParams = move.getMoveParams();
        byte rotatedFaceIndex = moveParams[0];
        byte direction = moveParams[1];
        byte turns = moveParams[2];
        
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[i][0];
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[i][0];
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[i][0];
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[i][0];
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[0][2-i];
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
                for(byte currentRotation = 0; currentRotation < rotations; currentRotation ++){
                    for(byte i = 0; i < 3; i ++){
                        byte temp = facesRotated[1].getColors()[2][i];
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
            
            for(byte[] line : r.getColors()){
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
    public byte[][] getEdges(byte index){
        final byte[][] edgeCoords = {{1, 0}, {0, 1}, {1, 2}, {2, 1}};
        byte[][] edges = new byte[4][3];
        byte found = 0;
        for(byte i = 0; i < 6; i ++){
            RFace face = this.faces[i];
            for(byte[] coord : edgeCoords){
                byte coordY = coord[0];
                byte coordX = coord[1];
                if(face.getColors()[coordY][coordX] == index){
                    edges[found] = new byte[]{i, coordY, coordX};
                    found ++;
                }
                
            }
        }
        return edges;
    }
    public RCube copy(){
        RFace[] newFaces = new RFace[6];
        for(byte i = 0; i < 6; i ++){
            RFace face = this.faces[i];
            byte[][] colors = face.getColors();
            byte[][] newColors = new byte[3][3];
            for(byte y = 0; y < 3; y ++){
                for(byte x = 0; x < 3; x ++){
                    newColors[y][x] = colors[y][x];
                }
            }
            newFaces[i] = new RFace(newColors);
            
            
            
        }
        return new RCube(newFaces);
    }
    
    public RFace getFace(byte i) {
        
        return this.faces[i];
        
    }
    
    /////solving reprs
    public String repr(){
        String repr = "";
        for(RFace face : this.faces){
            for(byte[] row : face.getColors()){
                for(byte val : row){
                    repr += String.valueOf(val);
                }
            }
        }
        return repr;
        
        
    }
    //////////////////////////////////
    /*converting the cube to different representations depending on the stage
    being solved*/
    
    
    
    public String stage1(){
        
        
        
        //now we must take the list of the edges of this instance of the cube.
        byte[][] thisEdges = this.getEdges();
        
        
        //order of priority of reference edges:
        /*if the edge has a F or B side,
        then its the reference,
        else its the U or D*/
        
        //this array contains the possible reference facelets of the edges
        //(the ones that get turned yellow in the edgeflip cube)
        byte[] refPriority = {0, 2, 4, 5};
        
        //array that will hold the flips of the edges (false = flipped/bad)
        boolean[] edgeFlip = new boolean[12];
        for(byte i = 0; i < 12; i ++){
            
            //get the edge we're working on currently
            byte[] edge = thisEdges[i];
            
            //based on reference priority, this will determine the reference facelet
            //of the edge
            byte ref = -1;
            for(byte curr : refPriority){
                if(Utils.contains(edge, curr)){
                    ref = curr;
                    break;
                }
            }
            //now we get its index in the array "edge".
            //since this is ordered with the reference facelet first,
            //if the reference color of the facelet is in pos 0,
            //it is not flipped
            edgeFlip[i] = Utils.indexOf(edge, ref) == 0;
            
        }
        
        String s = "";
        for(boolean  b : edgeFlip){
            s += b ? "1" : 0;
        }
        return s;
        
    }
    
    //stage 2
    public String stage2(){
        
        RCube cube =  new RCube();
        
        
        //holds twist information
        byte[] cornerTwist = new byte[8];
        
        //if set to true, then edge is in M slice
        boolean[] middleSlice = new boolean[12];
        
        
        //middle slice edges
        byte[][] middleSliceEdges = {{0, 4},{0, 5},{2, 4},{2, 5}};
        
        //getting the mSlice edges positions
        byte[][] thisEdges = this.getEdges();
        for(int i = 0; i < 12; i ++){
            boolean found = false;
            for(byte[] mSliceEdge : middleSliceEdges){
                if(Utils.setEquals(thisEdges[i], mSliceEdge)){
                    found = true;
                }
            }
            middleSlice[i] = found;
            
        }
        
        byte[] refPriority = {1, 3};
        
        byte[][] corners = this.getCorners();
        
        for(byte i = 0; i < 8 ; i ++){
            
            byte[] corner = corners[i];
            
            byte ref = -1;
            
            for(byte curr : refPriority){
                if(Utils.contains(corner, curr)){
                    ref = curr;
                    break;
                }
            }
            cornerTwist[i] = (byte) Utils.indexOf(corner, ref);
            
        }
        
        String edgeString = "";
        for(boolean b : middleSlice){
            edgeString += b ? "1" : "0";
        }
        
        String corn = "";
        for(byte c : cornerTwist){
            corn += String.valueOf(c);
        }
        
        return edgeString + "_" + corn;
    }
    
    
    public String stage3(){
        
        RCube cube = new RCube();
        
        byte[][] thisEdges = this.getEdges();
        
        boolean[] slices = new boolean[8];
        
        byte[][] udSlice = {{0, 3},{0, 1},{2, 1},{2, 3}};
        
        for(byte i = 4; i < 12; i ++){
            
            byte[] currentEdge = thisEdges[i];
            
            boolean isUDSlice = false;
            for(byte[] udSliceEdge : udSlice){
                if(Utils.setEquals(udSliceEdge, currentEdge)){
                    isUDSlice = true;
                }
            }
            slices[i-4] = isUDSlice;
            
        }
        //now for the corners
        
        byte[][] thisCorners = this.getCorners();
        
        byte[] cornerPairs = new byte[8];
        
        byte[][] baseCorners = cube.getCorners();
        
        for(byte i = 0; i < 8; i ++){
            
            byte[] currCorner = thisCorners[i];
            
            byte ind = -1;
            for(byte j = 0; j < 8; j ++){
                if(Utils.setEquals(currCorner, baseCorners[j])){
                    ind = j;
                }
            }
            cornerPairs[i] =(byte) (ind / 2);
            
        }
        //NOW FOR PERMUTATION PARITY...
        
        byte[] edgePerm = new byte[12];
        
        //first, establish the permuation
        byte[][] baseEdges = cube.getEdges();
        
        for(byte i = 0; i < 12; i ++){
            byte[] currEdge = thisEdges[i];
            
            byte ind = -1;
            
            for(byte j = 0; j < 12; j ++){
                if(Utils.setEquals(currEdge, baseEdges[j])){
                    ind = j;
                }
            }
            edgePerm[i] = ind;
        }
        
        byte[] basePerm = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        
        //let's start permuting the values, keeping track of the transpositions
        int trans = 0;
        
        //run till array is in its initial state
        while(!Arrays.equals(basePerm, edgePerm)){
            
            byte index = -1;
            //let's find an element/index pair that's not in it's spot
            for(byte i = 0; i < 12; i ++){
                if(edgePerm[i] != i){
                    index = i;
                    break;
                }
            }
            byte value = edgePerm[index];
            
            byte secondIndex = value;
            byte secondValue = edgePerm[secondIndex];
            
            //swapping the 2 elements:
            byte temp = value;
            edgePerm[index] = edgePerm[secondIndex];
            edgePerm[secondIndex] = temp;
            
            trans ++;
            
        }
        boolean parity = (trans % 2) == 0;
        
        String edg = "";
        for(boolean b : slices){
            edg += b ? "1" : "0";
        }
        
        String corn = "";
        for(byte i : cornerPairs){
            corn += String.valueOf(i);
        }
        
        String par = parity ? "1" : "0";
        
        return edg + "_" + corn + "_" + par;
        
        
        
        
    }
    
    public String stage4(){
        //this representation should only be able to hold 2**4 * (4!)**3 possible
        //states.
        
        //basically, 4 bits would be needed to encide the flip of the pairs.
        //then, a permutation has to be held in a
        
        
        
        //start with the flips of the corner pairs
        String flip = "";
        
        RCube ref = new RCube();
        byte[][] refCorners = ref.getCorners();
        byte[][] thisCorners = this.getCorners();
        
        byte[][] cornerPairs = {{0, 3},{1, 2},{5,6},{7,4}};
        
        for(byte i = 0; i < 4; i ++){
            if(Utils.setEquals(refCorners[2*i], thisCorners[2*i])){
                flip += "0";
            }
            else{
                flip += "1";
            }
        }
        //now we have the flip
        
        //gets the edges of the ref and the actual cube
        byte[][] refEdges = ref.getEdges();
        byte[][] thisEdges = this.getEdges();
        
        String edgePerm = "";
        
        
        //do this for each slice (0-4, 4-8, 8-12)
        for(byte slice = 0; slice < 3; slice ++){
            
            //pick one edge of the reference
            for(byte edge = 0; edge < 4; edge ++){
                
                //and iterate through the actual cubes edges till it's found
                for(byte ind = 0; ind < 4; ind ++){
                    if(Utils.setEquals(refEdges[4*slice + edge], thisEdges[4*slice + ind])){
                        edgePerm += String.valueOf(ind);
                    }
                    
                    
                    
                }
            }
            
        }
        
        
        
        
        
        
        return flip +"_"+ edgePerm;
    }
    
  
    
    public byte[] edgePerm(){
        
        byte[][] edges = new RCube().getEdges();
        
        byte[][] thisEdges = this.getEdges();
        
        byte[] perm = new byte[12];
        
        
        
        //for each position
        for(byte edge = 0; edge < 12; edge ++){
            
            //get the edge that's in that position
            for(byte ind = 0; ind < 12; ind ++){
                if(Utils.setEquals(edges[ind], thisEdges[edge])){
                    perm[edge] = ind;
                }
            }
        }
        return perm;
        
    }
    
    public byte[] cornerPerm(){
        
        byte[][] corners = new RCube().getCorners();
        
        byte[][] thisCorners = this.getCorners();
        
        byte[] perm = new byte[8];
        
        
        
        //for each position
        for(byte corner = 0; corner < 8; corner ++){
            
            //get the corner that's in that position
            for(byte ind = 0; ind < 8; ind ++){
                if(Utils.setEquals(corners[ind], thisCorners[corner])){
                    perm[corner] = ind;
                }
            }
        }
        return perm;
        
    }
    public String cornerTwist(){
        return this.stage2().substring(13);
    }
    
    
    
    
    //get the edges of the cube, in form of int[2] arrays. Same order as reference,
    //reference facelets given first (yellow facelets on edge flip cube)
    public byte[][] getEdges(){
        byte[][] thisEdges = new byte[12][2];
        
        byte i = 0;
        for(byte[][] position : EDGE_POSITIONS){
            
            byte[] firstPos = position[0];
            byte[] secondPos = position[1];
            
            
            byte[] faceCoords = new byte[]{firstPos[1], firstPos[2]};
            byte firstColor = this.getFace(firstPos[0]).getColor(faceCoords);
            
            faceCoords = new byte[]{secondPos[1], secondPos[2]};
            byte secondColor = this.getFace(secondPos[0]).getColor(faceCoords);
            
            
            
            thisEdges[i] = new byte[]{firstColor, secondColor};
            i ++;
            
        }
        return thisEdges;
    }
    
    public byte[][] getCorners(){
        byte[][] thisCorners = new byte[8][3];
        
        byte i = 0;
        for(byte[][] position : CORNER_POSITIONS){
            
            byte[] firstPos = position[0];
            byte[] secondPos = position[1];
            byte[] thirdPos = position[2];
            
            
            byte[] faceCoords = new byte[]{firstPos[1], firstPos[2]};
            byte firstColor = this.getFace(firstPos[0]).getColor(faceCoords);
            
            faceCoords = new byte[]{secondPos[1], secondPos[2]};
            byte secondColor = this.getFace(secondPos[0]).getColor(faceCoords);
            
            faceCoords = new byte[]{thirdPos[1], thirdPos[2]};
            byte thirdColor = this.getFace(thirdPos[0]).getColor(faceCoords);
            
            
            
            thisCorners[i] = new byte[]{firstColor, secondColor, thirdColor};
            i ++;
            
        }
        return thisCorners;
    }
    
    public String zzCross(){
        byte[] edgePerm = this.edgePerm();
        return this.stage1()+ "" + Utils.indexOf(edgePerm, (byte)0) + "_" + Utils.indexOf(edgePerm, (byte)2);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.faces);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RCube other = (RCube) obj;
        if (!Arrays.deepEquals(this.faces, other.faces)) {
            return false;
        }
        return true;
    }
    public boolean isInStage(int state){
        String stage1 = "111111111111";
        String stage2 = "111100000000_00000000";
        String stage3 = "11110000_00112233_1";
        String stage4 = "0000_012301230123";
        
        //System.out.println(this.stage1() + "  " + this.stage2() + "  " + this.stage3() + " " + this.stage4());
        
        if(!this.stage1().equals(stage1)){
            return false;
        }
        
        if(state == 2){
            return true;
        }
        if(!this.stage2().equals(stage2)){
            return false;
        }
        
        if(state == 3){
            return true;
        }
        if(!this.stage3().equals(stage3)){
            return false;
        }
        
        if(state == 4){
            return true;
        }
        if(!this.stage4().equals(stage4)){
            return false;
        }
        
        if(state == 5){
            return true;
        }
        return true;
        
    }
    public short edgeFlip(){
        return Short.parseShort(this.stage1(), 2);
    }
    public String ff2l(){
        byte[] cornerPerm = this.cornerPerm();
        byte[] edgePerm = this.edgePerm();
        
        byte posOfEdge6 = Utils.indexOf(edgePerm, (byte)5);
        byte posOfCorner2 = Utils.indexOf(cornerPerm, (byte)1);
        
        return String.valueOf(posOfEdge6) + "_" + posOfCorner2 + "_"+ this.stage1().charAt(posOfEdge6) + this.cornerTwist().charAt(posOfCorner2);
        
    }
    public String cross(){
        
        byte[] edgePerm = this.edgePerm();
        String orientation = this.stage1();
        
        byte edge2 = Utils.indexOf(edgePerm, (byte)1);
        byte edge10 = Utils.indexOf(edgePerm, (byte)9);
        byte edge4 = Utils.indexOf(edgePerm, (byte)3);
        byte edge11 = Utils.indexOf(edgePerm, (byte)10);
        
        char ori2 = orientation.charAt(edge2);
        char ori10 = orientation.charAt(edge10);
        char ori4 = orientation.charAt(edge4);
        char ori11 = orientation.charAt(edge11);
        
        return String.valueOf(edge2) + "_" + ori2 + "/"+
               String.valueOf(edge10) + "_" + ori10 + "/"+
               String.valueOf(edge4) + "_" + ori4 + "/"+
               String.valueOf(edge11) + "_" + ori11;
                
    }
    public String f2l1CrossState(){
        return this.cross() + "-" + this.ff2l();
    }
}
