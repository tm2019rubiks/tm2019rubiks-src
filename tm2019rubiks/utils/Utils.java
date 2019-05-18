package tm2019rubiks.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import tm2019rubiks.main.Main;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.solve.Solver;

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
    public static ArrayList<Move> parseMoves(String s){
        
        ArrayList<Move> moves = new ArrayList<>();
        
        if(s == null) return moves;
        if(s.equals("")) return moves;
        if(s.equals("-")) return moves;
        String[] moveStrings = s.split("-");
        for(String move : moveStrings){
            moves.add(new Move(move));
        }
        return moves;
        
    }
    
    
    //remove this tumor
    
    public static void thistleTest(){
        
        float len = 100000;
        Solver solv = Main.solver;
        
        int max = 0;
        
        int failed = 0;
        Random r = new Random();
        int movesUse = 0;
        
        long tbef = System.currentTimeMillis();
        
        for(int i = 0; i < len; i ++){
            
             
            RCube cube = new RCube();
            cube.scramble(r.nextInt(100)+ 100);
            
            RCube copy = cube.copy();
            
            
            
            ArrayList<Move> solution = solv.thistleSolution(cube);
            
            for(Move m : solution){
                cube.applyMove(m);
            }
            movesUse += solution.size();
            if(!cube.repr().equals(new RCube().repr())){
                failed ++;
                System.out.println("failed: " + copy.repr() + "  " + cube.repr());
            }
            
            if((i+1) % 1000 == 0){
                System.out.println("failed:" + failed + "/" + i + "  " + "average moves:" + movesUse/(float)i);
            }
            if(max < solution.size()){
                max = solution.size();
            }
            
            
            
            
            
                
        }
        System.out.println("failed:" + failed + "/" + len + "  " + "average moves:" + movesUse/len + "   " + max + "    time: " + (System.currentTimeMillis() - tbef) );
    }
    public static boolean contains(int[] array, int value){
        boolean found = false;
        for(int i : array){
            if(i == value){
                found = true;
            }
        }
        return found;
    }
    public static int indexOf(int[] array, int value){
        int index = -1;
        for(int i = 0; i < array.length; i ++){
            if(array[i] == value){
                index = i;
            }
        }
        return index;
    }
}
