/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g1;

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
    
    
    public static void gen(){
        
        
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
        
        HashMap<String, String> table = new HashMap<>();
        
        long l1 = System.currentTimeMillis();
        
        
        System.out.println("depth 1");
        ///depth 0:
        for(byte[] m0 : m0s){
            G0State a = new G0State();
            a.cycle(cycles[m0[0]], m0[0], m0[1]);
            String key = a.toString();
            String value = moveString(inverse(m0));
            if(!table.containsKey(key)){
               table.put(key, value);
            }
            
        }
        //////////////////////////////7depth 1
        System.out.println("depth 2");
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                G0State a = new G0State();
                a.cycle(cycles[m0[0]], m0[0], m0[1]);
                a.cycle(cycles[m1[0]], m1[0], m1[1]);
                String key = a.toString();
                String value = moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                if(!table.containsKey(key)){
                    table.put(key, value);
                }
            }
            
            
        }
        ////////////////////depth 2
        System.out.println("depth 3");
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                byte[][] l_m2 = mvs_left(m1);
                for(byte[] m2 : l_m2){
                    G0State a = new G0State();
                    a.cycle(cycles[m0[0]], m0[0], m0[1]);
                    a.cycle(cycles[m1[0]], m1[0], m1[1]);
                    a.cycle(cycles[m2[0]], m2[0], m2[1]);
                    String key = a.toString();
                    String value =  moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                    if(!table.containsKey(key)){
                        table.put(key, value);
                    }
                }
            }
        }
        ////////////////////depth 3
        System.out.println("depth 4");
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                byte[][] l_m2 = mvs_left(m1);
                for(byte[] m2 : l_m2){
                    
                    byte[][] l_m3 = mvs_left(m2);
                    for(byte[] m3 : l_m3){
                        G0State a = new G0State();
                        a.cycle(cycles[m0[0]], m0[0], m0[1]);
                        a.cycle(cycles[m1[0]], m1[0], m1[1]);
                        a.cycle(cycles[m2[0]], m2[0], m2[1]);
                        a.cycle(cycles[m3[0]], m3[0], m3[1]);
                        String key = a.toString();
                        String value =   moveString(inverse(m3)) + "-" +moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                        if(!table.containsKey(key)){
                            table.put(key, value);
                        }
                    }
                }
            }
        }
        /////////////////////77depth 4 (2 to go)
        System.out.println("depth 5");
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                byte[][] l_m2 = mvs_left(m1);
                for(byte[] m2 : l_m2){
                    
                    byte[][] l_m3 = mvs_left(m2);
                    for(byte[] m3 : l_m3){
                        
                        byte[][] l_m4 = mvs_left(m3);
                        for(byte[] m4 : l_m4){
                            G0State a = new G0State();
                            a.cycle(cycles[m0[0]], m0[0], m0[1]);
                            a.cycle(cycles[m1[0]], m1[0], m1[1]);
                            a.cycle(cycles[m2[0]], m2[0], m2[1]);
                            a.cycle(cycles[m3[0]], m3[0], m3[1]);
                            a.cycle(cycles[m4[0]], m4[0], m4[1]);
                            String key = a.toString();
                            String value = moveString(inverse(m4 )) + "-" +  moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                            if(!table.containsKey(key)){
                                table.put(key, value);
                            }
                        }
                    }
                }
            }
        }
        ////////////////////////////depth 5 (1 to go)
        System.out.println("depth 6");
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                byte[][] l_m2 = mvs_left(m1);
                for(byte[] m2 : l_m2){
                    
                    byte[][] l_m3 = mvs_left(m2);
                    for(byte[] m3 : l_m3){
                        
                        byte[][] l_m4 = mvs_left(m3);
                        for(byte[] m4 : l_m4){
                            
                            byte[][] l_m5 = mvs_left(m4);
                            for(byte[] m5 : l_m5){
                                G0State a = new G0State();
                                a.cycle(cycles[m0[0]], m0[0], m0[1]);
                                a.cycle(cycles[m1[0]], m1[0], m1[1]);
                                a.cycle(cycles[m2[0]], m2[0], m2[1]);
                                a.cycle(cycles[m3[0]], m3[0], m3[1]);
                                a.cycle(cycles[m4[0]], m4[0], m4[1]);
                                a.cycle(cycles[m5[0]], m5[0], m5[1]);
                                String key = a.toString();
                                String value = moveString(inverse(m5)) + "-" + moveString(inverse(m4 )) + "-" +  moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                if(!table.containsKey(key)){
                                    table.put(key, value);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        ////////////////////////////depth 6 Last 
        System.out.println("depth 7");
        
        short iter = 0;
        for(byte[] m0 : m0s){
            
            byte[][] l_m1 = mvs_left(m0);
            for(byte[] m1 : l_m1){
                
                byte[][] l_m2 = mvs_left(m1);
                for(byte[] m2 : l_m2){
                    
                    iter ++;
                    if(iter % 30 == 0) System.out.println(iter);
                    
                    byte[][] l_m3 = mvs_left(m2);
                    for(byte[] m3 : l_m3){
                        
                        byte[][] l_m4 = mvs_left(m3);
                        for(byte[] m4 : l_m4){
                            
                            byte[][] l_m5 = mvs_left(m4);
                            for(byte[] m5 : l_m5){
                                
                                byte[][] l_m6 = mvs_left(m5);
                                for(byte[] m6 : l_m6){
                                    G0State a = new G0State();
                                    a.cycle(cycles[m0[0]], m0[0], m0[1]);
                                    a.cycle(cycles[m1[0]], m1[0], m1[1]);
                                    a.cycle(cycles[m2[0]], m2[0], m2[1]);
                                    a.cycle(cycles[m3[0]], m3[0], m3[1]);
                                    a.cycle(cycles[m4[0]], m4[0], m4[1]);
                                    a.cycle(cycles[m5[0]], m5[0], m5[1]);
                                    a.cycle(cycles[m6[0]], m6[0], m6[1]);
                                    String key = a.toString();
                                    String value = moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4 )) + "-" +  moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                    if(!table.containsKey(key)){
                                        table.put(key, value);
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println(System.currentTimeMillis() - l1);
        for(Entry<String, String> entry : table.entrySet()){
            //System.out.println(entry.getKey() + "  " + entry.getValue());
        }
        System.out.println(table.size());
        
//        for(byte[] m0 : m0s){
//            
//            byte[][] l_m1 = mvs_left(m0);
//            for(byte[] m1 : l_m1){
//                
//                byte[][] l_m2 = mvs_left(m1);
//                for(byte[] m2 : l_m2){
//                    
//                    byte[][] l_m3 = mvs_left(m2);
//                    for(byte[] m3 : l_m3){
//                        
//                        byte[][] l_m4 = mvs_left(m3);
//                        for(byte[] m4 : l_m4){
//                            
//                        }
//                    }
//                    
//                }
//                G0State a = new G0State();
//                a.cycle(cycles[m0[0]], m0[0], m0[1]);
//                a.cycle(cycles[m1[0]], m1[0], m1[1]);
//                System.out.println(a + "  "+ Arrays.toString(m0) + "  " + Arrays.toString(m1));
//                
//                
//            }
//            
//        }
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
