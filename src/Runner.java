/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;

import java.io.IOException;
import java.net.InetAddress;

public class Runner {
    public static void main(String Args[]) throws IOException {
        InetAddress IP = InetAddress.getLocalHost();
        final ILCD lcd = new RealLCD();
        lcd.setBacklight(Color.RED);
        lcd.setText("Hello World");
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                switch (button) {
                    case SELECT:
                        try {
                            lcd.setText(IP.getHostAddress());
                        } catch (IOException e) {
                            System.out.println("ERROR 01");
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void handleException(IOException e) {
        System.out.println("Problem connecting to lcd");
        e.printStackTrace();
        System.exit(-2);
    }
}
