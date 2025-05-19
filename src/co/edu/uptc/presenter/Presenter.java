package co.edu.uptc.presenter;

import co.edu.uptc.model.Housing;
import co.edu.uptc.model.Room;
import co.edu.uptc.view.IOManager;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations. 
 */
public class Presenter {

    private IOManager objectIOManager;
    private Housing objectHousing;
    private boolean exit;
    private char action;
    private String password;

    public Presenter() {
        objectIOManager = new IOManager();
        objectHousing = new Housing();
        exit = false;
        action = 'a';
    }

    public void requestNumberRooms(String typeRoom) {
        boolean isCorrectRooms = false;
        int numberRoomRows = 0;
        do {
            try {
                numberRoomRows = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por columna que tiene su alojamiento.\n(Filas de habitaciones.)"));
                int numberRoomColumns = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por fila que tiene su alojamiento.\n(Columnas de habitaciones.)"));
                isCorrectRooms = objectHousing.validateRooms(typeRoom, numberRoomRows, numberRoomColumns);
            } catch (NumberFormatException e) {
                objectIOManager.showMessage(
                        "Ha ingresado un dato inesperado, vuelva a ingresar el numero de las habitaciones " + typeRoom);
            }
        } while (!isCorrectRooms);
        objectIOManager.showMessage("Se han creado correctamente las habitaciones " + typeRoom);
    }

    public void createRooms() {
        requestNumberRooms("Normales");
        requestNumberRooms("VIP");
        requestNumberRooms("Premium");
    }

    public void menuLog() {
        do {
            int resultOption = objectIOManager.bouttonQuestion("Seleccione el tipo de usuario", "MENU DE LOGGEO", new String[] { "Cliente", "Administrador" });
            switch (resultOption) {
                case 0:
                    objectIOManager.showMessage(clientMenu());
                    break;
                case 1:
                    adminMenu();
                    break;

                default:
                    exit = exit();
                    break;
            }
        } while (!exit);
    }

    public void adminMenu() {
        do {
            char action = objectIOManager.inputList("¿Qué opcion desea realizar?", "MENU DE ADMINISTRADOR.", new String[] { "" }).charAt(0);
            switch (action) {
                case 'A':

                    break;

                default:
                    break;
            }
        } while (!exit);
        exit = false;
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
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion");
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnNormalRoom(nameRoom, password);   
            case 'V':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion");
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnVIPRoom(nameRoom, password);
            case 'P':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion");
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnPremiumRoom(nameRoom, password);
            default:
                return "Saliendo del menu de cliente.";
        }
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
        return objectHousing.normalRating(ratings, nameRoom);
    }

    public String VIPRating(String nameRoom) {
        double[] ratings = new double[7];
        String[] sentences = objectHousing.getVIPRooms()[0][0].getSentences();
        for (int i = 0; i < 7; i++) {
            ratings[i] = Double.parseDouble(objectIOManager.inputData(sentences[i]));
        }
        return objectHousing.VIPRating(ratings, nameRoom);
    }

    public String PremiumRating(String nameRoom) {
        double[] ratings = new double[8];
        String[] sentences = objectHousing.getPremiumRooms()[0][0].getSentences();
        for (int i = 0; i < 8; i++) {
            ratings[i] = Double.parseDouble(objectIOManager.inputData(sentences[i]));
        }
        return objectHousing.premiumRating(ratings, nameRoom);
    }

    public boolean exit() {
        int resultOption = objectIOManager.bouttonQuestion("¿Esta seguro de salir del programa?", "Pregunta", new String[] { "Si, estoy seguro", "No, volver" });
        if (resultOption == 0) {
            String nameUser = objectIOManager.inputData("Ingrese su nombre de usuario.");
            String passwordUser = objectIOManager.inputData("Ingrese la contraseña.");
            if (objectHousing.getADMIN_NAME().equals(nameUser)
                    && objectHousing.getADMIN_PASSWORD().equals(passwordUser)) {
                return true;
            } else
                objectIOManager.showMessage("Las credenciales estan mal, lo devolveremos al menu de loggeo.");
        }
        return false;
    }

    public void init() {
        objectIOManager.showMessage("El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
        menuLog();
    }

}