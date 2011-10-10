/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

import java.util.List;

/**
 *
 * @author Vlad
 */
public class Room {
    
    private int m_id;
    private List<FlyingObject> m_objects;
    //private List<Player> m_players;
    private int m_max_player_count;
    
    public Room(int max_players){   //to do: getting room id
        m_max_player_count = max_players;
    }
    
    public int getID(){
        return m_id;
    }
    
    public List<FlyingObject> getPlayerList(){
        return m_objects;
    }
    
    public void GetPlayer(Player p){
        m_objects.add(p);
    }
    
    public void StartRoom(){
        
    }
    
}
