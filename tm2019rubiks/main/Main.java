package tm2019rubiks.main;

import com.jogamp.opengl.GLEventListener;
import javax.swing.JFrame;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import tm2019rubiks.gui.RCube3D;
import tm2019rubiks.gui.RCubeMover;
import tm2019rubiks.rcube.RCube;



public class Main implements GLEventListener {
    private static RCube cube;
    static int rotIters = 0;
    private static RCube3D cube3d;
    private static int frames;
    private static boolean start = false;
    public static float currRotY, currRotX, rotIter;
    
    @Override
    public void init(GLAutoDrawable obj) {
        
    }
    
    @Override
    public void display(GLAutoDrawable obj) {
        long time1 = System.currentTimeMillis();
        final GL2 gl = obj.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
        gl.glEnable(GL2.GL_DEPTH_TEST);
        //GLU g = GLU.createGLU(gl);
        
        
        cube3d.draw(gl);
        
        gl.glFlush();
        long time2 = System.currentTimeMillis();
        if(time2 == time1){
            time2 += 1;
        }
        int x, y;
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        x = mouse.x;
        y = mouse.y;
        float xrot = 200- x/4;
        float yrot = 200- y/4;
        if(!start){
            start = true;
            gl.glRotatef(22.5f, 1, 0, 0);
            
        } 
        gl.glRotatef(xrot-currRotX, 0, 1, 0);
        if(y < 540 && currRotY  != 45){
            System.out.println("moving");
            gl.glRotatef(-currRotX, 0, 1, 0);
            gl.glRotatef(45, 1, 0, 0);
            gl.glRotatef(currRotX, 0, 1, 0);
            currRotY = 45;
        }
        if(y > 540 && currRotY != -45){
            gl.glRotatef(-currRotX, 0, 1, 0);
            gl.glRotatef(-45, 1, 0, 0);
            gl.glRotatef(currRotX, 0, 1, 0);
            currRotY = -45;
            
        }
        currRotX = xrot;
        
        
        
        
        //System.out.println(1000/(time2-time1));
        frames += 1;
        
    }
    
    @Override
    public void reshape(GLAutoDrawable obj1, int obj2, int obj3, int obj4, int obj5) {
        
    }
    
    @Override
    public void dispose(GLAutoDrawable obj) {
        
    }
    
    public static void main(String[] args) {
        frames = 0;
        
        cube = RCube.BASE;
        cube3d = new RCube3D(cube);
        
        RCubeMover cubeMover = new RCubeMover(cube3d);
        
        
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);
        
        
        
        final GLCanvas gc = new GLCanvas(cap);
        Main af = new Main();
        gc.addGLEventListener(af);
        gc.setSize(350, 350);
        gc.addKeyListener(cubeMover);
        
        final JFrame frame = new JFrame ("tm2019rubiks");
        
        
        frame.add(gc);
        frame.setSize( 400, 400 );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final FPSAnimator animator = new FPSAnimator(gc, 60,true);
        animator.start();
        
        
        
        
        
    }
    
    
    
}