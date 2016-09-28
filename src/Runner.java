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
        showMenu(lcd,1);
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    int currentMenu = 1;
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
                            System.out.println(currentMenu);
                            currentMenu = currentMenu++;
                            System.out.println(currentMenu + "DOWN");
                            showMenu(lcd,currentMenu);
                            break;
                        case UP:
                            lcd.clear();
                            System.out.println(currentMenu);
                            currentMenu = currentMenu--;
                            System.out.println(currentMenu + "UP");
                            showMenu(lcd,currentMenu);
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
    private static void showMenu(ILCD ilcd, int i) throws IOException {
        switch (i) {
            case 0:
                showMenu(ilcd,4);
                System.out.println("Shifted to case 4.   case:0");
                break;
            case 1:
                ilcd.setText("Case 1:\nThe Weather");
                System.out.println("case:1");
                break;
            case 2:
                ilcd.setText("Case 2:\nText Carter");
                System.out.println("case:2");
                break;
            case 3:
                ilcd.setText("Case 3:\nText James");
                System.out.println("case:3");
                break;
            case 4:
                showIP.showIP(ilcd);
                System.out.println("case:4");
                break;
            case 5:
                showMenu(ilcd,1);
                System.out.println("Shifted to case 1. case:5");
        }
    }
}
