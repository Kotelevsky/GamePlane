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
    
    protected int m_x;                              // X coodrinate
    protected int m_y;                              //Y coordinate
    protected Vector m_velocity;                    //velocity vector
    protected Vector m_acceleration;                //acceleration vector
    protected Vector m_draft;                       //draft force vector
    protected Vector m_uplifting_force;             //uplifting force vector
    protected Vector m_gravity;                     //gravity vector
    protected int m_weight;                         //plane weight
    
    public int getX(){
        return m_x;
    }
    
    public int getY(){
        return m_y; 
    }
    /**
     * Returns direction vector of FlyingObject
     * @return 
     */
    public Vector getDirectionVector(){
        return m_velocity;
    }
    
    public FlyingObject(int x, int y, Vector vec){
        m_x = x;
        m_y = y;
        m_velocity = vec.Clone();
        m_acceleration = new Vector(0, 0);
        m_draft = new Vector(0, 0);
        m_uplifting_force = new Vector(0, 0);
        m_gravity = new Vector(0, Physics.GRAVITY);
    }
    
    /**
     * Computes new game state. 
     * @param e player's event
     */
    public void Compute(Event e){
        Event.PlaneEvents event = e.getEvent();
        switch(event){
            case accelerate:{
                Acceleration();
                break;
            }
            case clockwiseRotate:{
                MinClockwiseRotation();
                break;
            }
            case counterClockwiseRotate:{
                MinCounterClockwiseRotation();
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
    
    protected void MinClockwiseRotation(){
        m_draft.ClockwiseRotation(Physics.MIN_ROTATE_ANGLE);
        SimpleMotion();
    }
    
    protected void MinCounterClockwiseRotation(){
        m_draft.CounterClockwiseRotation(Physics.MIN_ROTATE_ANGLE);
        SimpleMotion();
    }
    
    protected void Acceleration(){    //simple acceleration. Change coordinates and increase speed
        m_draft.setX(m_draft.X() + Physics.ACCELERATION);
        m_draft.setY(m_draft.Y() + Physics.ACCELERATION);
        SimpleMotion();
    }
    
    protected void Slowdown(){    //simple slowdown. Change coordinatees and decrease speed
        m_draft.setX(m_draft.X() - Physics.ACCELERATION);
        m_draft.setY(m_draft.Y() - Physics.ACCELERATION);
        SimpleMotion();
    }
    
    protected void SimpleMotion(){    //uniform motion along X and accelereted motion along Y
        m_acceleration.setX((m_draft.X() + m_uplifting_force.X())/m_weight);
        m_acceleration.setY((m_draft.Y() + m_uplifting_force.Y() + m_gravity.Y())/m_weight);
        m_velocity.setX(m_velocity.X() + m_acceleration.X());
        m_velocity.setY(m_velocity.Y() + m_acceleration.Y());
        m_x += m_velocity.X();
        m_y += m_velocity.Y();
        m_uplifting_force.setX(m_velocity.SLength()*m_draft.X()/m_draft.SLength());
    }
    
}
