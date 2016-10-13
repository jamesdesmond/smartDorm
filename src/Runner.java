/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;

import java.io.IOException;


public class Runner {
    public static void menu(ILCD lcd) throws IOException {
        final int[] currentMenu = {1}; //There has got to be a better way
        lcd.setBacklight(Color.RED);
        lcd.setText("This is the \n start screen");
        showMenu(lcd,1);
        ButtonPressedObserver observer = new ButtonPressedObserver(lcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    currentMenu[0] = (currentMenu[0] >= 5)?1:currentMenu[0];
                    currentMenu[0] = (currentMenu[0] <= 0)?5:currentMenu[0];
                    switch (button) {
                        case RIGHT:
                            break;
                        case LEFT:
                            break;
                        case DOWN:
                            lcd.clear();
                            System.out.println(currentMenu[0]);
                            currentMenu[0]++;
                            System.out.println(currentMenu[0] + "DOWN");
                            currentMenu[0] = currentMenu[0] == 5?1:currentMenu[0]; //JANKY WAY OF HANDLING BOUNDS
                            showMenu(lcd, currentMenu[0]);
                            break;
                        case UP:
                            lcd.clear();
                            System.out.println(currentMenu[0]);
                            currentMenu[0]--;
                            System.out.println(currentMenu[0] + "UP");
                            currentMenu[0] = currentMenu[0] == 0?4:currentMenu[0]; //JANKY WAY OF HANDLING BOUNDS
                            showMenu(lcd, currentMenu[0]);
                            break;
                        case SELECT:
                            openSelected(lcd,currentMenu[0]);
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
    public static void main(String Args[]) throws IOException {
        final ILCD lcd = new RealLCD();
        menu(lcd);

    }
    private static void showMenu(ILCD ilcd, int i) throws IOException {
        switch (i) {
            case 0:
                showMenu(ilcd,4);
                System.out.println("Shifted to case 4.   case:0");
                break;
            case 1:
                ilcd.clear();
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
                ilcd.setText("Case 4:\nShow IP");
                System.out.println("case:4");
                break;
            case 5:
                showMenu(ilcd,1);
                System.out.println("Shifted to case 1. case:5");
        }
    }
    private static void openSelected(ILCD ilcd,int i) throws IOException {
        //TODO: Move the loading tips from here to sendText.java
        switch (i) {
            case 1:
                showWeather.showWeather(ilcd);
                break;
            case 2:
                ilcd.setText("Loading...");
                sendText.textCarter();
                ilcd.setText("Done.");
                break;
            case 3:
                ilcd.setText("Loading...");
                sendText.textJames();
                ilcd.setText("Done.");
                break;
            case 4:
                showIP.showIP(ilcd);
                break;
            default:
                System.out.println("openSelected() Error: failed number:" + i);
        }
    }
}
