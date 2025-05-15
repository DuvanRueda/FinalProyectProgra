package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Room {
    
    private String roomName;
    private String password; 
    private boolean isFree;

    public Room (String roomName) {
        this.roomName = roomName;
        password = "";
        isFree = true;
    }

    public String getRoomName() {
        return roomName;
    }

}
