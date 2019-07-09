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
public class ConvG3 {
    
    HashMap<String, String> turnConv, backConv;
    
    
    public ConvG3(){
        
        //this map defines which number to assign to which move
        turnConv = new HashMap<>();
        turnConv.put("F2", "1");
        turnConv.put("Rn", "2");
        turnConv.put("R'", "3");
        turnConv.put("R2", "4");
        turnConv.put("B2", "5");
        turnConv.put("Ln", "6");
        turnConv.put("L'", "7");
        turnConv.put("L2", "8");
        turnConv.put("U2", "9");
        turnConv.put("D2", "a");
        
        //the inverse of the first map
        backConv = new HashMap<>();
        for(Entry<String, String> e : turnConv.entrySet()){
            backConv.put(e.getValue(), e.getKey());
        }
        
        
    }
    
    
    //write the conversion to a file
    public  void conv() throws IOException{
        

        

        
        
        
        FileWriter fw = new FileWriter("C:\\Users\\estok\\Documents\\toG3_enc.txt");

        Scanner scanner = new Scanner(getClass().getResourceAsStream("generated/toG3.txt"));
        
        int i = 0;
        while(scanner.hasNextLine()){
            System.out.println(i ++);
            String line = scanner.nextLine();
            String[] lines = line.split(" ");
            String encodedState = encodeState(lines[0]);
            String encodedMoves = encodeMoves(lines[1]);
            
            if(!decodeState(encodedState).equals(lines[0])){
                System.out.println(decodeState(encodedState) + " " + lines[0]);
                break;
            }
            fw.write(encodedState + " " + encodedMoves + System.getProperty("line.separator"));
            
        }
        scanner.close();
        fw.close();

       
       

       
    }
    
    public String encodeMoves(String mvs){
        
        //makes  a hex literal based on the values of the moves
        String base11 = "";
        for(String s : mvs.split("-")){
            base11 += turnConv.get(s);
        }
        //converts that to a long
        long l = Long.parseLong(base11, 11);
        
        //encodes its bytes in b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        return encoded;
        
    }
    public String decodeMoves(String mvs){
    
        //creates empty string
        String decoded = "";
        
        //decodes the value into hex
        String decodedBase11 =  new BigInteger( Base64.getDecoder().decode(mvs.getBytes())).toString(11);
        
        
        //gets the corrresponding moves for each hec digit
        for(char c : decodedBase11.toCharArray()){
            String ofChar = "" + c;
            decoded += backConv.get(ofChar) + "-";
            
        }
        return decoded;
        
    }
    public String encodeState(String state){
        
        //8 bits: edges
        //16 bits: corners
        //1 bit: parity
        
        //getting the first part ( base 2)
        String stateLiteral = state.split("_")[0];
        
        //convert 2nd part to base2
        String secondLiteral = Integer.toString(Integer.parseInt(state.split("_")[1], 4), 2);
        for(; secondLiteral.length() < 16;){
            secondLiteral = "0" + secondLiteral;
        }
        
        //get 3rd part
        String thirdLiteral = state.split("_")[2];
        

        
        stateLiteral += secondLiteral + thirdLiteral;
        //getting a long with that value:
        long l = Long.parseLong(stateLiteral, 2);
        
        //converts its bytes to b64
        String encoded = Base64.getEncoder().encodeToString(BigInteger.valueOf(l).toByteArray());
        
        return encoded;
    }
    public String decodeState(String encoded){
        
        String withoutPadding = new BigInteger(Base64.getDecoder().decode(encoded.getBytes())).toString(2);
        int remainder = 25 - withoutPadding.length();
        
        //adding the padding
        for(int i = 0; i < remainder; i ++){
            withoutPadding = "0" + withoutPadding;
        }
        String edges = withoutPadding.substring(0, 8);
        String cornersBase2 = withoutPadding.substring(8, 24);
        //putting the padding on the second part
        String cornersBase3 = Integer.toString(Integer.parseInt(cornersBase2, 2), 4);
        for(;cornersBase3.length() < 8;){
            cornersBase3 = "0" + cornersBase3;
        }
        String parity = withoutPadding.substring(24);
        
        return edges + "_" + cornersBase3 + "_" +parity;
        
    }
    public int getID(String decoded){
        return 0;
    }
    
}
