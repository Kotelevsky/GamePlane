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
    
    //private int m_weight; //I don't use weigth yet
    private Player m_player;
    private Room m_room;
    private int m_id;
    private int m_length;
    private int m_height;
    
    public Player getPlayer(){
        return m_player;
    }
    
    public Plane(int x, int y, Vector v, Player p, Room r){
        super(x, y, v);               
        m_player = p;
        m_room = r;
        m_id = m_player.getID();
    }
    
    //метод Compute ведет пеесчет координат самолета по таймеру
    @Override
    public void Compute(Event e){
        Event.PlaneEvents event = e.getEvent();
        switch(event){
            case accelerate:{
                Acceleration();
                break;
            }
            case clockwiseRotate:{
                m_vector.MinClockwiseRotation();
                break;
                
            }
            case counterClockwiseRotate:{
                m_vector.MinCounterClockwiseRotation();
                break;
            }
            case fire:{
                
            }
            case slowdown:{
                Slowdown();
                break;
            }
            case none:{
                SimpleMotion();
                break;
            }
        }
    }
    
    private void Bump(){
        //to do
    }    
    
    public void NewFrag(Plane p){
        m_player.NewFrag(p);
    }
    
}
