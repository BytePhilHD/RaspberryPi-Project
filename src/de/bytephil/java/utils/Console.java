package utils;

import enums.MessageType;
import main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    private static int counter = 0;

    public static void print(String msg, MessageType type) {

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (type == MessageType.ERROR) {
            System.out.println("[" + date + "] " + type);
            System.out.println("[" + date + "] " + type + " - " + msg);
            System.out.println("[" + date + "] " + type);
        } else {
            System.out.println("[" + date + "] " + type + " - " + msg);
        }
    }

    public static void printProgress() {
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
