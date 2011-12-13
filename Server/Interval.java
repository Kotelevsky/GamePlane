/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 *
 * @author Vlad
 */
public class Interval {
    private Point m_start;
    private Point m_end;
    
    public Interval(Point s, Point e){
        m_start = s;
        m_end = e;
    }
    
    public Point Start(){
        return m_start;
    }
    
    public Point End(){
        return m_end;
    }
    
    public double Length(){
        return Math.sqrt((m_end.getX() - m_start.getX())*(m_end.getX() - m_start.getX()) + (m_end.getY() - m_start.getY())*(m_end.getY() - m_start.getY()));
    }
    
}
