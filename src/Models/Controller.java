package Models;


import GUI.customSwing.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller {

    private final Model model;

    public Controller(Model model){
        this.model = model;
        this.updateFilmsPanel("Category");
        this.updateSeriesPanel("Category");
        this.setCurrentState(1);
    }

    public void setCurrentState (int n) {
        String state = Integer.toString(n);
        if (n < 5 && n > 0) {
            model.getCardLayout().show(model, state);
        }else{
            System.out.println("State can only be 1 to 4");
        }
    }

    public void updateFilmsPanel(String category){
        if(!category.contains("Category"))
            System.out.println("Please enter a category");

        model.getFilmsPanel().removeAll();
        model.getFilmsPanel().revalidate();
        model.getFilmsPanel().repaint();

        int i = 0;
        int j = 0;

        for (Media media : model.getAllFilms()){
            if (media.getCategories().contains(category)){
                model.getFilmsPanel().add(new Button(this,media));
                i++;
                if(i == 8){
                    j++;
                    i = 0;
                }
            }
            else if (category.equals("Category")){
                model.getFilmsPanel().add(new Button(this,media));
                i++;
                if(i == 8){
                    j++;
                    i = 0;
                }
            }
        }

        model.getFilmsPanel().setPreferredSize(new Dimension(800,450*j));
    }

    public void updateSeriesPanel(String category){
        if(!category.contains("Category"))
            System.out.println("Please enter a category");

        model.getSeriesPanel().removeAll();
        model.getSeriesPanel().revalidate();
        model.getSeriesPanel().repaint();

        int i = 0;
        int j = 0;
        for (Media media : model.getAllSeries()){
            if (media.getCategories().contains(category)){
                model.getSeriesPanel().add(new Button(this,media));
                i++;
                if(i == 8){
                    j++;
                    i = 0;
                }
            }
            else if (category.equals("Category")){
                model.getSeriesPanel().add(new Button(this,media));
                i++;
                if(i == 8){
                    j++;
                    i = 0;
                }
            }
        }

        model.getSeriesPanel().setPreferredSize(new Dimension(800,450*j));
    }

    public void updateMediaPrePanel(Media media){
        if(media.toString().isEmpty())
            System.out.println("Media not found");

        model.getMediaPrePanel().removeAll();
        model.getMediaPrePanel().revalidate();
        model.getMediaPrePanel().repaint();

        JButton playButton = new JButton();
        ImageIcon icon = new ImageIcon("images/" + media.getTitle() + ".jpg");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(250, 400, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);  // transform it back
        playButton.setIcon(icon);
        playButton.setPreferredSize(new Dimension(250, 400));
        playButton.setBackground(Color.black);
        if (media.getClass() == Film.class) {
            Playable player = (Playable) media;
            playButton.addActionListener(e -> player.play());
        }
            model.getMediaPrePanel().add(playButton);

        if(!model.getCurrentUser().listContains(media.getTitle())) {
            JButton addToMyList = new JButton("Add To My List");
            addToMyList.setFont(new Font("Arial", Font.BOLD, 25));
            addToMyList.setBackground(Color.black);
            addToMyList.setForeground(Color.white);
            addToMyList.setFocusable(false);
            addToMyList.setBorder(BorderFactory.createEtchedBorder());
            addToMyList.addActionListener(e -> {
                if(!model.getCurrentUser().listContains(media.getTitle())) {
                    model.getCurrentUser().addToMyList(media);
                }
            });
            model.getMediaPrePanel().add(addToMyList);
        }else {
            JButton removeFromMyList = new JButton("Remove from my list");
            removeFromMyList.setFont(new Font("Arial", Font.BOLD, 15));
            removeFromMyList.setBackground(Color.black);
            removeFromMyList.setForeground(Color.white);
            removeFromMyList.setFocusable(false);
            removeFromMyList.setBorder(BorderFactory.createEtchedBorder());
            removeFromMyList.addActionListener(e -> model.getCurrentUser().removeFromMyList(media));
            model.getMediaPrePanel().add(removeFromMyList);
        }

        JTextArea info = new JTextArea(media.getCategories()+"; Released in:"+media.getReleaseYear() + ", Rating: " + media.getRating());
        info.setFont(new Font("Arial", Font.BOLD, 15));
        info.setBackground(Color.black);
        info.setForeground(Color.white);
        model.getMediaPrePanel().add(info);

        if (media.getClass() == Series.class){
            Season[] a = ((Series) media).getSeasons();
            JComboBox seasons = new JComboBox(a);
            JComboBox episodes = new JComboBox();

            seasons.setFont(new Font("Arial", Font.BOLD, 15));
            seasons.setBackground(Color.black);
            seasons.setForeground(Color.white);
            seasons.setFocusable(false);
            seasons.setBorder(BorderFactory.createEtchedBorder());
            seasons.addActionListener(e -> {
                Season currentSeason = (Season) seasons.getSelectedItem();
                Episode[] b = currentSeason.getEpisodes();
                episodes.setModel(new DefaultComboBoxModel(b));
            });

            episodes.setFont(new Font("Arial", Font.BOLD, 15));
            episodes.setBackground(Color.black);
            episodes.setForeground(Color.white);
            episodes.setFocusable(false);
            episodes.setBorder(BorderFactory.createEtchedBorder());
            episodes.addActionListener(e -> {
                Playable player = (Playable) episodes.getSelectedItem();
                playButton.addActionListener(e1 -> player.play());
            });

            model.getMediaPrePanel().add(seasons);
            model.getMediaPrePanel().add(episodes);
        }
    }

    public void updateMyListPanel(){
        model.getMyListPanel().removeAll();
        model.getMyListPanel().revalidate();
        model.getMyListPanel().repaint();

        int i = 0;
        int j = 0;
        for (Media media : model.getCurrentUser().getMyList()){
            JButton b = new Button(this, media);

            model.getMyListPanel().add(b);
            model.getMyListPanel().add(Box.createRigidArea(new Dimension(20, 0)));
            i++;
            if (i == 8){
                j++;
                i  = 0;
            }
        }
        model.getMyListPanel().setPreferredSize(new Dimension(800,450*j));
        model.getMyListPanel().add(Box.createRigidArea(new Dimension(0, 50)));
    }

    public void changeCurrentUser(String name){
        for (User user : model.getUsers()) {
            if (user.toString().equalsIgnoreCase(name)) {
                model.setCurrentUser(user);
            }
        }
    }

    public void addUser(String name){
        if(name.isEmpty()) {
            System.out.println("Please create a User");
        }
        User user = new User(name);
        model.getUsers().add(user);
    }

    public User getUser(String name){
        return model.getUser(name);
    }

    public ArrayList<String> getCategories(){
        return model.getCategories();
    }

    public void keyPressed(KeyEvent e, String string) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Hi from KeyListener");
            int k = 0;
            for (int i = 0; i < model.getAllFilms().length; i++) {
                if ((string.toLowerCase()).contains((model.getFilm(i).getTitle()).toLowerCase())) {
                    this.updateMediaPrePanel(model.getFilm(i));
                    this.setCurrentState(3);
                    break;
                }
                if (i >= model.getAllFilms().length-1){
                    k++;
                }

            }

            for (int i = 0; i < model.getAllSeries().length; i++) {
                if ((string.toLowerCase()).contains((model.getSeries(i).getTitle()).toLowerCase())) {
                    this.updateMediaPrePanel(model.getSeries(i));
                    this.setCurrentState(3);
                    break;
                }

                if (i >= model.getAllSeries().length-1){
                    k++;
                }
            }
            if (k > 1){
                JOptionPane.showMessageDialog(null, "Nothing Found");
            }
        }
    }
}
