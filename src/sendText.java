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
    private static final String[] messages = {"Let me know when\nyou're back","I need the room\nfor a little bit","Staying at Aly's\ntonight","Going out to\nskate","Test"}; //Remember that its a 16x2 display
    public sendText() {
        this.person = Enums.People.JAMES;
        currentMenu = 0;
    }

    public sendText(Enums.People person) {
        this.person = person;
        currentMenu = 0;
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

    private void menu(ILCD ilcd, Button button)throws IOException {
        ilcd.clear();
        ilcd.setText("Use Right & Left\nto select msg");
        ilcd.setText(messages[0]);
            try {
                    switch (button) {
                        case RIGHT:
                            ilcd.clear();
                            currentMenu++;
                            currentMenu = (currentMenu > messages.length - 1) ? 0 : currentMenu;
                            ilcd.clear();
                            ilcd.setText(messages[currentMenu]);
                            break;
                        case LEFT:
                            ilcd.clear();
                            currentMenu--;
                            currentMenu = (currentMenu < 0) ? messages.length - 1 : currentMenu;
                            ilcd.clear();
                            ilcd.setText(messages[currentMenu]);
                            break;
                        case SELECT:
                            ilcd.clear();
                            ilcd.setText("Loading...");
                            sendText(person.email, messages[currentMenu]);
                            ilcd.clear();
                            ilcd.setText("Sent!");
                            break;
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        ilcd.clear();
    }
    @Override
    public void run (ILCD ilcd, Button button) throws IOException {
        //ilcd.clear();
        //ilcd.setText("Use Right & Left\nto select msg");
        menu(ilcd , button);
    }

}
