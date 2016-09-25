import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;

/**
 * Created by james on 9/24/16.
 */
public class showIP {
    public static void showIP(ILCD ilcd) throws IOException {
        String address = Util.getLocalAddress().toString().substring(1);
        ilcd.clear();
        System.out.println("select case");
        ilcd.setText("IP Address:\n" + address);
    }
}
