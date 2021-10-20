package utils;

import java.util.*;

public class DMX {

    /**
     * Neue Idee 16.09.2021: Senden der Daten an einen Atmel, der dann DMX ausgibt
     *
     * Um Richtig auszulesen (aus Bytes) byte & 0xff
     *
     *
     *
     *
     *  NOTE: This Class is outdated and not working properly! The newest DMX sending method is in the "DMX_SEND" Class!
     */

    private static List<Byte> bytes = new ArrayList<Byte>();
    public static DMX dmx = new DMX();

    HashMap<Integer, Integer> channels = new HashMap<>();  // First int is the channel and the second the value (0-255)


    public static void readDMX() {

    }

    public static void main(String[] args) {
        dmx.setAllBytesZero();
        setChannelValue(300, 142);


/*
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
              // dmx.setData();
                System.out.println("Hallo");
            }
        }, 0, 1);


 */

    }

    public static String setChannelValue(int channel, int value) {
        DMX Test = new DMX();

        if (channel <= 512 && value <= 255) {
            bytes.remove(channel);
            bytes.add(channel, (byte) value);

            System.out.println("Success: " + (bytes.get(channel) & 0xff));
            return "Success";
        }
        return "Error";
    }


    public void setData() {
        for (int i = 0; i < 512; i++) {
            if (bytes.get(i) != 0) {

            }
            bytes.add((byte) 0);
        }
    }

    public void setAllBytesZero() {
        for (int i = 0; i < 512; i++) {
            byte b = (byte) 0;
            bytes.add(b);
        }
        System.out.println("Erledigt: " + bytes.size());
    }
}
       /* int i = 254;
        byte b = (byte) i;

        int i2 = b & 0xFF;
        System.out.println("i2 = " + i2); // i2 = 255
        bytes.add(b);
        System.out.println("Ergebnis: " + (bytes.get(0) & 0xFF));

        */