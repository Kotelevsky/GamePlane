/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;


/**
 *
 * @author Vlad
 */
public class FlyingObject {
    
    protected int m_x;
    protected int m_y;
    protected Vector m_vector;  //velocity vector
    
    
    public int getX(){
        return m_x;
    }
    
    public int getY(){
        return m_y; 
    }
    
    public Vector getVector(){
        return m_vector;
    }
    
    public FlyingObject(int x, int y, Vector vec){
        m_x = x;
        m_y = y;
        m_vector = vec.Clone();
    }
    
    public void Compute(Event e){
        Event.PlaneEvents event = e.getEvent();
        switch(event){
            case accelerate:{
                Acceleration();
                break;
            }
            case clockwiseRotate:{
                m_vector.MinClockwiseRotation();
                break;
            }
            case counterClockwiseRotate:{
                m_vector.MinCounterClockwiseRotation();
                break;
            }
            case fire:{
                
            }
            case slowdown:{
                Slowdown();
                break;
            }
            case none:{
                SimpleMotion();
                break;
            }
        }
    }
    
    protected void Acceleration(){    //simple acceleration. Change coordinates and increase speed
        m_x += m_vector.getX();
        m_vector.setX((Math.abs(m_vector.XDirection()*Physics.ACCELERATION + m_vector.getX()) < Physics.MAX_X_VELOCITY)?
            (m_vector.XDirection()*Physics.ACCELERATION + m_vector.getX()):(m_vector.XDirection()*Physics.MAX_X_VELOCITY));
        m_y += m_vector.getY();
        m_vector.setY((Math.abs(m_vector.getY() + m_vector.YDirection()*Physics.ACCELERATION - Physics.GRAVITY)<Physics.MAX_Y_VELOCITY)?
            (m_vector.getY() + m_vector.YDirection()*Physics.ACCELERATION - Physics.GRAVITY):(Physics.MAX_Y_VELOCITY));
    }
    
    protected void Slowdown(){    //simple slowdown. Change coordinatees and decrease speed
        m_x += m_vector.getX();
        m_vector.setX((Math.abs(m_vector.getX() - m_vector.XDirection()*Physics.SLOWDOWN) > 0)?(m_vector.getX() - m_vector.XDirection()*Physics.SLOWDOWN ):(0));
            m_y += m_vector.getY();
        m_vector.setY((Math.abs(m_vector.getY() - m_vector.YDirection()*Physics.ACCELERATION - Physics.GRAVITY)<Physics.MAX_Y_VELOCITY)?
            (m_vector.getY() - m_vector.YDirection()*Physics.ACCELERATION - Physics.GRAVITY):(Physics.MAX_Y_VELOCITY));
    }
    
    protected void SimpleMotion(){    //uniform motion along X and accelereted motion along Y
            m_x += m_vector.getX();
            m_y += m_vector.getY();
            m_vector.setY((m_vector.getY() > 0)?(m_vector.getY() - Physics.GRAVITY):(0));
        }
    
}
