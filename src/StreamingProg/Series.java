package StreamingProg;

import java.util.List;

public class Series extends Media {
    private int episodes;
    private int seasons;

    public Series(String username, List<String> mediaList, String title, String genre, int releaseYear, float rating, int episodes, int seasons) {
        super(username, mediaList, title, genre, releaseYear, rating);
        this.episodes = episodes;
        this.seasons = seasons;
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


