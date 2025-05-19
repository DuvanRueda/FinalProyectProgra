package co.edu.uptc.model;

public class PremiumRoom extends VIPRoom {

    private double jacuzziRate;
    private String[] sentences = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?", 
            "Del 1 al 5, ¿cómo calificarías la comodidad?",
            "Del 1 al 5, ¿cómo calificarías la ubicación de la habitacíon?",
            "Del 1 al 5, ¿cómo calificarías la atención del personal?",
            "Del 1 al 5, ¿cómo calificarías la relación calidad/precio?",
            "Del 1 al 5, ¿cómo calificarías la funcionalidad de los servicios en la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el interior de la habitación?",
            "Del 1 al 5, ¿qué tan conforme está con el servicio de jacuzzi?"
        };

    public PremiumRoom(String roomName) {
        super(roomName);
        jacuzziRate = 0;
    }

    @Override
    public String makeRating(double[] ratings){
        cleaningRating(ratings[0]);
        comfortRating(ratings[1]);
        locationRating(ratings[2]);
        customerServiceRating(ratings[3]);
        customerServiceRating(ratings[4]);
        servicesRating(ratings[5]);
        interiorRating(ratings[6]);
        jacuzziRating(ratings[7]);
        generalRating();
        return "Entrega de habitación exitosa, la habitación: " + getRoomName() + " esta libre.";
    }

    @Override
    public void generalRating() {
        double temp = (getCleaningRate() + getComfortRate() + getLocationRate() + getCustomerServiceRate() + getQualityRate() + getServicesRate() + getInteriorRate() + jacuzziRate)/8;
        if (getGeneralRate() != 0) {
            setGeneralRate((temp+getGeneralRate())/2);
        }else{
            setGeneralRate(temp);
        }
    }

    public void jacuzziRating(double newInteriorRate){
        if (jacuzziRate != 0) {
            jacuzziRate = (jacuzziRate + newInteriorRate)/2;
        }else{
            jacuzziRate = newInteriorRate;
        }
    }
 
    public String[] getSentences() {
        return sentences;
    }

}