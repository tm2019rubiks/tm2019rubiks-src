package tm2019rubiks.rcube;

import java.util.Arrays;
import java.util.Random;
import tm2019rubiks.utils.Utils;


/**
 *
 * @author estok
 */
public class RCube {
    
    private static final int[][][] EDGE_POSITIONS = {{{0, 0, 1},{4, 2, 1}},{{0, 2, 1},{5, 0, 1}},
                                                     {{2, 0, 1},{4, 0, 1}},{{2, 2, 1},{5, 2, 1}},
                                                     {{0, 1, 0},{3, 1, 2}},{{0, 1, 2},{1, 1, 0}},
                                                     {{2, 1, 0},{1, 1, 2}},{{2, 1, 2},{3, 1, 0}},
                                                     {{4, 1, 2},{1, 0, 1}},{{5, 1, 2},{1, 2, 1}},
                                                     {{5, 1, 0},{3, 2, 1}},{{4, 1, 0},{3, 0, 1}}};
    private static final int[][][] CORNER_POSITIONS = {{{3, 0, 2},{0, 0, 0},{4, 2, 0}},
                                                       {{1, 2, 0},{0, 2, 2},{5, 0, 2}},
                                                       {{3, 2, 0},{2, 2, 2},{5, 2, 0}},
                                                       {{1, 0, 2},{2, 0, 0},{4, 0, 2}},
                                                       {{1, 0, 0},{4, 2, 2},{0, 0, 2}},
                                                       {{3, 2, 2},{5, 0, 0},{0, 2, 0}},
                                                       {{1, 2, 2},{5, 2, 2},{2, 2, 0}},
                                                       {{3, 0, 0},{4, 0, 0},{2, 0, 2}}};
    
    
    
    
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
            red = new RFace(new int[][]{{6, 6, 6},{4, 6, 4},{6, 6, 6}});
            green = new RFace(new int[][]{{6, 6, 6},{6, 6, 6},{6, 6, 6}});
            orange = new RFace(new int[][]{{6, 6, 6},{4, 6, 4},{6, 6, 6}});
            blue = new RFace(new int[][]{{6, 6, 6},{6, 6, 6},{6, 6, 6}});
            yellow = new RFace(new int[][]{{6, 4, 6},{4, 6, 4},{6, 4, 6}});
            black = new RFace(new int[][]{{6, 4, 6},{4, 6, 4},{6, 4, 6}});
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
    
