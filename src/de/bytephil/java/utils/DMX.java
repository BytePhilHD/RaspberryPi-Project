package utils;

import enums.BitType;
import enums.MessageType;
import main.Main;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class DMX {

    public static int output = 0;



    /*
            utils.DMX Vorbereitung:
            https://www.soundlight.de/techtips/dmx512/dmx512.htm
            http://www.dmx512-online.com/dmx512_packet.html
            https://de.wikipedia.org/wiki/DMX_(Lichttechnik)#Zeitliches_Protokoll

            RESET: Thread.sleep(0.088)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String test = "Test";

        HashMap<Integer, BitType> bits = new HashMap<>();

        for (int i = 0; i <= 4096; i++) {
            bits.put(i, BitType.ZERO);
        }

        System.out.println(bits.get(512));
        testSchedular();
        //dmxthread.start();

    }


    public void sendDMX(int bit1, int bit2, int bit3, int bit4, int bit5, int bit6, int bit7, int bit8) {
        // Start Bit


    }

    public static void testSchedular() throws InterruptedException, ExecutionException {
        int iCount = 4096, iDelay = 4, processed = 0;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        System.out.println("Start");
        List<Future<Integer>> futures = new ArrayList<>(iCount);
        for (int i =0; i< iCount; i++) {
            int j = i;
            processed++;
            System.out.println("Processed: " + processed);
            futures.add(scheduler.schedule(() -> j, iDelay, TimeUnit.MICROSECONDS));
        }
        for (Future<Integer> e : futures) {
            e.get();
        }
        System.out.println("Complete");
    }

    public static Thread dmxthread = new Thread(new Runnable() {
        int processed = 0;
        @Override
        public void run() {

            try {
                output = 0;

                if (processed == 0) {
                    dmxthread.sleep(0, 88000);                                // BREAK = 88us
                    System.out.println("BREAK SECTION");
                    output = 0;

                    dmxthread.sleep(0, 8000);                                 // MARK = 8us
                    System.out.println("MARK zw. RESET und Startbyte");
                    output = 1;
                }
                Output.outputDMX(BitType.ZERO);
                dmxthread.sleep(0, 4000);                                        // Start-Bit = 4 us

                for (int i = 0; i < 8; i++) {
                    processed++;
                    System.out.println("Processed: " + processed);

                    Thread.sleep(0, 0);                                   // Bit-Time = 4 us
                }
                Output.outputDMX(BitType.ONE);
                dmxthread.sleep(0, 8000);                                        // 2 Stop-Bits = 8 us

                if (processed == 4096) {
                    return;
                }
                run();

            } catch (InterruptedException e) {
                Console.print("Failed to process DMX Packet! Error Code: PDMX#0", MessageType.ERROR);
                e.printStackTrace();
            }
        }
    }) {                  // ERROR CODE: PDMX#0


    };
}
