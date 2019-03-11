package tm2019rubiks.utils;

import java.util.Arrays;
import tm2019rubiks.rcube.Move;

/**
 *
 * @author estok
 */
public class Utils {
    
    
    //1 clockwise, -1 anti-clockwise
    public static int[][] rotate(int[][] toRotate, int direction){
        int[][] newMatrix = {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}};
        //iterating throug each element of the matrix and placing them to the right spot
        //only works for 3x3 matrixes
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
        return newMatrix;
    }
    public static int[][] fill(int color){
        int[][] matrix = new int[3][3];
        for(int i = 0; i < 3; i ++){
            int[] line = new int[3];
            Arrays.fill(line, color);
            matrix[i] = line;
        }
        return matrix;
    }
    public static int[][] copyOf(int[][] toCopy){
        int lengthY = toCopy.length;
        int lengthX = toCopy[0].length;
        
        //init an array with the same length as the one to copy
        int[][] newArray = new int[lengthY][lengthX];
        
        //iterating through each element, and putting it in the new matrix
        for(int y = 0; y < lengthY; y ++){
            for(int x = 0; x < lengthX; x ++){
                newArray[y][x] = toCopy[y][x];
            }
        }
        return newArray;
        
    }
    public static boolean setEquals(int[] a, int[] b){
       
        if(a.length != b.length){
            return false;
        }
        boolean same = true;
        for(int curr : a){
            //check if contains
            boolean contained = false;
            for(int currb : b){
                if(currb == curr){
                    contained = true;
                }
            }
            if(!contained){
                same = false;
            }
            
        }
        return same;
        
    }
    public static Move[] simplifyMoves(Move[] moves){
        return null;
    }
    
    public static float[] average(float[][] items){
        
        int length = items[0].length;
        
        float[] toReturn = new float[length];
        
        for(int i = 0; i < items.length; i ++){
            
            for(int j = 0; j < length; j ++){
                
                toReturn[j] += items[i][j];
                
            }
            
            
        }
        for(int i = 0; i < length; i ++){
            toReturn[i] /= items.length;
        }
        return toReturn;
    }
}
