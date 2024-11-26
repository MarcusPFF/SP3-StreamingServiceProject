package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public abstract class Media {
    protected String title;
    protected String genre;
    protected String releaseYear;
    protected float rating;
    private
    FileIO io;
    TextUI ui;
    ArrayList<String> movieTags = (ArrayList<String>) io.readMovieData(io.getMoviesDataPath());

    public Media(String title, String releaseYear, String genre, float rating) {

        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public void movieTags() {
        for (int i = 0; i < movieTags.size(); i++) {
            String[] movie = movieTags.get(i).split(";");
            String title = movieTags.get(0);
            String releaseYear = movieTags.get(1);
            String genre = movieTags.get(2);
            float rating = Float.parseFloat(movieTags.get(3).replace(",", "."));
            movieTags.add(movieTags.get(4));
        }
    }

   /* public static List<Movie> searchMoviesByGenres(List<Movie> movies, List<String> genresToSearch) {
        List<Movie> matchingMovies = new ArrayList<>();

        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                if (genresToSearch.contains(genre)) {
                    matchingMovies.add(movie);
                    break; // Avoid adding the same movie multiple times
                }
            }
        }

        return matchingMovies;
    }
}    */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public void getMovieTags() {
        this.movieTags = (ArrayList<String>) io.readMovieData(io.getMoviesDataPath());
    }

    @Override
    public String toString() {
        return title;
    }
}

