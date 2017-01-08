import org.json.JSONException;
import java.io.IOException;

/**
 * IGaze.java
 * @author Brad Huang
 * Nov 9, 2016
 * iGaze algorithm main method
 */
public class IGaze {
    public static void main(String[] args) throws IOException, JSONException{

        long startTime, midTime, endTime;

        startTime = System.currentTimeMillis();

        double lon = -79.33, lat = 43.90;
        Location location= new Location(lat, lon);

        midTime = System.currentTimeMillis();

        location.calcHourPoints();
        location.displayHourPoints();

        endTime = System.currentTimeMillis();

        System.out.print("\nAPI Loading = " + ((midTime - startTime)) + " milliseconds.\nCalculation = " + ((endTime / midTime)) + " milliseconds.");

    }
}