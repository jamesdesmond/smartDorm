import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.ButtonListener;
import se.hirt.pi.adafruitlcd.ButtonPressedObserver;
import se.hirt.pi.adafruitlcd.ILCD;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/**
 * Created by james on 9/24/16.
 */

public class showWeather {
    public static void showWeather (ILCD ilcd) throws IOException {
        //Constants
        final int TODAY = 0;
        final int HIGH_TEMP = 1;
        final int LOW_TEMP = 18;
        //Setting up
        ilcd.clear();
        ilcd.setText("Loading...");
        //Getting api responses
        String api = new String(Files.readAllBytes(Paths.get("api.txt")));
        api = api.substring(0,32);
        System.out.println("api: " + api + "length: " + api.length());
        ForecastIO fio = new ForecastIO(api);
        fio.setUnits(ForecastIO.UNITS_US);             //sets the units as SI - optional
        fio.getForecast("42.3605","-71.0596");
        FIOCurrently currently = new FIOCurrently(fio);
        FIODaily daily = new FIODaily(fio);
        String [] h = daily.getDay(TODAY).getFieldsArray();
        int hi = (int) Double.parseDouble(daily.getDay(TODAY).getByKey(h[HIGH_TEMP])); //high for the day
        int lo = (int) Double.parseDouble(daily.getDay(TODAY).getByKey(h[LOW_TEMP])); //low for the day
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
        ilcd.setText(
                "NOW:" + temp + offsetTopRow + "   " + "HI:" + hi + "\n" +
                "RAIN:" + rain + "%" + offsetBottomRow + " " + "LO:" + lo
        );
    }
}