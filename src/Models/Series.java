package Models;


public class Series extends Media {

    private final Season[] seasons;

    public Series(String title, String releaseYear, String categories1, double rating, String seasonsAndEpisodes){
        super(title, releaseYear, categories1, rating);

        String [] seasonsAndEpisodes2 = seasonsAndEpisodes.split(",");
        seasons = new Season [seasonsAndEpisodes2.length];

        for (int j  = 0; j < seasonsAndEpisodes2.length; j++ ){
            int a = seasonsAndEpisodes2[j].indexOf("-") + 1;
            String b = seasonsAndEpisodes2[j].substring(a);
            int c = Integer.parseInt(b);
            seasons[j] = new Season(c,j,this);
            for(int k = 0; k < c; k++){
                seasons[j].addEpisode(k);
            }
        }
    }

    public Season[] getSeasons(){
        return seasons;
    }
}
 