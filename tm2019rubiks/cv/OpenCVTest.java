/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm2019rubiks.cv;


import java.awt.image.BufferedImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;


/**
 *
 * @author estok
 */
public class OpenCVTest {
    public static void ji() throws FrameGrabber.Exception{
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        
        
       

        CanvasFrame canvasFrame1=new CanvasFrame("GrayImage");
        canvasFrame1.setCanvasSize(640,480);
        int a = 1;
        Java2DFrameConverter conv = new Java2DFrameConverter();
        OpenCVFrameConverter.ToMat conv2 = new OpenCVFrameConverter.ToMat();
      
        while(a == 1){
            
            Frame grabbedImage = grabber.grab();
            
            Mat mat = new Mat();
            Size size = new Size(20,20);
            Mat image = conv2.convert(grabbedImage);
            
            blur(image, mat, size);
            
            
            
            
            
        canvasFrame1.showImage(conv.convert(conv2.convert(mat)));
            
        }
        
        
        
        
        
        grabber.stop();
	
        
    }
    
    
}
