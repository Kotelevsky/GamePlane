/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

import java.util.HashMap;


/**
 * The abstract class for flying objects in game.
 * @author Vlad
 */
public abstract class FlyingObject {
    
    /** X coordinate */ 
    protected int m_x;
    /** Y coordinate */
    protected int m_y;
    /** Velocity vector aka motion vector */
    protected Vector m_velocity;         
    
    /** 
     * 
     * @return X coordinate
     */
    public int getX(){
        return m_x;
    }
    
    /**
     * 
     * @return Y coordinate
     */
    public int getY(){
        return m_y; 
    }
    
    /**
     * 
     * @return motion vector of FlyingObject
     */
    public Vector getDirectionVector(){
        return m_velocity;
    }
    
    public FlyingObject(int x, int y, Vector vec){
        m_x = x;
        m_y = y;
        m_velocity = vec.Clone();
        
    }
    
    /**
     * Computes new state of Flying object accordingly player's event. 
     * @param e event posted by user
     */
    public abstract void Compute(HashMap<Integer, Integer> events);
    
}
