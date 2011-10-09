/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 *
 * @author Vlad
 */
public class Vector {
    private double m_x;
    private double m_y;
    
    public double getX(){
        return m_x;
    }
    
    public double getY(){
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
    
    public void MinClockwiseRotation(){
        m_x = m_x*Physics.COS_MIN_ROTATE_ANGLE - m_y*Physics.SIN_MIN_ROTATE_ANGLE;
        m_y = m_x*Physics.SIN_MIN_ROTATE_ANGLE + m_y*Physics.COS_MIN_ROTATE_ANGLE;
    }
    
    public void MinCounterClockwiseRotation(){
        m_x = m_x*Physics.COS_MIN_ROTATE_ANGLE + m_y*Physics.SIN_MIN_ROTATE_ANGLE;
        m_y = -m_x*Physics.SIN_MIN_ROTATE_ANGLE + m_y*Physics.COS_MIN_ROTATE_ANGLE;
    }
    
    public int XDirection(){  //направление по X
        if(m_x == 0)
            return 0;
        return (m_x - Math.abs(m_x) < 0)?(-1):(1);
    }
    
    public int YDirection(){  //направление по Y
        if(m_y == 0)
            return 0;
        return (m_y - Math.abs(m_y) < 0)?(-1):(1);
    }
    
}
