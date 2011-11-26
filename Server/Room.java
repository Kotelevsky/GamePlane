/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.Timer;
//import java.util.Timer;

/**
 *
 * @author Vlad
 */
public class Room {
    
    private int m_id;
    private List<FlyingObject> m_objects;
    //private List<Player> m_players;
    private int m_max_player_count;
    private Timer m_tmr;
    private ActionListener m_alistner;
    private HashMap<Integer, Event> m_events;
    private String m_name;
    
    public Room(int max_players, String name){
        m_name = name;
        m_max_player_count = max_players;
        m_objects = new ArrayList<FlyingObject>();
        m_alistner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GameTick();
                }
                catch(Exception ex){
                    
                }
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        m_tmr = new Timer(5, m_alistner);
        //m_id = id;
    }
    
    public int getID(){
        return m_id;
    }
    
    public void setID(int id){
        m_id = id;
    }
    
    
    public List<FlyingObject> getPlayerList(){
        return m_objects;
    }
    
    public boolean AddPlayer(Player p){
        if(!(m_objects.size() == m_max_player_count)){
            Plane plane = new Plane(0, 0, new Vector(1, 0), p, this);
            p.setPlane(plane);
            m_objects.add(p.getPlane());
            return true;
        }
        else
            return false;
    }
    
    public void StartRoom(){
        if(!m_tmr.isRunning()){
            m_tmr.start();
        }
    }
    
    public void StopRoom(){
        m_tmr.stop();
        
    }
    
    private void GameTick() throws Exception{
        for(FlyingObject f : m_objects){
            if(f instanceof Plane){
                Plane p = (Plane)f;
                if(m_events.containsKey(p.getPlayer().getID()))
                    p.Compute(m_events.get(p.getPlayer().getID()));
                else 
                    throw new Exception("Player doesn't exists");
            }
            else{
                Bullet b = (Bullet)f;
                b.Compute(null);
            }
        }
        SetDefaultEvents();
    }
 
    public void DisconnectPlayer(int id){
        for(FlyingObject f : m_objects){
            Plane p = (Plane)f;
            if(p.getPlayer().getID() == id){
                p.ExitRoom();
            }
        }
    }
    
    public void AddBullet(Bullet b){
        m_objects.add(b);
    }
    
    private void SetDefaultEvents(){
        HashMap<Integer, Event> map = new HashMap<Integer, Event>();
        for(FlyingObject f : m_objects){
            if(f instanceof Plane){
                map.put(((Plane)f).getPlayer().getID(), new Event(Event.PlaneEvents.none));
            }
        }
        m_events = map;
    }
    
}