/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 *  Vector specified for the game 
 * @author Vlad
 */
public class Vector{
    private float m_x;
    private float m_y;
    
    public Vector(float x, float y){
        m_x = x;
        m_y = y;
    }
    
    public float X(){
        return m_x;
    }
    
    public float Y(){
        return m_y;
    }
    
    public void setX(float x){
        m_x = x;
    }
    
    public void setY(float y){
        m_y = y;
    }
    
    public Vector getUnitVector(){
        return new Vector(m_x/this.Length(), m_y/this.Length());
    }
    
    public float SLength(){
        return m_x*m_x + m_y*m_y;
    }
    
    public float Length(){
        return (float)Math.sqrt(SLength());
    }
    
    public Vector Clone(){
        return new Vector(m_x, m_y);
    }
    
    public void ClockwiseRotation(double angle){
        angle = (angle) * Math.PI / 180;
        m_x = (float) (m_x*Math.cos(angle) + m_y*Math.sin(angle));
        m_y = (float) (-m_x*Math.sin(angle) + m_y*Math.cos(angle));
    }
    
    public void CounterClockwiseRotation(double angle){
        angle = (angle) * Math.PI / 180;
        m_x = (float) (m_x*Math.cos(angle) - m_y*Math.sin(angle));
        m_y = (float) (m_x*Math.sin(angle) + m_y*Math.cos(angle));
    }
    
}
