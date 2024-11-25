package StreamingProg;
import java.util.List;
import java.util.ArrayList;

public class Netflix {

    UserManager userManager;
    Menu menu;

    public Netflix(UserManager userManager, Menu menu) {
        this.userManager = userManager;
        this.menu = menu;
    }



    public void runApplication() {
        ArrayList<Media> Medias = new ArrayList<>();
        FileIO fileIO = new FileIO();
        List<String> movieData = fileIO.readMovieData("entertainmentData\\movies.txt");

            for (int i = 0; i < movieData.size(); i++){
                String [] values = movieData.get(i).split(";");
                String message = values[0];
                int income = Integer.parseInt(values[1].trim());//"100"
                int cost = Integer.parseInt(values[2].trim());

                String event = values[3].trim();
                int moveToPosition = Integer.parseInt(values[4].trim());

                Card c = new Card(message, income, cost, event, moveToPosition);

                cards[i] = c;
        }
    }
}
