/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;

import java.io.IOException;


public class Runner {
    public static void main(String Args[]) throws IOException {
        final ILCD lcd = new RealLCD();
        lcd.setBacklight(Color.RED);
        lcd.setText("Use Up, Down, and the Select button to make a task selection");
        String address = Util.getLocalAddress().toString().substring(1);
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    switch (button) {
                        case RIGHT:
                            break;
                        case LEFT:
                            break;
                        case DOWN:
                            break;
                        case UP:
                            break;
                        case SELECT:
                            showIP.showIP(lcd);
                            break;
                        default:
                            lcd.clear();
                            lcd.setText("Huh, that isnt right");
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        lcd.stop();
        while(true){}
    }
}
