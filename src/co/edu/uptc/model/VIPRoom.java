package co.edu.uptc.model;

public class VIPRoom extends Room {

    private double interiorRate;
    private String[] sentences = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?",
            "Del 1 al 5, ¿cómo calificarías la limpieza?", "Del 1 al 5, ¿cómo calificarías la comodidad?",
            "Del 1 al 5, ¿cómo calificarías la ubicación de la habitacíon?",
            "Del 1 al 5, ¿cómo calificarías la atención del personal?",
            "Del 1 al 5, ¿cómo calificarías la relación calidad/precio?",
            "Del 1 al 5, ¿cómo calificarías la funcionalidad de los servicios en la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el interior de la habitación?"
    };


    public VIPRoom(String roomName) {
        super(roomName);
        interiorRate = 0;
    }

    @Override
    public void makeRating() {
        double temp = (getCleaningRate() + getComfortRate() + getLocationRate() + getCustomerServiceRate() + getQualityRate() + getServicesRate() + getInteriorRate())/7;
        if (getGeneralRate() != 0) {
            setGeneralRate((temp+getGeneralRate())/2);
        }
        setGeneralRate(temp);
    }

    public void interiorRating(double newInteriorRate){
        if (interiorRate != 0) {
            interiorRate = (interiorRate+newInteriorRate)/2;
        }else{
            interiorRate = newInteriorRate;
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

}
