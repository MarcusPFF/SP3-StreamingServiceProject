package StreamingProg;

public class Series extends Media {
    private int episodes;
    private int seasons;

    public Series(String title, String releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
    }

    // Getters and Setters for episodes and seasons
    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    @Override
    public void play() {
        System.out.println("Playing series: " + getTitle());
    }

    public void playEpisode(int episodeNumber) {
        if (episodeNumber <= 0 || episodeNumber > episodes) {
            System.out.println("Invalid episode number.");
        } else {
            System.out.println("Playing episode " + episodeNumber + " of series: " + getTitle());
        }
    }
}
