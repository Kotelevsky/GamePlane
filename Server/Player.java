/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;


/**
 *
 * @author Vlad
 */
public class Player {
    private int m_id;
    private int m_score;
    private Room m_room;
    private Plane m_plane;
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
    
    public void NewFrag(Plane p){
        m_score++;
    }
    
    public void Disconnect(){
        
    }
    
}