package tm2019rubiks.main;

import com.jogamp.opengl.GLEventListener;
import javax.swing.JFrame;
import com.jogamp.opengl.*;  
import com.jogamp.opengl.awt.GLCanvas;  
import com.jogamp.opengl.util.FPSAnimator;
import java.util.logging.Level;
import java.util.logging.Logger;
import tm2019rubiks.utils.Utils;
  
public class Main implements GLEventListener {  
  
    private static int frames;
    
   @Override  
    public void init(GLAutoDrawable obj) {  
      
       }  
      
   @Override  
public void display(GLAutoDrawable obj) {  
    long time1 = System.currentTimeMillis();
  final GL2 gl = obj.getGL().getGL2();  
  gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );    
  gl.glEnable(GL2.GL_DEPTH_TEST);
  
gl.glBegin (GL2.GL_TRIANGLES); 
gl.glColor3f(0.4f, 0.1f, 0.1f);
gl.glVertex3f(0, 0, 0);
gl.glVertex3f(-0.6f, -0.6f, 0);  
gl.glVertex3f(-0.6f, 0.6f, 0);  
gl.glColor3f(0.4f, 0.1f, 0.9f);
gl.glVertex3f(0, 0, 0);
gl.glVertex3f(-0.6f, 0.6f, 0); 
gl.glVertex3f(-0.6f, 0.6f, -0.6f);
gl.glColor3f(0.0f, 0.1f, 0.9f);
gl.glVertex3f(-0.6f, 0.6f, -0.6f);
gl.glVertex3f(-0.6f, 0.6f, 0); 
gl.glVertex3f(-0.6f, -0.6f, 0);
gl.glEnd();  
gl.glFlush();
long time2 = System.currentTimeMillis();
if(time2 == time1){
    time2 += 1;
}
gl.glRotatef(1, 0, 1, 0);
System.out.println(1000/(time2-time1));

   }  
  
   @Override  
public void reshape(GLAutoDrawable obj1, int obj2, int obj3, int obj4, int obj5) {  
  
   }  
      
   @Override  
public void dispose(GLAutoDrawable obj) {  
  
   }  
  
public static void main(String[] args) {  
  frames = 0;
  
//final GLProfile gp = GLProfile.get(GLProfile.GL2);  
//GLCapabilities cap = new GLCapabilities(gp);  
//  
//  
//final GLCanvas gc = new GLCanvas(cap); 
//Main af = new Main();  
//gc.addGLEventListener(af);          
//gc.setSize(350, 350);  
//  
//      //Now creating a frame using Frame class of AWT  
//final JFrame frame = new JFrame ("AWT Frame");  
//  
//frame.add(gc);  
//frame.setSize( 500, 400 );  
//frame.setVisible(true);  
//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//final FPSAnimator animator = new FPSAnimator(gc, 60,true); 
//animator.start();   
//  
    int[][] a = {{1,2,3},{8,9,4},{7,6,5}};
    int[][] b = {{1,2,3},{8,9,4},{7,6,5}};
        try {
            b = Utils.rotate(a, -1);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i <3; i ++){
            System.out.println(a[i][0] +" "+ a[i][1] + " "+ a[i][2] + "  "+ b[i][0] + " " + b[i][1] + " " + b[i][2]);
        }
   }  
      
}