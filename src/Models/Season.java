package Models;


public class Season {
    private final Episode[] episodes;
    private final int seasonNr;
    private final Media mySeries;

    public Season(int episodesAmount, int seasonNr, Media mySeries){
        episodes = new Episode[episodesAmount];
        this.seasonNr = seasonNr;
        this.mySeries = mySeries;
    }

    public void addEpisode(int episodeNr){
        episodes[episodeNr] = new Episode(episodeNr, this);
    }

    public String toString(){
        return "Season " + (seasonNr+1);
    }

    public Episode[] getEpisodes(){
        return episodes;
    }

    public Media getMySeries(){
        return mySeries;
    }
}
