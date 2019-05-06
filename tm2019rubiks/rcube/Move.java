package tm2019rubiks.rcube;

/**
 *
 * @author estok
 */
public class Move {
    
    
    final private int direction,
                      faceIndex,
                      turns;
    final String moveString;
    //final private int[][]
    
    
    public static final Move F, FP, F2, R, RP, R2, B, BP, B2, L, LP, L2, U, UP, U2, D, DP, D2;
    public static final Move[] MOVES;
    
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
        MOVES = new Move[]{F, FP, F2, R, RP, R2, B, BP, B2, L, LP, L2, U, UP, U2, D, DP, D2};
    }

    //designs one of the 12 manips possible on the rcube
    
    public Move(int faceIndex, int direction, int turns) {
        this.faceIndex = faceIndex;
        this.direction = direction;
        this.turns = turns;
        this.moveString = null;
        
    }
    //constructor used with strings ( just plug in the standard notation name of the move
    //and it initializes a move which corresponds to the string
    public Move(String moveString) {
        char[] symbols = moveString.toCharArray();
        int faceIndex, direction, turns;
        switch(symbols[0]){
            case 'F':
                faceIndex = RFace.INDEX_FACE_FRONT;
                break;
            case 'R':
                faceIndex = RFace.INDEX_FACE_RIGHT;
                break;
            case 'B':
                faceIndex = RFace.INDEX_FACE_BACK;
                break;
            case 'L':
                faceIndex = RFace.INDEX_FACE_LEFT;
                break;
            case 'U':
                faceIndex = RFace.INDEX_FACE_UP;
                break;
            case 'D':
                faceIndex = RFace.INDEX_FACE_DOWN;
                break;
            default:
                System.out.println("unsupported move " + symbols[0]);
                faceIndex = RFace.INDEX_FACE_DOWN;
        }
        
        // ' means anti-clockwise 
        // 2 means half-turn
        // n means nothing
        switch (symbols[1]) {
            case 'p':
                direction = -1;
                turns = 1;
                break;
            case '\'':
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
                System.out.println("more operators found than expected: " + String.valueOf(symbols));
                direction = 1;
                turns = 4;
        }
        this.faceIndex = faceIndex;
        this.direction = direction;
        this.turns = turns;
        this.moveString = moveString;
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Move other = (Move) obj;
        if (this.direction != other.direction) {
            return false;
        }
        if (this.faceIndex != other.faceIndex) {
            return false;
        }
        if (this.turns != other.turns) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(this.moveString != null){
            return this.moveString;
        }
        else {
            char face;
            switch(this.faceIndex){
                case RFace.INDEX_FACE_FRONT:
                    face = 'F';
                    break;
                case RFace.INDEX_FACE_RIGHT:
                    face = 'R';
                    break;
                case RFace.INDEX_FACE_BACK:
                    face = 'B';
                    break;
                case RFace.INDEX_FACE_LEFT:
                    face = 'L';
                    break;
                case RFace.INDEX_FACE_UP:
                    face = 'U';
                    break;
                case RFace.INDEX_FACE_DOWN:
                    face = 'D';
                    break;
                default:
                    face = '?';
            }
            char mod;
            if(this.direction == -1){
                mod = 'p';
            }
            else if(this.turns == 2){
                mod = '2';
            }
            else{
                mod = 'n';
            }
            return String.valueOf(face) + String.valueOf(mod);
        }
        
    }

    public Move inverse() {
        int invDir, invTurns, invFace;
        invFace = this.faceIndex;
        invTurns = this.turns;
        if(this.turns == 2){
            invDir = 1;
        }
        else{
            invDir = this.direction * -1;
        }
        return new Move(invFace, invDir, invTurns);
    }
    
    
}
