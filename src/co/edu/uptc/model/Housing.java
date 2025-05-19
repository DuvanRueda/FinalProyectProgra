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
    private VIPRoom vipRooms[][];
    private PremiumRoom premiumRooms[][];

    public Housing(){

    }

    public boolean verifyAvailability(String typeRoom) {
        if (typeRoom.equalsIgnoreCase("Normal")) {
            return verifyAvailability(normalRooms);
        } else if (typeRoom.equalsIgnoreCase("VIP")){
            return verifyAvailability(vipRooms);
        } else 
            return verifyAvailability(premiumRooms);
    }

    public boolean verifyAvailability(Room[][] rooms) {
        for (int i = rooms.length - 1; i > -1; i--) {
            for (int j = rooms[0].length - 1; j > -1; j--) {
                if (rooms[i][j].isFree()) {
                    return true;
                }
            }
        }
        return false;
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
            initNormalRooms(rows, columns);
        } else if(typeRoom.equals("VIP")){
            initVipRooms(rows, columns);
        } else {
            initPremiumRooms(rows, columns);
            
        }
    }

    public void initNormalRooms(int rows, int columns){
        normalRooms = new Room[rows][columns];
        creatreRooms(normalRooms);
    }
    public void initVipRooms(int rows, int columns){
        vipRooms = new VIPRoom[rows][rows];
        creatreVIPRooms(vipRooms);
    }
    public void initPremiumRooms(int rows, int columns){
        premiumRooms = new PremiumRoom[rows][columns];
        creatrePremiumRooms(premiumRooms);
    }

    public void creatreRooms(Room[][] rooms) {
        int countCreateRoom = 1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new Room("N" + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }
    public void creatreVIPRooms(Room[][] rooms) {
        int countCreateRoom = 1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new VIPRoom("V" + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }
    public void creatrePremiumRooms(Room[][] rooms) {
        int countCreateRoom = 1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new PremiumRoom("P" + countCreateRoom);
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
            return bookkingRoom(password, premiumRooms);
        }
    }

    public String bookkingRoom(String password, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
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
            return returnRoom(nameRoom, password, premiumRooms);
        }
    }

    public String returnRoom(String nameRoom, String password, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
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