package co.edu.uptc.presenter;

import java.text.DecimalFormat;

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
    private DecimalFormat objectDecimalFormat;


    public Presenter() {
        action = 'a';
        password = "";
        resultProcess = "";
        objectIOManager = new IOManager();
        objectHousing = new Housing();
        objectDecimalFormat = new DecimalFormat("#.#");
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
                objectIOManager.showMessage("Ha ingresado un dato inesperado, vuelva a ingresar el numero de las habitaciones " + typeRoom);
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
        password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.\nLa contraseña debe ca¿ontar con minimo 3 letras y 2 numeros.");
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
                return returnRoom(nameRoom, password);
            case 'V':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion").toUpperCase();
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnRoom(nameRoom, password);
            case 'P':
                nameRoom = objectIOManager.inputData("Ingrese el nombre de su habitacion").toUpperCase();
                password = objectIOManager.inputData("Ingrese la contraseña de su habitación.");
                return returnRoom(nameRoom, password);
            default:
                return "Saliendo del menu de cliente.";
        }
    }

    public String returnRoom(String nameRoom, String password) {
        if (objectHousing.returnRoom(nameRoom, password)) {
            objectIOManager.showMessage("Credenciales correctas, procederemos a hacerle la evaluacion de satisfaccion.");
            return rating(nameRoom);
        } else
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String rating(String nameRoom) {
        String[] sentences = getSentences(nameRoom);
        double[] ratings = new double[sentences.length];
        for (int i = 0; i < ratings.length; i++) {
            double rate = Double.parseDouble(objectIOManager.inputData(sentences[i]));
            if(objectHousing.verifyRate(rate)) {
                ratings[i] = rate;
            } else {
                objectIOManager.showMessage("Ingreso una calificación fuera de los parametros.");
                i--;
            }
        }
        resultProcess = addComment();
        return objectHousing.rating(ratings, nameRoom, resultProcess);
    }

    public String[] getSentences(String nameRoom) {
        return objectHousing.getSentences(nameRoom.charAt(0));
    }

    public String addComment() {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea dejar un comentario de su experiencia en el hospedaje?",
                null, new String[] { "SI", "NO" });
        if (actionInt == 0) {
            return objectIOManager.inputData("Ingrese su comentario adicional");
        } else
            return null;

    }

    // Parte de los metodos del Administrador.

    public void warningItem() {
        resultProcess = objectHousing.automaticWarning();
        if (resultProcess != null) {
            objectIOManager.showMessage(resultProcess);
        }
    }
    public void adminMenu() {
        warningItem();
        boolean exitMenu = false;
        do {
            char action = objectIOManager.inputList("¿Qué opcion desea realizar?", "MENU DE ADMINISTRADOR.", new String[] { "1.Revisar evaluaciones de una habitación en especifico", "2.Revisar la evaluación general de un tipo de habitación", "3.Revisar la evaluación genereal del establecimiento","4.Mostrar el ranking gobal de los items.", "5.Cambiar el nombre del administrador.", "6.Cambiar la contraseña.", "7"}).charAt(0);
                    switch (action) {
                case '1':
                    objectIOManager.showMessage(showRate());
                    break;
                case '2':
                    objectIOManager.showMessage(showRatingTypeRoom());
                    break;
                case '3':
                    objectIOManager.showMessage( "La calificación global del establecimiento es de: " + objectDecimalFormat.format(objectHousing.searchInGlobalRates("GENERAL")));
                    objectIOManager.showMessage(showGlobalComments());
                    break;
                case '4':
                    objectIOManager.showMessage(objectHousing.showRanking());
                    break;
                case '5':
                    if (adminLog())
                        objectIOManager.showMessage(changeAdminName());
                    break;
                case '6':
                    if (adminLog())
                        objectIOManager.showMessage(changeAdminPassword());
                    break;
                case '7':
                        objectIOManager.showMessage(objectHousing.showRanking());;
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
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objectDecimalFormat.format(objectHousing.getNormalRates()[0]));
                    return showCommentsTypeRoom(action);
                case 'V':
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objectDecimalFormat.format(objectHousing.getVIPRates()[0]));
                    return showCommentsTypeRoom(action);
                case 'P':
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objectDecimalFormat.format(objectHousing.getPremiumRates()[0]));
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
        String nameRoom = objectIOManager.inputData("Ingrese el nombre de la habitacion que desea revisar").toUpperCase();
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

    public boolean adminLog() {
        String nameUser = objectIOManager.inputData("Ingrese su nombre de usuario.");
        String passwordUser = objectIOManager.inputData("Ingrese la contraseña.");
        if (objectHousing.getAdminName().equals(nameUser) && objectHousing.getAdminPassword().equals(passwordUser)) {
            return true;
        } else
            objectIOManager.showMessage("Las credenciales estan mal, lo devolveremos al menu de loggeo.");
        return false;
    }

    public String changeAdminName() {
        String newName = objectIOManager.inputData("Ingresa el nuevo nombre.");
        return objectHousing.changeAdminName(newName);
    }
    public String changeAdminPassword() {
        String newPassword = objectIOManager.inputData("Ingresa la nueva contraseña.");
        return objectHousing.changeAdminPassword(newPassword);
    }

    // Metodos utlizados por Admin y el cliente

    public char verifyInputList(String message, String titule, String[] options) {
        String optionString = objectIOManager.inputList("¿Qué tipo de habitación desea revisar?",
                "REVISIÓN DE HABITACIONES.",
                new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." });
        if (optionString.equals("null")) {
            return '0';
        } else
            return optionString.charAt(11);
    }

    public void init() {
        objectIOManager.showMessage(
                "El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
        menuLog();
    }

}