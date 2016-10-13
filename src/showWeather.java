import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import se.hirt.pi.adafruitlcd.ILCD;
import smartDorm.Enums;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by james on 9/24/16.
 */

public class showWeather implements LCDApps{
    public showWeather(){};
    //TODO: 5 day forecast
    private String getWeather () throws IOException {
        //Getting api responses
        String api = new String(Files.readAllBytes(Paths.get("api.txt")));
        api = api.substring(0,32);
        System.out.println("api: " + api + "length: " + api.length());
        ForecastIO fio = new ForecastIO(api);
        fio.setUnits(ForecastIO.UNITS_US);             //sets the units as SI - optional
        fio.getForecast("42.3605","-71.0596");
        FIOCurrently currently = new FIOCurrently(fio);
        FIODaily daily = new FIODaily(fio);
        String [] h = daily.getDay(Enums.WeatherInfo.TODAY.getIndex()).getFieldsArray();
        int hi = (int) Double.parseDouble(daily.getDay(Enums.WeatherInfo.TODAY.getIndex()).getByKey(h[Enums.WeatherInfo.HIGH_TEMP.getIndex()])); //high for the day
        int lo = (int) Double.parseDouble(daily.getDay(Enums.WeatherInfo.TODAY.getIndex()).getByKey(h[Enums.WeatherInfo.LOW_TEMP.getIndex()])); //low for the day
        int temp = currently.get().temperature().intValue();
        int rain = currently.get().precipProbability().intValue();
        //DEBUG
        System.out.println("hi: " + hi);
        System.out.println("lo: " + lo);
        System.out.println("temp: " + temp);
        System.out.println("rain: " + rain);
        //Building output
        String offsetTopRow = "";
        for (int i = 0; i < 3 - String.valueOf(temp).length();i++) {
            offsetTopRow = offsetTopRow + " ";
        }
        String offsetBottomRow = "";
        rain = rain * 100; //Make into percentage
        for (int i = 0; i < 3 - String.valueOf(rain).length(); i++) {
            offsetBottomRow = offsetBottomRow + " ";
        }
        offsetBottomRow = offsetBottomRow + "";
        return
                "NOW:" + temp + offsetTopRow + "   " + "HI:" + hi + "\n" +
                "RAIN:" + rain + "%" + offsetBottomRow + " " + "LO:" + lo;
    }

    @Override
    public String getName() {
        return "Weather";
    }

    @Override
    public void run(ILCD ilcd) throws IOException {
        //Setting up
        ilcd.clear();
        ilcd.setText("Loading...");
        ilcd.setText(getWeather());
    }
}