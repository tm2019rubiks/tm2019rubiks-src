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

    
    public Move(int faceIndex, int direction, int turns) throws Exception{
        this.faceIndex = faceIndex;
        this.direction = direction;
        this.turns = turns;
        if(faceIndex < 0 || faceIndex > 5){
            throw new Exception("face of rotation not valid" + String.valueOf(faceIndex));
        }
        if(direction != -1 && direction != 1){
            throw new Exception("direction of rotation not valid" + String.valueOf(direction));
        }
    }
    public Move(String moveString) throws Exception{
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
                throw new Exception("unsupported move " + symbols[0]);
        }
        switch (symbols[1]) {
            case '\'':
                direction = -1;
                turns = 1;
                break;
            case '2':
                direction = 1;
                turns = 2;
                break;
            case ' ':
                direction = 1;
                turns = 1;
            default:
                throw new Exception("more operators found than expected: " + String.valueOf(symbols));
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
