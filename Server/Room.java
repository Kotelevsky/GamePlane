/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

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
    
    public Room(int max_players, int id){   //to do: getting room id
        m_max_player_count = max_players;
        m_objects = new ArrayList<FlyingObject>();
        
        m_tmr = new Timer(5, m_alistner);
        m_id = id;
    }
    
    public int getID(){
        return m_id;
    }
    
    public List<FlyingObject> getPlayerList(){
        return m_objects;
    }
    
    public void AddPlayer(Player p){
        m_objects.add(p.Plane);
    }
    
    public void StartRoom(){
        if(!m_tmr.isRunning()){
            m_tmr.start();
        }
    }
    
    public void StopRoom(){
        m_tmr.stop();
        
    }
    
    private void GameTick(HashMap<Integer, Event> events) throws Exception{
        for(FlyingObject f : m_objects){
            Plane p = (Plane)f;
            if(events.containsKey(p.getPlayer().getID()))
                p.Compute(events.get(p.getPlayer().getID()));
            else 
                throw new Exception("Player doesn't exists");
        }
    }
 
    private void DisconnectPlayer(int id){
        for(FlyingObject f : m_objects){
            Plane p = (Plane)f;
            if(p.getPlayer().getID() == id){
                p.ExitRoom();
                
            }
        }
    }
    
}
