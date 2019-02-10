package tm2019rubiks.gui;

import com.jogamp.opengl.GL2;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;

public class RCube3D {
    private RCube cube;
    

    public RCube3D(RCube cube) {
        this.cube = cube;
    }
    public void draw(GL2 gl){
        gl.glBegin (GL2.GL_QUADS);
        
        
        for(RFace face : this.cube.getFaces()){
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
    
    
}
