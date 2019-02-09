package tm2019rubiks.main;

import com.jogamp.opengl.GLEventListener;
import javax.swing.JFrame;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;
import tm2019rubiks.utils.Utils;



public class Main implements GLEventListener, KeyListener {
    private static RCube cube;
    private static int frames;
    private static boolean start = false, m = false;
    
    @Override
    public void init(GLAutoDrawable obj) {
        
    }
    
    @Override
    public void display(GLAutoDrawable obj) {
        long time1 = System.currentTimeMillis();
        final GL2 gl = obj.getGL().getGL2();
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
        gl.glEnable(GL2.GL_DEPTH_TEST);
        
        gl.glBegin (GL2.GL_QUADS);
        
        
        for(RFace face : cube.getFaces()){
            int[][] colors = face.getColors();
            int index = colors[1][1];
            
            switch(index){
                case RFace.INDEX_FACE_RED:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(-0.6f + x*0.4f, 0.6f - y* 0.4f, -0.6f);
                            gl.glVertex3f(-0.2f + x*0.4f, 0.6f - y* 0.4f, -0.6f);
                            gl.glVertex3f(-0.2f + x*0.4f, 0.2f - y* 0.4f, -0.6f);
                            gl.glVertex3f(-0.6f + x*0.4f, 0.2f - y* 0.4f, -0.6f);
                        }
                    }
                    break;
                case RFace.INDEX_FACE_ORANGE:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(0.6f - x*0.4f, 0.6f - y* 0.4f, 0.6f);
                            gl.glVertex3f(0.2f - x*0.4f, 0.6f - y* 0.4f, 0.6f);
                            gl.glVertex3f(0.2f - x*0.4f, 0.2f - y* 0.4f, 0.6f);
                            gl.glVertex3f(0.6f - x*0.4f, 0.2f - y* 0.4f, 0.6f);
                        }
                    }
                    break;
                case RFace.INDEX_FACE_GREEN:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(0.6f, 0.6f - y* 0.4f, -0.6f+ x * 0.4f);
                            gl.glVertex3f(0.6f, 0.6f - y* 0.4f, -0.2f+ x * 0.4f);
                            gl.glVertex3f(0.6f, 0.2f - y* 0.4f, -0.2f+ x * 0.4f);
                            gl.glVertex3f(0.6f, 0.2f - y* 0.4f, -0.6f+ x * 0.4f);
                        }
                    }
                    break;
                case RFace.INDEX_FACE_BLUE:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(-0.6f, 0.6f - y* 0.4f, +0.6f- x * 0.4f);
                            gl.glVertex3f(-0.6f, 0.6f - y* 0.4f, +0.2f- x * 0.4f);
                            gl.glVertex3f(-0.6f, 0.2f - y* 0.4f, +0.2f- x * 0.4f);
                            gl.glVertex3f(-0.6f, 0.2f - y* 0.4f, +0.6f- x * 0.4f);
                        }
                    }
                    break;
                case RFace.INDEX_FACE_YELLOW:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(-0.6f + x* 0.4f, 0.6f, 0.6f- y * 0.4f);
                            gl.glVertex3f(-0.2f + x* 0.4f, 0.6f, 0.6f- y * 0.4f);
                            gl.glVertex3f(-0.2f + x* 0.4f, 0.6f, 0.2f- y * 0.4f);
                            gl.glVertex3f(-0.6f + x* 0.4f, 0.6f, 0.2f- y * 0.4f);
                        }
                    }
                    break;
            }
        }
        
        
        
        
        
        
        
        
        gl.glEnd();
        gl.glFlush();
        long time2 = System.currentTimeMillis();
        if(time2 == time1){
            time2 += 1;
        }
        if(!start){
            start = true;
            gl.glRotatef(-45, 1, 0, 0);
        }
        gl.glRotatef(0.2f, 0, 1, 0);
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
        
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);
        
        
        final GLCanvas gc = new GLCanvas(cap);
        Main af = new Main();
        gc.addGLEventListener(af);
        gc.setSize(350, 350);
        gc.addKeyListener(af);
        
        //Now creating a frame using Frame class of AWT
        final JFrame frame = new JFrame ("AWT Frame");
        
        
        frame.add(gc);
        frame.setSize( 400, 400 );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final FPSAnimator animator = new FPSAnimator(gc, 60,true);
        animator.start();
        
        
        cube = RCube.BASE;
        Move r = null, u = null, rp = null, up = null;
        try {
            r = new Move("Rn");
            u = new Move("Un");
            rp = new Move("Rp");
            up = new Move("Up");
            System.out.println(cube);
            
            for(int i = 0; i < 100; i ++){
                while(!m){
                    Thread.sleep(30);
                    
                }
                m = false;
                
                cube.applyMove(r);
                Thread.sleep(200);
                cube.applyMove(u);
                Thread.sleep(200);
                cube.applyMove(rp);
                Thread.sleep(200);
                cube.applyMove(up);
                Thread.sleep(200);
                //cube.applyMove(u);
                //cube.applyMove(rp);
                //cube.applyMove(up);
//                cube.applyMove(rp);
//                cube.applyMove(up);
System.out.println("___________\n"+cube);
            }
            System.out.println("___________\n"+cube);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_SPACE){
            m = true;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}