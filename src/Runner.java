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
        int[] currentMenu = {0};
        ilcd.setBacklight(Color.RED);
        ilcd.setText("This is the \n start screen");
        ilcd.clear();
        ilcd.setText(1 + ".)\n" +APPS[0].getName());

        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);

        final boolean[] inApp = {false};

        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {

                    if (!inApp[0]) {
                        switch (button) {
                            case DOWN:
                                ilcd.clear();
                                System.out.println("Down: " + currentMenu[0]);
                                currentMenu[0] = currentMenu[0] + 1;
                                System.out.println("current menu is now: " + currentMenu[0]);
                                if (currentMenu[0] > APPS.length - 1) {
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
                                if (currentMenu[0] < 0) {
                                    currentMenu[0] = APPS.length - 1;
                                    System.out.println("currentMenu was equal to -1, now equal to 3");
                                }
                                ilcd.clear();
                                ilcd.setText(currentMenu[0] + 1 + ".)\n" + APPS[currentMenu[0]].getName());
                                System.out.println("DONE WITH UP CASE");
                                break;
                            case SELECT:
                                APPS[currentMenu[0]].run(ilcd);
                                inApp[0] = true;
                                break;
                            default:
                                ilcd.clear();
                                ilcd.setText("Default case in Runner");
                                break;
                        }
                    } else {
                        ilcd.clear();
                        ilcd.setText(":)");

                        if (button == Button.UP) {
                            inApp[0] = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        while(true) {}
    }
    public static void main(String Args[]) throws IOException {
        final ILCD ilcd = new RealLCD();
        main(ilcd);
    }
}