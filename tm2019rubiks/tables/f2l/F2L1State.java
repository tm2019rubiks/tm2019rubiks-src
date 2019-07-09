/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.f2l;

import java.util.Arrays;
import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class F2L1State {
    
    public byte[] edges;
    public boolean[] oris;
    public byte[] corner;
    public byte[] cOri;
    
    
    static byte[] cornerTwist = {2, 1, 2, 1};
    static byte[][] cyclesE = {{0, 5, 1, 4},
                            {5, 8, 6, 9},
                            {2, 7, 3, 6},
                            {11, 4, 10, 7},
                            {0, 11, 2, 8},
                            {1, 9, 3, 10} };
    static byte[][] cornerCycles = new byte[][]{{0, 4, 1, 5},
            {4, 3, 6, 1},
            {3, 7, 2, 6},
            {7, 0, 5, 2},
            {7, 3, 4, 0},
            {5, 1, 6, 2}};
    static byte[][] moves =   {{0, 1},
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
    
    
    
    
    public F2L1State(){
        this.edges = new byte[]{0, 1, 0, 3, 0, 5, 0, 0, 0, 9, 10, 0};
        this.oris = new boolean[]{false, false, false, false, false, false,
                                  false, false, false, false, false, false};
        this.cOri = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
        this.corner = new byte[]{0, 1, 0, 0, 0, 0, 0, 0};
        
    }

    private static  byte[] inverse(byte[] move){
        byte turns = move[1];
        
        turns = (byte) (4 - turns);
        
        return new byte[]{move[0],turns};
    }
    protected void cycle(byte face, byte turns){
        
        this.cycleCorners(face, turns);
        
        byte[] toCycle = cyclesE[face];
        
        for(byte t = 0; t < turns; t ++){
            byte temp = this.edges[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.edges[toCycle[3- i]] = this.edges[toCycle[3-i-1]];
            }
            this.edges[toCycle[0]] = temp;
            
            //
            
        }
        for(byte t = 0; t < turns; t ++){
            boolean temp = this.oris[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.oris[toCycle[3- i]] = this.oris[toCycle[3-i-1]];
            }
            this.oris[toCycle[0]] = temp;
            
            //
            if(face == 4 || face == 5){
                this.flip(toCycle);
            }
        }
        
        
    }
    protected void flip(byte[] edges){
        for(short s: edges){
            this.oris[s] = !this.oris[s];
        }
    }
    public boolean isCrossIntact(){
        
        if(this.edges[1] != 1){
            return false;
        }
        if(this.edges[3] != 3){
            return false;
        }
        if(this.edges[9] != 9){
            return false;
        }
        if(this.edges[10] != 10){
            return false;
        }
        if(this.oris[Utils.indexOf(edges, (byte)1)]){
            return false;
        }
        if(this.oris[Utils.indexOf(edges, (byte)3)]){
            return false;
        }
        if(this.oris[Utils.indexOf(edges, (byte)9)]){
            return false;
        }
        if(this.oris[Utils.indexOf(edges, (byte)10)]){
            return false;
        }
        return true;
        
        
//        if(this.edges[1] == 1){
//            if(this.edges[3] == 3){
//                if(this.edges[9] == 9){
//                    if(this.edges[10] == 10){
//                        if(!this.oris[Utils.indexOf(edges, (byte)1)]){
//                            if(!this.oris[Utils.indexOf(edges, (byte)3)]){
//                                if(!this.oris[Utils.indexOf(edges, (byte)9)]){
//                                    if(!this.oris[Utils.indexOf(edges, (byte)10)]){
//                                        return true;
//                                    }
//                                    else return false;
//                                }
//                                else return false;
//                            }
//                            else return false;
//                        }
//                        else return false;
//                    }
//                    else return false;
//                }
//                else return false;
//            }
//            else return false;
//        }
//        else return false;
    }
    private void cycleCorners(byte face, byte turns){
        
        byte[] toCycle = cornerCycles[face];
        
        for(byte t = 0; t < turns; t ++){
            byte temp = this.corner[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.corner[toCycle[3- i]] = this.corner[toCycle[3-i-1]];
            }
            this.corner[toCycle[0]] = temp;
            
        }
        
        
        for(byte t = 0; t < turns; t ++){
            byte temp = this.cOri[toCycle[3]];
            for(byte i = 0; i < 3; i ++){
                this.cOri[toCycle[3- i]] = this.cOri[toCycle[3-i-1]];
            }
            this.cOri[toCycle[0]] = temp;
            if(face == 0 || face == 2){
                for(byte i = 0; i < 4; i ++){
                    this.cOri[toCycle[i]] += cornerTwist[i];
                    this.cOri[toCycle[i]] %= 3;
                }
            }
        }
        
        
        
    }
    public String crossState(){
        byte edge2 = Utils.indexOf(this.edges, (byte)1);
        byte edge10 = Utils.indexOf(this.edges, (byte)9);
        byte edge4 = Utils.indexOf(this.edges, (byte)3);
        byte edge11 = Utils.indexOf(this.edges, (byte)10);
        
        char ori2 = this.oris[edge2] ? '1' : '0';
        char ori10 = this.oris[edge10] ? '1' : '0';
        char ori4 = this.oris[edge4] ? '1' : '0';
        char ori11 = this.oris[edge11] ? '1' : '0';
        
        return String.valueOf(edge2) + "_" + ori2 + "/"+
               String.valueOf(edge10) + "_" + ori10 + "/"+
               String.valueOf(edge4) + "_" + ori4 + "/"+
               String.valueOf(edge11) + "_" + ori11; 
    }
    public String ff2l(){

        
        byte posOfEdge6 = Utils.indexOf(this.edges, (byte)5);
        byte posOfCorner2 = Utils.indexOf(this.corner, (byte)1);
        
        return String.valueOf(posOfEdge6) + "_" + posOfCorner2 + "_"+ (this.oris[posOfEdge6] ? '1' : '0') + this.cOri[posOfCorner2];
        
    }
    public F2L1State copy(){
        F2L1State state = new F2L1State();
        state.cOri = Arrays.copyOf(this.cOri, this.cOri.length);
        state.corner = Arrays.copyOf(this.corner, this.corner.length);
        state.edges = Arrays.copyOf(this.edges, this.edges.length);
        state.oris = Arrays.copyOf(this.oris, this.oris.length);
        return state;
    }
    public String f2l1CrossState(){
        return this.crossState() + "-" + this.ff2l();
    }
    
    
}
