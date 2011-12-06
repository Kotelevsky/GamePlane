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
    
    public Player(int id, String name){
        m_id = id;
        m_name = name;
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
    
}