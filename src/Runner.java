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
        lcd.setText("Hello World");
        String address = Util.getLocalAddress().toString().substring(1);
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    switch (button) {
                        case UP:
                            lcd.clear();
                            System.out.println("up case");
                            lcd.setText("Nice");
                            break;
                        case SELECT:
                            lcd.clear();
                            System.out.println("select case");
                            lcd.setText(address);
                            break;
                        default:
                            lcd.clear();
                            System.out.println("default case");
                            lcd.setText("Huh, that isnt right");
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Press enter to quit!");
        System.in.read();
        lcd.stop();
        while(true){}
        }
    private void handleException(IOException e) {
        System.out.println("Problem connecting to lcd");
        e.printStackTrace();
        System.exit(-2);
    }
}
