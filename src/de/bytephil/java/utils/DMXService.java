package utils;

import enums.BitType;
import enums.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DMXService {

    private static int processed = 0;
    private static int bits = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        sendDMXRoutine();
    }

    public static boolean dmxRoutine = false;

    public static void sendDMXRoutineOLD() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        List<Future<Integer>> futures = new ArrayList<>(100);
        // time = System.currentTimeMillis();
        while (dmxRoutine) {
            int iCount = 4096, iDelay = 4;
            System.out.println("Start");

            if (bits == 0) {
                Output.outputDMX(0);
                System.out.println("BREAK SECTION");
                futures.add(scheduler.schedule(() -> 0, 88, TimeUnit.MICROSECONDS));           // BREAK BIT = 88 us
                for (Future<Integer> e : futures) { e.get(); }
                futures.clear();

                Output.outputDMX(1);
                System.out.println("MARK after BREAK");

            }
            while (bits <= 512) {

                Output.outputDMX(0);

                if (bits == 1) {
                    for (int i = 0; i < 8; i++) {
                        processed++;
                        Output.outputDMX(0);
                    }
                }
                for (int i = 0; i < 8; i++) {
                    processed++;
                    Output.outputDMX(1);
                }
                Output.outputDMX(1);
                bits++;
            }
            Console.print("Finished", MessageType.INFO);
            processed = 0;
            bits = 0;
        }
        Console.print("The DMX routine was stopped!", MessageType.WARNING);
        // long timetest = System.currentTimeMillis()-time;
        // System.out.println("Took " + timetest + "ms");
    }

    public static void sendDMXRoutine() {
        long time = 0;
        int timer = 0;
        int bits = 0;
        boolean started = false;
        boolean mab = false;

        while (true) {
            if (!started) {
                time = System.nanoTime();
                started = true;
            }
            if (bits == 0) {
                Output.outputDMX(0);
                if (System.nanoTime() >= time+88000) {              // 88us RESET
                    time = System.nanoTime();
                    bits++;
                }
            }
            if (bits == 1) {
                if (!mab) {
                    Output.outputDMX(1);
                    if (System.nanoTime() >= time+8000) {              // 8us Mark after Break
                        time = System.nanoTime();
                        mab = true;
                    }
                }
                else {
                    Output.outputDMX(0);
                    if (System.nanoTime() >= time+44000) {              // 44us Startbyte Frame Width
                        time = System.nanoTime();
                        bits++;
                    }
                }
            }
            else if (bits > 1) {
                if (System.nanoTime() >= time+4000) {                  // 4us pro Bit
                    time = System.nanoTime();

                }
            }
            /*if (System.nanoTime() >= time+4000) {
                time = System.nanoTime();
                if (timer >= 250000) {
                    System.out.println("Time erreicht");
                    timer = 0;
                }
                timer++;
            }

             */
        }
    }
}
