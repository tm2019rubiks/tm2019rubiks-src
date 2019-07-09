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
public class ConvG4 {
    
    HashMap<String, String> turnConv, backConv;
    
    
    public ConvG4(){
        turnConv = new HashMap<>();
        turnConv.put("F2", "1");
        turnConv.put("R2", "2");
        turnConv.put("B2", "3");
        turnConv.put("L2", "4");
        turnConv.put("U2", "5");
        turnConv.put("D2", "6");
        
        backConv = new HashMap<>();
        for(Entry<String, String> e : turnConv.entrySet()){
            backConv.put(e.getValue(), e.getKey());
        }
        
        
    }
    
    public  void conv() throws IOException{
        

        

        
        
        
        FileWriter fw = new FileWriter("C:\\Users\\estok\\Documents\\toG4_enc.txt");

        Scanner scanner = new Scanner(getClass().getResourceAsStream("toG4.txt"));
        
        int i = 0;
        while(scanner.hasNextLine()){
            System.out.println(i ++);
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            String encodedState = encodeState(lines[0]);
            String encodedMoves = encodeMoves(lines[1]);
            
            if(!decodeMoves(encodedMoves).equals(lines[1]+"-")){
                System.out.println(decodeMoves(encodedMoves) + " " + lines[1]);
            }
            fw.write(encodedState + " " + encodedMoves + System.getProperty("line.separator"));
            
        }
        scanner.close();
        fw.close();

       
       

       
    }
    
    public String encodeMoves(String mvs){
        
        //makes  a hex literal based on the values of the moves
        String base7 = "";
        for(String s : mvs.split("-")){
            base7 += turnConv.get(s);
        }
        //converts that to a long
        long l = Long.parseLong(base7, 7);
        
        //encodes its bytes in b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        return encoded;
        
    }
    public String decodeMoves(String mvs){
    
        //creates empty string
        String decoded = "";
        
        //decodes the value into base7
        String decodedBase7 =  new BigInteger( Base64.getDecoder().decode(mvs.getBytes())).toString(7);
        
        
        //gets the corrresponding moves for each hec digit
        for(char c : decodedBase7.toCharArray()){
            String ofChar = "" + c;
            decoded += backConv.get(ofChar) + "-";
            
        }
        return decoded;
        
    }
    public String encodeState(String state){
        
        //4 bits: corners
        //24 bits: slices
        
        //getting the first part ( base 2)
        String stateLiteral = state.split("_")[0];
        
        //convert 2nd part to base2
        String secondLiteral = Integer.toString(Integer.parseInt(state.split("_")[1], 4), 2);
        for(; secondLiteral.length() < 24;){
            secondLiteral = "0" + secondLiteral;
        }
        
        

        
        stateLiteral += secondLiteral;
        //getting a long with that value:
        long l = Long.parseLong(stateLiteral, 2);
        
        //converts its bytes to b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        
        return encoded;
    }
    public String decodeState(String encoded){
        
        String withoutPadding = new BigInteger(Base64.getDecoder().decode(encoded.getBytes())).toString(2);
        int remainder = 28 - withoutPadding.length();
        
        //adding the padding
        for(int i = 0; i < remainder; i ++){
            withoutPadding = "0" + withoutPadding;
        }
        String cornerPairs = withoutPadding.substring(0, 4);
        String slices = withoutPadding.substring(4);
        
        String slicesBase4 = Long.toString(Long.parseLong(slices, 2), 4);
        //putting the padding on the second part
        
        for(; slicesBase4.length() < 12;){
            slicesBase4 = "0" + slicesBase4;
        }
        
        
        return cornerPairs + "_" + slicesBase4;
        
    }
    public int getID(String decoded){
        
        String corners, slices;
        String[] splitted = decoded.split("_");
        slices = splitted[1];
        
        //every 4th char can be ignored, because it can be found by looking at which
        //num (0-4) wasn't used before
        String simplifiedSlices = "";
        
        char[] chars = slices.toCharArray();
        for(byte i = 0; i < chars.length; i ++){
            if(i%4 != 3){
                simplifiedSlices += String.valueOf(chars[i]);
            }
        }
        String slicesBase2 = Integer.toString(Integer.parseInt(simplifiedSlices, 4), 2);
        String total = splitted[0] + slicesBase2;
        
        
        return Integer.parseInt(total, 2);
    }
    
}
