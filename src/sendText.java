import se.hirt.pi.adafruitlcd.*;
import smartDorm.Enums;
import smartDorm.LCDApps;

import java.io.IOException;

/**
 * Created by james on 10/12/16.
 */
public class sendText implements LCDApps {
    private int currentMenu;
    private Enums.People person;
    private String[] messages = {"Let me know when\nyou're back","I need the room\nfor a little bit","Staying at Aly's\ntonight","Going out to\nskate","Test"}; //Remember that its a 16x2 display
    private boolean inApp;
    public sendText() {
        this.person = Enums.People.JAMES;
        currentMenu = 0;
        inApp = true;
    }

    public sendText(Enums.People person) {
        this.person = person;
        currentMenu = 0;
        inApp = true;
    };

    private  void sendText(String address, String message) {
        String[] command = {"/bin/bash", "-c", "mutt -F /root/.muttrc -s \"SmartDorm\" " + address + " <<< \"" + message + "\""};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Text " + person;
    }

    @Override
    public void run (ILCD ilcd) throws IOException {
        ilcd.setBacklight(Color.GREEN);
        ilcd.clear();
        System.out.println("1");
        ilcd.setText("Loading...");
        System.out.println("2");
        //ilcd.setText(messages[0]);
        System.out.println("3");
        ButtonPressedObserver observer = new ButtonPressedObserver(ilcd);
        observer.addButtonListener(new ButtonListener() {
            @Override
            public void onButtonPressed(Button button) {
                try {
                    if (inApp) {
                        switch (button) {
                            case RIGHT:
                                ilcd.clear();
                                currentMenu++;
                                currentMenu = (currentMenu > messages.length - 1) ? 0 : currentMenu;
                                ilcd.clear();
                                ilcd.setText(messages[currentMenu].toString());
                                break;
                            case LEFT:
                                ilcd.clear();
                                currentMenu--;
                                currentMenu = (currentMenu < 0) ? messages.length - 1 : currentMenu;
                                ilcd.clear();
                                ilcd.setText(messages[currentMenu].toString());
                                break;
                            case SELECT:
                                ilcd.clear();
                                ilcd.setText("Loading...");
                                sendText(person.email, messages[currentMenu]);
                                ilcd.clear();
                                ilcd.setText("Sent!");
                                break;
                            case UP:
                                inApp = false;
                                break;
                            case DOWN:
                                inApp = false;
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ilcd.clear();
    }
}
