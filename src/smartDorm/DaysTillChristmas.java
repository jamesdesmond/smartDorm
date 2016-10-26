package smartDorm;

import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by james on 10/25/16.
 */
public class DaysTillChristmas implements LCDApps {
    @Override
    public String getName() {
        return "Days Till\nChristmas";
    }

    @Override
    public void run(ILCD ilcd, Button button) throws IOException {
        ilcd.clear();
        ilcd.setText(daysTillChristmas() +  " days until\nChristmas");
    }

    @Override
    public void run(ILCD ilcd) throws IOException {
        ilcd.clear();
        ilcd.setText(daysTillChristmas() +  " days until\nChristmas");
    }

    private String daysTillChristmas() {
        String days,hours,minutes,seconds;
        String frontPadding = "  ";
        String backPadding = "  ";

        days = String.valueOf(getDaysTillChristmas(TimeUnit.DAYS));
        hours = String.valueOf(getDaysTillChristmas(TimeUnit.HOURS));
        minutes = String.valueOf(getDaysTillChristmas(TimeUnit.MINUTES));
        seconds = String.valueOf(getDaysTillChristmas(TimeUnit.SECONDS));

        for(int i = 3; i > days.length();i--) {
            frontPadding += " ";
        }

        return frontPadding + days + ":" + hours + ":" + minutes + ":" + seconds + backPadding;
    }

    @SuppressWarnings("deprecation")
    private long getDaysTillChristmas(TimeUnit timeUnit){
        Date christmas = null;
        try {
            christmas = new Date(String.valueOf(new SimpleDateFormat("MM/dd/yyyy").parse("12/25/2016")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        long diff = Long.parseLong(christmas.toString()) - Long.parseLong(today.toString());
        return timeUnit.convert(diff,timeUnit);
    }
}
