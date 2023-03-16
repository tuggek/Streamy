package Models;

import java.util.ArrayList;

public class User {
    private final String name;
    private final ArrayList<Media> myList;

    public User(String name){
        this.myList = new ArrayList<>();
        this.name = name;
    }

    public void addToMyList(Media media){
        myList.add(media);
    }

    public void removeFromMyList(Media media){
        myList.remove(media);
    }

    public boolean listContains(String title) {
        for(Media media : myList){
            if(media.getTitle().contains(title)) return true;
        }
        return false;
    }

    public ArrayList<Media> getMyList() {
        return myList;
    }

    public String toString(){
        return name;
    }
}
