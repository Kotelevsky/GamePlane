/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 * 
 * @author Vlad
 */
public class Point {
    private int m_x;
    private int m_y;
    
    public Point(int x, int y){
        m_x = x;
        m_y = y;
    }
    
    public int getX(){
        return m_x;
    }
    
    public int getY(){
        return m_y;
    }
    
    public void setX(int x){
        m_x = x;
    }
    
    public void setY(int y){
        m_y = y;
    }
    
    public boolean equals(Object obj){
        if(obj instanceof Point){
            Point p = (Point)obj;
            if(p.getX() == m_x && p.getY() == m_y)
                return true;
        }
        return false;
    }

    public int hashCode() {
        int hash = 7;
        return m_x + m_y + hash;
    }
    
}
