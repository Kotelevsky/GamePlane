/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 *
 * @author Vlad
 */
public class Plane extends FlyingObject{
    
    private int m_weight;
    private Player m_player;
    private int m_score;
    
    
    
    public Player getPlayer(){
        return m_player;
    }
    
    public Plane(int x, int y, int angle){
        m_x = x; 
        m_y = y; 
        m_angle = angle;
    }
    
    //метод Compute ведет пеесчет координат самолета по таймеру
    @Override
    public void Compute(Event e){
        m_y += m_y_velocity;
        m_y_velocity += Physics.GRAVITY;
    }
    
    public void NewFrag(Plane p){
        m_player.NewFrag(p);
    }
    
}
