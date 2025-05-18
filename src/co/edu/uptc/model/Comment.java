package co.edu.uptc.model;

import java.util.ArrayList;

public class Comment {
    
    private ArrayList<String> reviews;

    public Comment(){
        reviews = new ArrayList<>();
    }

    public void addComment(String review) {
        reviews.add(review);
    }

    public String showComments() {
        String reseulString = "";
        for (String data : reviews) {
            reseulString += data + "\n";
        }
        return reseulString;
    }
}
