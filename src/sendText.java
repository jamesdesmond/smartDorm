import smartDorm.Enums;
import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;

/**
 * Created by james on 10/12/16.
 */
public class sendText implements LCDApps{
    private Enums.People person;
    public sendText() {
        this.person = Enums.People.JAMES;
    }
    public sendText(Enums.People person) {
        this.person = person;
    };
    //TODO: multiple options for messages
    //TODO: some kind of password based system for protecting against spam by my other roomates who are obviously jealous of my SmartDorm system.
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
    private  void textJames() {
        sendText("8608332915@vtext.com","I need the room for a little bit");
    }
    private  void textCarter() {
        sendText("9788357759@vtext.com","I need the room for a little bit");
    }
    @Override
    public String getName() {
        return "Text " + person;
    }
    @Override
    public void run (ILCD ilcd) throws IOException {
        ilcd.clear();
        ilcd.setText("Loading...");
        switch (person.ordinal()) {
            case 0:
                textJames();
                break;
            case 1:
                textCarter();
                break;
            default:
                System.out.println("sendText default case in run()!");
        }
        ilcd.clear();
        ilcd.setText("Sent!");
    }
}
