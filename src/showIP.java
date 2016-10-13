import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.ButtonListener;
import se.hirt.pi.adafruitlcd.ButtonPressedObserver;
import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;

/**
 * Created by james on 9/24/16.
 */
public class showIP implements LCDApps {
    public showIP(){};

    private  String getIP() throws IOException {
        return Util.getLocalAddress().toString().substring(1);

    }
    @Override
    public String getName() {
        return "Show IP Address";
    }
    @Override
    public void run (ILCD ilcd) throws IOException {
        ilcd.clear();
        ilcd.setText("IP Address:\n" + getIP());
    }
}