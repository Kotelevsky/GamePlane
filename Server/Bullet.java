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
    
    public Bullet(int x, int y, Vector vector, Plane p){
        super(x, y, vector);
        m_plane = p;
    }
    
    @Override
    public void Compute(Event e){
        m_x += m_velocity.X();
        m_y += m_velocity.Y();
    }
    
    @Override
    public int getX(){
        return m_x;
    }
    
    @Override
    public int getY(){
        return Physics.MAX_Y - m_y;
    }
    
    @Override
    public Vector getDirectionVector(){
        return new Vector(m_velocity.X(), -m_velocity.Y());
    }
    
}