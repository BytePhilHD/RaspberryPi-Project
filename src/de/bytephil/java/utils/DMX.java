package utils;

import enums.BitType;
import enums.MessageType;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;

public class DMX {

    public static int output = 0;
    private static Console console = Console.getInstance();



    /*
            utils.DMX Vorbereitung:
            https://www.soundlight.de/techtips/dmx512/dmx512.htm
            http://www.dmx512-online.com/dmx512_packet.html

            RESET: Thread.sleep(0.088)
     */

    public static void main(String[] args) {
        String test = "Test";

        HashMap<Integer, BitType> bits = new HashMap<>();

        for (int i = 0; i <= 512; i++) {
            bits.put(i, BitType.ZERO);
        }

        System.out.println(bits.get(512));
        dmxthread.run();

    }

    public void sendDMX (int bit1, int bit2, int bit3, int bit4, int bit5, int bit6, int bit7, int bit8) {
        // Start Bit


    }

    public static Thread dmxthread = new Thread() {                  // ERROR CODE: PDMX#0

        int processed = 0;
        public void run() {

            try {
                output = 0;

                sleep(0, 88000);
                System.out.println("BREAK SECTION");
                output = 1;

                sleep(0, 8000);
                System.out.println("MARK zw. RESET und Startbyte");
                output = 0;

                for (int i = 1; i< 8; i++) {
                    System.out.println("Processed: " + processed);
                    processed++;

                }
                if (processed == 512) {
                    return;
                }
                run();

            } catch (InterruptedException e) {
                console.print("Failed to process DMX Packet! Error Code: PDMX#0", MessageType.ERROR);
                e.printStackTrace();
            }
        }
    };
}
