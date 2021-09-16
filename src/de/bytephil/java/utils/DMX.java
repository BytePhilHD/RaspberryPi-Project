package utils;

import java.util.*;

public class DMX {

    /**
     * Neue Idee 16.09.2021: Senden der Daten an einen Atmel, der dann DMX ausgibt
     *
     * Um Richtig auszulesen (aus Bytes) byte & 0xff
     */

    private static List<Byte> bytes = new ArrayList<Byte>();
    public static DMX Test = new DMX();

    HashMap<Integer, Integer> channels = new HashMap<>();  // First int is the channel and the second the value (0-255)


    public static void main(String[] args) {
        Test.setAllBytesZero();
        setChannelValue(300, 142);
       /* int i = 254;
        byte b = (byte) i;

        int i2 = b & 0xFF;
        System.out.println("i2 = " + i2); // i2 = 255
        bytes.add(b);
        System.out.println("Ergebnis: " + (bytes.get(0) & 0xFF));

        */

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               Test.setData();
            }
        }, 0, 1);
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
