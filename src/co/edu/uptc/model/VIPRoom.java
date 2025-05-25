package co.edu.uptc.model;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 18/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class VIPRoom extends NormalRoom {

    protected double interiorRate;
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
        return "Entrega de habitación exitosa, la habitación: " + roomName + " esta libre.";
    }

    @Override
    public void generalRating() {
        double temp = (cleaningRate + comfortRate + locationRate + customerServiceRate + qualityRate + servicesRate + interiorRate)/7;
        if (generalRate != 0) {
            generalRate = (temp + generalRate)/2;
        }
        generalRate = temp;
    }

    @Override
    public double[] getRatings(){
        return new double[] {generalRate, cleaningRate, comfortRate, locationRate, customerServiceRate, qualityRate, servicesRate, interiorRate};
    }

    @Override
    public String myToString() {
        return "Estadisticas cabaña " + roomName +
                "\nLimpieza: " + String.format("%.1f",cleaningRate) + 
                "\nComodidad: " + String.format("%.1f",comfortRate) + 
                "\nUbicación de la habitación: " + String.format("%.1f",locationRate) + 
                "\nAtención del personal: " + String.format("%.1f",customerServiceRate) + 
                "\nRelacion calidad/precio: " + String.format("%.1f",qualityRate) + 
                "\nFuncionabilidad de los servicios de la habitación: " + String.format("%.1f",servicesRate) +
                "\nInterior de la habitación: " + String.format("%.1f",interiorRate) + 
                "\nGeneral: " + String.format("%.1f",generalRate);
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
