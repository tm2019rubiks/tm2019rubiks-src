package tm2019rubiks.rcube;

import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class RFace {
    
    //serves as index for the colors, and since colors on middle-pieces don't get rotated
    //they also denote which face of the rcube we are dealing with
    public static final byte INDEX_FACE_FRONT = 0,
                            INDEX_FACE_RIGHT = 1,
                            INDEX_FACE_BACK = 2,
                            INDEX_FACE_LEFT = 3,
                            INDEX_FACE_UP = 4,
                            INDEX_FACE_DOWN = 5,
                            INDEX_FACE_UNASSIGNED = 6,
                            INDEX_FACE_UNASSIGNED_2 = 7,
                            INDEX_FACE_UNASSIGNED_3 = 8;
    
    public static final float[] RGB_COLOR_FRONT = {1f, 0.0f, 0.0f},
                                RGB_COLOR_RIGHT = {0.0f, 1.0f, 0.0f},
                                RGB_COLOR_BACK = {1.0f, 0.50f, 0.0f},
                                RGB_COLOR_LEFT = {0.0f,  0.0f, 1.0f},
                                RGB_COLOR_UP = {1f, 1f, 0.0f},
                                RGB_COLOR_DOWN = {1.0f, 1.0f, 1.0f},
                                RGB_COLOR_UNASSIGNED = {0.5f, 0.5f, 0.5f},
                                RGB_COLOR_UNASSIGNED_2 = {0.48f, 0.48f, 0.48f},
                                RGB_COLOR_UNASSIGNED_3 = {0.52f, 0.52f, 0.52f};
    
    
    
    //to make it easy to get the colors for the rendering
    public static final float[][] COLORS_BY_INDEX =
                                {RGB_COLOR_FRONT,
                                RGB_COLOR_RIGHT,
                                RGB_COLOR_BACK,
                                RGB_COLOR_LEFT,
                                RGB_COLOR_UP,
                                RGB_COLOR_DOWN,
                                RGB_COLOR_UNASSIGNED,
                                RGB_COLOR_UNASSIGNED_2,
                                RGB_COLOR_UNASSIGNED_3};
                                
    
    private byte colorIndex;
    private byte[][] colors;
    
    
    //an RFace is a wrapper for a matrix of ints denoting indexes
    //these indexes indicate the colors of the face
    
    public RFace(byte[][] colors){
        this.colors = colors;
        this.colorIndex = colors[1][1];
        
    }
    //called only when a move is applied to the cube.
    //calling this out of context will corrupt the cube, if the face is part of it
    protected void twist(byte direction, byte turns){
        byte[][] newColors = Utils.copyOf(this.colors);
        for(int i = 0; i < turns; i ++){
            newColors = Utils.rotate(newColors, direction);
        }
        this.colors = newColors;
    }
    public byte[][] getColors() {
        return colors;
    }
    public byte getColor(byte[] coords){
        return colors[coords[0]][coords[1]];
    }

    public void setColors(byte[][] colors) {
        this.colors = colors;
        this.colorIndex = colors[1][1];
    }

    public byte getColorIndex() {
        return colorIndex;
    }
    public String toString() {
        String f = "";
        
        
        for(byte[] line : this.getColors()){
            for(byte col : line){
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
        return f;
    
    }
    
}
