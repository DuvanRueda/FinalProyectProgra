package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 18/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class PremiumRoom extends VIPRoom {

    private double jacuzziRate;
    private final String[] SENTENCES = new String[] {
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

    public void jacuzziRating(double newInteriorRate) {
        if (jacuzziRate != 0) {
            jacuzziRate = (jacuzziRate + newInteriorRate) / 2;
        } else {
            jacuzziRate = newInteriorRate;
        }
    }

    @Override
    public String makeRating(double[] ratings) {
        cleaningRating(ratings[0]);
        comfortRating(ratings[1]);
        locationRating(ratings[2]);
        customerServiceRating(ratings[3]);
        qualityRating(ratings[4]);
        servicesRating(ratings[5]);
        interiorRating(ratings[6]);
        jacuzziRating(ratings[7]);
        generalRating();
        return "Entrega de habitación exitosa, la habitación: " + getRoomName() + " esta libre.";
    }

    @Override
    public void generalRating() {
        double temp = (getCleaningRate() + getComfortRate() + getLocationRate() + getCustomerServiceRate()
                + getQualityRate() + getServicesRate() + getInteriorRate() + jacuzziRate) / 8;
        if (getGeneralRate() != 0) {
            setGeneralRate((temp + getGeneralRate()) / 2);
        } else {
            setGeneralRate(temp);
        }
    }

    @Override
    public double[] getRatings(){
        return new double[] {getGeneralRate(), getCleaningRate(), getComfortRate(), getLocationRate(), getCustomerServiceRate(), getQualityRate(), getServicesRate(), getInteriorRate(), jacuzziRate};
    }

    @Override
    public String myToString() {
        return "Estadisticas cabaña " + getRoomName() +
                "\nLimpieza: " + String.format("%.1f",getCleaningRate()) + 
                "\nComodidad: " + String.format("%.1f",getComfortRate()) + 
                "\nUbicación de la habitación: " + String.format("%.1f",getLocationRate()) + 
                "\nAtención del personal: " + String.format("%.1f",getCustomerServiceRate()) + 
                "\nRelacion calidad/precio: " + String.format("%.1f",getQualityRate()) + 
                "\nFuncionabilidad de los servicios de la habitación: " + String.format("%.1f",getServicesRate()) +
                "\nInterior de la habitación: " + String.format("%.1f",getInteriorRate()) +
                "\nJacuzzi: " + String.format("%.1f",jacuzziRate)+ 
                "\nGeneral: " + String.format("%.1f",getGeneralRate());
    }

    public String[] getSENTENCES() {
        return SENTENCES;
    }

}