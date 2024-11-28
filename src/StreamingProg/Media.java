package StreamingProg;

public abstract class Media {
    //Initialising protected attributes
    protected String title;
    protected String genre;
    protected String releaseYear;
    protected String rating;

    //Constructor
    public Media(String title, String genre, String releaseYear, String rating) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters and Setters
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // Overriding toString for readability
    @Override
    public String toString() {
        return title + ";" + genre + ";" + releaseYear + ";" + rating;
    }

    // Abstract method to be implemented by subclasses
    public abstract void play();
}
