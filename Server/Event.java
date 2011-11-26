/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planegame;

/**
 * The Event class represents events that player able to send.
 * @author Vlad Zotov
 */
public class Event {
    /** The available events  */
    public static enum PlaneEvents {accelerate, slowdown, clockwiseRotate, counterClockwiseRotate, fire , none};
    
    private PlaneEvents m_event;
    
    public Event(PlaneEvents e){
        m_event = e;
    }
    
    public PlaneEvents getEvent(){
        return m_event;
    }
}
