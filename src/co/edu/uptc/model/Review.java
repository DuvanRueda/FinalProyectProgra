package co.edu.uptc.model;

import java.util.ArrayList;

public class Review {
    
    private ArrayList<String> reviews;

    public Review(){
        reviews = new ArrayList<>();
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public String showReviews() {
        String reseulString = "";
        for (String data : reviews) {
            reseulString += data + "\n";
        }
        return reseulString;
    }
}
