/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g3;

import java.util.Arrays;

/**
 *
 * @author estok
 */
public class G2State {
    
    
    private static short[] powsOf2 = {128, 64, 32, 16, 8, 4, 2, 1};
    private static short[] powsOf4 = {16384, 4096, 1024, 256, 64, 16, 4, 1};
    
    private boolean[] edges;
    private byte[] corners;
    private boolean isEven;
    
    
    public G2State(){
        
        
        //for edges from 5-12, because 0-4 are fixed in their slice and can not be moved
        //from there.
        //true = 5678, false = 9101112
        edges = new boolean[]{true, true, true, true, false, false, false, false};
        corners = new byte[]{0, 0, 1, 1, 2, 2, 3, 3};
        isEven = true;
    }
    public G2State(short edgeCoord, int cornerCoord, byte parityCoord){
        
        short coordEdge = edgeCoord;
        int coordCorner = cornerCoord;
        
        boolean[] edg = new boolean[8];
        byte[] corn = new byte[8];
        
        for(byte i = 0; i < 8; i ++){
            if(coordEdge >= powsOf2[i]){
                edg[i] = true;
                coordEdge -= powsOf2[i];
            }
        }
        for(byte i = 0; i < 8; i ++){
            byte n = 0;
            while(coordCorner >= powsOf4[i]){
                n += 1;
                coordCorner -= powsOf4[i];
            }
            corn[i] = n;
        }
        this.corners = corn;
        this.edges = edg;
        isEven = parityCoord == 1;
    }
    
    public short edgeCoord(){
        short sum = 0;
        for(byte i = 0; i < 8; i ++){
            if(this.edges[i]){
                sum += this.powsOf2[i];
            }
            
        }
        return sum;
    }
    
    public int cornerCoord(){
        int sum = 0;
        for(byte i = 0; i < 8; i ++){
            sum += this.corners[i] * this.powsOf4[i];
        }
        return sum;
    }
    
    public byte isEven(){
        return this.isEven ? (byte)1 : 0;
    }
    protected void cycle(byte[] toCycle, byte face, byte turns){
        
        byte[] realCorners = new byte[12];
        for(byte i = 0; i < 12; i ++){
            if(i < 4){
                realCorners[i] = Byte.MIN_VALUE;
            }
            else{
                realCorners[i] = (this.edges[i-4]) ? (byte) 1 : (byte) 0;
            }
            
        }
        
        for(byte t = 0; t < turns; t ++){
            byte temp = realCorners[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                realCorners[toCycle[3- i]] = realCorners[toCycle[3-i-1]];
            }
            realCorners[toCycle[0]] = temp;
            
            this.isEven = !this.isEven;
            
        }
        for(int i = 4; i < 12; i ++){
            this.edges[i-4] = (realCorners[i] == 1); 
        }
        
        
    }
    protected void cycleCorners(byte[] toCycle, byte face, byte turns){
        
        for(byte t = 0; t < turns; t ++){
            byte temp = this.corners[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.corners[toCycle[3- i]] = this.corners[toCycle[3-i-1]];
            }
            this.corners[toCycle[0]] = temp;
            
            
            
        }
        
        
        
    }

    @Override
    public String toString() {
        
        String edgeRepr = "";
        for(boolean b : edges){
            edgeRepr += (b) ? "1" : "0";
        }
        
        String cornRepr = "";
        for(byte b : corners){
            cornRepr += Byte.toString(b);
        }
        
        String parityRepr = (isEven) ? "1" : "0";
        
        return edgeRepr + "_" + cornRepr + "_" + parityRepr;
    }
    
}


