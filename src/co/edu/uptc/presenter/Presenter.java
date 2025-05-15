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
                numberRoomRows = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por columna que tiene su alojamiento.\n(Filas de habitaciones.)"));
                int numberRoomColumns = Integer.parseInt(objectIOManager.inputData("Ingrese la cantidad de habitaciones " + typeRoom + " por fila que tiene su alojamiento.\n(Columnas de habitaciones.)"));
                isCorrectRooms = objectHousing.validateRooms(typeRoom,numberRoomRows, numberRoomColumns);
            } catch (NumberFormatException e) {
                objectIOManager.showMessage("Ha ingresado un dato inesperado, vuelva a ingresar el numero de las habitaciones " + typeRoom);
            }
        } while (!isCorrectRooms);
        objectIOManager.showMessage("Se han creado correctamente las habitaciones " + typeRoom);
    }

    public void createRooms() {       
        requestNumberRooms("normales");
        requestNumberRooms("VIP");
        requestNumberRooms("Premiun");
    }

    public void init() {
        objectIOManager.showMessage("El siguiente programa esta pensado para que el alojamiento cuente con sistema de \nreview y pueda tener retroalimentación por parte de los huespedes.");
        createRooms();
    }

}