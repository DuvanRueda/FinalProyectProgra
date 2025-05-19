package co.edu.uptc.presenter;

import co.edu.uptc.model.Housing;
import co.edu.uptc.view.IOManager;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations. 
 */
public class Presenter {

    private IOManager objectIOManager;
    private Housing objectHousing;
    private ClientPresenter objectClientPresenter;
    private AdminPresenter objecTAdminPresenter;

    public Presenter() {
        objectIOManager = new IOManager();
        objectHousing = new Housing();
        objectClientPresenter = new ClientPresenter(objectHousing);
        objecTAdminPresenter = new AdminPresenter(objectHousing);
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
        boolean exit = false;
        do {
            int resultOption = objectIOManager.bouttonQuestion("Seleccione el tipo de usuario", "MENU DE LOGGEO", new String[] { "Cliente", "Administrador" });
            switch (resultOption) {
                case 0:
                    objectIOManager.showMessage(objectClientPresenter.clientMenu());
                    break;
                case 1:
                    objectIOManager.showMessage(objecTAdminPresenter.adminMenu());
                    break;

                default:
                    exit = exit();
                    break;
            }
        } while (!exit);
    }

    public boolean exit() {
        System.out.println(objectHousing.getNormalRooms()[0][0].getGeneralRate());
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