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
                numberRoomRows = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por columna que tiene su alojamiento.\n(Filas de habitaciones.)"));
                int numberRoomColumns = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por fila que tiene su alojamiento.\n(Columnas de habitaciones.)"));
                isCorrectRooms = objectHousing.initRooms(typeRoom.charAt(0), numberRoomRows, numberRoomColumns);
                if (!isCorrectRooms) {
                    objectIOManager.showMessage("No puede crear matices de de 0 filas o 0 columnas");
                }
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
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
                return "Saliendo del menu del cliente.";
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
        if (!objectHousing.verifyAvailabilityRoom(typeRoom))
            return "Lo sentimos mucho, en este momento no hay habitaciones normales disponibles en este momento.";
        password = objectIOManager.inputData("Ingrese la contraseña que desea ponerle a su habitación en su instancia.\nLa contraseña debe contar con mínimo 3 letras y 2 números.");
        return objectHousing.bookkingRoom(typeRoom, password);
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
            return makeRatingRoom(nameRoom);
        } else
            return "El nombre de la habitacion o la contraseña son erroneas, vuelva a intentarlo.";
    }

    public String makeRatingRoom(String nameRoom) {
        String[] sentences = objectHousing.getSentences(nameRoom.charAt(0));
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
        resultProcess = addCommentRoom();
        return objectHousing.makeRatingRoom(ratings, nameRoom, resultProcess);
    }

    public String addCommentRoom() {
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
            char action = objectIOManager.inputList("¿Qué opcion desea realizar?", "MENU DE ADMINISTRADOR.", new String[] { "1.Revisar evaluaciones de una habitación en especifico", "2.Revisar la evaluación general de un tipo de habitación", "3 .Mostrar el ranking gobal de los items.", "4.Cambiar el nombre del administrador.", "5.Cambiar la contraseña."}).charAt(0);
            try {
                switch (action) {
                    case '1':
                        objectIOManager.showMessage(showRate());
                        break;
                    case '2':
                        objectIOManager.showMessage(showRatingTypeRoom());
                        break;
                    case '3':
                        objectIOManager.showMessage(objectHousing.showGeneralRates(action));
                        objectIOManager.showMessage(showGlobalComments());
                        break;
                    case '4':
                        if (adminLog())
                            objectIOManager.showMessage(changeAdminName());
                        break;
                    case '5':
                        if (adminLog())
                            objectIOManager.showMessage(changeAdminPassword());
                        break;
                    default:
                        exitMenu = true;
                        objectIOManager.showMessage("Saliendo al menu general.");
                        break;
                }
            } catch (Exception e) {
                objectIOManager.showMessage("Volviendo al menu principal del Administrador");
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
                    objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: \n" + objectHousing.showGeneralRates(action));
                    return showCommentsTypeRoom(action);
                case 'V':
                    objectIOManager.showMessage("Las habitaciones VIP tienen una puntuacion general de:\n " + objectHousing.showGeneralRates(action));
                    return showCommentsTypeRoom(action);
                case 'P':
                    objectIOManager.showMessage("Las habitaciones premium tienen una puntuacion general de:\n " + objectHousing.showGeneralRates(action));
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
            resultProcess = objectHousing.showGlobalComments();
            return resultProcess.isEmpty() ? "No hay comentarios aun." : resultProcess;
        } else
            return "Saliendo al menu principal del Administrador.";
    }

    public String showCommentsTypeRoom(char typeRoom) {
        int actionInt = objectIOManager.bouttonQuestion(
                "¿Desea revisar todos los comentarios de este tipo de habitaciones?", null,
                new String[] { "SI", "NO" });
        if (actionInt == 0) {
            resultProcess = objectHousing.showCommentsTypeRoom(typeRoom); 
            return resultProcess.isEmpty() ? "No hay comentarios aun." : resultProcess;
        } else
            return "Saliendo al menu principal del Administrador.";
    }

    public String showRate() {
        String nameRoom = objectIOManager.inputData("Ingrese el nombre de la habitacion que desea revisar").toUpperCase();
        String resultProcess = objectHousing.showRateRoom(nameRoom);
        if (resultProcess.charAt(0) == 'N')
            return resultProcess;
        objectIOManager.showMessage(resultProcess);
        return showCommentsRoom(nameRoom);
    }

    public String showCommentsRoom(String nameRoom) {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea revisar los comentarios de la habitación?", null,
                new String[] { "SI", "NO" });
        if (actionInt == 0) {
            resultProcess = objectHousing.showCommentsRoom(nameRoom);
            return resultProcess.isEmpty()? "No hay comentarios aun." : resultProcess;
        } else
            return "Saliendo al menu principal del Administrador.";

    }

    public boolean adminLog() {
        String nameUser = objectIOManager.inputData("Ingrese su nombre de usuario.");
        String passwordUser = objectIOManager.inputData("Ingrese la contraseña.");
        if (objectHousing.isCredentialsValid(nameUser, passwordUser)) {
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
        objectIOManager.showMessage("El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
        menuLog();
    }

}