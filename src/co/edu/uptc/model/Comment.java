package co.edu.uptc.model;

import java.util.ArrayList;
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 18/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
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

    public boolean isEmpty() {
        return reviews.isEmpty();
    }
}
