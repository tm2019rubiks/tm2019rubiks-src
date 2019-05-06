/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author estok
 */
public class GenG0 {
    
    static char[] faces = {'F', 'R', 'B', 'L', 'U', 'D' };
    static char[] mods = {'?', 'n', '2', '\''};
    
    
    public static HashMap<String, String> treeGen(){
        byte[][] cycles = {{0, 5, 1, 4},
                            {5, 8, 6, 9},
                            {2, 7, 3, 6},
                            {11, 4, 10, 7},
                            {0, 11, 2, 8},
                            {1, 9, 3, 10} };
        byte[][] moves =   {{0, 1},
                            {0, 2},
                            {0, 3},
                            {1, 1},
                            {1, 2},
                            {1, 3},
                            {2, 1},
                            {2, 2},
                            {2, 3},
                            {3, 1},
                            {3, 2},
                            {3, 3},
                            {4, 1},
                            {4, 2},
                            {4, 3},
                            {5, 1},
                            {5, 2},
                            {5, 3}};
        byte[][] m0s = {{4, 1}, {5, 1}};
        
        
        
        HashMap<String, String> states = new HashMap<>();
        
        
        ArrayList<G0State>[] statesByDepth = new ArrayList[8];
        for(int i = 0; i < 8; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new G0State());
        
        boolean[] found = new boolean[4096];
        found[4095] = true;
        String[] movesByState = new String[4096];
        
        Arrays.fill(movesByState, "");
        
        
        for(byte depth = 1; depth < 8; depth ++){
            
            int added = 0;
            
            for(G0State g : statesByDepth[depth-1]){
                
                byte[][] left_moves = moves;
                
                if(depth == 1){
                   left_moves = m0s;
                }
                for(byte[] m : left_moves){
                    
                    short originEdgeCoord = g.edgeCoord();
                    
                    G0State cop = new G0State(originEdgeCoord);
                    cop.cycle(cycles[m[0]], m[0], m[1]);
                    
                    short e = cop.edgeCoord();
                    
                    if(!found[e]){
                        
                        movesByState[e] = moveString(inverse(m)) + "-" + movesByState[originEdgeCoord];
                        found[e] = true;
                        statesByDepth[depth].add(cop);
                    }
                }
            }            
        }
        int length = 0;
        for(ArrayList h : statesByDepth){
            length += h.size();
        }
        
        for(short e = 0; e < 4096; e ++){
            
            if(found[e]){
                states.put(new G0State(e).toString(), movesByState[e] );
            }
        }
        
        return states;
    }
    
    
    
    
    
    
    private static byte[][] mvs_left(byte[] move){
        byte[][] mvs = new byte[15][2];
        byte i = 0;
        byte[] currmove = new byte[2];
        for(byte f = 0; f < 6; f ++){
            
            if(f == move[0]) continue;
            for(byte t = 1; t < 4; t ++){
                currmove[0] = f;
                currmove[1] = t;
                mvs[i] = Arrays.copyOf(currmove, 2);
                i ++;
            }
            
        }
        return mvs;
    }
    private static String moveString(byte[] move){
        return String.valueOf(faces[move[0]]) +String.valueOf( mods[move[1]]);
        
        
        
    }
    private static  byte[] inverse(byte[] move){
        byte turns = move[1];
        if(turns != 2){
            turns = (byte) (4 - turns);
        }
        return new byte[]{move[0],turns};
    }
    
}
