package co.edu.uptc.model;

import java.util.Map;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {

    private final String[] CATEGORIES = { "GENERAL", "LIMPIEZA", "COMODIDAD", "UBICACION DE LA HABITACIÓN", "ATENCION DEL PERSONAL", "RELACIÓN CALIDAD/PRECIO", "FUNCIONALIDAD DE LOS SERVICIOS DE LA HABITACIÓN", "INTERIOR", "JACUZZI"};
    private RoomsManager normalRooms;
    private RoomsManager VIPRooms;
    private RoomsManager premiumRooms;
    private RateManager globalRates;
    private Averages objectAverages;
    private AdminCredentials objectAdminCredentials;
    private String resultProcess;

    public Housing() {
        normalRooms = new RoomsManager(7);
        VIPRooms = new RoomsManager(8);
        premiumRooms = new RoomsManager(9);
        globalRates = new RateManager(9, CATEGORIES);
        objectAverages = new Averages();
        objectAdminCredentials = new AdminCredentials();
        resultProcess = "";
    }


    public boolean initRooms(char typeRoom, int rows, int columns) {
        if (validateRooms(rows, columns)) {
            switch (typeRoom) {
            case 'N'-> normalRooms.createRooms(rows, columns, typeRoom, Room::new);
            case 'V'-> VIPRooms.createRooms(rows, columns, typeRoom, VIPRoom::new);
            case 'P'-> premiumRooms.createRooms(rows, columns, typeRoom, PremiumRoom::new);
            }
            return true;
        }  
        return false;
    }

    public boolean validateRooms(int rows, int columns) {
        return rows > 0 && columns > 0;
    }

    // APARTADO DEL CLIENTE

    public boolean verifyAvailabilityRoom(char typeRoom) {
        if (typeRoom == 'N') {
            return normalRooms.verifyAvailabilityRoom();
        } else if (typeRoom == 'V') {
            return VIPRooms.verifyAvailabilityRoom();
        } else
            return premiumRooms.verifyAvailabilityRoom();
    }

    public String bookkingRoom(char typeRoom, String password) {
        if (typeRoom == 'N') {
            return normalRooms.bookkingRoom(password);
        } else if (typeRoom == 'V') {
            return VIPRooms.bookkingRoom(password);
        } else {
            return premiumRooms.bookkingRoom(password);
        }
    }

    public boolean returnRoom(String nameRoom, String password) {
        if (nameRoom == null || nameRoom.equals("")) {
            return false;
        } else if (nameRoom.charAt(0) == 'N') {
            return normalRooms.returnRoom(nameRoom, password);
        } else if (nameRoom.charAt(0) == 'V') {
            return VIPRooms.returnRoom(nameRoom, password);
        } else
            return premiumRooms.returnRoom(nameRoom, password);
    }

    public String makeRatingRoom(double[] ratings, String nameRoom, String comment) {
        if (nameRoom.charAt(0) == 'N') {
            resultProcess = normalRooms.makeRatingRoom(ratings, nameRoom, comment);
            globalAverage();
            return resultProcess;
        } else if (nameRoom.charAt(0) == 'V') {
            resultProcess = VIPRooms.makeRatingRoom(ratings, nameRoom, comment);
            globalAverage();
            return resultProcess;
        } else{
            resultProcess = premiumRooms.makeRatingRoom(ratings, nameRoom, comment);
            globalAverage();
            return resultProcess;
        }
    }


    //METODOS USADOS INDIRECTAMENTE POR EL USUARIO
    
    public void globalAverage() {
        for (int i = 0; i < CATEGORIES.length-1; i++) {
            double[] rates;
            if (i == 7) {
                rates = new double[] {0, normalRooms.getEspecificRate(i) , premiumRooms.getEspecificRate(i)};
            } else {
                rates = new double[] { normalRooms.getEspecificRate(i), VIPRooms.getEspecificRate(i), premiumRooms.getEspecificRate(i)};
            }
            globalRates.setRate(CATEGORIES[i], objectAverages.globalAverage(rates));
        }
        globalRates.setRate("JACUZZI", premiumRooms.getEspecificRate(8));
    }

    // APARTADO DEL ADMINISTRADOR

    public String showGlobalComments() {
        String commentsNormal = "Comentarios habitaciones normales: \n" + showCommentsTypeRoom('N');
        String commentsVIP = "Comentarios habitaciones VIP: \n" +  showCommentsTypeRoom('V');
        String commentsPremiun = "Comentarios habitaciones premium: \n" +  showCommentsTypeRoom('P');
        return commentsNormal + commentsVIP + commentsPremiun;
    }

    public String showCommentsTypeRoom(char typeRoom) {
        if (typeRoom == 'N') {
            return normalRooms.showCommentsTypeRoom();
        } else if (typeRoom == 'V') {
            return VIPRooms.showCommentsTypeRoom();
        } else
            return premiumRooms.showCommentsTypeRoom();
    }

    public String showCommentsRoom(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return normalRooms.showCommentsRoom(nameRoom);
        } else if (nameRoom.charAt(0) == 'V') {
            return VIPRooms.showCommentsRoom(nameRoom);
        } else
            return premiumRooms.showCommentsRoom(nameRoom);
    }

    public String showRateRoom(String nameRoom) {
        if (nameRoom.charAt(0) == 'N') {
            return normalRooms.showRateRoom(nameRoom);
        } else if (nameRoom.charAt(0) == 'V') {
            return VIPRooms.showRateRoom(nameRoom);
        } else
            return premiumRooms.showRateRoom(nameRoom);
    }

    public String automaticWarning() {
        resultProcess = "";
        for (Map.Entry<String, Double> input : globalRates.getAllRates().entrySet()) {
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

    public String showGeneralRates(char typeRates) {
        if (typeRates == 'N') {
            return  normalRooms.showRates();
        } else if (typeRates == 'V') {
            return VIPRooms.showRates();
        } else if (typeRates == 'P') {
            return premiumRooms.showRates();
        } else 
            return globalRates.showRates();
    }

    public String[] getSentences(char typeRoom) {
        if (typeRoom == 'N') {
            return normalRooms.getSentences();
        } else if (typeRoom == 'V') {
            return VIPRooms.getSentences();
        } else
            return premiumRooms.getSentences();
    }

    public String changeAdminName(String newName) {
        return objectAdminCredentials.changeAdminName(newName);
    }

    public String changeAdminPassword(String newPassword) {
        return objectAdminCredentials.changeAdminPassword(newPassword);
    }

    public boolean isCredentialsValid(String name, String password) {
        return objectAdminCredentials.isValid(name, password);
    }

    public boolean verifyRate(double rate) {
        return normalRooms.verifyRate(rate);
    }
}