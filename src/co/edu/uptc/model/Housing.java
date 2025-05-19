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
    private VIPRoom VIPRooms[][];
    private PremiumRoom premiumRooms[][];

    public Housing(){

    }

    public String normalRating(double[] ratings, String nameRoom) {
        for (int i = 0; i < normalRooms.length; i++) {
            for (int j = 0; j < normalRooms[0].length; j++) {
                if (normalRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return normalRooms[i][j].makeRating(ratings);
                }
            }
        }
        return "";
    }

    public String VIPRating(double[] ratings, String nameRoom) {
        for (int i = 0; i < VIPRooms.length; i++) {
            for (int j = 0; j < VIPRooms[0].length; j++) {
                if (VIPRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return VIPRooms[i][j].makeRating(ratings);
                }
            }
        }
        return "";
    }

    public String premiumRating(double[] ratings, String nameRoom) {
        for (int i = 0; i < premiumRooms.length; i++) {
            for (int j = 0; j < premiumRooms[0].length; j++) {
                if (premiumRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return premiumRooms[i][j].makeRating(ratings);
                }
            }
        }
        return "";
    }

    public boolean verifyAvailability(String typeRoom) {
        if (typeRoom.equalsIgnoreCase("Normal")) {
            return verifyAvailability(normalRooms);
        } else if (typeRoom.equalsIgnoreCase("VIP")){
            return verifyAvailability(VIPRooms);
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
        VIPRooms = new VIPRoom[rows][rows];
        creatreVIPRooms(VIPRooms);
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
            return bookkingRoom(password, VIPRooms);
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

    public boolean returnNormalRoom(String nameRoom, String password) {
        for (int i = 0; i < normalRooms.length; i++) {
            for (int j = 0; j < normalRooms[0].length; j++) {
                if (!normalRooms[i][j].isFree() && normalRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom) && normalRooms[i][j].getPassword().equals(password)) {
                    normalRooms[i][j].setPassword("");
                    normalRooms[i][j].setFree(true);
                    return true;//"Credenciales correctas, procederemos ha hacerte la encuestga de satisfacción";
                } 
            }
        }        
        return false;//"El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public boolean returnVIPRoom(String nameRoom, String password) {
        for (int i = 0; i < VIPRooms.length; i++) {
            for (int j = 0; j < VIPRooms[0].length; j++) {
                if (!VIPRooms[i][j].isFree() && VIPRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom) && VIPRooms[i][j].getPassword().equals(password)) {
                    VIPRooms[i][j].setPassword("");
                    VIPRooms[i][j].setFree(true);
                    return true; //"Entrega de habitación exitosa, la habitación: " + roomName + " esta libre."; 
                } 
            }
        }        
        return false;//"El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public boolean returnPremiumRoom(String nameRoom, String password) {
        for (int i = 0; i < premiumRooms.length; i++) {
            for (int j = 0; j < premiumRooms[0].length; j++) {
                if (!premiumRooms[i][j].isFree() && premiumRooms[i][j].getRoomName().equalsIgnoreCase(nameRoom) && premiumRooms[i][j].getPassword().equals(password)) {
                    premiumRooms[i][j].setPassword("");
                    premiumRooms[i][j].setFree(true);
                    return true; //"Entrega de habitación exitosa, la habitación: " + roomName + " esta libre."; 
                } 
            }
        }        
        return false; //"El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String getADMIN_NAME() {
        return ADMIN_NAME;
    }

    public String getADMIN_PASSWORD() {
        return ADMIN_PASSWORD;
    }

    public Room[][] getNormalRooms() {
        return normalRooms;
    }

    public VIPRoom[][] getVIPRooms() {
        return VIPRooms;
    }

    public PremiumRoom[][] getPremiumRooms() {
        return premiumRooms;
    }

    public void setNormalRooms(Room[][] normalRooms) {
        this.normalRooms = normalRooms;
    }

    public void setVIPRooms(VIPRoom[][] vIPRooms) {
        VIPRooms = vIPRooms;
    }

    public void setPremiumRooms(PremiumRoom[][] premiumRooms) {
        this.premiumRooms = premiumRooms;
    }

}