package tm2019rubiks.rcube;

/**
 *
 * @author estok
 */
public class Move {
    
    
    final private int direction,
                      faceIndex,
                      turns;
    //final private int[][]
    
    
    public static final Move F, FP, F2, R, RP, R2, B, BP, B2, L, LP, L2, U, UP, U2, D, DP, D2;
    
    static {
        F = new Move("Fn");
        FP = new Move("Fp");
        F2 = new Move("F2");
        R = new Move("Rn");
        RP = new Move("Rp");
        R2 = new Move("R2");
        B = new Move("Bn");
        BP = new Move("Bp");
        B2 = new Move("B2");
        L = new Move("Ln");
        LP = new Move("Lp");
        L2 = new Move("L2");
        U = new Move("Un");
        UP = new Move("Up");
        U2 = new Move("U2");
        D = new Move("Dn");
        DP = new Move("Dp");
        D2 = new Move("D2");
    }

    //designs one of the 12 manips possible on the rcube
    
    private Move(int faceIndex, int direction, int turns) {
        this.faceIndex = faceIndex;
        this.direction = direction;
        this.turns = turns;
        
        //rcube moves need to have a face which is rotated(U D L R F B), so if the index is
        //not in the good range, it throws an error
        if(faceIndex < 0 || faceIndex > 5){
            //throw new Exception("face of rotation not valid" + String.valueOf(faceIndex));
            System.out.println("face of rotation not valid" + String.valueOf(faceIndex));
        }
        
        if(direction != -1 && direction != 1){
            //throw new Exception("direction of rotation not valid" + String.valueOf(direction));
            System.out.println("direction of rotation not valid" + String.valueOf(direction));
        }
    }
    //constructor used with strings ( just plug in the standard notation name of the move
    //and it initializes a move which corresponds to the string
    private Move(String moveString) {
        char[] symbols = moveString.toCharArray();
        int faceIndex, direction, turns;
        switch(symbols[0]){
            case 'F':
                faceIndex = RFace.INDEX_FACE_RED;
                break;
            case 'R':
                faceIndex = RFace.INDEX_FACE_GREEN;
                break;
            case 'B':
                faceIndex = RFace.INDEX_FACE_ORANGE;
                break;
            case 'L':
                faceIndex = RFace.INDEX_FACE_BLUE;
                break;
            case 'U':
                faceIndex = RFace.INDEX_FACE_YELLOW;
                break;
            case 'D':
                faceIndex = RFace.INDEX_FACE_BLACK;
                break;
            default:
                //throw new Exception("unsupported move " + symbols[0]);
                System.out.println("unsupported move " + symbols[0]);
                faceIndex = RFace.INDEX_FACE_BLACK;
        }
        
        // ' means anti-clockwise 
        // 2 means half-turn
        //   means nothing
        switch (symbols[1]) {
            case 'p':
                direction = -1;
                turns = 1;
                break;
            case '2':
                direction = 1;
                turns = 2;
                break;
            case 'n':
                direction = 1;
                turns = 1;
                break;
            default:
                //throw new Exception("more operators found than expected: " + String.valueOf(symbols));
                System.out.println("more operators found than expected: " + String.valueOf(symbols));
                direction = 1;
                turns = 4;
        }
        this.faceIndex = faceIndex;
        this.direction = direction;
        this.turns = turns;
    }

    public int getDirection() {
        return direction;
    }

    public int getFaceIndex() {
        return faceIndex;
    }

    public int getTurns() {
        return turns;
    }
    public int[] getMoveParams(){
        return new int[]{this.faceIndex, this.direction, this.turns};
    }
}
