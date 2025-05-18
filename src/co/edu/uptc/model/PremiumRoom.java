package co.edu.uptc.model;

public class PremiumRoom extends VIPRoom {

    private double jacuzziRate;
    private String[] sentences = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?",
            "Del 1 al 5, ¿cómo calificarías la limpieza?", "Del 1 al 5, ¿cómo calificarías la comodidad?",
            "Del 1 al 5, ¿cómo calificarías la ubicación de la habitacíon?",
            "Del 1 al 5, ¿cómo calificarías la atención del personal?",
            "Del 1 al 5, ¿cómo calificarías la relación calidad/precio?",
            "Del 1 al 5, ¿cómo calificarías la funcionalidad de los servicios en la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el interior de la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el servicio de jacuzzi?"};

    public PremiumRoom(String roomName) {
        super(roomName);
        jacuzziRate = 0;
    }

    @Override
    public void makeRating() {
        double temp = (getCleaningRate() + getComfortRate() + getLocationRate() + getCustomerServiceRate() + getQualityRate() + getServicesRate() + getInteriorRate() + jacuzziRate)/8;
        if (getGeneralRate() != 0) {
            setGeneralRate((temp+getGeneralRate())/2);
        }else{
            setGeneralRate(temp);
        }
    }
 
    public String[] getSentences() {
        return sentences;
    }

}