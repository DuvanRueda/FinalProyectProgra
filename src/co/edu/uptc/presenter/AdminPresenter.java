package co.edu.uptc.presenter;

import co.edu.uptc.model.Housing;
import co.edu.uptc.view.IOManager;

public class AdminPresenter {

    private IOManager objectIOManager;
    private Housing objetcHousing;
    private boolean exitMenu;
    
    public AdminPresenter(Housing objectHousing){
        this.objetcHousing = objectHousing;
        objectIOManager = new IOManager();
        exitMenu = true;
    }

    public String adminMenu() {
        
        do {
            int actionInt = objectIOManager.bouttonQuestion("¿Qué opcion desea realizar?", "MENU DE ADMINISTRADOR.", new String[] {"Revisar evaluaciones de una habitación en especifico", "Revisar la evaluación general de un tipo de habitación" ,"Revisar la evaluación genereal del establecimiento"});
            switch (actionInt) {
                case 0:
                    return showRate();
                case 1:
                    return showRatingTypeRoom();
                case 2:
                    return "La calificación global del establecimiento es de: " + objetcHousing.getGlobalRate();
                default:
                    exitMenu = false;
                    return "Saliendo al menu general.";
            }
        } while (!exitMenu);
    }

    public String showRatingTypeRoom() {
        char action = objectIOManager.inputList("¿Qué tipo de habitación desea revisar?", "REVISIÓN DE HABITACIONES.", new String[] { "Habitación Normal.", "Habitación VIP.", "Habitación Premium." }).charAt(11);
        switch (action) {
            case 'N':
                objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objetcHousing.getNormalRate());
                return showCommentsTypeRoom(action);
            case 'V':
                objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objetcHousing.getVIPRate());
                return showCommentsTypeRoom(action);
            case 'P':
                objectIOManager.showMessage("Las habitaciones normales tienen una puntuacion general de: " + objetcHousing.getPremiumRate());
                return showCommentsTypeRoom(action);
        
            default:
                return "Saliendo al menu principal del Administrador.";
        }
    }

    public String showCommentsTypeRoom(char typeRoom) {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea revisar todos los comentarios de este tipo de habitaciones?", null, new String[]{"SI", "NO"});
        if (actionInt == 0){
            return objetcHousing.showCommentsTypeRoom(typeRoom);
        } else
            return "Saliendo al menu principal del Administrador.";

    }
    
    public String showRate() {
        String nameRoom = objectIOManager.inputData("Ingrese el nombre de la habitacion que desea revisar").toUpperCase();
        String resultProcess = objetcHousing.showRate(nameRoom);
        if (resultProcess.charAt(0) == 'N') 
            return resultProcess;
        objectIOManager.showMessage(resultProcess);
            return showCommentsRoom(nameRoom);
    }

    public String showCommentsRoom(String nameRoom) {
        int actionInt = objectIOManager.bouttonQuestion("¿Desea revisar los comentarios de la habitación?", null, new String[]{"SI", "NO"});
        if (actionInt == 0){
            return objetcHousing.showCommentsRoom(nameRoom);
        } else
            return "Saliendo al menu principal del Administrador.";

    }


}
