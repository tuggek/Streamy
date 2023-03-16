package Models;

import GUI.customSwing.Panel;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Model extends JPanel {

    private Media[] series;
    private Media[] films;
    private final ArrayList<String> categories;
    private final ArrayList<User> users;
    private User currentUser;
    private final Panel filmsPanel;
    private final Panel seriesPanel;
    private final Panel mediaPrePanel;
    private final Panel myListPanel;
    private final CardLayout cl;


    public Model() throws IOException {
        this.categories = new ArrayList<>();
        this.users = new ArrayList<>();
        this.addUser("Default User");
        this.changeCurrentUser("Default User");
        this.load("film");
        this.load("serier");

        cl = new CardLayout();
        this.setLayout(cl);

        filmsPanel = new Panel();
        seriesPanel = new Panel();
        mediaPrePanel = new Panel(); // using homemade JPanels, we make 3 of our 4 panels we are working with.
        myListPanel = new Panel();

        this.add(filmsPanel, "1");
        this.add(seriesPanel, "2");
        this.add(mediaPrePanel, "3");
        this.add(myListPanel, "4");
    }

    public void load(String mediaType) throws IOException, NumberFormatException {
        String fileName ="Data/" + mediaType + ".txt";
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();

        try{
        String[] lines = content.split("\\r?\\n");

        if (mediaType.equalsIgnoreCase("film")) {
            this.films = new Film[lines.length];
            for (int i = 0; i < lines.length; i++) {
                String[] values = lines[i].split(";");
                String title = values[0];
                values[1] = values[1].trim(); // L
                String releaseYear = values[1];
                String categories2 = values[2];
                values[3] = values[3].replaceAll(",", ".");
                double rating = Double.parseDouble(values[3]);

                films[i] = new Film(title, releaseYear, categories2, rating); // We now make a Film for each line, and put it in movies

                String[] categories3 = categories2.split(","); // Lastly, we split our String of all categories in the film, into an array of its categories
                for(int j = 0; j < categories3.length; j++) {
                    categories3[j] = categories3[j].strip();
                    if (!categories.contains(categories3[j])) {
                        categories.add(categories3[j]);
                    }
                }
            }
        } else if(mediaType.equalsIgnoreCase("serier")) {
            this.series = new Series[lines.length];
            for(int i = 0; i < lines.length; i++) {
                String[] values = lines[i].split(";");
                String title = values[0];

                values[1] = values[1].trim();
                String releaseYear = values[1];
                String categories2 = values[2];
                values[3] = values[3].replaceAll(",", ".");

                double rating = Double.parseDouble(values[3]);

                String seasonsAndEpisodes = values[4];
                series[i] = new Series(title, releaseYear, categories2, rating, seasonsAndEpisodes);

                String[] categories3 = categories2.split(",");
                for(int j = 0; j < categories3.length; j++) {
                    categories3[j] = categories3[j].strip();
                    if (!categories.contains(categories3[j])) {
                        categories.add(categories3[j]);
                    }
                }
            }
        }
    }catch(NumberFormatException e) {
            System.out.println("Please enter a number");
        }
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public Media[] getAllSeries() {
        return series;
    }

    public Media[] getAllFilms() {
        return films;
    }

    public Media getFilm(int i){
        return films[i];
    }

    public Media getSeries(int i){
        return series[i];

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }

    public User getUser(String name) {
        for (User user : users) {
            if (user.toString().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(String name) {
        User user = new User(name);
        users.add(user);
    }

    public void changeCurrentUser(String name) {
        for (User user : users) {
            if (user.toString().equalsIgnoreCase(name)) {
                currentUser = user;
            }
        }
    }

    public CardLayout getCardLayout(){
        return cl;
    }

    public Panel getFilmsPanel(){
        return filmsPanel;
    }

    public Panel getSeriesPanel(){
        return seriesPanel;
    }

    public Panel getMediaPrePanel(){
        return mediaPrePanel;
    }

    public Panel getMyListPanel(){
        return myListPanel;
    }
}
