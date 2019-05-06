/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tm2019rubiks.tables.g3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author estok
 */
public class GenG2 {
    
    
    
    
    static char[] faces = {'F', 'R', 'B', 'L', 'U', 'D' };
    static char[] mods = {'?', 'n', '2', '\''};
    static byte[][] edgeCycles = new byte[][]{{0, 5, 1, 4},
            {5, 8, 6, 9},
            {2, 7, 3, 6},
            {11, 4, 10, 7},
            {0, 11, 2, 8},
            {1, 9, 3, 10} };
    static  byte[][] cornerCycles = new byte[][]{{0, 4, 1, 5},
            {4, 3, 6, 1},
            {3, 7, 2, 6},
            {7, 0, 5, 2},
            {7, 3, 4, 0},
            {5, 1, 6, 2}};
        
        
    static byte[][] moves =   {
            {0, 2},
            {1, 1},
            {1, 2},
            {1, 3},
            {2, 2},
            {3, 1},
            {3, 2},
            {3, 3},
            {4, 2},
            {5, 2}};

    private static void move(G2State a, byte face, byte turns){
        a.cycle(edgeCycles[face], face, turns);
        a.cycleCorners(cornerCycles[face], face, turns);
        
    }
    
    
    private static  byte[] inverse(byte[] move){
        byte turns = move[1];
        if(turns != 2){
            turns = (byte) (4 - turns);
        }
        return new byte[]{move[0],turns};
    }
    private static byte[][] mvs_left(byte[] move){
        boolean isUD = move[0] == 4 || move[0] == 5;
        byte length = (isUD) ? (byte)13 : (byte)11;
        byte[][] mvs = new byte[length][2];
        byte ind = 0;
        if(isUD){
            for(byte i = 0; i < 4; i ++){
                for(byte j = 1; j < 4; j ++){
                    mvs[ind] = new byte[]{i, j};
                    ind ++;
                }
            }
            mvs[ind] = new byte[]{(move[0] == 4) ? 5 : (byte)4, (byte)2};
        }
        else{
            for(byte i = 0; i < 4; i ++){
                if(i == move[0]) continue;
                for(byte j = 1; j < 4; j ++){
                    
                    mvs[ind] = new byte[]{i, j};
                    ind ++;
                }
            }
            mvs[9] = new byte[]{4, 2};
            mvs[10] = new byte[]{5, 2};
        }
        return mvs;
        
    }
    private static String moveString(byte[] move){
        return String.valueOf(faces[move[0]]) +String.valueOf( mods[move[1]]);
    }
    
    
    public static HashMap<String, String> treeGen(){
        
        //2520 elements, contains numbers in base 4
        int[] idToNum = new int[2520];
        new CoorConversion();
        int[] numToId = CoorConversion.numToId;
        
        System.out.println("started");
        edgeCycles = new byte[][]{{0, 5, 1, 4},
            {5, 8, 6, 9},
            {2, 7, 3, 6},
            {11, 4, 10, 7},
            {0, 11, 2, 8},
            {1, 9, 3, 10} };
        cornerCycles =new byte[][]{{0, 4, 1, 5},
            {4, 3, 6, 1},
            {3, 7, 2, 6},
            {7, 0, 5, 2},
            {7, 3, 4, 0},
            {5, 1, 6, 2}};
        
        
        byte[][] moves =   {
            {0, 2},//f2
            
            {1, 1},//r
            {1, 2},
            {1, 3},
            
            {2, 2},//b2
            
            {3, 1},//l
            {3, 2},
            {3, 3},
            {4, 2},//u2, d2
            {5, 2}};
        
        
        
        
        HashMap<String, String> states = new HashMap<>();
        
        
        ArrayList<G2State>[] statesByDepth = new ArrayList[15];
        for(int i = 0; i < 15; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new G2State());
        
        boolean[][][] found = new boolean[2520][4096][2];
        String[][][] movesByState = new String[2520][4096][2];
        
        for(String[][] stringArray : movesByState){
            for(String[] s : stringArray){
                Arrays.fill(s, "");
            }
        }
        
        for(byte depth = 1; depth < 15; depth ++){
            
            int added = 0;
            
            for(G2State g : statesByDepth[depth-1]){
                
                byte[][] left_moves = moves;
                
                for(byte[] m : left_moves){
                    
                    short originEdgeCoord = g.edgeCoord();
                    int originCornerCoord = g.cornerCoord();
                    byte originParityCoord = g.isEven();
                    
                    int originCornerId = numToId[originCornerCoord];
                    
                    
                    G2State cop = new G2State(originEdgeCoord, originCornerCoord, originParityCoord);
                    move(cop, m[0], m[1]);
                    
                    int cBs4 = cop.cornerCoord();
                    int c = numToId[cBs4];
                    short e = cop.edgeCoord();
                    byte p = cop.isEven();
                    
                    if(!found[c][e][p]){
                        movesByState[c][e][p] = moveString(inverse(m)) + "-" + movesByState[originCornerId][originEdgeCoord][originParityCoord];
                        found[c][e][p] = true;
                        statesByDepth[depth].add(cop);
                        added ++;
                    }
                    
                    
                }
                
            }
            System.out.println("depth: " + depth + ", added: " + added);
            
            
            
        }
        int length = 0;
        for(ArrayList h : statesByDepth){
            length += h.size();
        }
        for(int c = 0; c < 2520; c ++){
            for(short e = 0; e < 4096; e ++){
                for(byte p = 0; p < 2; p ++){
                    if(found[c][e][p]){
                        
                        int cornerCoord = CoorConversion.idToNum[c];
                    
                        states.put(new G2State(e, cornerCoord, p).toString(), movesByState[c][e][p] );
                    
                    }
                }
                
                
                
                
            }
        }
        
        
        
        return states; 
    }
}
