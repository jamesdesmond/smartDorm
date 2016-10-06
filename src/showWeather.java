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
import java.util.Scanner;

/**
 * Created by james on 9/24/16.
 */

public class showWeather {
    public static void showWeather (ILCD ilcd) throws IOException {
        //Test
        final int TODAY = 0;
        final int HIGH_TEMP = 1;
        final int LOW_TEMP = 18;
        ilcd.clear();
        ilcd.setText("Hey its the\nweather");
        String api = new String(Files.readAllBytes(Paths.get("api.txt")));
        api = api.substring(0,32);
        System.out.println("api: " + api + "length: " + api.length());
        //DEBUG
        for (int i = 0; i < api.length(); i++) {
            System.out.println(i + " : " + api.charAt(i));
        }
        //DEBUG
        ForecastIO fio = new ForecastIO(api);
        fio.setUnits(ForecastIO.UNITS_US);             //sets the units as SI - optional
        //fio.setExcludeURL("hourly,minutely");             //excluded the minutely and hourly reports from the reply
        fio.getForecast("42.3605","-71.0596");
        System.out.println(fio.hasCurrently());
        FIOCurrently currently = new FIOCurrently(fio);
        FIODaily daily = new FIODaily(fio);
        String [] h = daily.getDay(TODAY).getFieldsArray();
        String hi = daily.getDay(TODAY).getByKey(h[HIGH_TEMP]); //high for the day
        String lo = daily.getDay(TODAY).getByKey(h[LOW_TEMP]); //low for the day
        Double temp = currently.get().temperature();
        Double rain = currently.get().precipProbability();
        String offsetTopRow = "";
        for (int i = 0; i < 3 - String.valueOf(temp).length();i++) {
            offsetTopRow = offsetTopRow + " ";
        }
        String offsetBottomRow = "";
        rain = rain * 100; //Make into percentage
        for (int i = 0; i < 3 - String.valueOf(rain).length(); i++) {
            offsetBottomRow = offsetBottomRow + " ";
        }
        offsetBottomRow = offsetBottomRow + "%";
        ilcd.setText(
                "NOW:" + temp + offsetTopRow + "   " + "HI:" + hi +
                "RAIN:" + rain + offsetBottomRow + " " + "LO:" + lo
        );
        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    switch (button) {
                        case LEFT:
                            Runner.menu(ilcd);
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        while(true){}
    }
}

