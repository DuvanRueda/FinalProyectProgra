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

    public Presenter() {
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
        requestNumberRooms("normales");
        requestNumberRooms("VIP");
        requestNumberRooms("Premiun");
    }

    public void menuLog() {
        boolean isOffApplication = false;
        do {
            int resultOption = objectIOManager.bouttonQuestion("Seleccione el tipo de usuario", "MENU DE LOGGEO",
                    new String[] { "Cliente", "Administrador" });
            switch (resultOption) {
                case 0:
                    System.out.println("Vamos a descansar");
                    break;
                case 1:
                    System.out.println("mierda, hay que trabajar");
                    break;

                default:
                    resultOption = objectIOManager.bouttonQuestion("¿Esta seguro de salir del programa?", "Pregunta",
                            new String[] { "Si, estoy seguro", "No, volver" });
                    if (resultOption == 0) {
                        String nameUser = objectIOManager.inputData("Ingrese su nombre de usuario.");
                        String passwordUser = objectIOManager.inputData("Ingrese la contraseña.");
                        if (objectHousing.getADMIN_NAME().equals(nameUser)
                                && objectHousing.getADMIN_PASSWORD().equals(passwordUser)) {
                            isOffApplication = true;
                        } else
                            objectIOManager.showMessage("Las credenciales estan mal, lo devolveremos al menu de loggeo.");
                    }
                    break;
            }
        } while (!isOffApplication);
    }

    public void init() {
        objectIOManager.showMessage("El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
        menuLog();
    }

}