
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitriy
 */
public class ServerGame {
    
    // Клиенты и их парметры для связи
    private List<ClientSocket> Client;
    
    public ServerGame(){
        Client = new LinkedList<ClientSocket>();
    }
    
    // Комнаты с игроками
    
    
    //Создаем комнату и возвращаем клиенту id-room 
    // если комнат нет или клиент хочет создать свою
    public void CreateRoom(PrintWriter out){
        
    }
    
    // Возвращает количество комнат клиенту для выбора.
    // C возможностью передавать описане комнат
    public void GetRoom(PrintWriter out){
        
    }
    
    // Анализатор комманд от клиента
    public void Command(String str){
        
    }
    
    // Добавляем клинтов
    public int addClient(ClientSocket client){
        int result = -1;
        if(!Client.equals(client)){
            if (Client.add(client)){
                result = Client.indexOf(client);
            }
        }
        return result;
    }
    
    // Удаляем клинта
    public void removeClient(int client){
        Client.remove(client);
    }
}
