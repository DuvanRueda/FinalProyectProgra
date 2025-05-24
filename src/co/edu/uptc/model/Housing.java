package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import co.edu.uptc.exceptionOwn.*;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {

    private final String[] CATEGORIES = { "GENERAL", "LIMPIEZA", "COMODIDAD", "UBICACION DE LA HABITACIÓN", "ATENCION DEL PERSONAL", "RELACIÓN CALIDAD/PRESIO", "FUNCIONALIDAD DE LOS SERVICIOS DE LA HABITACIÓN", "INTERIOR", "JACUZZI"};
    private final String ADMIN_REGEX = "^[A-Za-z0-9]{1,10}$";
    private Room normalRooms[][];
    private VIPRoom VIPRooms[][];
    private PremiumRoom premiumRooms[][];
    private HashMap<String, Double> globalRates;
    private HashMap<String, Double> normalRates;
    private HashMap<String, Double> VIPRates;
    private HashMap<String, Double> premiumRates;
    private String resultProcess;
    private String adminName;
    private String adminPassword;
    private Averages objectAverages;

    public Housing() {
        adminName = "Admin";
        adminPassword = "soyElAdmin231";
        globalRates = new HashMap<>();
        normalRates = new HashMap<>();
        VIPRates = new HashMap<>();
        premiumRates = new HashMap<>();
        resultProcess = "";
        objectAverages = new Averages();
        initHousing();
    }

    public boolean validateRooms(String typeRoom, int rows, int columns) {
        if (rows > 0 && columns > 0) {
            initRooms(typeRoom, rows, columns);
            return true;
        }
        return false;
    }

    public void initRooms(String typeRoom, int rows, int columns) {
        if (typeRoom.equals("Normales")) {
            initNormalRooms(rows, columns);
        } else if (typeRoom.equals("VIP")) {
            initVipRooms(rows, columns);
        } else {
            initPremiumRooms(rows, columns);

        }
    }

    public void initNormalRooms(int rows, int columns) {
        normalRooms = new Room[rows][columns];
        createRooms();
    }

    public void initVipRooms(int rows, int columns) {
        VIPRooms = new VIPRoom[rows][rows];
        createVIPRooms();
    }

    public void initPremiumRooms(int rows, int columns) {
        premiumRooms = new PremiumRoom[rows][columns];
        createPremiumRooms();
    }

    public void createRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < normalRooms.length; i++) {
            for (int j = 0; j < normalRooms[0].length; j++) {
                normalRooms[i][j] = new Room("N" + countCreateRoom);
                countCreateRoom++;
            }
        }
    }

    public void createVIPRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < VIPRooms.length; i++) {
            for (int j = 0; j < VIPRooms[0].length; j++) {
                VIPRooms[i][j] = new VIPRoom("V" + countCreateRoom);
                countCreateRoom++;
            }
        }
    }

    public void createPremiumRooms() {
        int countCreateRoom = 1;
        for (int i = 0; i < premiumRooms.length; i++) {
            for (int j = 0; j < premiumRooms[0].length; j++) {
                premiumRooms[i][j] = new PremiumRoom("P" + countCreateRoom);
                countCreateRoom++;
            }
        }
    }
    
    public void roomAverage(Room[][] rooms) {
        HashMap<String, Double> average = objectAverages.roomAverage2(CATEGORIES, rooms);
        int sizeRates = average.size();
        if (sizeRates == 7) {
                normalRates = average;
        } else if (sizeRates == 8) {
                VIPRates = average;
        } else {
            premiumRates = average;
        }
    }
    
    public void globalAverage() {
        for (int i = 0; i < CATEGORIES.length-1; i++) {
            double[] rates;
            if (i == 7) {
                rates = new double[] {0, VIPRates.get(CATEGORIES[i]), premiumRates.get(CATEGORIES[i])};
            } else {
                rates = new double[] { normalRates.get(CATEGORIES[i]), VIPRates.get(CATEGORIES[i]), premiumRates.get(CATEGORIES[i])};
            }
            globalRates.put(CATEGORIES[i], objectAverages.globalAverage(rates));
        }
        globalRates.put("JACUZZI", premiumRates.get(CATEGORIES[8]));
    }

    public String makeRatingRoom(double[] ratings, String nameRoom, String comment) {
        if (nameRoom.charAt(0) == 'N') {
            return makeRatingRoom(ratings, nameRoom, comment, normalRooms);
        } else if (nameRoom.charAt(0) == 'V') {
            return makeRatingRoom(ratings, nameRoom, comment, VIPRooms);
        } else
            return makeRatingRoom(ratings, nameRoom, comment, premiumRooms);
    }

    public String makeRatingRoom(double[] ratings, String nameRoom, String comment, Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    if (comment != null)
                        rooms[i][j].addComment(comment);
                    resultProcess = rooms[i][j].makeRating(ratings);
                    roomAverage(rooms);
                    globalAverage();
                    return resultProcess;
                }
            }
        }
        return "";
    }

    public String showGlobalComments() {
        String commentsNormal = "Comentarios habitaciones normales: \n" + showCommentsTypeRoom(normalRooms);
        String commentsVIP = "Comentarios habitaciones VIP: \n" +  showCommentsTypeRoom(VIPRooms);
        String commentsPremiun = "Comentarios habitaciones premium: \n" +  showCommentsTypeRoom(premiumRooms);
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
        resultProcess = "";
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                resultProcess += rooms[i][j].getCommentsRoom().showComments();
            }
        }
        return resultProcess;
    }

    public String showCommentsRoom(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return showCommentsRoom(normalRooms, nameRoom);
        } else if (nameRoom.charAt(0) == 'V') {
            return showCommentsRoom(VIPRooms, nameRoom);
        } else
            return showCommentsRoom(premiumRooms, nameRoom);
    }

    public String showCommentsRoom(Room[][] rooms, String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return rooms[i][j].getCommentsRoom().showComments();
                }
            }
        }
        return "No se encontro la habitacion que buscas";
    }

    public boolean verifyAvailabilityRoom(char typeRoom) {
        if (typeRoom == 'N') {
            return verifyAvailabilityRoom(normalRooms);
        } else if (typeRoom == 'V') {
            return verifyAvailabilityRoom(VIPRooms);
        } else
            return verifyAvailabilityRoom(premiumRooms);
    }

    public boolean verifyAvailabilityRoom(Room[][] rooms) {
        for (int i = rooms.length - 1; i > -1; i--) {
            for (int j = rooms[0].length - 1; j > -1; j--) {
                if (rooms[i][j].isFree()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String bookkingRoom(char typeRoom, String password) {
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
                if (!rooms[i][j].isFree() && rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)
                        && rooms[i][j].getPassword().equals(password)) {
                    rooms[i][j].setPassword("");
                    rooms[i][j].setFree(true);
                    return true;
                }
            }
        }
        return false;
    }

    public String showRateRoom(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return showRateRoom(normalRooms, nameRoom);
        } else if (nameRoom.charAt(0) == 'V') {
            return showRateRoom(VIPRooms, nameRoom);
        } else
            return showRateRoom(premiumRooms, nameRoom);
    }

    public String showRateRoom(Room[][] rooms, String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return rooms[i][j].myToString();
                }
            }
        }
        return "No se ha encontrado la cabaña digitada.";
    }

    public String changeAdminName(String newName) {
        try {
            if (!newName.matches(ADMIN_REGEX))
                throw new InvalidInputAdminException(newName);
            return "Se ha cambiado la contraseña correctamente";
        } catch (InvalidInputAdminException e) {
            return e.getMessage();
        }
    }

    public String changeAdminPassword(String newPassword) {
        try {
            if (!newPassword.matches(ADMIN_REGEX))
                throw new InvalidInputAdminException(newPassword);
            return "Se ha cambiado la contraseña correctamente";
        } catch (InvalidInputAdminException e) {
            return e.getMessage();
        }
    }

    public String automaticWarning() {
        resultProcess = "";
        for (Map.Entry<String, Double> input : globalRates.entrySet()) {
            if (input.getValue() > 0 && input.getValue() < 3.0) {
                resultProcess += "Alerta: El ítem " + input.getKey() + " tiene un evalución crítica: "
                        + String.format("%.1f", input.getValue()) + "\n";
            }
        }
        if (resultProcess.equals("")) {
            return null;
        }
        return resultProcess;
    }

    public String showRates(char typeRates) {
        if (typeRates == 'N') {
            return  showRates(makeRanking(normalRates));
        } else if (typeRates == 'V') {
            return showRates(makeRanking(VIPRates));
        } else if (typeRates == 'P') {
            return showRates(makeRanking(premiumRates));
        } else 
            return showRates(makeRanking(globalRates));
    }

    public String showRates(Map<String, Double> rates) {
        resultProcess = "";
        for (Map.Entry<String, Double> input : rates.entrySet()) {
            String key = input.getKey();
            double value = input.getValue();
            resultProcess += key + " = " + String.format("%.1f", value) + "\n";
        }
        return resultProcess;
    }

    public LinkedHashMap<String, Double> makeRanking(Map<String, Double> rates) {
        Map.Entry<String, Double> generalEntry = null;
        ArrayList<Map.Entry<String, Double>> localList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            if (entry.getKey().equals("GENERAL")) {
                generalEntry = entry;
            } else
                localList.add(entry);
        }
        return sortRanking(generalEntry, localList);
    }

    public LinkedHashMap<String, Double> sortRanking(Map.Entry<String, Double> generalEntry, ArrayList<Map.Entry<String, Double>> ranking) {
        for (int i = 0; i < ranking.size() - 1; i++) {
            for (int j = 0; j < ranking.size() - i - 1; j++) {
                Map.Entry<String, Double> current = ranking.get(j);
                Map.Entry<String, Double> next = ranking.get(j + 1);

                if (current.getValue() < next.getValue()) {
                    ranking.remove(j + 1);
                    ranking.remove(j);
                    ranking.add(j, next);
                    ranking.add(j + 1, current);
                }
            }
        }
        LinkedHashMap<String, Double> localRanking = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : ranking) {
            localRanking.put(entry.getKey(), entry.getValue());
        }
        localRanking.put(generalEntry.getKey(), generalEntry.getValue());
        return localRanking;
    }

    private void initHousing() {
        for (int i = 0; i < CATEGORIES.length; i++) {
            globalRates.put(CATEGORIES[i], 0.0);
            premiumRates.put(CATEGORIES[i], 0.0);
            if (i < 8) {
                normalRates.put(CATEGORIES[i],0.0);
            }
            if (i < 9) {
                VIPRates.put(CATEGORIES[i],0.0);
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

    public boolean verifyRate(double rate) {
        return normalRooms[0][0].verifyRate(rate);
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public Room[][] getNormalRooms() {
        return normalRooms;
    }
}