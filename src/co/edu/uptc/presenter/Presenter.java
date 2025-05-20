package co.edu.uptc.presenter;

import co.edu.uptc.model.Housing;
import co.edu.uptc.view.IOManager;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations. 
 */
public class Presenter {

    private char action;
    private String password;
    private String resultProcess;
    private IOManager objectIOManager;
    private Housing objectHousing;

    public Presenter() {
        action = 'a';
        password = "";
        resultProcess = "";
        objectIOManager = new IOManager();
        objectHousing = new Housing();
    }

    public void requestNumberRooms(String typeRoom) {
        boolean isCorrectRooms = false;
        int numberRoomRows = 0;
        do {
            try {
                numberRoomRows = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones "
                        + typeRoom + " por columna que tiene su alojamiento.\n(Filas de habitaciones.)"));
                int numberRoomColumns = Integer
                        .parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom
                                + " por fila que tiene su alojamiento.\n(Columnas de habitaciones.)"));
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
        boolean exit = false;
        do {
            // try {
            int resultOption = objectIOManager.bouttonQuestion("Seleccione el tipo de usuario", "MENU DE LOGGEO",
                    new String[] { "Cliente", "Administrador" });
            switch (resultOption) {
                case 0:
                    objectIOManager.showMessage(clientMenu());
                    break;
                case 1:
                    if (adminLog()) {
                        adminMenu();
                    }
                    break;

                default:
                    exit = exit();
                    break;
            }
            // } catch (Exception e) {
            // System.out.println("Que machetaso tan gonorrea");
            // }
        } while (!exit);
    }

    public boolean exit() {
        int resultOption = objectIOManager.bouttonQuestion("¿Esta seguro de salir del programa?", "Pregunta",
                new String[] { "Si, estoy seguro", "No, volver" });
        if (resultOption == 0) {
            return adminLog();
        }
        return false;
    }

    public boolean adminLog() {
        String nameUser = objectIOManager.inputData("Ingrese su nombre de usuario.");
        String passwordUser = objectIOManager.inputData("Ingrese la contraseña.");
        if (objectHousing.getADMIN_NAME().equals(nameUser) && objectHousing.getADMIN_PASSWORD().equals(passwordUser)) {
            return true;
        } else
            objectIOManager.showMessage("Las credenciales estan mal, lo devolveremos al menu de loggeo.");
        return false;
    }

    // Parte de los menus del cliente.
    public String clientMenu() {
        boolean exitMenu = false;
        do {
            action = objectIOManager.inputList("¿Qué opcion desea realizar?", "MENU DE CLIENTE.",
                    new String[] { "Reservar habitación", "Entregar habitación" }).charAt(0);
            switch (action) {
                case 'R':
                    return bookking();
                case 'E':
                    return returnRoomMenu();

                default:
                    exitMenu = true;
                    return "Saliendo del menu del cliente";
            }
        } while (!exitMenu);
    }

    public String bookking() {
        action = verifyInputList("¿Qué tipo de habitación desea reservar?", "RESERVA DE HABITACIONES.",
                new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." });
        switch (action) {
            case 'N':
                return bookking(action);
            case 'V':
                return bookking(action);
            case 'P':
                return bookking(action);
            default:
                return "Saliendo del menu del cliente.";
        }
    }

    public String bookking(char typeRoom) {
        if (!objectHousing.verifyAvailability(typeRoom))
                    return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
                password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.");
                if (!objectHousing.getNormalRooms()[0][0].verifyPassword(password)) {
                    return "La contraseña no cumple con los requisitos pedidos";
                }
                return objectHousing.bookking(typeRoom, password);
    }

    public String returnRoomMenu() {
        String nameRoom = "";
        action = verifyInputList("¿Qué tipo de habitación desea entregar?", "ENTREGA DE HABITACIONES.",
                new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." });
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
        int actionInt = objectIOManager.bouttonQuestion("¿Desea dejar un comentario de su experiencia en el hospedaje?",
                null, new String[] { "SI", "NO" });
        if (actionInt == 0) {
            return objectIOManager.inputData("Ingrese su comentario adicional");
        } else
            return null;

    }

    public String returnNormalRoom(String nameRoom, String password) {
        if (objectHousing.returnNormalRoom(nameRoom, password)) {
            objectIOManager.showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
            return normalRating(nameRoom);
        } else
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String returnVIPRoom(String nameRoom, String password) {
        if (objectHousing.returnVIPRoom(nameRoom, password)) {
            objectIOManager
                    .showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
            return VIPRating(nameRoom);
        } else
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String returnPremiumRoom(String nameRoom, String password) {
        if (objectHousing.returnPremiumRoom(nameRoom, password)) {
            objectIOManager
                    .showMessage("Credenciales correectas, procederemos a hacerle la evaluacion de satisfaccion.");
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

    // Parte de los metodos del Administrador.

    public void adminMenu() {
        boolean exitMenu = false;
        do {
            int actionInt = objectIOManager.bouttonQuestion("¿Qué opcion desea realizar?", "MENU DE ADMINISTRADOR.",
                    new String[] { "Revisar evaluaciones de una habitación en especifico",
                            "Revisar la evaluación general de un tipo de habitación",
                            "Revisar la evaluación genereal del establecimiento" });
            switch (actionInt) {
                case 0:
                    objectIOManager.showMessage(showRate());
                    break;
                case 1:
                    objectIOManager.showMessage(showRatingTypeRoom());
                    break;
                case 2:
                    objectIOManager.showMessage( "La calificación global del establecimiento es de: " + objectHousing.getGlobalRate());
                    objectIOManager.showMessage(showGlobalComments());
                    break;
                default:
                    exitMenu = true;
                    objectIOManager.showMessage("Saliendo al menu general.");
                    break;
            }
        } while (!exitMenu);
    }

    public String showRatingTypeRoom() {
        boolean exitMenu = false;
        do {
            char action = verifyInputList("¿Qué tipo de habitación desea revisar?", "REVISIÓN DE HABITACIONES.",
                    new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." });
            switch (action) {
                case 'N':
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: "
                            + objectHousing.getNormalRate());
                    return showCommentsTypeRoom(action);
                case 'V':
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: "
                            + objectHousing.getVIPRate());
                    return showCommentsTypeRoom(action);
                case 'P':
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: "
                            + objectHousing.getPremiumRate());
                    return showCommentsTypeRoom(action);
                default:
                    exitMenu = true;
                    return "Saliendo al menu principal del Administrador.";
            }
        } while (!exitMenu);
    }

    public String showGlobalComments() {
        int actionInt = objectIOManager.bouttonQuestion(
                "¿Desea revisar todos los comentarios de este tipo de habitaciones?", null,
                new String[] { "SI", "NO" });
        if (actionInt == 0) {
            return objectHousing.showGlobalComments();
        } else
            return "Saliendo al menu principal del Administrador.";
    }

    public String showCommentsTypeRoom(char typeRoom) {
        int actionInt = objectIOManager.bouttonQuestion(
                "¿Desea revisar todos los comentarios de este tipo de habitaciones?", null,
                new String[] { "SI", "NO" });
        if (actionInt == 0) {
            return objectHousing.showCommentsTypeRoom(typeRoom);
        } else
            return "Saliendo al menu principal del Administrador.";
    }

    public String showRate() {
        String nameRoom = objectIOManager.inputData("Ingrese el nombre de la habitacion que desea revisar")
                .toUpperCase();
        String resultProcess = objectHousing.showRate(nameRoom);
        if (resultProcess.charAt(0) == 'N')
            return resultProcess;
        objectIOManager.showMessage(resultProcess);
        return showCommentsRoom(nameRoom);
    }

    public String showCommentsRoom(String nameRoom) {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea revisar los comentarios de la habitación?", null,
                new String[] { "SI", "NO" });
        if (actionInt == 0) {
            return objectHousing.showCommentsRoom(nameRoom);
        } else
            return "Saliendo al menu principal del Administrador.";

    }

    // Metodos utlizados por Admin y el cliente

    public char verifyInputList(String message, String titule, String[] options) {
        String optionString = objectIOManager.inputList("¿Qué tipo de habitación desea revisar?",
                "REVISIÓN DE HABITACIONES.",
                new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." });
        if (optionString.equals("null")) {
            return 'n';
        } else
            return optionString.charAt(11);
    }

    public void verifyInput() {

    }

    public void init() {
        objectIOManager.showMessage(
                "El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
        menuLog();
    }

}