    public RCube(RCube base, int stage){
        RCube copy = base.copy();
        if(stage == RCube.SQUARES_GROUP){
            for(RFace face : copy.faces){
                for(int[] row : face.getColors()){
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
    
    /////solving reprs
    public String repr(){
        String repr = "";
        for(RFace face : this.faces){
            for(int[] row : face.getColors()){
                for(int val : row){
                    repr += String.valueOf(val);
                }
            }
        }
        return repr;
        
        
    }
    //////////////////////////////////
    /*converting the cube to different representations depending on the stage
    being solved*/
    
    
    
    public String edgeFlip(){
        
        //on the edgeflip cube, this would be a "yellow facelet".
        //for it to be solved, the yellow facelets must be correctly flipped
        //see RCube.EDGE_FLIP
        //this array represents on which side the yellow facelet of each edge would
        //need to lie if it is a good edge
//        int[][] goodFaceletPositions = {{0, 0, 1},{0, 2, 1},{2, 0, 1},
//                                                {2, 2, 1},{0, 1, 0},{0, 1, 2},
//                                                {2, 1, 0},{2, 1, 2},{4, 1, 2},
//                                                {5, 1, 2},{5, 1, 0},{4, 1, 0}};
//        
//        //this array will contain which color of which edgepiece would be yellow
//        //example : for edgepiece 0, it would be its F side
//        int[] goodEdges = new int[12];
//        RCube cube = new RCube();
//        for(int i = 0; i < 12; i ++){
//            
//            //
//            int[] edge = EDGE_POSITIONS[i][0];
//            int face = edge[0], y = edge[1], x = edge[2];
//            
//            
//            
//            int goodEdgeColor = cube.getFace(face).getColor(new int[]{y, x});
//            
//            //now for each edge (0 - 11) we have it's "good color"
//            goodEdges[i] = goodEdgeColor;
//            
//        }
//        
//        //now let's construct the array that holds all edge pieces in the same order
//        //as in reference
//        int[][] edges = new int[12][2];
//        
//        
//        int i = 0;
//        for(int[][] position : EDGE_POSITIONS){
//            
//            int[] firstPos = position[0];
//            int[] secondPos = position[1];
//            
//            
//            int[] faceCoords = new int[]{firstPos[1], firstPos[2]};
//            int firstColor = cube.getFace(firstPos[0]).getColor(faceCoords);
//            
//            faceCoords = new int[]{secondPos[1], secondPos[2]};
//            int secondColor = cube.getFace(secondPos[0]).getColor(faceCoords);
//            
//           
//            
//            edges[i] = new int[]{firstColor, secondColor};
//            i ++;
//            
//        }
        
        //now we must take the list of the edges of this instance of the cube.
        int[][] thisEdges = this.getEdges();
        
        
        //order of priority of reference edges:
        /*if the edge has a F or B side, 
        then its the reference,
        else its the U or D*/
        
        //this array contains the possible reference facelets of the edges
        //(the ones that get turned yellow in the edgeflip cube)
        int[] refPriority = {0, 2, 4, 5};
        
        //array that will hold the flips of the edges (false = flipped/bad)
        boolean[] edgeFlip = new boolean[12];
        for(int i = 0; i < 12; i ++){
            
            //get the edge we're working on currently
            int[] edge = thisEdges[i];
            
            //based on reference priority, this will determine the reference facelet 
            //of the edge
            int ref = -1;
            for(int curr : refPriority){
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
        int[][] middleSliceEdges = {{0, 4},{0, 5},{2, 4},{2, 5}};
        
        //getting the mSlice edges positions
        int[][] thisEdges = this.getEdges();
        for(int i = 0; i < 12; i ++){
            boolean found = false;
            for(int[] mSliceEdge : middleSliceEdges){
                if(Utils.setEquals(thisEdges[i], mSliceEdge)){
                    found = true;
                }
            }
            middleSlice[i] = found;
            
        } 
        
        int[] refPriority = {1, 3};
        
        int[][] corners = this.getCorners();
        
        for(int i = 0; i < 8 ; i ++){
            
            int[] corner = corners[i];
            
            int ref = -1;
            
            for(int curr : refPriority){
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
        for(int c : cornerTwist){
            corn += String.valueOf(c);
        }
        
        return edgeString + "_" + corn;
    }
    
    
    public String stage3(){
        
        RCube cube = new RCube();
        
        int[][] thisEdges = this.getEdges();
        
        boolean[] slices = new boolean[8];
        
        int[][] udSlice = {{0, 3},{0, 1},{2, 1},{2, 3}};
        
        for(int i = 4; i < 12; i ++){
            
            int[] currentEdge = thisEdges[i];
            
            boolean isUDSlice = false;
            for(int[] udSliceEdge : udSlice){
                if(Utils.setEquals(udSliceEdge, currentEdge)){
                    isUDSlice = true;
                }
            }
            slices[i-4] = isUDSlice;
            
        }
        //now for the corners
        
        int[][] thisCorners = this.getCorners();
        
        int[] cornerPairs = new int[8];
        
        int[][] baseCorners = cube.getCorners();
        
        for(int i = 0; i < 8; i ++){
            
            int[] currCorner = thisCorners[i]; 
            
            int ind = -1;
            for(int j = 0; j < 8; j ++){
                if(Utils.setEquals(currCorner, baseCorners[j])){
                    ind = j;
                }
            }
            cornerPairs[i] = ind / 2;
            
        }
        //NOW FOR PERMUTATION PARITY... 
        
        int[] edgePerm = new int[12];
        
        //first, establish the permuation
        int[][] baseEdges = cube.getEdges();
        
        for(int i = 0; i < 12; i ++){
            int[] currEdge = thisEdges[i];
            
            int ind = -1;
            
            for(int j = 0; j < 12; j ++){
                if(Utils.setEquals(currEdge, baseEdges[j])){
                    ind = j;
                }
            }
            edgePerm[i] = ind;
        }
        
        int[] basePerm = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        
        //let's start permuting the values, keeping track of the transpositions
        int trans = 0;
        
        //run till array is in its initial state
        while(!Arrays.equals(basePerm, edgePerm)){
            
            int index = -1;
            //let's find an element/index pair that's not in it's spot
            for(int i = 0; i < 12; i ++){
                if(edgePerm[i] != i){
                    index = i;
                    break;
                }
            }
            int value = edgePerm[index];
            
            int secondIndex = value;
            int secondValue = edgePerm[secondIndex];
            
            //swapping the 2 elements:
            int temp = value;
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
        for(int i : cornerPairs){
            corn += String.valueOf(i);
        }
        
        String par = parity ? "1" : "0";
        
        return edg + "_" + corn + "_" + par;
        
        
        
        
    }
    //get the edges of the cube, in for of int[2] arrays. Same order as reference,
    //reference facelets given first (yellow facelets on edge flip cube)
    public int[][] getEdges(){
        int[][] thisEdges = new int[12][2];
        
        int i = 0;
        for(int[][] position : EDGE_POSITIONS){
            
            int[] firstPos = position[0];
            int[] secondPos = position[1];
            
            
            int[] faceCoords = new int[]{firstPos[1], firstPos[2]};
            int firstColor = this.getFace(firstPos[0]).getColor(faceCoords);
            
            faceCoords = new int[]{secondPos[1], secondPos[2]};
            int secondColor = this.getFace(secondPos[0]).getColor(faceCoords);
            
           
            
            thisEdges[i] = new int[]{firstColor, secondColor};
            i ++;
            
        }
        return thisEdges;
    }
    
    public int[][] getCorners(){
        int[][] thisCorners = new int[8][23];
        
        int i = 0;
        for(int[][] position : CORNER_POSITIONS){
            
            int[] firstPos = position[0];
            int[] secondPos = position[1];
            int[] thirdPos = position[2];
            
            
            int[] faceCoords = new int[]{firstPos[1], firstPos[2]};
            int firstColor = this.getFace(firstPos[0]).getColor(faceCoords);
            
            faceCoords = new int[]{secondPos[1], secondPos[2]};
            int secondColor = this.getFace(secondPos[0]).getColor(faceCoords);
            
            faceCoords = new int[]{thirdPos[1], thirdPos[2]};
            int thirdColor = this.getFace(thirdPos[0]).getColor(faceCoords);
            
           
            
            thisCorners[i] = new int[]{firstColor, secondColor, thirdColor};
            i ++;
            
        }
        return thisCorners;
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
    
}
