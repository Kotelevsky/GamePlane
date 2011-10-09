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
    protected Vector m_vector;  //вектор скорости объекта.
    
    
    
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
        m_vector = vec;
    }
    
    public void Compute(Event e){
        Event.PlaneEvents event = e.getEvent();
        switch(event){//пока без учета направления вектора движения
            case accelerate:{
                /*m_x += m_x_velocity*m_vector.XDirection();
                m_x_velocity += (m_x_velocity + Physics.ACCELERATION < Physics.MAX_X_VELOCITY)?(Physics.ACCELERATION):(Physics.MAX_X_VELOCITY - m_x_velocity);
                m_y += m_y_velocity*m_vector.YDirection();
                m_y_velocity += m_y_velocity + Physics.ACCELERATION - Physics.GRAVITY;*/
                m_x += m_vector.getX();
                m_vector.setX((Math.abs(m_vector.XDirection()*Physics.ACCELERATION + m_vector.getX()) < Physics.MAX_X_VELOCITY)?
                        (m_vector.XDirection()*Physics.ACCELERATION + m_vector.getX()):(m_vector.XDirection()*Physics.MAX_X_VELOCITY));
                m_y += m_vector.getY();
                m_vector.setY(m_vector.getY() + m_vector.YDirection()*Physics.ACCELERATION - Physics.GRAVITY);
                break;//нет проверки на скорость по Y
            }
            case clockwiseRotate:{
                m_vector.MinClockwiseRotation();
                
            }
            case counterClockwiseRotate:{
                m_vector.MinCounterClockwiseRotation();
            }
            case fire:{
                
            }
            case slowdown:{
                    
            }
            case none:{
                m_x += m_vector.getX();
                m_y += m_vector.getY();
                m_vector.setY((m_vector.getY() > 0)?(m_vector.getY() - Physics.GRAVITY):(0));
                break;
            }
        }
        
        
    }
}
