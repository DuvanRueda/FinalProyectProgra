package co.edu.uptc.model;

import java.util.HashMap;
import java.util.function.Function;

public class RoomsManager {

    private final String[] CATEGORIES = { "GENERAL", "LIMPIEZA", "COMODIDAD", "UBICACION DE LA HABITACIÓN", "ATENCION DEL PERSONAL", "RELACIÓN CALIDAD/PRECIO", "FUNCIONALIDAD DE LOS SERVICIOS DE LA HABITACIÓN", "INTERIOR", "JACUZZI"};
    private String resultProcess;
    private Room[][] rooms;
    private RateManager rates;
    private Averages objectAverages;

    public RoomsManager(int size){
        resultProcess = "";
        rates = new RateManager(size, CATEGORIES);
        objectAverages = new Averages();
    }

    public void createRooms(int rows, int columns, char prefix, Function<String, Room> roomCreator) {
        rooms = new Room[rows][columns];
        int countCreateRoom = 1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                String roomName = String.valueOf(prefix) + countCreateRoom;
                rooms[i][j] = roomCreator.apply(roomName);
                countCreateRoom++;
            }
        }
    }

    public boolean verifyAvailabilityRoom() {
        for (int i = rooms.length - 1; i > -1; i--) {
            for (int j = rooms[0].length - 1; j > -1; j--) {
                if (rooms[i][j].isFree()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String bookkingRoom(String password) {
        if (!rooms[0][0].verifyPassword(password)) {
            return "La contraseña no cumple con los requisitos pedidos";
        }
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

    public String makeRatingRoom(double[] ratings, String nameRoom, String comment) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    if (comment != null)
                        rooms[i][j].addComment(comment);
                    resultProcess = rooms[i][j].makeRating(ratings);
                    roomAverage();
                    // globalAverage();
                    return resultProcess;
                }
            }
        }
        return "";
    }

    private void roomAverage() {
        HashMap<String, Double> average = objectAverages.roomAverage(CATEGORIES, rooms);
        rates.setAllRates(average);
    }

    // METODOS USADOS POR EL ADMINISTRADOR

    public String showCommentsTypeRoom() {
        resultProcess = "";
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                resultProcess += rooms[i][j].getCommentsRoom().showComments();
            }
        }
        return resultProcess.isEmpty() ? "No hay comentarios aun.\n" : resultProcess;
    }

    public String showCommentsRoom(String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return rooms[i][j].getCommentsRoom().showComments();
                }
            }
        }
        return "No se encontro la habitacion que buscas";
    }

    public String showRateRoom(String nameRoom) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getRoomName().equalsIgnoreCase(nameRoom)) {
                    return rooms[i][j].myToString();
                }
            }
        }
        return "No se ha encontrado la cabaña digitada.";
    }

    public String showRates() {
        return rates.showRates();
    }

    public boolean verifyRate(double rate) {
        return rooms[0][0].verifyRate(rate);
    }

    public String[] getSentences() {
        return rooms[0][0].getSENTENCES();
    }

    public double getEspecificRate(int index) {
        return rates.getRate(CATEGORIES[index]);
    }
}
