import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.Color;
import se.hirt.pi.adafruitlcd.ILCD;
import smartDorm.LCDApps;

import java.io.IOException;

/**
 * Created by james on 10/26/16.
 */
public class Sleep implements LCDApps {
    @Override
    public String getName() {
        return "Sleep Display";
    }

    @Override
    public void run(ILCD ilcd, Button button) throws IOException {
        if(button == Button.SELECT) {
            ilcd.setBacklight(Color.OFF);
        }
    }

    @Override
    public void run(ILCD ilcd) throws IOException {
        ilcd.setText("Press Select to\nSleep Display");
    }
}
