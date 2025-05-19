package co.edu.uptc.model;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Room {

    private String roomName;
    private String password;
    private boolean isFree;
    private Comment commentsRoom;
    private double generalRate;
    private double cleaningRate;
    private double comfortRate;
    private double locationRate;
    private double customerServiceRate;
    private double qualityRate;
    private double servicesRate;
    private String[] sentences = new String[] {
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
            setGeneralRate((temp + generalRate) / 2);
        } else {
            setGeneralRate(temp);
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

    public String myToString() {
        return "Estadisticas cabaña " + roomName +
                "\nLimpieza: " + cleaningRate + 
                "\nComodidad: " + comfortRate + 
                "\nUbicación de la habitación: " + locationRate + 
                "\nAtención del personal: " + customerServiceRate + 
                "\nRelacion calidad/precio: " + qualityRate + 
                "\nFuncionabilidad de los servicios de la habitación: " + servicesRate + 
                "\nGeneral: " + generalRate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public void setCommentsRoom(Comment commentsRoom) {
        this.commentsRoom = commentsRoom;
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

    public void setCleaningRate(double cleaningRate) {
        this.cleaningRate = cleaningRate;
    }

    public double getComfortRate() {
        return comfortRate;
    }

    public void setComfortRate(double comfortRate) {
        this.comfortRate = comfortRate;
    }

    public double getLocationRate() {
        return locationRate;
    }

    public void setLocationRate(double locationRate) {
        this.locationRate = locationRate;
    }

    public double getCustomerServiceRate() {
        return customerServiceRate;
    }

    public void setCustomerServiceRate(double customerServiceRate) {
        this.customerServiceRate = customerServiceRate;
    }

    public double getQualityRate() {
        return qualityRate;
    }

    public void setQualityRate(double qualityRate) {
        this.qualityRate = qualityRate;
    }

    public double getServicesRate() {
        return servicesRate;
    }

    public void setServicesRate(double servicesRate) {
        this.servicesRate = servicesRate;
    }

    public String[] getSentences() {
        return sentences;
    }
}