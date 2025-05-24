package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 18/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */

public class Averages {
    
    private final double[] VALUE_RATES = new double[] { 0.2, 0.35, 0.45 };


    public Averages(){
        
    }

    public double globalAverage(double[] rates) {
        double rateSum = 0;
        double average = 0;
        List<Integer> invalidIndex = new ArrayList<>();
        for (int i = 0; i < rates.length; i++) {
            if (rates[i] == 0) {
                invalidIndex.add(i);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (invalidIndex.size() == 0) {
                average += VALUE_RATES[i] * rates[i];

            } else if (invalidIndex.size() == 1) {
                rateSum = (VALUE_RATES[invalidIndex.get(0)]) / 2;
                average += (VALUE_RATES[i] + rateSum) * rates[i];
            } else if (invalidIndex.size() == 2) {
                average += rates[i];
            } else
                average = 0;
        }
        return average;
    }

    public double[] roomAverage(Room[][] rooms) {
        int count = 0;
        double[] generalRates = new double[rooms[0][0].getRatings().length];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getGeneralRate() != 0) {
                    double[] rates = rooms[i][j].getRatings();
                    generalRates = plusRates(generalRates, rates);
                    count++;
                }
            }
        }
        for (int i = 0; i < generalRates.length; i++) {
            generalRates[i] = generalRates[i] / count;
        }
        return generalRates;
    }

    public HashMap<String, Double> roomAverage2(String[] sentences, Room[][] rooms) {
        int count = 0;
        HashMap<String,Double> generalRates = new HashMap<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].getGeneralRate() != 0) {
                    double[] rates = rooms[i][j].getRatings();
                    for (int k = 0; k < rates.length; k++) {
                        if (generalRates.get(sentences[k]) == null) {
                            generalRates.put(sentences[k], rates[k]);
                        } else 
                            generalRates.put(sentences[k], generalRates.get(sentences[k]) + rates[k]);
                    }
                    count++;
                }
            }
        }
        for (int i = 0; i < generalRates.size(); i++) {
            generalRates.put(sentences[i], generalRates.get(sentences[i])/count);
        }
        return generalRates;
    }

    public double[] plusRates(double[] generalRates, double[] rates) {
        double[] localRates = new double[rates.length];
        for (int i = 0; i < rates.length; i++) {
            localRates[i] = generalRates[i] + rates[i];
        }
        return localRates;
    }
}
