package tm2019rubiks.gui;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JPanel;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;

public class RCube3D extends JPanel implements GLEventListener{
    final private RCube cube;
    private float rotXState, rotYState, desiredRotX, desiredRotY;
    private boolean turningLeft, turningRight, turningUp, turningDown;
    

    public RCube3D(RCube cube){
        super();
        this.desiredRotX = (float) (Math.PI/4);
        this.desiredRotY = (float) (Math.PI/4);
        this.cube = cube;
        
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);
        
        GLCanvas canvas = new GLCanvas(cap);
        
        canvas.addGLEventListener(this);
        
        canvas.setSize(600, 600);
        
        this.add(canvas);
        this.setSize(700, 700);
        this.setVisible(true);
        this.setFocusable(false);
        canvas.setFocusable(false);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();
        
        
    }

    public RCube getRCube() {
        return cube;
    }
    public void startRot(){
        //TODO: make rotation animation
    }
    //basically all of the vertices are drawn in the method
    //the colors are determined by the colors of the RCube object
    public void draw(GL2 gl){
        gl.glBegin (GL2.GL_QUADS);
        
        
        for(RFace face : this.cube.getFaces()){
            int[][] colors = face.getColors();
            int index = face.getColorIndex();
            // a switch is needed because 
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
                case RFace.INDEX_FACE_BLACK:
                    for(int y = 0; y < 3; y ++){
                        for(int x = 0; x < 3; x ++){
                            float r, g, b;
                            float[] color = RFace.COLORS_BY_INDEX[face.getColors()[y][x]];
                            r = color[0]; g = color[1]; b = color[2];
                            gl.glColor3f(r,g,b);
                            gl.glVertex3f(-0.6f + x* 0.4f, -0.6f, -0.6f+ y * 0.4f);
                            gl.glVertex3f(-0.2f + x* 0.4f, -0.6f, -0.6f+ y * 0.4f);
                            gl.glVertex3f(-0.2f + x* 0.4f, -0.6f, -0.2f+ y * 0.4f);
                            gl.glVertex3f(-0.6f + x* 0.4f, -0.6f, -0.2f+ y * 0.4f);
                        }
                    }
                    break;
            }
            
        }        
        gl.glEnd();
        
        
        
        
    }

    @Override
    public void init(GLAutoDrawable glad) {
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        
        if(this.turningRight){
            this.desiredRotX -= 0.03;
        }
        if(this.turningLeft){
            this.desiredRotX += 0.03;
        }
        if(this.turningUp && this.desiredRotY > -0.7){
            this.desiredRotY -= 0.03;
        }
        if(this.turningDown && this.desiredRotY < 0.7){
            this.desiredRotY += 0.03;
        }
        
        
        
        
        
        
        final GL2 gl = glad.getGL().getGL2();
        gl.glClearColor(0.933333f, 0.933333f, 0.933333f, 1.0f);
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
        gl.glEnable(GL2.GL_DEPTH_TEST);
        GLU g = GLU.createGLU(gl);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        if(this.desiredRotX != this.rotXState || this.desiredRotY != this.rotYState){
            gl.glLoadIdentity();
            g.gluPerspective(90, -1, 0.1, 10);
            double posX = 2*Math.cos(this.desiredRotX) * Math.cos(this.desiredRotY);
            double posY = 2*Math.sin(this.desiredRotY);
            double posZ = 2*Math.sin(this.desiredRotX) * Math.cos(this.desiredRotY);
            g.gluLookAt(posX, posY, posZ,
                        0, 0, 0,
                        0, 1, 0
            );
            this.rotXState = this.desiredRotX;
            this.rotYState = this.desiredRotY;
        }
        this.draw(gl);
        
        gl.glFlush();
//        
        
        
        
        
    }
    

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    public float getDesiredRotX() {
        return desiredRotX;
    }

    public void setDesiredRotX(float desiredRotX) {
        this.desiredRotX = desiredRotX;
    }

    public float getDesiredRotY() {
        return desiredRotY;
    }

    public void setDesiredRotY(float desiredRotY) {
        this.desiredRotY = desiredRotY;
    }

    public void setTurningLeft(boolean turningLeft) {
        this.turningLeft = turningLeft;
    }

    public void setTurningRight(boolean turningRight) {
        this.turningRight = turningRight;
    }

    public void setTurningUp(boolean turningUp) {
        this.turningUp = turningUp;
    }

    public void setTurningDown(boolean turningDown) {
        this.turningDown = turningDown;
    }
    
    
    
    
    
    
    
}
