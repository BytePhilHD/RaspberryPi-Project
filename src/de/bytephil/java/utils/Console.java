package utils;

import enums.MessageType;
import main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {


    public static void print(String msg, MessageType type) {
        try {
            if (!Main.getInstance().debugMSG && type == MessageType.DEBUG) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[" + getTime() + "] " + type + " - " + msg);
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
