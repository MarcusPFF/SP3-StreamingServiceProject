package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    Netflix netflix = new Netflix();

    netflix.runApplication();
    List<String> result = netflix.getMovieData();
        System.out.println(result);
    }
}