package tm2019rubiks.gui;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.util.Arrays;
import javax.swing.JPanel;
import tm2019rubiks.gui.piece.CenterPiece3D;
import tm2019rubiks.gui.piece.CornerPiece3D;
import tm2019rubiks.gui.piece.Piece;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;
import tm2019rubiks.utils.Utils;


//A JComponent that shows a RCube in its 3d form
//

public class RCube3D extends JPanel implements GLEventListener{
    final private RCube cube;
    private float desiredRotX, desiredRotY;
    private boolean turningLeft, turningRight, turningUp, turningDown, persp, setPersp;
    
    

    
    
    public static final float BORDER = 0.05f;
    
    
    public static final byte[][] axisMods = {{1, -1, -2, 0},
                                              {2, -1, 1, 1},
                                              {-1, -1 , 2, 0},
                                              {-2, -1, -1, 1},
                                              {1, 2, -1, 0},
                                              {1, -2, 1, 0}};
    
    //black BORDER for the faces
    
    float[][][][][] faceletVertices = new float[6][3][3][4][3];
    float[][][][][] blackVertices = new float[6][3][3][4][3];
    
     
    
    float bsize = (1.2f - 2*BORDER)/3;
    float step = BORDER + bsize;
    float start = 0.6f;
    float end = start - bsize;
    float bstart = start - 0.02f;
    float bstep = 2*bstart / 3;
    float bend = bstart - bstep;
    
    float camRad = 0.1f;
    
    private Piece[] centers;
    private Piece[] pieces;
    
    
    
    

