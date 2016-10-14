/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;
import smartDorm.Enums;

import java.io.IOException;


public class Runner {
    private  static final LCDApps[] APPS = new LCDApps[]{
            new showWeather(),new sendText(Enums.People.CARTER),new sendText(Enums.People.JAMES),//new showIP(),new sleepDisplay()
    };
    public static void main(ILCD ilcd) throws IOException {
        int[] currentMenu = {0};
        ilcd.setBacklight(Color.RED);
        ilcd.setText("This is the \n start screen");
        ilcd.clear();
        ilcd.setText(1 + ".)\n" +APPS[0].getName());
        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
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
                            ilcd.clear();
                            System.out.println("Down: " + currentMenu[0]);
                            currentMenu[0] = currentMenu[0] + 1;
                            System.out.println("current menu is now: " + currentMenu[0]);
                            //System.out.println(currentMenu + "DOWN");
                            //currentMenu = currentMenu == MAX_VALUE?0:currentMenu; //JANKY WAY OF HANDLING BOUNDS
                            if (currentMenu[0] > APPS.length -1) {
                                currentMenu[0] = 0;
                                System.out.println("currentMenu was equal to 4, now equal to 0");
                            }
                            ilcd.clear();
                            ilcd.setText(currentMenu[0] + 1 + ".)\n" + APPS[currentMenu[0]].getName());
                            System.out.println("DONE WITH DOWN CASE");
                            break;
                        case UP:
                            ilcd.clear();
                            System.out.println("Up: " + currentMenu[0]);
                            currentMenu[0]--;
                            System.out.println("current menu is now: " + currentMenu[0]);
                            //System.out.println(currentMenu + "UP");
                            //currentMenu = currentMenu == 0?MAX_VALUE:currentMenu; //JANKY WAY OF HANDLING BOUNDS
                            if (currentMenu[0] < 0) {
                                currentMenu[0] = APPS.length -1;
                                System.out.println("currentMenu was equal to -1, now equal to 3");
                            }
                            ilcd.clear();
                            ilcd.setText(currentMenu[0] + 1 + ".)\n" +APPS[currentMenu[0]].getName());
                            System.out.println("DONE WITH UP CASE");
                            break;
                        case SELECT:
                            APPS[currentMenu[0]].run(ilcd);
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

    }
}