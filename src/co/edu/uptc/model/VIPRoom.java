package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 18/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class VIPRoom extends Room {

    private double interiorRate;
    private final String[] SENTENCES = new String[] {
            "Del 1 al 5, ¿cómo calificarías la limpieza?", 
            "Del 1 al 5, ¿cómo calificarías la comodidad?",
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

    public void interiorRating(double newInteriorRate){
        if (interiorRate != 0) {
            interiorRate = (interiorRate+newInteriorRate)/2;
        }else{
            interiorRate = newInteriorRate;
        }
    }

    @Override
    public String makeRating(double[] ratings){
        cleaningRating(ratings[0]);
        comfortRating(ratings[1]);
        locationRating(ratings[2]);
        customerServiceRating(ratings[3]);
        qualityRating(ratings[4]);
        servicesRating(ratings[5]);
        interiorRating(ratings[6]);
        generalRating();
        return "Entrega de habitación exitosa, la habitación: " + getRoomName() + " esta libre.";
    }

    @Override
    public void generalRating() {
        double temp = (getCleaningRate() + getComfortRate() + getLocationRate() + getCustomerServiceRate() + getQualityRate() + getServicesRate() + getInteriorRate())/7;
        if (getGeneralRate() != 0) {
            setGeneralRate((temp+getGeneralRate())/2);
        }
        setGeneralRate(temp);
    }

    @Override
    public double[] getRatings(){
        return new double[] {getGeneralRate(), getCleaningRate(), getComfortRate(), getLocationRate(), getCustomerServiceRate(), getQualityRate(), getServicesRate(), interiorRate};
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
                "\nInterior de la habitación: " + String.format("%.1f",interiorRate) + 
                "\nGeneral: " + String.format("%.1f",getGeneralRate());
    }

    public String[] getSENTENCES() {
        return SENTENCES;
    }

    public double getInteriorRate() {
        return interiorRate;
    }

    public void setInteriorRate(double interiorRate) {
        this.interiorRate = interiorRate;
    }

}
