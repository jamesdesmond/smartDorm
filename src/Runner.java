/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;
import smartDorm.Enums;

import java.io.IOException;


public class Runner {
    private  static final LCDApps[] APPS = new LCDApps[]{
            new showWeather(),new sendText(Enums.People.CARTER),new sendText(Enums.People.JAMES),new showIP()
    };
    public static void main(ILCD ilcd) throws IOException {
        int MAX_VALUE = 3;
        ilcd.setBacklight(Color.RED);
        ilcd.setText("This is the \n start screen");
        ilcd.clear();
        ilcd.setText(APPS[0].getName());
        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    int currentMenu = 0;
                    currentMenu = (currentMenu >= MAX_VALUE)?1:currentMenu;
                    currentMenu = (currentMenu <= 0)?MAX_VALUE:currentMenu;
                    switch (button) {
                        case RIGHT:
                            break;
                        case LEFT:
                            break;
                        case DOWN:
                            ilcd.clear();
                            System.out.println(currentMenu);
                            currentMenu++;
                            System.out.println(currentMenu + "DOWN");
                            currentMenu = currentMenu == MAX_VALUE?0:currentMenu; //JANKY WAY OF HANDLING BOUNDS
                            ilcd.clear();
                            ilcd.setText(currentMenu + ".)\n" + APPS[currentMenu].getName());
                            break;
                        case UP:
                            ilcd.clear();
                            System.out.println(currentMenu);
                            currentMenu--;
                            System.out.println(currentMenu + "UP");
                            currentMenu = currentMenu == 0?MAX_VALUE:currentMenu; //JANKY WAY OF HANDLING BOUNDS
                            ilcd.clear();
                            ilcd.setText(currentMenu + ".)\n" +APPS[currentMenu].getName());
                            break;
                        case SELECT:
                            APPS[currentMenu].run(ilcd);
                            break;
                        default:
                            ilcd.clear();
                            ilcd.setText("Huh, that isnt right");
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        while(true) {}
    }
    public static void main(String Args[]) throws IOException {
        final ILCD lcd = new RealLCD();
        main(lcd);
    }
}
