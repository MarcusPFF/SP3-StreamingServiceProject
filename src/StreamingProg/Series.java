package StreamingProg;

import java.util.List;

public class Series extends Media {
    private int episodes;
    private int seasons;

    public Series(String title, String releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
    }

    public void playEpisode(int episodeNumber) {

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