    public RCube3D(RCube cube){
        //TODO: get rid of warnings
        super();
        this.pieces = new Piece[20];
        this.centers = new Piece[6];
        
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
        
        
        
        this.constructPosArray();
        
        /////test////
        for(byte i = 0; i < 6; i ++){
            centers[i].addRotate(i, 1, 1);
        }
        
        ////test////
        
        
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
        
        
        //center1.draw(gl);
        for(byte face = 0; face < 6; face ++){
            for(int y = 0; y < 3; y ++){
                for(int x = 0; x < 3; x ++){
                    
                    
                    float r, g, b;
                    float[] color = RFace.COLORS_BY_INDEX[this.cube.getFace(face).getColors()[y][x]];
                    r = color[0]; g = color[1]; b = color[2];
                    gl.glColor3f(r,g,b);
                    
                    float[][] coords = faceletVertices[face][y][x];
                    
                    if(x == 1 && y == 1){
                        //centers[face].draw(gl);
                        //continue;
                    }
                    
                    for(float[] coord : coords){
                        gl.glVertex3f(coord[0], coord[1], coord[2]);
                    }
                    
                    gl.glColor3f(0, 0, 0);
                    coords = blackVertices[face][y][x];
                    for(float[] coord : coords){
                        gl.glVertex3f(coord[0], coord[1], coord[2]);
                    }
                }
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
        
        
        if(this.setPersp){
            this.persp  = !this.persp;
            this.setPersp = false;
            
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
        
        if(this.persp){
            this.camRad = 3f;
            g.gluPerspective(60, -1, 0.1f, 5);
        }
        else{
            this.camRad = 0.1f;
            gl.glScalef(0.7f, 0.7f, 0.7f);
        }
        
        
        
        //setting perspective
        //fov = 90
        
        
        //calculating eye position in 3d
        //the camera can move around the equator of a sphere(rotX)
        //and can move up/down along the sphere(rotY)
        double eyeX = camRad*Math.cos(this.desiredRotX) * Math.cos(this.desiredRotY);
        double eyeY = camRad*Math.sin(this.desiredRotY);
        double eyeZ = camRad*Math.sin(this.desiredRotX) * Math.cos(this.desiredRotY);
        g.gluLookAt(eyeX, eyeY, eyeZ,
                    0, 0, 0,
                    0, 1, 0);
            
        //drawing cube
        this.draw(gl);
        

        
        
        
    }
    
    private void constructPosArray(){
        
        //for each face
        for(byte index = 0; index < 6; index ++){
            RFace face = cube.getFace(index);
            byte[][] colors = face.getColors();
            
            //facing changes based on which face it's drawing
            
            //switch statement needed because the faces are not facing the same
            //direction, making it hard to find a sigle loop that can draw them all
            //only using math formulas
            
            
            
            for(byte y = 0; y < 3; y ++){
                for(byte x = 0; x < 3; x ++){
                    float[][] coords = new float[4][3];
                    float[][] bcoords = new float[4][3];

                    
                    
                    byte[] mods = this.axisMods[index];
                    
                    
                    
                    
                    //putting constants
                    int constInd = Utils.indexOf(mods, (byte)2);
                    if(constInd == -1){
                        constInd = Utils.indexOf(mods, (byte)-2);
                    }
                    for(byte i = 0; i < 4; i ++){
                        coords[i][constInd] = Math.signum(mods[constInd]) * start;
                        bcoords[i][constInd] = Math.signum(mods[constInd]) * (bstart);
                    }
                    
                    byte[][] left = {{1,2},{0,2},{0,1}};
                    
                    
                    for(byte i = 0; i < 2; i ++){
                        byte coordInd = left[constInd][i];
                        
                        int rx = x;
                        int ry = y;
                        if(mods[3] == 1){
                            rx = y;
                            ry = x;
                        }
                        
                        if(i == 0){
                            coords[0][coordInd] = -mods[coordInd] * start + mods[coordInd] * step * rx;
                            coords[1][coordInd] = -mods[coordInd] * end + mods[coordInd] * step * rx;
                            coords[2][coordInd] = -mods[coordInd] * end + mods[coordInd] * step * rx;
                            coords[3][coordInd] = -mods[coordInd] * start + mods[coordInd] * step * rx;
                            
                            bcoords[0][coordInd] = -mods[coordInd] * bstart + mods[coordInd] * bstep * rx;
                            bcoords[1][coordInd] = -mods[coordInd] * bend + mods[coordInd] * bstep * rx;
                            bcoords[2][coordInd] = -mods[coordInd] * bend + mods[coordInd] * bstep * rx;
                            bcoords[3][coordInd] = -mods[coordInd] * bstart + mods[coordInd] * bstep * rx;
                        }
                        else{
                            coords[0][coordInd] = -mods[coordInd] * start + mods[coordInd] * step * ry;
                            coords[1][coordInd] = -mods[coordInd] * start + mods[coordInd] * step * ry;
                            coords[2][coordInd] = -mods[coordInd] * end + mods[coordInd] * step * ry;
                            coords[3][coordInd] = -mods[coordInd] * end + mods[coordInd] * step * ry;
                            
                            bcoords[0][coordInd] = -mods[coordInd] * bstart + mods[coordInd] * bstep * ry;
                            bcoords[1][coordInd] = -mods[coordInd] * bstart + mods[coordInd] * bstep * ry;
                            bcoords[2][coordInd] = -mods[coordInd] * bend + mods[coordInd] * bstep * ry;
                            bcoords[3][coordInd] = -mods[coordInd] * bend + mods[coordInd] * bstep * ry;
                        }
                        
                    }
                    if(x == 1 && y == 1){
                        centers[index] = new CenterPiece3D(coords, bcoords, RFace.INDEX_FACE_BACK);
                            
                        
                        
                    }
                    faceletVertices[index][y][x] = coords;
                    blackVertices[index][y][x] = bcoords;
                    
                    
                }
            
            
                
                
            }
        }
        
           
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
    public void setPersp(boolean stepping) {
        this.persp = stepping;
    }

    public void setSetPersp(boolean setPersp) {
        this.setPersp = setPersp;
    }
    
    
    
    
    
    
    
}
