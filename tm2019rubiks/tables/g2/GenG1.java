/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tm2019rubiks.tables.g2;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author estok
 */
public class GenG1 {
    static byte[][] edgeCycles, cornerCycles;
    static char[] faces = {'F', 'R', 'B', 'L', 'U', 'D' };
    static char[] mods = {'?', 'n', '2', '\''};
    public static void gen(){
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
            {4, 2},
            {5, 2}};
        
        HashMap<String, String> states = new HashMap<>();
        
        byte[][] m0s = {{0, 1},{0, 3},{2, 1},{2, 3}};
        
        for(byte[] m0 : m0s){
            G1State a = new G1State();
            move(a, m0[0], m0[1]);
            String key = a.toString();
            String value = moveString(inverse(m0));
            if(!states.containsKey(key)){
                states.put(key, value);
            }
            
            
        }
        
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                G1State a = new G1State();
                move(a, m0[0], m0[1]);
                move(a, m1[0], m1[1]);
                String key = a.toString();
                String value = moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                if(!states.containsKey(key)){
                    states.put(key, value);
                }
            }
            
        }
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    G1State a = new G1State();
                    move(a, m0[0], m0[1]);
                    move(a, m1[0], m1[1]);
                    move(a, m2[0], m2[1]);
                    String key = a.toString();
                    String value = moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                    if(!states.containsKey(key)){
                        states.put(key, value);
                    }
                }
                
            }
            
        }
        
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    
                    byte[][] left3 = mvs_left(m2);
                    for(byte[] m3 : left3){
                        G1State a = new G1State();
                        move(a, m0[0], m0[1]);
                        move(a, m1[0], m1[1]);
                        move(a, m2[0], m2[1]);
                        move(a, m3[0], m3[1]);
                        String key = a.toString();
                        String value = moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                        if(!states.containsKey(key)){
                            states.put(key, value);
                        }
                    }
                }
            }
        }
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    
                    byte[][] left3 = mvs_left(m2);
                    for(byte[] m3 : left3){
                        
                        byte[][] left4 = mvs_left(m3);
                        for(byte[] m4 : left4){
                            G1State a = new G1State();
                            move(a, m0[0], m0[1]);
                            move(a, m1[0], m1[1]);
                            move(a, m2[0], m2[1]);
                            move(a, m3[0], m3[1]);
                            move(a, m4[0], m4[1]);
                            String key = a.toString();
                            String value = moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                            if(!states.containsKey(key)){
                                states.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    
                    byte[][] left3 = mvs_left(m2);
                    for(byte[] m3 : left3){
                        
                        byte[][] left4 = mvs_left(m3);
                        for(byte[] m4 : left4){
                            
                            byte[][] left5 = mvs_left(m4);
                            for(byte[] m5 : left5){
                                
                                G1State a = new G1State();
                                move(a, m0[0], m0[1]);
                                move(a, m1[0], m1[1]);
                                move(a, m2[0], m2[1]);
                                move(a, m3[0], m3[1]);
                                move(a, m4[0], m4[1]);
                                move(a, m5[0], m5[1]);
                                String key = a.toString();
                                String value =  moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                if(!states.containsKey(key)){
                                    states.put(key, value);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    
                    byte[][] left3 = mvs_left(m2);
                    for(byte[] m3 : left3){
                        
                        byte[][] left4 = mvs_left(m3);
                        for(byte[] m4 : left4){
                            
                            byte[][] left5 = mvs_left(m4);
                            for(byte[] m5 : left5){
                                
                                byte[][] left6 = mvs_left(m5);
                                for(byte[] m6 : left6){
                                    G1State a = new G1State();
                                    move(a, m0[0], m0[1]);
                                    move(a, m1[0], m1[1]);
                                    move(a, m2[0], m2[1]);
                                    move(a, m3[0], m3[1]);
                                    move(a, m4[0], m4[1]);
                                    move(a, m5[0], m5[1]);
                                    move(a, m6[0], m6[1]);
                                    String key = a.toString();
                                    String value = moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                    if(!states.containsKey(key)){
                                        states.put(key, value);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                byte[][] left2 = mvs_left(m1);
                for(byte[] m2 : left2){
                    
                    byte[][] left3 = mvs_left(m2);
                    for(byte[] m3 : left3){
                        
                        byte[][] left4 = mvs_left(m3);
                        for(byte[] m4 : left4){
                            
                            byte[][] left5 = mvs_left(m4);
                            for(byte[] m5 : left5){
                                
                                byte[][] left6 = mvs_left(m5);
                                for(byte[] m6 : left6){
                                    G1State a = new G1State();
                                    move(a, m0[0], m0[1]);
                                    move(a, m1[0], m1[1]);
                                    move(a, m2[0], m2[1]);
                                    move(a, m3[0], m3[1]);
                                    move(a, m4[0], m4[1]);
                                    move(a, m5[0], m5[1]);
                                    move(a, m6[0], m6[1]);
                                    String key = a.toString();
                                    String value = moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                    if(!states.containsKey(key)){
                                        states.put(key, value);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(states.size());
        
        
    }
    private static void move(G1State a, byte face, byte turns){
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
}
