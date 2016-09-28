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
        lcd.setText("This is the \n start screen");
        String address = Util.getLocalAddress().toString().substring(1);
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    switch (button) {
                        case RIGHT:
                            lcd.clear();
                            lcd.setText("RIGHT");
                            break;
                        case LEFT:
                            lcd.clear();
                            lcd.setText("LEFT");
                            break;
                        case DOWN:
                            lcd.clear();
                            lcd.setText("DOWN");
                            break;
                        case UP:
                            lcd.clear();
                            lcd.setText("UP");
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
        while(true){}
    }
}
