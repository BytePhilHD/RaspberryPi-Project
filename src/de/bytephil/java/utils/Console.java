package utils;

import enums.MessageType;
import main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

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
}
