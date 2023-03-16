package Models;

import java.util.*;
import java.util.ArrayList;


public class Media {
    private final String title;
    private final List<String> categories;
    private final String releaseYear;
    private final double rating;

    public Media(String title, String releaseYear, String categories1, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.categories = new ArrayList<>();
        String [] categories2 = categories1.split(",");
        categories.addAll(Arrays.asList(categories2));
    }


    public String getTitle(){
        return this.title;
    }

    public String getCategories(){
        String a = "";
        for (String string : categories){
            a = a + string;
        }
        return a;
    }

    public String getReleaseYear(){
        return releaseYear;
    }

    public double getRating(){
        return rating;
    }

    public String toString() {
        return "Title: " + getTitle() +
               "; Release Year: " + getReleaseYear() +
               "; Category: " + getCategories() +
               "; Rating: " + getRating();
    }
}
