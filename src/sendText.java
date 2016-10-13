import se.hirt.pi.adafruitlcd.ILCD;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by james on 10/12/16.
 */
public class sendText {
    /*public sendText(ILCD ilcd) throws IOException {
        //TODO: multiple options for messages
        //TODO: some kind of password based system for protecting against spam by my other roomates who are obviously jealous of my SmartDorm system.
    }*/
    private static void sendText(String address, String message) {
        System.out.println("sendText()0");
        //String command = "bash", "-c", \"mutt -F /root/.muttrc -s \"SmartDorm\" 8608332915@vtext.com <<< \\\"I need the room for a little bit\\\"\"";
        String[] command = {"mutt" ,  "-F" ,  "/root/.muttrc" ,  "-s" ,  "\"SmartDorm\"", "8608332915@vtext.com" , "<<<" ,  "\"I need the room for a little bit\""};
        //String[] command = {"touch","/home/pi/test.txt"};
        System.out.println(Arrays.toString(command));
        /*int i = Runtime.getRuntime().exec(command).waitFor();
        try {
            Runtime.getRuntime().exec(command).waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.name"));
        System.out.println("sendText()1");
    }
    public static void textJames() {
        System.out.println("textJames()");
        sendText("8608332915@vtext.com","I need the room for a little bit");
    }
    public static void textCarter() {
        sendText("9788357759@vtext.com","I need the room for a little bit");
    }
}
