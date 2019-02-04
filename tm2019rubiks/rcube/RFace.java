package tm2019rubiks.rcube;

import java.util.HashMap;

/**
 *
 * @author estok
 */
public class RFace {
    
    //serves as index for the colors, and since colors on middle-pieces don't get rotated
    //they also denote which face of the rcube we are dealing with
    public static final int INDEX_FACE_RED = 0,
                            INDEX_FACE_GREEN = 1,
                            INDEX_FACE_ORANGE = 2,
                            INDEX_FACE_BLUE = 3,
                            INDEX_FACE_YELLOW = 4,
                            INDEX_FACE_BLACK = 5,
                            INDEX_FACE_UNASSIGNED = 6;
    
    public static final float[] RGB_COLOR_RED = {1.0f, 0.0f, 0.0f},
                                RGB_COLOR_GREEN = {0.0f, 1.0f, 0.0f},
                                RGB_COLOR_ORANGE = {1.0f, 0.5f, 0.0f},
                                RGB_COLOR_BLUE = {0.0f, 0.0f, 1.0f},
                                RGB_COLOR_YELLOW = {1.0f, 1.0f, 0.0f},
                                RGB_COLOR_BLACK = {0.0f, 0.0f, 0.0f},
                                RGB_COLOR_UNASSIGNED = {0.5f, 0.5f, 0.5f};
    
    
    //to make it easy to get the colors for the rendering
    public static final float[][] COLORS_BY_INDEX =
                                {{1.0f, 0.0f, 0.0f},
                                {0.0f, 1.0f, 0.0f},
                                {1.0f, 0.5f, 0.0f},
                                {0.0f, 0.0f, 1.0f},
                                {1.0f, 1.0f, 0.0f},
                                {0.0f, 0.0f, 0.0f}};
                                
    
    private int colorIndex;
    private int[][] colors;
    
    
    //an RFace() is a wrapper for a matrix of ints denoting indexes
    //these indexes indicate the colors of the face
    
    public RFace(int[][] colors) throws Exception{
        if(colors.length != 3){
            throw new Exception("cant create face with line number other than 3");
        }
        for(int[] line : colors){
            if(colors.length != 3){
                throw new Exception("cant create face row with element number other than 3");
            } 
        }
        this.colors = colors;
        this.colorIndex = colors[1][1];
        
    }

    public int[][] getColors() {
        return colors;
    }

    public void setColors(int[][] colors) {
        this.colors = colors;
        this.colorIndex = colors[1][1];
    }
    
    
    
    
}
