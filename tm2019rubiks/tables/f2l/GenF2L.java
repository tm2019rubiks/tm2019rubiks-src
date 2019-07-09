/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.f2l;

import tm2019rubiks.tables.g4.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class GenF2L {
    
    static char[] faces = {'F', 'R', 'B', 'L', 'U', 'D' };
    static char[] mods = {'?', 'n', '2', '\''};
    
    public static HashMap<String, String> treeGen(){
        
        HashMap<String, String> states = new HashMap<>();
        HashMap<String, String> validStates = new HashMap<>();
        
        
        ArrayList<F2L1State>[] statesByDepth = new ArrayList[60];
        for(int i = 0; i < 60; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new F2L1State());
        states.put(new F2L1State().f2l1CrossState(), "");
        validStates.put(new F2L1State().ff2l(), "");
        

        for(byte depth = 1; depth < 60; depth ++){
            
            if(depth > 2){
                statesByDepth[depth-2].clear();
            }
            
            int size = statesByDepth[depth-1].size();
            int i = 0;
            for(int k = 0; k < statesByDepth[depth-1].size(); k ++){
                
                F2L1State r = statesByDepth[depth-1].get(k);
                i ++;
                if(i% 10000 == 0) System.out.println(i + "/" + size);
                
                for(byte[] m : F2L1State.moves){
                    
                    
                    F2L1State copy = r.copy();
                    byte turns = 2;
                    
                    copy.cycle(m[0], m[1]);
                    
                    boolean notMovedC = Utils.indexOf(copy.corner, (byte) 1) == Utils.indexOf(r.corner, (byte)1);
                    boolean notMovedE = Utils.indexOf(copy.edges, (byte) 5) == Utils.indexOf(r.edges, (byte)5);
                    boolean notSlotMoved = Utils.contains(new byte[]{2, 3, 4}, m[0]);
                    boolean crossIntact = copy.isCrossIntact();
                    
                    if(notMovedC && notMovedE && notSlotMoved) continue; 
                    
                    
                    if(!states.containsKey(copy.f2l1CrossState())){
                            String value = moveString(inverse(m)) + "-" + states.get(r.f2l1CrossState());
                            states.put(copy.f2l1CrossState(), value);
                            statesByDepth[depth].add(copy);
                    }
                    if(crossIntact && (!validStates.containsKey(copy.ff2l()))){
                        String value = moveString(inverse(m)) + "-" + states.get(r.f2l1CrossState());
                        validStates.put(copy.ff2l(), value);
                        
                        //System.out.println("new valid entry:" + copy.crossState());
                    }
                    
                    //System.out.println(validStates.size());
                }
                r=null;
                statesByDepth[depth-1].set(k, null);
            } 
            //System.out.println(depth + ":" + statesByDepth[depth].size());
            System.out.println(validStates.size() + "  " + states.size());
        }
        int length = 0;
        for(ArrayList h : statesByDepth){
            length += h.size();
        }
        
        
        return validStates;
    }
    
    public static void write(){
        
        try{
            
        FileWriter fw = new FileWriter("C:\\Users\\estok\\Documents\\toG4.txt");
//        
	for (Map.Entry e : treeGen().entrySet()) {
//            
//                
		fw.write(e.getKey() + " " + e.getValue() + System.getProperty("line.separator"));
	}
// 
	fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static  byte[] inverse(byte[] move){
        byte turns = move[1];
        if(turns != 2){
            turns = (byte) (4 - turns);
        }
        return new byte[]{move[0],turns};
    }
    private static String moveString(byte[] move){
        return String.valueOf(faces[move[0]]) +String.valueOf( mods[move[1]]);
    }
    
    
}
