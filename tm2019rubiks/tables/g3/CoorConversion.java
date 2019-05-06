/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.tables.g3;

import java.util.Scanner;

/**
 *
 * @author estok
 */
public class CoorConversion {
    public static int[] numToId, idToNum;
    
    {
        numToId = new int[65536];
        Scanner scanner = new Scanner(getClass().getResourceAsStream("numToId.txt"));
        int i = 0;
        while(scanner.hasNextLine()){
            numToId[i] = Integer.valueOf(scanner.nextLine());
            i ++;
        }
        scanner.close();
        
        idToNum = new int[2520];
        scanner = new Scanner(getClass().getResourceAsStream("idToNum.txt"));
        i = 0;
        while(scanner.hasNextLine()){
            idToNum[i] = Integer.valueOf(scanner.nextLine());
            i ++;
        }
        scanner.close();
        
    }
    
}
