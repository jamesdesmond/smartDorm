import se.hirt.pi.adafruitlcd.ILCD;

import java.io.File;
import java.io.IOException;

/**
 * Created by james on 10/12/16.
 */
public class sendText {
    /*public sendText(ILCD ilcd) throws IOException {
        //TODO: multiple options for messages
        //TODO: some kind of password based system for protecting against spam by my other roomates who are obviously jealous of my SmartDorm system.
    }*/
    private static void sendText(String address, String message) throws IOException {
        System.out.println("sendText()0");
        String command = "mutt -F /root/.muttrc -s \"SmartDorm\"" + " "  + address + " " + "<<< \"" + message + "\""; //empty strings added for emphasis
        String workspace = "/bin/bash";
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(workspace));
        processBuilder.start();
        System.out.println(System.getProperty("user.name"));
        System.out.println(command);
        System.out.println("sendText()1");
        System.out.println("sendText()2");
    }
    public static void textJames() throws IOException {
        System.out.println("textJames()");
        sendText("8608332915@vtext.com","I need the room for a little bit");
    }
    public static void textCarter() throws IOException {
        sendText("9788357759@vtext.com","I need the room for a little bit");
    }
}
