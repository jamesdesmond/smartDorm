/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.Color;
import se.hirt.pi.adafruitlcd.ILCD;
import se.hirt.pi.adafruitlcd.impl.RealLCD;

import java.io.IOException;

public class Runner {
    public static void main(String Args[]) throws IOException {
        final ILCD lcd = new RealLCD();
        lcd.setBacklight(Color.RED);
        lcd.setText("Hello World");
    }
}
