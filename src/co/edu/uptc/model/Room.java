package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public abstract class Room {
    
    private String roomName;
    private String password; 
    private boolean isFree;
    private Comment commentsRoom;
    private double generalRate;

    public Room (String roomName) {
        this.roomName = roomName;
        password = "";
        isFree = true;
        commentsRoom = new Comment();
    }

    public abstract void makeRating();

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public Comment getCommentsRoom() {
        return commentsRoom;
    }

    public void setCommentsRoom(Comment commentsRoom) {
        this.commentsRoom = commentsRoom;
    }

    public void addComment(String comment){
        commentsRoom.addComment(comment);
    }

    public double getGeneralRate() {
        return generalRate;
    }

    public void setGeneralRate(double generalRate) {
        this.generalRate = generalRate;
    }

    
}
