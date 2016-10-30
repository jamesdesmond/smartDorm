/**
 * Created by james on 9/24/16.
 */
import se.hirt.pi.adafruitlcd.*;
import se.hirt.pi.adafruitlcd.impl.RealLCD;
import smartDorm.Enums;
import smartDorm.LCDApps;

import java.io.IOException;


public class Runner {
    private  static final LCDApps[] APPS = new LCDApps[]{
            new showWeather(),new sendText(Enums.People.CARTER),new sendText(Enums.People.JAMES),new showIP(),new Random(),new Settings(),new Sleep()
    };
    private boolean inApp;
    private int currentMenu;

    public Runner() {
        inApp = false;
        currentMenu = 0;
    }
    private void menu(ILCD ilcd) throws IOException {
        ilcd.setBacklight(Color.RED);
        ilcd.clear();
        ilcd.setText(1 + ".)\n" +APPS[0].getName());
        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    if (!inApp) {
                        switch (button) {
                            case LEFT:
                                break;
                            case RIGHT:
                                break;
                            case DOWN:
                                //ilcd.clear();
                                System.out.println("Down: " + currentMenu);
                                currentMenu++;
                                System.out.println("current menu is now: " + currentMenu);
                                if (currentMenu > APPS.length - 1) {
                                    currentMenu = 0;
                                    System.out.println("currentMenu was equal to 4, now equal to 0");
                                }
                                ilcd.clear();
                                ilcd.setText(currentMenu + 1 + ".)\n" + APPS[currentMenu].getName());
                                System.out.println("DONE WITH DOWN CASE");
                                break;
                            case UP:
                                //ilcd.clear();
                                System.out.println("Up: " + currentMenu);
                                currentMenu--;
                                System.out.println("current menu is now: " + currentMenu);
                                if (currentMenu < 0) {
                                    currentMenu = APPS.length - 1;
                                    System.out.println("currentMenu was equal to -1, now equal to 3");
                                }
                                ilcd.clear();
                                ilcd.setText(currentMenu + 1 + ".)\n" + APPS[currentMenu].getName());
                                System.out.println("DONE WITH UP CASE");
                                break;
                            case SELECT:
                                APPS[currentMenu].run(ilcd);
                                inApp = true;
                                break;
                            default:
                                ilcd.clear();
                                ilcd.setText("Default case in Runner");
                                break;
                        }
                    } else {
                        if (button == Button.UP || button == Button.DOWN) {
                            inApp = false;
                            if (ilcd.getBacklight() == Color.OFF) {
                                ilcd.setBacklight(Color.RED);
                            }
                            ilcd.clear();
                            ilcd.setText(currentMenu + 1 + ".)\n" + APPS[currentMenu].getName());
                        } else {
                            APPS[currentMenu].run(ilcd,button);
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
        new Runner().menu(ilcd);
    }
}