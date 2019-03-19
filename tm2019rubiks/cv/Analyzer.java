/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.cv;

import java.awt.Color;
import java.awt.image.BufferedImage;
import tm2019rubiks.rcube.RFace;
import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class Analyzer {
    
    public static RFace[] getFaces(BufferedImage img, int[][][][] positions, int[] faces){
        //positions: contains 3 faces
        //           ---------------> each containing 3 rows with 3 places
        //each of these places contains an x and a y coordinate
        
        
        //"radius" around given point to scan
        int r = 20;
        
        //faces that are to be returned
        RFace[] toReturn = new RFace[3];
        
        
        //for each face
        for(int i = 0; i < 3; i ++){
            
            int[][][] face = positions[i];
            
            //create a matrix containing indexes
            int[][] colorMatrix = new int[3][3];
            
            //iterate through all the positions to fill the matrix (3x3)
            for(int y = 0; y < 3; y ++){
                
                for(int x = 0; x < 3; x ++){
                    
                    //pixels in the given radius by the float rgb values
                    float[][] pixels = new float[r*r/4][3];
                    
                    //in radius r
                    for(int pos = 0; pos < r*r/4; pos ++){
                        
                        //get rgb value from the good position
                        int rgb = img.getRGB(face[y][x][0] + pos % (r/2), face[y][x][1] + (int) pos / (r/2));
                        Color col = new Color(rgb);
                        
                        float compR = col.getRed()/255f;
                        float compG = col.getGreen()/255f;
                        float compB = col.getBlue()/255f;
                        
                        //put it in the pixels array
                        pixels[pos] = new float[]{compR, compG, compB};
                        
                    }
                    
                    //now  that the array is filled, get the average rgb value
                    float[] pixelAverage = Utils.average(pixels);
                    
                    //in the current position of the matrix, put the good color
                    colorMatrix[y][x] = hsvClosestColor(pixelAverage);
                    
                }
                
                
            }
            toReturn[i] = new RFace(colorMatrix);
            
            
            
        }
        
        
        return toReturn;
    }
    
    private static double distance(float[] a, float[] b){
        
        float dx = b[0] - a[0];
        float dy = b[1] - a[1];
        float dz = b[2] - a[2];
        
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
        
        
    }
    private static int closestColor(float[] rgb){
        
        double closest = Float.MAX_VALUE;
        int closestIndex = -1;
        
        for(int i = 0; i < 6; i ++){
            
            double currentDistance = distance(rgb, RFace.COLORS_BY_INDEX[i]);
            
            if(currentDistance < closest){
                closest = currentDistance;
                closestIndex = i;
            }
            
        }
        return closestIndex;
        
    }
    private static int hsvClosestColor(float[] rgb){
        
        int r = (int) (rgb[0] * 255);
        int g = (int) (rgb[1] * 255);
        int b = (int) (rgb[2] * 255);
        
        float[] hsvComps = Color.RGBtoHSB(r, g, b, null);
        
        
        float h = hsvComps[0]*360;
        float s = hsvComps[1]*100;
        float v = hsvComps[2]*100;
        
        System.out.println(h + " " + s + " " + v);
        
        
        for(int treshold = 0; treshold < 20; treshold ++){
            
            
            
            
            //face down
            if(h < 10 + treshold || h > 340 - treshold){
                if(s < 20 + treshold * 2){
                    if(v > 80 - treshold){
                        return RFace.INDEX_FACE_DOWN;
                    }
                }
            }
            
            //face right
            if(h < 160  + treshold && h > 90 - treshold){
                if(s > 60 - treshold * 2){
                    if(v > 60 - treshold * 2){
                        return RFace.INDEX_FACE_RIGHT;
                    }
                }
            }
            //face up
            if(h > 56 - treshold && h < 80 + treshold){
                if(s > 60 - treshold * 2){
                    if(v > 60 - treshold * 2){
                        return RFace.INDEX_FACE_UP;
                    }
                }
                
                
            }
            //face front
            if(h < 20 + treshold || h > 350 - treshold){
                if(s > 60 - treshold * 2){
                    if(v > 60 - treshold * 2){
                        return RFace.INDEX_FACE_FRONT;
                    }
                }
                
                
            }
            //face left
            if(h < 220 + treshold && h > 200 - treshold){
                if(s > 60 - treshold * 2){
                    if(v > 60 - treshold * 2){
                        return RFace.INDEX_FACE_LEFT;
                    }
                }
                
                
            }
            //face back
            if(h > 20 - treshold && h < 40 + treshold){
                if(s > 60 - treshold * 2){
                    if(v > 60 - treshold * 2){
                        return RFace.INDEX_FACE_BACK;
                    }
                }
                
                
            }
            
            
            
            
            
            
            
        }
        
        return RFace.INDEX_FACE_UNASSIGNED;
    }
    
    
    
}
