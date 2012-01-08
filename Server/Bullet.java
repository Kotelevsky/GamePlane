/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

import java.util.HashMap;

/**
 * The Bullet class represents bullets on the playing field.
 * @author Vlad Zotov
 * 
 */
public class Bullet extends FlyingObject{
    
    /** Reference to plane owner */
    private Plane m_plane;
    private boolean m_is_alive;
    /**
     * Create an instance of Bullet class 
     * @param x The X coordinate of bullet
     * @param y The Y coordinate of bullet
     * @param vector The motion vector of bullet
     * @param p Reference to the plane owner
     */
    public Bullet(int x, int y, Vector vector, Plane p){
        super(x, y, vector);
        m_plane = p;
        m_is_alive = true;
    }
    
    /**
     * Computes new state of Bullet accordingly event
     * @param e The Event posted by user
     */
    @Override
    public void Compute(HashMap<Integer, Integer>  events){
        m_coordinates.setX(m_coordinates.getX() + Math.round(m_velocity.X()));
        m_coordinates.setY(m_coordinates.getY() + Math.round(m_velocity.Y()));
        if((m_coordinates.getX() < 0 ) || (m_coordinates.getX() > Physics.MAX_X) || (m_coordinates.getY() < 0 ) || (m_coordinates.getY() > Physics.MAX_Y))
                m_is_alive = false;
    }
    
    /**
     * 
     * @return The X coordinate of bullet.
     */
    public int getX(){
        return m_coordinates.getX();
    }
    
    /**
     * 
     * @return The Y coordinate of bullet.
     */
    
    public int getY(){
        return Physics.MAX_Y - m_coordinates.getY();
    }
    
    /**
     * 
     * @return The motion vector of Bullet.
     */
    @Override
    public Vector getDirectionVector(){
        return new Vector(m_velocity.X(), -m_velocity.Y());
    }
    
    public boolean IsAlive(){
        return m_is_alive;
    }
    
    public void NewFrag(Plane p){
        m_plane.NewFrag(p);
        m_is_alive = false;
    }
   
}