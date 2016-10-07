import se.hirt.pi.adafruitlcd.Button;
import se.hirt.pi.adafruitlcd.ButtonListener;
import se.hirt.pi.adafruitlcd.ButtonPressedObserver;
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
//        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
//        observer.addButtonListener(new ButtonListener() {
//            @Override
//            public void onButtonPressed(Button button) {
//                try {
//                    switch (button) {
//                        case LEFT:
//                            Runner.menu(ilcd);
//                            break;
//                        default:
//                            break;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        while(true){}
    }
}
