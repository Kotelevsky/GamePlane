/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;


/**
 * Class Player represents player in game
 * @author Vlad Zotov
 */
public class Player {
    
    /** Identifier of player */
    private int m_id;
    /** Player's score */
    private int m_score;
    /** Reference to the Room in which player playing */
    private Room m_room;
    /** Player's plane */
    private Plane m_plane;
    /** Player's name */
    private String m_name;

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }
    
    public Player(int id){
        m_id = id;
    }
    
    public int getID(){
        return m_id;
    }
    
    public int getScore(){
        return m_score;
    }
    
    public Plane getPlane(){
        return m_plane;
    }
    
    public void setPlane(Plane p){
        m_plane = p;
    }
    
    public String getName(){
        return m_name;
    }
    
    public void JoinRoom(Room r){
        if(m_room == null){
            m_room = r;
            r.AddPlayer(this);
        }
    }
    
    /**
     * Increace score of player
     * @param p Plane killed by player 
     */
    public void NewFrag(Plane p){
        m_score++;
    }
    
    /**
     * disconnects player from room
     */
    public void Disconnect(){
       m_plane = null;
       m_room = null;
    }
    
    public boolean equals(Object obj){
        if(obj instanceof Player){
            Player pla = (Player)obj;
            if(pla.getID() == this.m_id)
                return true;
        }
        return false;
    }

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.m_id;
        return hash;
    }
    
}