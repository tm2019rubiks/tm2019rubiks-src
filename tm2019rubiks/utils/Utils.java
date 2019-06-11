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
    public static byte[][] rotate(byte[][] toRotate, byte direction){
        byte[][] newMatrix = {{-1, -1, -1},{-1, -1, -1},{-1, -1, -1}};
        //iterating throug each element of the matrix and placing them to the right spot
        //only works for 3x3 matrixes
        for(byte y = 0; y < 3; y ++){
            for(byte x = 0; x < 3; x ++){
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
    public static byte[][] fill(byte color){
        byte[][] matrix = new byte[3][3];
        for(byte i = 0; i < 3; i ++){
            byte[] line = new byte[3];
            Arrays.fill(line, color);
            matrix[i] = line;
        }
        return matrix;
    }
    public static byte[][] copyOf(byte[][] toCopy){
        int lengthY = toCopy.length;
        int lengthX = toCopy[0].length;
        
        //init an array with the same length as the one to copy
        byte[][] newArray = new byte[lengthY][lengthX];
        
        //iterating through each element, and putting it in the new matrix
        for(byte y = 0; y < lengthY; y ++){
            for(byte x = 0; x < lengthX; x ++){
                newArray[y][x] = toCopy[y][x];
            }
        }
        return newArray;
        
    }
    public static boolean setEquals(byte[] a, byte[] b){
       
        if(a.length != b.length){
            return false;
        }
        boolean same = true;
        for(byte curr : a){
            //check if contains
            boolean contained = false;
            for(byte currb : b){
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
    public static ArrayList<Move> simplifyMoves(ArrayList<Move> moves){
        
        if(moves.isEmpty()){
            return moves;
        }
        
        ArrayList<Move> finalMoves = new ArrayList<>();
        
        finalMoves.add(moves.get(0));
        
        for(int i = 1; i < moves.size(); i ++){
            
            
            
            if(finalMoves.isEmpty()){
                finalMoves.add(moves.get(0));
                i ++;
            }
            if(i == moves.size()) return moves;
            Move before = finalMoves.get(finalMoves.size()-1);
            Move current = moves.get(i);
            
            if(before.getFaceIndex() == current.getFaceIndex()){
                if(before.getDirection() == -current.getDirection()){
                    
                    if(before.getTurns() == current.getTurns()){
                        finalMoves.remove(finalMoves.size()-1);
                        continue;
                    }
                    else{
                        finalMoves.remove(finalMoves.size()-1);
                        finalMoves.add(new Move(before.getFaceIndex(), (byte) 1, (byte) 1));
                        continue;
                    }
                    
                }
                else{
                    if(before.getTurns() == current.getTurns()){
                        
                        if(before.getTurns() == 2){
                            finalMoves.remove(finalMoves.size()-1);
                        }
                        else{
                            finalMoves.remove(finalMoves.size()-1);
                        finalMoves.add(new Move(before.getFaceIndex(), (byte) 1, (byte) 2));
                        continue;
                        }
                        
                    }
                    else{
                        finalMoves.remove(finalMoves.size()-1);
                        finalMoves.add(new Move(before.getFaceIndex(), (byte) -1, (byte) 1));
                        continue;
                    }
                    
                }
                
            }
            else{
                finalMoves.add(current);
            }
            
        }
        
        
        
        return finalMoves;
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
        
        
        
        float len = 10000;
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
            ArrayList<Move> solutionSimp = Utils.simplifyMoves(solution);
     
            String originMoves = "";
            String newMoves = "";
            for(Move m : solutionSimp){
                cube.applyMove(m);
                newMoves += m.toString() + "-";
            }
            for(Move m : solution){
                originMoves += m.toString() + "-";
            }
            movesUse += solutionSimp.size();
            if(!cube.repr().equals(new RCube().repr())){
                failed ++;
                
                System.out.println(originMoves + "\n" + newMoves + "\n\n");
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
    public static boolean contains(byte[] array, byte value){
        boolean found = false;
        for(byte i : array){
            if(i == value){
                found = true;
            }
        }
        return found;
    }
    public static byte indexOf(byte[] array, byte value){
        byte index = -1;
        for(byte i = 0; i < array.length; i ++){
            if(array[i] == value){
                index = i;
            }
        }
        return index;
    }
}
