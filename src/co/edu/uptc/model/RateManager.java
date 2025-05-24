package co.edu.uptc.model;
import java.util.ArrayList;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 20/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RateManager {
    private HashMap<String, Double> rates;

    public RateManager(int size, String[] keys) {
        rates = new HashMap<>();
        initMap(size, keys);
    }

    
    public String showRates() {
        String resultProcess = "";
        for (Map.Entry<String, Double> input : makeRanking().entrySet()) {
            String key = input.getKey();
            double value = input.getValue();
            resultProcess += key + " = " + String.format("%.1f", value) + "\n";
        }
        return resultProcess;
    }

    public LinkedHashMap<String, Double> makeRanking() {
        Map.Entry<String, Double> generalEntry = null;
        ArrayList<Map.Entry<String, Double>> localList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            if (entry.getKey().equals("GENERAL")) {
                generalEntry = entry;
            } else
                localList.add(entry);
        }
        return sortRanking(generalEntry, localList);
    }

    public LinkedHashMap<String, Double> sortRanking(Map.Entry<String, Double> generalEntry, ArrayList<Map.Entry<String, Double>> ranking) {
        for (int i = 0; i < ranking.size() - 1; i++) {
            for (int j = 0; j < ranking.size() - i - 1; j++) {
                Map.Entry<String, Double> current = ranking.get(j);
                Map.Entry<String, Double> next = ranking.get(j + 1);

                if (current.getValue() < next.getValue()) {
                    ranking.remove(j + 1);
                    ranking.remove(j);
                    ranking.add(j, next);
                    ranking.add(j + 1, current);
                }
            }
        }
        LinkedHashMap<String, Double> localRanking = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : ranking) {
            localRanking.put(entry.getKey(), entry.getValue());
        }
        localRanking.put(generalEntry.getKey(), generalEntry.getValue());
        return localRanking;
    }

    private void initMap(int size, String[] keys) {
        for (int i = 0; i < size; i++) {
            rates.put(keys[i], 0.0);
        }
    }

    public void setRate(String category, double value) {
        rates.put(category, value);
    }

    public void setAllRates(HashMap<String, Double> rates){
        this.rates = rates;
    }

    public Double getRate(String category) {
        return rates.get(category);
    }

    public HashMap<String, Double> getAllRates() {
        return rates;
    }

    public void clearRates() {
        rates.clear();
    }
}
