/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g4;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;

/**
 *
 * @author estok
 */
public class GenG3 {
    
    public static HashMap<String, String> treeGen(){
        
        Move[] moves =   {Move.F2, Move.R2, Move.B2, Move.L2, Move.U2, Move.D2};
        
        
        
        HashMap<String, String> states = new HashMap<>();
        
        
        ArrayList<RCube>[] statesByDepth = new ArrayList[16];
        for(int i = 0; i < 16; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new RCube());
        states.put(new RCube().repr(), "");
        
        
        
        
        for(byte depth = 1; depth < 16; depth ++){
            
            
            for(RCube r : statesByDepth[depth-1]){
                
                
                for(Move m : moves){
                    
                    
                    RCube copy = r.copy();
                    copy.applyMove(m);
                    
                    
                    if(!states.containsKey(copy.repr())){
                        String value = m.inverse().toString() + "-" + states.get(r.repr());
                        states.put(copy.repr(), value);
                        statesByDepth[depth].add(copy);
                    }
                    //System.out.println(states.size());
                }
            } 
            System.out.println(statesByDepth[depth].size());
        }
        int length = 0;
        for(ArrayList h : statesByDepth){
            length += h.size();
        }
        
        
        return states;
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
            
        }
    }
    
    
}
