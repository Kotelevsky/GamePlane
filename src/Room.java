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
    private List<Player> m_players;
    
    public int getID(){
        return m_id;
    }
    
    public List<Player> getPlayerList(){
        return m_players;
    }
    
    public void GetPlayer(Player p){
        m_players.add(p);
    }
    
    public void StartRoom(){
        
    }
    
}
