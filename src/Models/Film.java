package Models;

public class Film extends Media implements Playable {


    public Film(String title, String releaseYear, String categories1, double rating) {
        super(title, releaseYear, categories1, rating);
    }

    @Override
    public void play() {
        System.out.println("AAAHHHHH, DONT CLICK ME BRO, btw i am, " + this.getTitle()+", and im playing");
    }
}
