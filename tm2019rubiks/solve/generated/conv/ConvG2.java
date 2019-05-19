/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.solve.generated.conv;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author estok
 */
public class ConvG2 {
    
    HashMap<String, String> turnConv, backConv;
    
    
    public ConvG2(){
        turnConv = new HashMap<>();
        turnConv.put("Fn", "e");
        turnConv.put("F'", "1");
        turnConv.put("F2", "2");
        turnConv.put("Rn", "3");
        turnConv.put("R'", "4");
        turnConv.put("R2", "5");
        turnConv.put("Bn", "6");
        turnConv.put("B'", "7");
        turnConv.put("B2", "8");
        turnConv.put("Ln", "9");
        turnConv.put("L'", "a");
        turnConv.put("L2", "b");
        turnConv.put("U2", "c");
        turnConv.put("D2", "d");
        
        backConv = new HashMap<>();
        for(Entry<String, String> e : turnConv.entrySet()){
            backConv.put(e.getValue(), e.getKey());
        }
        
        
    }
    
    public  void conv() throws IOException{
        

        

        
        
        
        //FileWriter fw = new FileWriter("C:\\Users\\estok\\Documents\\toG2_enc.txt");

        Scanner scanner = new Scanner(getClass().getResourceAsStream("generated/toG2.txt"));
        
        int i = 0;
        while(scanner.hasNextLine()){
            System.out.println(i ++);
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            String encodedState = encodeState(lines[0]);
            String encodedMoves = encodeMoves(lines[1]);
            System.out.println(encodedState + " " + encodedMoves);
            //fw.write(encodedState + " " + encodedMoves + System.getProperty("line.separator"));
            
        }
        scanner.close();
        //fw.close();
    }
    
    public String encodeMoves(String mvs){
        
        //makes  a hex literal based on the values of the moves
        String hexa = "";
        for(String s : mvs.split("-")){
            hexa += turnConv.get(s);
        }
        //converts that to a long
        long l = Long.parseLong(hexa, 16);
        
        //encodes its bytes in b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        return encoded;
        
    }
    public String decodeMoves(String mvs){
    
        //creates empty string
        String decoded = "";
        
        //decodes the value into hex
        String decodedHex =  new BigInteger( Base64.getDecoder().decode(mvs.getBytes())).toString(16);
        
        //gets the corrresponding moves for each hec digit
        for(char c : decodedHex.toCharArray()){
            String ofChar = "" + c;
            decoded += backConv.get(ofChar) + "-";
            
        }
        return decoded;
        
    }
    public String encodeState(String state){
        
        //getting the raw base3 literal (concat base2 and base3 literals)
        String stateLiteral = state.split("_")[0] + state.split("_")[1];
        
        //getting a long with that value:
        long l = Long.parseLong(stateLiteral, 3);
        
        //converts its bytes to b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        
        return encoded;
    }
    public String decodeState(String encoded){
        
        String withoutPadding = new BigInteger(Base64.getDecoder().decode(encoded.getBytes())).toString(3);
        int remainder = 20 - withoutPadding.length();
        
        //adding the padding
        for(int i = 0; i < remainder; i ++){
            withoutPadding = "0" + withoutPadding;
        }
        String edges = withoutPadding.substring(0, 13);
        String corners = withoutPadding.substring(13);
        
        return edges + "_" + corners;
        
    }
    
}
