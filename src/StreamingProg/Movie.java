package StreamingProg;

public class Movie extends Media {


    public Movie(String title, String releaseYear, String genre, float rating) {
        super(title, releaseYear, genre, rating);
    }

    public void playMovie() {
        System.out.println(getTitle() + " afspilles nu...");
    }
}


