package co.edu.uptc.model;

public class VIPRoom extends Room {

    private String[] sentences = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?",
            "Del 1 al 5, ¿cómo calificarías la limpieza?", "Del 1 al 5, ¿cómo calificarías la comodidad?",
            "Del 1 al 5, ¿cómo calificarías la ubicación de la habitacíon?",
            "Del 1 al 5, ¿cómo calificarías la atención del personal?",
            "Del 1 al 5, ¿cómo calificarías la relación calidad/precio?",
            "Del 1 al 5, ¿cómo calificarías la funcionalidad de los servicios en la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el interior de la habitación?"
    };

    private double cleaningRate;
    private double comfortRate;
    private double locationRate;
    private double customerServiceRate;
    private double qualityRate;
    private double servicesRate;
    private double interiorRate;

    public VIPRoom(String roomName) {
        super(roomName);
    }

    @Override
    public void makeRating() {
        double temp = cleaningRate + comfortRate + locationRate + customerServiceRate + qualityRate + servicesRate+ interiorRate;
        setGeneralRate(temp/7);
    }

    public void cleaningRating(double newCleaningRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newCleaningRate)/2;
        }else{
            cleaningRate = newCleaningRate;
        }
    }

    public void comfortRating(double newComfortRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newComfortRate)/2;
        }else{
            cleaningRate = newComfortRate;
        }
    }

    public void locationRating(double newLocationRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newLocationRate)/2;
        }else{
            cleaningRate = newLocationRate;
        }
    }

    public void customerServiceRating(double newCustomerServiceRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newCustomerServiceRate)/2;
        }else{
            cleaningRate = newCustomerServiceRate;
        }
    }

    public void qualityRating(double newQualityRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newQualityRate)/2;
        }else{
            cleaningRate = newQualityRate;
        }
    }

    public void servicesRating(double newServiceRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newServiceRate)/2;
        }else{
            cleaningRate = newServiceRate;
        }
    }

    public void interiorRating(double newInteriorRate){
        if (cleaningRate != 0) {
            cleaningRate = (cleaningRate+newInteriorRate)/2;
        }else{
            cleaningRate = newInteriorRate;
        }
    }

    public String[] getSentences() {
        return sentences;
    }

    public void setSentences(String[] sentences) {
        this.sentences = sentences;
    }

    public double getInteriorRate() {
        return interiorRate;
    }

    public void setInteriorRate(double interiorRate) {
        this.interiorRate = interiorRate;
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

}
