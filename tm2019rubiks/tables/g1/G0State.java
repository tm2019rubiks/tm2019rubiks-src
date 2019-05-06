/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g1;

/**
 *
 * @author estok
 */
public class G0State {
    private boolean[] edges = {true, true, true,true, true, true,true, true, true,true, true, true};
    private static short[] powsOf2 ={2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    
    
    protected G0State(){
    }
    public G0State(short edgeCoord){
        short coordEdge = edgeCoord;
        
        boolean[] edg = new boolean[12];
        
        for(byte i = 0; i < 12; i ++){
            if(coordEdge >= powsOf2[i]){
                edg[i] = true;
                coordEdge -= powsOf2[i];
            }
        }
        this.edges = edg;
    }
    protected void cycle(byte[] toCycle, byte face, byte turns){
        
        for(byte t = 0; t < turns; t ++){
            boolean temp = this.edges[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.edges[toCycle[3- i]] = this.edges[toCycle[3-i-1]];
            }
            this.edges[toCycle[0]] = temp;
            
            //
            if(face == 4 || face == 5){
                this.flip(toCycle);
            }
        }
        
        
    }
    protected void flip(byte[] edges){
        for(short s: edges){
            this.edges[s] = !this.edges[s];
        }
    }
    public short edgeCoord(){
        short sum = 0;
        for(byte i = 0; i < 12; i ++){
            if(this.edges[i]){
                sum += this.powsOf2[i];
            }
            
        }
        return sum;
    }
    @Override
    public String toString(){
        String s = "";
        for(boolean b : this.edges){
            s += (b)?"1":"0";
        }
        return s;
    }
}
