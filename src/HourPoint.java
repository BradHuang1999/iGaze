import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Brad Huang on 9/25/2016.
 */
public class HourPoint {
    private Date time;
    private String summary;
    private double precipIntensity, precipProbability, temperature, humidity, windSpeed, visibility, cloudCover;

    public HourPoint(Date time,
                     String summary,
                     double precipIntensity,
                     double precipProbability,
                     double temperature,
                     double humidity,
                     double windSpeed,
                     double visibility,
                     double cloudCover){

        this.time = time;
        this.summary = summary;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.cloudCover = cloudCover;
    }

    public String formatTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a, MMM dd yyyy");
        return dateFormat.format(this.time);
    }

    public String getSummary(){
        return summary;
    }

    public double getPrecipIntensity(){
        return precipIntensity;
    }

    public double getPrecipProbability(){
        return precipProbability;
    }

    public double getTemperature(){
        return temperature;
    }

    public double getHumidity(){
        return humidity;
    }

    public double getWindSpeed(){
        return windSpeed;
    }

    public double getVisibility(){
        return visibility;
    }

    public double getCloudCover(){
        return cloudCover;
    }

}
