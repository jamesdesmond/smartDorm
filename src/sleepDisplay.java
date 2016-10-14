import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;

/**
 * Created by james on 10/13/16.
 */
public class sleepDisplay implements LCDApps{
    @Override
    public String getName() {
        return "Turn off Backlight";
    }

    @Override
    public void run(ILCD ilcd) throws IOException {
        ilcd.setDisplayEnabled(false);
    }
}
