/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 *
 * @author Vlad
 */
public class Bullet extends FlyingObject{
    
    
    private Plane m_plane;
    
    
    
    public Bullet(int x, int y, int angle){
        m_x = x;
        m_y = y;
        m_angle = angle;
    }
    
    @Override
    public void Compute(Event e){
        m_x += m_x_velocity;
        m_y += m_y_velocity;
    }
    
}