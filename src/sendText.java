import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;

/**
 * Created by james on 10/12/16.
 */
public class sendText {
    /*public sendText(ILCD ilcd) throws IOException {
        ilcd.clear();
        ilcd.setText("sendText\nMenu");
        //TODO: multiple options for messages
        //TODO: some kind of password based system for protecting against spam by my other roomates who are obviously jealous of my SmartDorm system.
    }*/
    private static void sendText(String address, String message) throws IOException {
        //Eventually make a whole menu system
        Runtime.getRuntime().exec("cd /root/ && mutt -s \"SmartDorm\"" + address + "<<< \"" + message + "\"");
    }
    public static void textJames() throws IOException {
        sendText("8608332915@vtext.com","I need the room for a little bit");
    }
    public static void textCarter() throws IOException {
        sendText("9788357759@vtext.com","I need the room for a little bit");
    }
}