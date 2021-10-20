package utils;

import enums.MessageType;
import main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    private static int counter = 0;

    public static void print(String msg, MessageType type) {    // This methods sends out a console Message with the current time and MessageType

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (type == MessageType.ERROR) {  // When the given Messagetype is ERROR, the method sends a bigger message to the console
            System.out.println("[" + date + "] " + type);
            System.out.println("[" + date + "] " + type + " - " + msg);
            System.out.println("[" + date + "] " + type);
        } else {
            System.out.println("[" + date + "] " + type + " - " + msg);
        }
    }

    public static void printProgress() {   // This method creates a progress bar. Everytime its called it sends 1 character, when its over 100 it goes to a new line. (Creates: "--------+-----------+-------------+-....")
        if (counter <= 100) {
            if (counter == 20 || counter == 40 || counter == 60 || counter == 80) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
            counter++;
        } else {
            System.out.println("");
            counter = 0;
        }
    }
}
