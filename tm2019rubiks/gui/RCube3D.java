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


//A JComponent that shows a RCube in its 3d form
//

public class RCube3D extends JPanel implements GLEventListener{
    final private RCube cube;
    private float desiredRotX, desiredRotY;
    private boolean turningLeft, turningRight, turningUp, turningDown;
    

    public RCube3D(RCube cube){
        //TODO: get rid of warnings
        super();
        
        //setting rotX to see F and R at 45 degrees
        this.desiredRotX = (float) (-Math.PI/4);
        this.desiredRotY = (float) (0);
        this.cube = cube;
        
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);
        
        GLCanvas canvas = new GLCanvas(cap);
        
        canvas.addGLEventListener(this);
        
        canvas.setSize(600, 600);
        super.add(canvas);
        super.setSize(700, 700);
        super.setVisible(true);
        super.setFocusable(false);
        canvas.setFocusable(false);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();
        
        
    }

    public RCube getRCube() {
        return cube;
    }
    
    //basically all of the vertices are drawn in the method
    //the colors are determined by the colors of the RCube object
    public void draw(GL2 gl){
        
        //start drawing quadrilaterals, meaning that every 4 vertices are
        //interpreted as a single quadrilateral.
        gl.glBegin (GL2.GL_QUADS);
        
        //for each face
        for(RFace face : this.cube.getFaces()){
            int[][] colors = face.getColors();
            
            //facing changes based on which face it's drawing
            int index = face.getColorIndex();
            
            //switch statement needed because the faces are not facing the same
            //direction, making it hard to find a sigle loop that can draw them all
            //only using math formulas
            switch(index){
                
                
                //drawing the faces
                case RFace.INDEX_FACE_FRONT:
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
                case RFace.INDEX_FACE_BACK:
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
                case RFace.INDEX_FACE_RIGHT:
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
                case RFace.INDEX_FACE_LEFT:
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
                case RFace.INDEX_FACE_UP:
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
                case RFace.INDEX_FACE_DOWN:
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
        
        //step out of quad drawing mode
        gl.glEnd();
        
        
        
        
    }

    @Override
    public void init(GLAutoDrawable glad) {
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    //the method that displays the 3d scene
    public void display(GLAutoDrawable glad) {
        
        //because of an FPSAnimator, this method gets executed 60 times / s
        //every frame, if the turning booleans are on, this function will change
        //the angle from which the cube is viewed
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
        
        
        
        //getting gl environment
        final GL2 gl = glad.getGL().getGL2();
        final GLU g = GLU.createGLU(gl);
        
        //same color as JPanel default background color( 238, 238, 238)
        float val = 2.8f/3;
        gl.glClearColor(val, val, val, 1.0f);
        
        //clearing scene, enabling depth
        gl.glClear (GL2.GL_COLOR_BUFFER_BIT |  GL2.GL_DEPTH_BUFFER_BIT );
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        //resetting view
        gl.glLoadIdentity();
        
        //setting perspective
        //fov = 90
        g.gluPerspective(90, -1, 0.1, 3);
        
        //calculating eye position in 3d
        //the camera can move around the equator of a sphere(rotX)
        //and can move up/down along the sphere(rotY)
        double eyeX = 2*Math.cos(this.desiredRotX) * Math.cos(this.desiredRotY);
        double eyeY = 2*Math.sin(this.desiredRotY);
        double eyeZ = 2*Math.sin(this.desiredRotX) * Math.cos(this.desiredRotY);
        g.gluLookAt(eyeX, eyeY, eyeZ,
                    0, 0, 0,
                    0, 1, 0);
            
        //drawing cube
        this.draw(gl);
        

        
        
        
    }
    

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
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
