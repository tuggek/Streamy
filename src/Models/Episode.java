package Models;

public class Episode implements Playable{
    private final int episodeNr;
    private final Season mySeason;

    public Episode(int episodeNr, Season mySeason){
        this.episodeNr = episodeNr;
        this.mySeason = mySeason;
    }

    public String toString(){
        return "Episode " + (episodeNr+1);
    }
    @Override

    public void play() {
        System.out.println("AAAHHHHH, DONT CLICK ME BRO, btw i am, " + mySeason.getMySeries().getTitle() +": " + mySeason.toString() + ", " + this + ", and im playing");
    }
}
