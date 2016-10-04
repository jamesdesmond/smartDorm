import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.oracle.tools.packager.IOUtils;
import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.ButtonListener;
import se.hirt.pi.adafruitlcd.ButtonPressedObserver;
import se.hirt.pi.adafruitlcd.ILCD;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by james on 9/24/16.
 */
public class showWeather {
    public static void showWeather (ILCD ilcd) throws IOException {
        ilcd.clear();
        ilcd.setText("Hey its the weather");
        FileInputStream inputStream = new FileInputStream("api.txt");
        String api = inputStream.toString();
        ForecastIO fio = new ForecastIO(api);
        fio.setUnits(ForecastIO.UNITS_US);             //sets the units as SI - optional
        fio.setExcludeURL("hourly,minutely");             //excluded the minutely and hourly reports from the reply
        fio.getForecast("42.3605","-71.0596");

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

