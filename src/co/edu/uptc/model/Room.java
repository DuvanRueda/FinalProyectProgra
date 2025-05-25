package co.edu.uptc.model;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Room {

    protected String roomName;
    protected String password;
    protected boolean isFree;
    protected Comment commentsRoom;
    protected double generalRate;
    protected double cleaningRate;
    protected double comfortRate;
    protected double locationRate;
    protected double customerServiceRate;
    protected double qualityRate;
    protected double servicesRate;
    private final String[] SENTENCES = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?", 
            "Del 1 al 5, ¿cómo calificarías la comodidad?",
            "Del 1 al 5, ¿cómo calificarías la ubicación de la habitacíon?",
            "Del 1 al 5, ¿cómo calificarías la atención del personal?",
            "Del 1 al 5, ¿cómo calificarías la relación calidad/precio?",
            "Del 1 al 5, ¿cómo calificarías la funcionalidad de los servicios en la habitación?" 
        };

    public Room(String roomName) {
        this.roomName = roomName;
        password = "";
        isFree = true;
        commentsRoom = new Comment();
        generalRate = 0;
        cleaningRate = 0;
        comfortRate = 0;
        locationRate = 0;
        customerServiceRate = 0;
        qualityRate = 0;
        servicesRate = 0;
    }

    public boolean verifyPassword(String password) {
        String passwordRegex = "^(?=(?:[^A-Za-záéíóúÁÉÍÓÚñÑ]*[A-Za-záéíóúÁÉÍÓÚñÑ]){3,})(?=(?:[^0-9]*[0-9]){2,})[A-Za-záéíóúÁÉÍÓÚñÑ0-9]+$";
        if (password.matches(passwordRegex)) {
            return true;
        }
        return false;
    }

    public boolean verifyRate(double rate){
        if (rate >= 1 && rate <= 5) {
            return true;
        }
        return false;
    }

    public String makeRating(double[] ratings) {
        cleaningRating(ratings[0]);
        comfortRating(ratings[1]);
        locationRating(ratings[2]);
        customerServiceRating(ratings[3]);
        qualityRating(ratings[4]);
        servicesRating(ratings[5]);
        generalRating();
        return "Entrega de habitación exitosa, la habitación: " + roomName + " esta libre.";
    }
    public void generalRating() {
        double temp = (cleaningRate + comfortRate + locationRate + customerServiceRate + qualityRate + servicesRate)
                / 6;
        if (generalRate != 0) {
            generalRate = (temp + generalRate) / 2;
        } else {
            generalRate = temp;
        }
    }

    public void cleaningRating(double newCleaningRate) {
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate + newCleaningRate) / 2;
        } else {
            cleaningRate = newCleaningRate;
        }
    }

    public void comfortRating(double newComfortRate) {
        if (comfortRate != 0) {
            comfortRate = (comfortRate + newComfortRate) / 2;
        } else {
            comfortRate = newComfortRate;
        }
    }

    public void locationRating(double newLocationRate) {
        if (locationRate != 0) {
            locationRate = (locationRate + newLocationRate) / 2;
        } else {
            locationRate = newLocationRate;
        }
    }

    public void customerServiceRating(double newCustomerServiceRate) {
        if (customerServiceRate != 0) {
            customerServiceRate = (customerServiceRate + newCustomerServiceRate) / 2;
        } else {
            customerServiceRate = newCustomerServiceRate;
        }
    }

    public void qualityRating(double newQualityRate) {
        if (qualityRate != 0) {
            qualityRate = (qualityRate + newQualityRate) / 2;
        } else {
            qualityRate = newQualityRate;
        }
    }

    public void servicesRating(double newServiceRate) {
        if (servicesRate != 0) {
            servicesRate = (servicesRate + newServiceRate) / 2;
        } else {
            servicesRate = newServiceRate;
        }
    }

    public double[] getRatings() {
        return new double[] {generalRate, cleaningRate, comfortRate, locationRate, customerServiceRate, qualityRate, servicesRate};
    }

    public String myToString() {
        return commentsRoom.isEmpty() ? "No hay comentarios aun." : 
                "Estadisticas cabaña " + roomName +
                "\nLimpieza: " + String.format("%.1f",cleaningRate) + 
                "\nComodidad: " + String.format("%.1f",comfortRate) + 
                "\nUbicación de la habitación: " + String.format("%.1f",locationRate) + 
                "\nAtención del personal: " + String.format("%.1f",customerServiceRate) + 
                "\nRelacion calidad/precio: " + String.format("%.1f",qualityRate) + 
                "\nFuncionabilidad de los servicios de la habitación: " + String.format("%.1f",servicesRate) + 
                "\nGeneral: " + String.format("%.1f",generalRate);
    }

    public String getRoomName() {
        return roomName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public Comment getCommentsRoom() {
        return commentsRoom;
    }

    public void addComment(String comment) {
        commentsRoom.addComment(comment);
    }

    public double getGeneralRate() {
        return generalRate;
    }

    public void setGeneralRate(double generalRate) {
        this.generalRate = generalRate;
    }

    public double getCleaningRate() {
        return cleaningRate;
    }

    public double getComfortRate() {
        return comfortRate;
    }

    public double getLocationRate() {
        return locationRate;
    }

    public double getCustomerServiceRate() {
        return customerServiceRate;
    }

    public double getQualityRate() {
        return qualityRate;
    }

    public double getServicesRate() {
        return servicesRate;
    }

    public String[] getSENTENCES() {
        return SENTENCES;
    }
}