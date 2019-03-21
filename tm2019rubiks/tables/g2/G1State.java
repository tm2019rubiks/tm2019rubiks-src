/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g2;

/**
 *
 * @author estok
 */
public class G1State {
    
    byte[] cornerTwist = {2, 1, 2, 1};
    
    
    private byte[] corners = {0, 0, 0, 0, 0, 0, 0, 0};
    private boolean[] edges = {true, true, true, true, false, false, false, false, false, false, false, false};
    //u2 : no effect on orientation
    //d2 : -||- 
    //l, r, l2, r2, r', l' : no effect 
    
    //corners are labeled from ufr ccw starting with U
    //
    
    //corner pos/ f -> 0 -> 1 -> 0 -> 1
    // 
    
    public G1State(){
        
    }
    
    
    protected void cycle(byte[] toCycle, byte face, byte turns){
        
        for(byte t = 0; t < turns; t ++){
            boolean temp = this.edges[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.edges[toCycle[3- i]] = this.edges[toCycle[3-i-1]];
            }
            this.edges[toCycle[0]] = temp;
            
            //
            
        }
        
        
    }
    protected void cycleCorners(byte[] toCycle, byte face, byte turns){
        
        for(byte t = 0; t < turns; t ++){
            byte temp = this.corners[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.corners[toCycle[3- i]] = this.corners[toCycle[3-i-1]];
            }
            this.corners[toCycle[0]] = temp;
            
            //
            if(face == 0 || face == 2){
                for(byte i = 0; i < 4; i ++){
                    this.corners[toCycle[i]] += cornerTwist[i];
                    this.corners[toCycle[i]] %= 3;
                }
            }
            
        }
        
        
        
    }
    
    public String toString(){
        String s = "";
        for(boolean b : this.edges){
            s += (b)?"1":"0";
        }
        s += "_";
        for(byte b : this.corners){
            s += String.valueOf(b);
        }
        return s;
    }
    
}
