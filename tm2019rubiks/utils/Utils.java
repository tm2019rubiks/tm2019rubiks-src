package tm2019rubiks.utils;

import java.util.Arrays;

/**
 *
 * @author estok
 */
public class Utils {
    public static int[][] rotate(int[][] toRotate, int direction) throws Exception{
        int[][] newMatrix = {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}};
        
        for(int y = 0; y < 3; y ++){
            for(int x = 0; x < 3; x ++){
                if(direction == 1){
                    newMatrix[x][2-y] = toRotate[y][x];
                }
                else{
                    newMatrix[2-x][y] = toRotate[y][x];
                }
                
            }
        }
        for(int[] line : newMatrix){
            for(int value : line){
                if(value == -1){
                    throw new Exception("something went wrong during rotation (-1 left in array)" + Arrays.toString(line));
                }
            }
        }
        return newMatrix;
    }
    public static int[][] copyOf(int[][] toCopy){
        int lengthY = toCopy.length;
        int lengthX = toCopy[0].length;
        
        int[][] newArray = new int[lengthY][lengthX];
        
        for(int y = 0; y < lengthY; y ++){
            for(int x = 0; x < lengthX; x ++){
                newArray[y][x] = toCopy[y][x];
            }
        }
        return newArray;
        
    }
}
