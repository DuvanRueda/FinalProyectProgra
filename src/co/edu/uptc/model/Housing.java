package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import co.edu.uptc.exceptionOwn.InvalidInputAdminException;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {

    private final double[] VALUE_RATES = new double[] {0.2,0.35,0.45};
    private final double VALUE_NORMAL_RATE = 0.2;
    private final double VALUE_VIP_RATE = 0.35;
    private final double VALUE_PREMIUM_RATE = 0.45;
    private final String ADMIN_REGEX = "^[A-Za-z0-9]{1,10}$";
    private Room normalRooms[][];
    private VIPRoom VIPRooms[][];
    private PremiumRoom premiumRooms[][];
    private HashMap<String, Double> globalRates;
    private double[] normalRates;
    private double[] VIPRates;
    private double[] premiumRates;
    private String resultString;
    private String adminName;
    private String adminPassword;

    public Housing() {
        adminName = "Admin";
        adminPassword = "soyElAdmin231";
        globalRates = new HashMap<>();
        normalRates = new double[7];
        VIPRates = new double[8];
        premiumRates = new double[9];
        resultString = "";
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

    public String[] getSentences(char typeRoom) {
        if (typeRoom == 'N') {
            return normalRooms[0][0].getSENTENCES();
        } else if (typeRoom == 'V') {
            return VIPRooms[0][0].getSENTENCES();
        } else
            return premiumRooms[0][0].getSENTENCES();
    }

    public void roomRating(Room[][] rooms) {
        int count = 0;
        int sizeVector = 0;
        double[] generalRates = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getGeneralRate() != 0) {
                    double[] rates = rooms[i][j].getRatings();
                    sizeVector = rates.length;
                    generalRates = plusRates(generalRates, rates);
                    count++;
                }
            }
        }
        for (int i = 0; i < sizeVector; i++) {
            generalRates[i] = generalRates[i] / count;
        }
        sentRates(sizeVector, generalRates);
    }

    public void sentRates(int sizeRates, double[] rates) {
        if (sizeRates == 7) {
            for (int i = 0; i < sizeRates; i++) {
                normalRates[i] = rates[i];
            }
        } else if (sizeRates == 8) {
            for (int i = 0; i < sizeRates; i++) {
                VIPRates[i] = rates[i];
            }
        } else {
            premiumRates = rates;
        }
    }

    public double[] plusRates(double[] generalRates, double[] rates) {
        double[] localRates = new double[rates.length];
        for (int i = 0; i < rates.length; i++) {
            localRates[i] = generalRates[i] + rates[i];
        }
        return localRates;
    }

    public void globalRates() {
        globalRates.put("GENERAL", average(new double[] {normalRates[0], VIPRates[0], premiumRates[0] }));
        globalRates.put("LIMPIEZA", average(new double[] {normalRates[1],VIPRates[1], premiumRates[1]}));
        globalRates.put("COMODIDAD", average(new double[] {normalRates[2], VIPRates[2],premiumRates[2]}));
        globalRates.put("UBICACION DE LA HABITACIÓN", average(new double[] {normalRates[3], VIPRates[3], premiumRates[3]}));
        globalRates.put("ATENCION DEL PERSONAL", average(new double[] {normalRates[4], VIPRates[4], premiumRates[4]}));
        globalRates.put("RELACIÓN CALIDAD/PRESIO", average(new double[]{normalRates[5], VIPRates[5], premiumRates[5]}));
        globalRates.put("FUNCIONALIDAD DE LOS SERVICIOS DE LA HABITACIÓN", average(new double[]{normalRates[6], VIPRates[6], premiumRates[6]}));
        globalRates.put("INTERIOR", average(new double[]{0, VIPRates[7], premiumRates[7]}));
        globalRates.put("JACUZZI", premiumRates[8]);
    }

    public double average(double[] rates) {
        double rateSum = 0;
        double average = 0;
        List<Integer> invalidIndex = new ArrayList<>();
        for (int i = 0; i < rates.length; i++) {
            if (rates[i] == 0) {
                invalidIndex.add(i);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (invalidIndex.size() == 0) {
                average += VALUE_RATES[i] * rates[i];

            } else if (invalidIndex.size() == 1) {
                rateSum = (VALUE_RATES[invalidIndex.get(0)]) / 2;
                average += (VALUE_RATES[i] + rateSum) * rates[i];
            } else if (invalidIndex.size() == 2) {
                average += rates[i];
            } else 
                average = 0;
        }

        return average;
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
                    roomRating(rooms);
                    globalRates();
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

    public boolean verifyAvailability(char typeRoom) {
        if (typeRoom == 'N') {
            return verifyAvailability(normalRooms);
        } else if (typeRoom == 'V') {
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
        resultString = "";
        for (Map.Entry<String, Double> input : globalRates.entrySet()) {
            if (input.getValue() > 0 && input.getValue() < 3.0) {
                resultString += "Alerta: El ítem " + input.getKey() + " tiene un evalución crítica: "
                        + String.format("%.1f", input.getValue()) + "\n";
            }
        }
        if (resultString.equals("")) {
            return null;
        }
        return resultString;
    }

    public String showRanking() {
        resultString = "";
        for (Map.Entry<String, Double> input : makeRanking().entrySet()) {
            String key = input.getKey();
            double value = input.getValue();
            resultString += key + " = " + String.format("%.1f", value) + "\n";
        }
        return resultString;
    }

    public LinkedHashMap<String, Double> makeRanking() {
        Map.Entry<String, Double> generalEntry = null;
        ArrayList<Map.Entry<String, Double>> localList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : globalRates.entrySet()) {
            if (entry.getKey().equals("GENERAL")) {
                generalEntry = entry;
            } else
                localList.add(entry);
        }
        return sortRanking(generalEntry, localList);
    }

    public LinkedHashMap<String, Double> sortRanking(Map.Entry<String, Double> generalEntry,
            ArrayList<Map.Entry<String, Double>> ranking) {
        Collections.sort(ranking, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        LinkedHashMap<String, Double> localRanking = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : ranking) {
            localRanking.put(entry.getKey(), entry.getValue());
        }
        if (generalEntry != null) {
            localRanking.put(generalEntry.getKey(), generalEntry.getValue());
        }
        return localRanking;
    }

    public double searchInGlobalRates(String key) {
        return globalRates.get(key);
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

    public double[] getNormalRates() {
        return normalRates;
    }

    public double[] getVIPRates() {
        return VIPRates;
    }

    public double[] getPremiumRates() {
        return premiumRates;
    }
}