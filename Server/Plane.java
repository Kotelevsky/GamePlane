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
    
    private Player m_player;                    //Игрок, управляющий самолетом
    private Room m_room;                        //Комната в которой находится самолет
    private int m_id;                           //идентификатор самолета в комнате
    private int m_length;                       //длина самолета
    private int m_height;                       //высота самолета
    private int r;                              //радиус окружности оисаной вокруг самолета
    
    public Player getPlayer(){
        return m_player;
    }
    
    public Plane(int x, int y, Vector v, Player p, Room room){
        super(x, y, v);               
        m_player = p;
        m_room = room;
        m_id = m_player.getID();
        r = (int)Math.sqrt(m_length*m_length/4 + m_height*m_height/4);
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
                MinClockwiseRotation();
                break;
            }
            case counterClockwiseRotate:{
                MinCounterClockwiseRotation();
                break;
            }
            case fire:{
                new Bullet(m_x, m_y, m_velocity, this);
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
    
    public Room getRoom(){
        return m_room;
    }
    
    public void ExitRoom(){
        m_room = null;
        m_player.Disconnect();
    }
    
    private void Bump(){
        
    }    
    
    public void NewFrag(Plane p){
        m_player.NewFrag(p);
    }
    
}