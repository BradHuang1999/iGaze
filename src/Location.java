import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Brad Huang on 11/8/2016.
 */
public class Location{

    private double lon, lat;
    private JSONObject darkSkyData, sevenTimerData;

    private HourPoint[] hourPoints = new HourPoint[49];

    public Location(double lon, double lat) throws IOException, JSONException{

        this.lon = lon;
        this.lat = lat;

        URL darkSkyURL = new URL("https://api.darksky.net/forecast/a1f55906a27742eca737ff7f9bbedee4/" + String.valueOf(lon) + "," + String.valueOf(lat));
        Scanner darkSkyIn = new Scanner(darkSkyURL.openStream());
        this.darkSkyData = new JSONObject(darkSkyIn.nextLine());

        URL sevenTimerURL = new URL("http://202.127.24.18/bin/astro.php?lon=" + String.valueOf(lon) + "&lat=" + String.valueOf(lat) + "&ac=0&unit=metric&output=json&tzshift=0");
        Scanner sevenTimerIn = new Scanner(sevenTimerURL.openStream());
        String sevenTimerJson = "";
        while(sevenTimerIn.hasNextLine()) {
            sevenTimerJson += sevenTimerIn.nextLine();
        }
        this.sevenTimerData = new JSONObject(sevenTimerJson);
    }

    public void calcHourPoints() throws JSONException, FileNotFoundException{

        JSONObject darkSkyHourly = this.darkSkyData.getJSONObject("hourly");
        JSONArray darkSkyHourlyArray = darkSkyHourly.getJSONArray("data");
        JSONObject darkSkyHourlyObj;
        double visibility;

        for(int i = 0 ; i < 49 ; i++){
            darkSkyHourlyObj = darkSkyHourlyArray.getJSONObject(i);

            try{
                visibility = Double.parseDouble(darkSkyHourlyObj.getString("visibility"));
            } catch (NullPointerException e){
                visibility = 10;
            }

            this.hourPoints[i] = new HourPoint(
                    new Date (Long.parseLong(darkSkyHourlyObj.getString("time")) * 1000),
                    darkSkyHourlyObj.getString("summary"),
                    Double.parseDouble(darkSkyHourlyObj.getString("precipIntensity")),
                    Double.parseDouble(darkSkyHourlyObj.getString("precipProbability")),
                    Double.parseDouble(darkSkyHourlyObj.getString("temperature")),
                    Double.parseDouble(darkSkyHourlyObj.getString("humidity")),
                    Double.parseDouble(darkSkyHourlyObj.getString("windSpeed")),
                    visibility,
                    Double.parseDouble(darkSkyHourlyObj.getString("cloudCover"))
            );

        }

        JSONArray sevenTimerArray = this.sevenTimerData.getJSONArray("dataseries");
        System.out.println(sevenTimerData.getString("init"));

    }

    public void displayHourPoints(){

        for(int i = 0 ; i < 49 ; i++){
            System.out.print("Time = " + this.hourPoints[i].formatTime() + " ");
            System.out.print("summary = " + this.hourPoints[i].getSummary() + " ");
            System.out.print("precipIntensity = " + this.hourPoints[i].getPrecipIntensity() + " ");
            System.out.print("precipProbability = " + this.hourPoints[i].getPrecipProbability() + " ");
            System.out.print("temperature = " + this.hourPoints[i].getTemperature() + " ");
            System.out.print("humidity = " + this.hourPoints[i].getHumidity() + " ");
            System.out.print("windSpeed = " + this.hourPoints[i].getWindSpeed() + " ");
            System.out.print("visibility = " + this.hourPoints[i].getVisibility() + " ");
            System.out.print("cloudCover = " + this.hourPoints[i].getCloudCover() + "\n");
        }

    }

}
