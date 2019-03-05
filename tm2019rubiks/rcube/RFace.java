package tm2019rubiks.rcube;

import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class RFace {
    
    //serves as index for the colors, and since colors on middle-pieces don't get rotated
    //they also denote which face of the rcube we are dealing with
    public static final int INDEX_FACE_FRONT = 0,
                            INDEX_FACE_RIGHT = 1,
                            INDEX_FACE_BACK = 2,
                            INDEX_FACE_LEFT = 3,
                            INDEX_FACE_UP = 4,
                            INDEX_FACE_DOWN = 5,
                            INDEX_FACE_UNASSIGNED = 6;
    
    public static final float[] RGB_COLOR_FRONT = {1.0f, 0.0f, 0.0f},
                                RGB_COLOR_RIGHT = {0.0f, 1.0f, 0.0f},
                                RGB_COLOR_BACK = {1.0f, 0.5f, 0.0f},
                                RGB_COLOR_LEFT = {0.0f,  0.0f, 1.0f},
                                RGB_COLOR_UP = {1.0f, 1.0f, 0.0f},
                                RGB_COLOR_DOWN = {0.0f, 0.0f, 0.0f},
                                RGB_COLOR_UNASSIGNED = {0.5f, 0.5f, 0.5f};
    
    
    //to make it easy to get the colors for the rendering
    public static final float[][] COLORS_BY_INDEX =
                                {RGB_COLOR_FRONT,
                                RGB_COLOR_RIGHT,
                                RGB_COLOR_BACK,
                                RGB_COLOR_LEFT,
                                RGB_COLOR_UP,
                                RGB_COLOR_DOWN};
                                
    
    private int colorIndex;
    private int[][] colors;
    
    
    //an RFace is a wrapper for a matrix of ints denoting indexes
    //these indexes indicate the colors of the face
    
    protected RFace(int[][] colors){
        this.colors = colors;
        this.colorIndex = colors[1][1];
        
    }
    //called only when a move is applied to the cube.
    //calling this out of context will corrupt the cube, if the face is part of it
    protected void twist(int direction, int turns){
        int[][] newColors = Utils.copyOf(this.colors);
        for(int i = 0; i < turns; i ++){
            newColors = Utils.rotate(newColors, direction);
        }
        this.colors = newColors;
    }
    public int[][] getColors() {
        return colors;
    }
    public int getColor(int[] coords){
        return colors[coords[0]][coords[1]];
    }

    public void setColors(int[][] colors) {
        this.colors = colors;
        this.colorIndex = colors[1][1];
    }

    public int getColorIndex() {
        return colorIndex;
    }
    
    
    
    
}
