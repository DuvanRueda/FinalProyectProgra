package co.edu.uptc.presenter;

import co.edu.uptc.model.Housing;
import co.edu.uptc.view.IOManager;

public class ClientPresenter {
    
    private char action;
    private String password;
    private String resultProcess;
    private IOManager objectIOManager;
    private Housing objectHousing;

    public ClientPresenter( Housing objectHousing){
        action = 'a';
        password = "";
        resultProcess = "";
        objectIOManager = new IOManager();
        this.objectHousing = objectHousing;
    }

    public String clientMenu() {
        action = objectIOManager.inputList("¿Qué opcion desea realizar?", "MENU DE CLIENTE.", new String[] { "Reservar habitación", "Entregar habitación" }).charAt(0);
        switch (action) {
            case 'R':
                return bookking();
            case 'E':
                return returnRoomMenu();

            default:
                return "Saliendo del menu del cliente";
        }
    }

    public String bookking() {
        action = objectIOManager.inputList("¿Qué tipo de habitación desea reservar?", "RESERVA DE HABITACIONES.", new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." }).charAt(11);
        switch (action) {
            case 'N':
                if (!objectHousing.verifyAvailability("Normal"))
                    return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
                password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.");
                return objectHousing.bookking("Normal",password);
            case 'V':
            if (!objectHousing.verifyAvailability("VIP"))
                    return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
                password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.");
                return objectHousing.bookking("VIP", password);
            case 'P':
                if (!objectHousing.verifyAvailability("Premiun"))
                    return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
                password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.");
                return objectHousing.bookking("Premium",password);
            default:
                return "Saliendo del menu de cliente.";
        }
    }

    public String returnRoomMenu() {
        String nameRoom = "";
        action = objectIOManager.inputList("¿Qué tipo de habitación desea entregar?", "ENTREGA DE HABITACIONES.", new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." }).charAt(11);
        switch (action) {
            case 'N':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion").toUpperCase();
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnNormalRoom(nameRoom, password);
            case 'V':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion").toUpperCase();
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.").toUpperCase();
                return returnVIPRoom(nameRoom, password);
            case 'P':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion");
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnPremiumRoom(nameRoom, password);
            default:
                return "Saliendo del menu de cliente.";
        }
    }

    public String addComment() {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea dejar un comentario de su experiencia en el hospedaje?", null, new String[]{"SI", "NO"});
        if (actionInt == 0){
            return objectIOManager.inputData("Ingrese su comentario adicional");
        } else
            return null;

    }

    public String returnNormalRoom(String nameRoom, String password) {
        if (objectHousing.returnNormalRoom(nameRoom,password)){
            objectIOManager.showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
            return normalRating(nameRoom);
        } else 
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String returnVIPRoom(String nameRoom, String password) {
        if (objectHousing.returnVIPRoom(nameRoom,password)){
            objectIOManager.showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
            return VIPRating(nameRoom);
        } else 
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String returnPremiumRoom(String nameRoom, String password) {
        if (objectHousing.returnPremiumRoom(nameRoom,password)){
            objectIOManager.showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
            return PremiumRating(nameRoom);
        } else 
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String normalRating(String nameRoom) {
        double[] ratings = new double[6];
        String[] sentences = objectHousing.getNormalRooms()[0][0].getSentences();
        for (int i = 0; i < 6; i++) {
            ratings[i] = Double.parseDouble(objectIOManager.inputData(sentences[i]));
        }
        resultProcess = addComment();
        return objectHousing.normalRating(ratings, nameRoom, resultProcess);
    }

    public String VIPRating(String nameRoom) {
        double[] ratings = new double[7];
        String[] sentences = objectHousing.getVIPRooms()[0][0].getSentences();
        for (int i = 0; i < 7; i++) {
            ratings[i] = Double.parseDouble(objectIOManager.inputData(sentences[i]));
        }
        resultProcess = addComment();
        return objectHousing.VIPRating(ratings, nameRoom, resultProcess);
    }

    public String PremiumRating(String nameRoom) {
        double[] ratings = new double[8];
        String[] sentences = objectHousing.getPremiumRooms()[0][0].getSentences();
        for (int i = 0; i < 8; i++) {
            ratings[i] = Double.parseDouble(objectIOManager.inputData(sentences[i]));
        }
        resultProcess = addComment();
        return objectHousing.premiumRating(ratings, nameRoom, resultProcess);
    }
}
