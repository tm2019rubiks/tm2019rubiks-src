/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.motors;

import arduino.*;
import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.util.Arrays;
import tm2019rubiks.rcube.Move;


/**
 *
 * @author estok
 */
public class MotorControl {
    
    Arduino arduino;
    String port;
    
    public MotorControl(){
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println(Arrays.toString(ports));
        if(ports.length > 1){
            System.out.println("more ports than expected");
        }
        this.port = ports[0].getSystemPortName();
        arduino = new Arduino(this.port, 9600);
        arduino.openConnection();
    }
    

    public void send(ArrayList<Move> moves){
        String toSend = "/";
        
        for(Move m : moves){
            toSend += String.valueOf(m.getFaceIndex());
            int turns = m.getDirection() == -1 ? 3 : m.getTurns();
            turns --; //0 based index
            toSend += String.valueOf(turns);
        }
        toSend += "/";
        
        
        
        
        arduino.serialWrite(toSend);
    }
    public void end(){
        arduino.closeConnection();
    }
    
}
