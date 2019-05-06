/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tm2019rubiks.tables.g2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author estok
 */
public class GenG1 {
    
    
    
    
    static char[] faces = {'F', 'R', 'B', 'L', 'U', 'D' };
    static char[] mods = {'?', 'n', '2', '\''};
    static byte[][] edgeCycles = new byte[][]{{0, 5, 1, 4},
            {5, 8, 6, 9},
            {2, 7, 3, 6},
            {11, 4, 10, 7},
            {0, 11, 2, 8},
            {1, 9, 3, 10} };
    static  byte[][] cornerCycles = new byte[][]{{0, 4, 1, 5},
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
            {4, 2},
            {5, 2}};
    public static HashMap<String, String>  gen(){
        
        
        boolean[][] found = new boolean[6561][4096];
        
        HashMap<String, String> states = new HashMap<>();
        
        long l1 = System.currentTimeMillis();
        
        byte[][] m0s = {{0, 1},{0, 3},{2, 1},{2, 3}};
        
        for(byte[] m0 : m0s){
            G1State a = new G1State();
            move(a, m0[0], m0[1]);
            short e = a.edgeCoord();
            short c = a.cornerCoord();
            if(!found[c][e]){
                found[c][e] = true;
                String key = a.toString();
                String value = moveString(inverse(m0));
                states.put(key, value);
            }
        }
        
        
        for(byte[] m0 : m0s){
            
            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                G1State a = new G1State();
                move(a, m0[0], m0[1]);
                move(a, m1[0], m1[1]);
                short e = a.edgeCoord();
                short c = a.cornerCoord();
                
                if(!found[c][e]){
                    found[c][e] = true;
                    
                    String key = a.toString();
                    String value = moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                    
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
                    short e = a.edgeCoord();
                    short c = a.cornerCoord();
                    
                    if(!found[c][e]){
                        found[c][e] = true;
                        
                        String key = a.toString();
                        String value = moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                        
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
                        short e = a.edgeCoord();
                        short c = a.cornerCoord();
                        
                        if(!found[c][e]){
                            found[c][e] = true;
                            
                            String key = a.toString();
                            String value = moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                            
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
                            short e = a.edgeCoord();
                            short c = a.cornerCoord();
                            if(!found[c][e]){
                                found[c][e] = true;
                                
                                String key = a.toString();
                                String value = moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                
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
                                short e = a.edgeCoord();
                                short c = a.cornerCoord();
                                if(!found[c][e]){
                                    found[c][e] = true;
                                    
                                    String key = a.toString();
                                    String value =  moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                    
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
                                    short e = a.edgeCoord();
                                    short c = a.cornerCoord();
                                    
                                    if(!found[c][e]){
                                        found[c][e] = true;
                                        
                                        String key = a.toString();
                                        String value = moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                        
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

                                    byte[][] left7 = mvs_left(m6);
                                    for(byte[] m7 : left7){
                                        G1State a = new G1State();
                                        move(a, m0[0], m0[1]);
                                        move(a, m1[0], m1[1]);
                                        move(a, m2[0], m2[1]);
                                        move(a, m3[0], m3[1]);
                                        move(a, m4[0], m4[1]);
                                        move(a, m5[0], m5[1]);
                                        move(a, m6[0], m6[1]);
                                        move(a, m7[0], m7[1]);
                                        short e = a.edgeCoord();
                                        short c = a.cornerCoord();
                                        
                                        if(!found[c][e]){
                                            found[c][e] = true;
                                            
                                            String key = a.toString();
                                            String value = moveString(inverse(m7)) + "-" + moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                            
                                            states.put(key, value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //////////////////////////depth 9 (1 to go)
        
        int step = 0;
        for(byte[] m0 : m0s){
            

            byte[][] left1 = mvs_left(m0);
            for(byte[] m1 : left1){
                
                System.out.println((step ++) + "/44 ");

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

                                    byte[][] left7 = mvs_left(m6);
                                    for(byte[] m7 : left7){
                                        
                                        byte[][] left8 = mvs_left(m7);
                                        for(byte[] m8 : left8){
                                            G1State a = new G1State();
                                            move(a, m0[0], m0[1]);
                                            move(a, m1[0], m1[1]);
                                            move(a, m2[0], m2[1]);
                                            move(a, m3[0], m3[1]);
                                            move(a, m4[0], m4[1]);
                                            move(a, m5[0], m5[1]);
                                            move(a, m6[0], m6[1]);
                                            move(a, m7[0], m7[1]);
                                            move(a, m8[0], m8[1]);
                                            short e = a.edgeCoord();
                                            short c = a.cornerCoord();
                                            
                                            if(!found[c][e]){
                                                found[c][e] = true;
                                                
                                                String key = a.toString();
                                                String value = moveString(inverse(m8)) + "-" + moveString(inverse(m7)) + "-" + moveString(inverse(m6)) + "-" + moveString(inverse(m5)) + "-" + moveString(inverse(m4)) + "-" + moveString(inverse(m3)) + "-" + moveString(inverse(m2)) + "-" + moveString(inverse(m1)) + "-" + moveString(inverse(m0));
                                                
                                                states.put(key, value);
                                            }
                                        }
                                        
                                    }
                                    
                                }
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println("time for depth 9 search" + (System.currentTimeMillis()-l1));
        l1 = System.currentTimeMillis();
        for(int i = 0; i < 6561; i ++){
            if(i%81 == 0){
                System.out.println("cornercoord : " + i);
            }
            
            for(int j = 0; j < 4096; j ++){
                if(!found[i][j]){
                    for(byte[] m : moves){
                        G1State a = new G1State((short)j, (short)i);
                        move(a, m[0], m[1]);
                        
                        
                        short c = a.cornerCoord();
                        short e = a.edgeCoord();
                        if(found[c][e]){
                            String value = moveString(m) +"-" + states.get(a.toString());
                            String key = new G1State((short)j, (short)i).toString();
                            states.put(key, value);
                            found[i][j] = true;
                        }
                    }
                }
            }
        }
        
        
        
        System.out.println("time for reverse search: " + (System.currentTimeMillis()-l1));
        
        
        
        System.out.println(states.size());
        
        return states;


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
    
    
    public static HashMap<String, String> treeGen(){
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
        
        
        byte[][] m0s = {{0, 1},{0, 3},{2, 1},{2, 3}};
        
        
        HashMap<String, String> states = new HashMap<>();
        
        
        ArrayList<G1State>[] statesByDepth = new ArrayList[11];
        for(int i = 0; i < 11; i ++){
            statesByDepth[i] = new ArrayList<>();
        }
        
        
        statesByDepth[0].add(new G1State());
        
        boolean[][] found = new boolean[6561][4096];
        String[][] movesByState = new String[6561][4096];
        
        for(String[] s : movesByState){
            Arrays.fill(s, "");
        }
        
        
        for(byte depth = 1; depth < 11; depth ++){
            
            
            int added = 0;
            
            for(G1State g : statesByDepth[depth-1]){
                
                byte[][] left_moves = moves;
                
                if(depth == 1){
                   left_moves = m0s;
                }
                for(byte[] m : left_moves){
                    
                    short originEdgeCoord = g.edgeCoord();
                    short originCornerCoord = g.cornerCoord();
                    
                    
                    G1State cop = new G1State(originEdgeCoord, originCornerCoord);
                    move(cop, m[0], m[1]);
                    
                    short c = cop.cornerCoord();
                    short e = cop.edgeCoord();
                    
                    if(!found[c][e]){
                        movesByState[c][e] = moveString(inverse(m)) + "-" + movesByState[originCornerCoord][originEdgeCoord];
                        found[c][e] = true;
                        statesByDepth[depth].add(cop);
                    }
                    
                    
                }
                
            }
            
            
            
            
        }
        int length = 0;
        for(ArrayList h : statesByDepth){
            length += h.size();
        }
        for(short c = 0; c < 6561; c ++){
            for(short e = 0; e < 4096; e ++){
                
                if(found[c][e]){
                    
                    
                     states.put(new G1State(e, c).toString(), movesByState[c][e] );
                    
                    
                }
                
                
                
            }
        }
        
        
        
        return states; 
    }
}
