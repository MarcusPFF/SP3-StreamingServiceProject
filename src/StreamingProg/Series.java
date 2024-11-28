package StreamingProg;

public class Series extends Media {
    //Initialising private attributes
    private int seasonsAndEpisodes;

    //Constructor
    public Series(String title, String genre, String releaseYear, String rating, String seasonsAndEpisodes) {
        super(title, genre, releaseYear, rating);

    }

    // Getters and Setters for episodes and seasons
    public int getSeasonsAndEpisodes() {
        return seasonsAndEpisodes;
    }

    public void setSeasonsAndEpisodes(int episodes) {
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }


    //This method plays and displays the current title
    @Override
    public void play() {
        System.out.println("Spiller serien: " + getTitle());
    }

    public void playEpisode(int episodeNumber) {
        if (episodeNumber <= 0 || episodeNumber > episodeNumber) {
            System.out.println("Ugyldigt episode nummer: ");
        } else {
            System.out.println("Spiller episode " + episodeNumber + " i serien " + getTitle());
        }
    }
}
