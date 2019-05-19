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
        HashMap<String, String> validStates = new HashMap<>();
        
        
        ArrayList<RCube>[] statesByDepth = new ArrayList[30];
        for(int i = 0; i < 30; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new RCube());
        states.put(new RCube().repr(), "");
        
        
        
        
        for(byte depth = 1; depth < 29; depth ++){
            
            
            for(RCube r : statesByDepth[depth-1]){
                
                
                for(Move m : moves){
                    
                    
                    RCube copy = r.copy();
                    copy.applyMove(m);
                    
                    //System.out.println(copy.isInStage(4));
                    
                    boolean a = copy.stage1().equals("111111111111");
                    boolean b = copy.stage2().equals("111100000000_00000000");
                    boolean c = copy.stage3().equals("11110000_00112233_1");
                    
                    if(!states.containsKey(copy.repr())){
                            String value = m.inverse().toString() + "-" + states.get(r.repr());
                            states.put(copy.repr(), value);
                            statesByDepth[depth].add(copy);
                    }
                    if(a &&  b && c){
                        String value = m.inverse().toString() + "-" + states.get(r.repr());
                        validStates.put(copy.stage4(), value);
                    }
                    
                    
                    //System.out.println(states.size());
                }
            } 
            System.out.println(depth + ":" + statesByDepth[depth].size());
            System.out.println(validStates.size());
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
    
    
}
