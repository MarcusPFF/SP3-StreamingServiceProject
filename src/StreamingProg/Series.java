package StreamingProg;

public class Series extends Media {
    private int seasonsAndEpisodes;


    public Series(String title, String releaseYear, String genre, String rating, String seasonsAndEpisodes) {
        super(title, releaseYear, genre, rating);

    }

    // Getters and Setters for episodes and seasons
    public int getSeasonsAndEpisodes() {
        return seasonsAndEpisodes; //Fix
    }

    public void setSeasonsAndEpisodes(int episodes) {
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }


    @Override
    public void play() {
        System.out.println("Spiller series: " + getTitle());
    }

    public void playEpisode(int episodeNumber) {
        if (episodeNumber <= 0 || episodeNumber > episodeNumber) {
            System.out.println("Ugyldigt episode nummer: ");
        } else {
            System.out.println("Spiller episode " + episodeNumber + " i serien " + getTitle());
        }
    }
}
