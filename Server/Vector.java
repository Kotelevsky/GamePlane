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
    private double m_x;
    private double m_y;
    
    public Vector(double x, double y){
        m_x = x;
        m_y = y;
    }
    
    public double X(){
        return m_x;
    }
    
    public double Y(){
        return m_y;
    }
    
    public void setX(double x){
        m_x = x;
    }
    
    public void setY(double y){
        m_y = y;
    }
    
    public double SLength(){
        return m_x*m_x + m_y*m_y;
    }
    
    public double Length(){
        return Math.sqrt(SLength());
    }
    
    public Vector Clone(){
        return new Vector(m_x, m_y);
    }
    
    public void ClockwiseRotation(double angle){
        m_x = m_x*Math.cos(angle) - m_y*Math.sin(angle);
        m_y = m_x*Math.sin(angle) + m_y*Math.cos(angle);
    }
    
    public void CounterClockwiseRotation(double angle){
        m_x = m_x*Math.cos(angle) + m_y*Math.sin(angle);
        m_y = -m_x*Math.sin(angle) + m_y*Math.cos(angle);
    }
    
}
