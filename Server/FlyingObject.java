/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;


/**
 *
 * @author Vlad
 */
public abstract class FlyingObject {
    
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
    public abstract void Compute(Event e);
    
}
