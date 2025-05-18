package co.edu.uptc.model;    
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {
    
    private final String ADMIN_NAME = "Admin";
    private final String ADMIN_PASSWORD = "soyElAdmin231";
    private Room normalRooms[][];
    private Room vipRooms[][];
    private Room premiunRooms[][];

    public Housing(){
    }

    public boolean validateRooms(String typeRoom, int rows, int columns) {
        if(rows > 0 && columns > 0){
            initRooms(typeRoom, rows, columns);
            return true;
        }
        return false;
    }

    public void initRooms(String typeRoom, int rows, int columns) {
        if(typeRoom.equals("Normales")){
            initNormalRooms(typeRoom, rows, columns);
        } else if(typeRoom.equals("VIP")){
            initVipRooms(typeRoom, rows, columns);
        } else {
            initPremiunRooms(typeRoom, rows, columns);
            
        }
    }

    public void initNormalRooms(String typeRoom,int rows, int columns){
        normalRooms = new Room[rows][columns];
        creatreRooms(typeRoom, normalRooms);
    }
    public void initVipRooms(String typeRoom,int rows, int columns){
        vipRooms = new Room[rows][rows];
        creatreRooms(typeRoom, vipRooms);
    }
    public void initPremiunRooms(String typeRoom,int rows, int columns){
        premiunRooms = new Room[rows][columns];
        creatreRooms(typeRoom, premiunRooms);
    }

    public void creatreRooms(String typeRoom,Room[][] rooms) {
        int countCreateRoom = 1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new Room("" + typeRoom.charAt(0) + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }

    public String bookking(String typeRoom, String password) {
        if (typeRoom.equalsIgnoreCase("Normal")) {
            return bookkingRoom(password, normalRooms);
        } else if (typeRoom.equalsIgnoreCase("vip")) {
            return bookkingRoom(password, vipRooms);
        } else {
            return bookkingRoom(password, premiunRooms);
        }
    }

    public String bookkingRoom(String password, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if (rooms[i][j].isFree()) {
                    String roomName = rooms[i][j].getRoomName();
                    rooms[i][j].setPassword(password);
                    rooms[i][j].setFree(false);
                    return "Reserva exitosa, su habitación es: " + roomName; 
                } 
            }
        }        
        return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
    }

    public String returnRoom(String nameRoom, String typeRoom, String password) {
        if (typeRoom.equalsIgnoreCase("Normal")) {
            return returnRoom(nameRoom, password, normalRooms);
        } else if (typeRoom.equalsIgnoreCase("vip")) {
            return returnRoom(nameRoom, password, vipRooms);
        } else {
            return returnRoom(nameRoom, password, premiunRooms);
        }
    }

    public String returnRoom(String nameRoom, String password, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++) {
                if (!rooms[i][j].isFree() && rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom) && rooms[i][j].getPassword().equals(password)) {
                    String roomName = rooms[i][j].getRoomName();
                    rooms[i][j].setPassword("");
                    rooms[i][j].setFree(true);
                    return "Entrega de habitación exitosa, la habitación: " + roomName + " esta libre."; 
                } 
            }
        }        
        return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String getADMIN_NAME() {
        return ADMIN_NAME;
    }

    public String getADMIN_PASSWORD() {
        return ADMIN_PASSWORD;
    }
}