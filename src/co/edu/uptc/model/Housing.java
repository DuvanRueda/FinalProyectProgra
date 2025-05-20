package co.edu.uptc.model;    
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {
    
    private final String ADMIN_NAME = "Admin";
    private final String ADMIN_PASSWORD = "soy"; //ElAdmin231
    private final double VALUE_NORMAL_RATE = 0.2;
    private final double VALUE_VIP_RATE = 0.35;
    private final double VALUE_PREMIUM_RATE = 0.45;
    private Room normalRooms[][];
    private VIPRoom VIPRooms[][];
    private PremiumRoom premiumRooms[][];
    private double globalRate;
    private double normalRate;
    private double VIPRate;
    private double premiumRate;
    private String resultString;

    public Housing(){
        globalRate = 0.0;
        normalRate = 0.0;
        VIPRate = 0.0;
        premiumRate = 0.0;
        resultString = "";
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
        createRooms();
    }
    public void initVipRooms(int rows, int columns){
        VIPRooms = new VIPRoom[rows][rows];
        createVIPRooms();
    }
    public void initPremiumRooms(int rows, int columns){
        premiumRooms = new PremiumRoom[rows][columns];
        createPremiumRooms();
    }

    public void createRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < normalRooms.length; i++) {
            for (int j = 0; j < normalRooms[0].length; j++) {
                normalRooms[i][j] = new Room("N" + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }
    public void createVIPRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < VIPRooms.length; i++) {
            for (int j = 0; j < VIPRooms[0].length; j++) {
                VIPRooms[i][j] = new VIPRoom("V" + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }
    public void createPremiumRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < premiumRooms.length; i++) {
            for (int j = 0; j < premiumRooms[0].length; j++) {
                premiumRooms[i][j] = new PremiumRoom("P" + countCreateRoom);
                countCreateRoom ++;
            }
        }
    }

    public String[] getSentences(char typeRoom) {
        if (typeRoom == 'N') {
            return normalRooms[0][0].getSENTENCES();
        } else if (typeRoom == 'V') {
            return VIPRooms[0][0].getSENTENCES();
        } else 
            return premiumRooms[0][0].getSENTENCES();
    }

    public void roomRating(Room[][] rooms, String nameRoom) {
        int count = 0;
        double rateLocal = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getGeneralRate() != 0) {
                    rateLocal += rooms[i][j].getGeneralRate();
                    count ++;
                }
            }
        }
        if (nameRoom.charAt(0) == 'N') {
            normalRate = rateLocal/count;
        } else if (nameRoom.charAt(0) == 'V') {
            VIPRate = rateLocal/count;
        } else
            premiumRate = rateLocal/count;
    }

    public void globalRating() {
        globalRate = (normalRate*VALUE_NORMAL_RATE + VIPRate*VALUE_VIP_RATE + premiumRate*VALUE_PREMIUM_RATE);
    }

    public String rating(double[] ratings, String nameRoom, String comment) {
        if (nameRoom.charAt(0) == 'N') {
            return rating(ratings, nameRoom, comment, normalRooms);
        } else if (nameRoom.charAt(0) == 'V') {
            return rating(ratings, nameRoom, comment, VIPRooms);
        } else 
            return rating(ratings, nameRoom, comment, premiumRooms);
    }

    public String rating(double[] ratings, String nameRoom, String comment, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    if (comment != null) 
                        rooms[i][j].addComment(comment);
                    resultString = rooms[i][j].makeRating(ratings);
                    roomRating(rooms, nameRoom);
                    globalRating();
                    return resultString;
                }
            }
        }
        return "";
    }

    public String showGlobalComments() {
        String commentsNormal = showCommentsTypeRoom(normalRooms);
        String commentsVIP = showCommentsTypeRoom(VIPRooms);
        String commentsPremiun = showCommentsTypeRoom(premiumRooms);
        return commentsNormal + commentsVIP + commentsPremiun; 
    }

    public String showCommentsTypeRoom(char typeRoom) {
        if (typeRoom == 'N') {
            return showCommentsTypeRoom(normalRooms);
        } else if (typeRoom == 'V') {
            return showCommentsTypeRoom(VIPRooms);
        } else 
            return showCommentsTypeRoom(premiumRooms);
    }

    public String showCommentsTypeRoom(Room[][] rooms) {
        resultString = "";
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                resultString += rooms[i][j].getCommentsRoom().showComments();
            }
        }
        return resultString;
    }

    public String showCommentsRoom(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return showCommentsRoom(normalRooms, nameRoom);
        } else if (nameRoom.charAt(0) == 'V'){
            return showCommentsRoom(VIPRooms, nameRoom);
        } else 
            return showCommentsRoom(premiumRooms, nameRoom);
    }

    public String showCommentsRoom(Room[][] rooms, String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)){
                    return rooms[i][j].getCommentsRoom().showComments();
                }
            }
        }
        return "No se encontro la habitacion que buscas";
    }

    public boolean verifyAvailability(char typeRoom) {
        if (typeRoom == 'N') {
            return verifyAvailability(normalRooms);
        } else if (typeRoom == 'V'){
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

    public String bookking(char typeRoom, String password) {
        if (typeRoom == 'N') {
            return bookkingRoom(password, normalRooms);
        } else if (typeRoom == 'V') {
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

    public boolean returnRoom(String nameRoom, String password) {
        if (nameRoom.charAt(0) == 'N') {
            return returnRoom(nameRoom, password, normalRooms);
        } else if (nameRoom.charAt(0) == 'V') {
            return returnRoom(nameRoom, password, VIPRooms);
        } else
            return returnRoom(nameRoom, password, premiumRooms);

    }

    public boolean returnRoom(String nameRoom, String password, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (!rooms[i][j].isFree() && rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom) && rooms[i][j].getPassword().equals(password)) {
                    rooms[i][j].setPassword("");
                    rooms[i][j].setFree(true);
                    return true;
                } 
            }
        }        
        return false;
    }

    public String showRate(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return showRate(normalRooms, nameRoom);
        } else if (nameRoom.charAt(0) == 'V') {
            return showRate(VIPRooms, nameRoom);
        } else
            return showRate(premiumRooms, nameRoom);
    }

    public String showRate(Room[][] rooms, String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
           for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return rooms[i][j].myToString();
                }
           } 
        }
        return "No se ha encontrado la cabaña digitada.";
    }

    public boolean verifyRate(double rate) {
        return normalRooms[0][0].verifyRate(rate);
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

    public double getGlobalRate() {
        return globalRate;
    }

    public double getNormalRate() {
        return normalRate;
    }

    public double getVIPRate() {
        return VIPRate;
    }

    public double getPremiumRate() {
        return premiumRate;
    }

}