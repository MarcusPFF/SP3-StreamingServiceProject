package StreamingProg;

public class Series extends Media {
    private int episodes;
    private int seasons;

    public Series(String title, String genre, int releaseYear, float rating, int episodes, int seasons) {
        super(title, genre, releaseYear, rating);
        this.episodes = episodes;
        this.seasons = seasons;
    }
}

    public void playEpisode(int episodeNumber){

    }
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
}
