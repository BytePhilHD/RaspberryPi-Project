package utils;

import enums.BitType;
import enums.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DMXService {

    private static int processed = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        sendDMXRoutine();
    }

    public static boolean dmxRoutine = false;

    public static void sendDMXRoutine() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        List<Future<Integer>> futures = new ArrayList<>(100);
        // time = System.currentTimeMillis();
        while (dmxRoutine) {
            int iCount = 4096, iDelay = 4;
            System.out.println("Start");

            if (processed == 0) {
                Output.outputDMX(0);
                System.out.println("BREAK SECTION");
                futures.add(scheduler.schedule(() -> 0, 1, TimeUnit.MILLISECONDS));           // BREAK BIT = 88 us
                for (Future<Integer> e : futures) { e.get(); }
                futures.clear();

                Output.outputDMX(1);
                System.out.println("MARK after BREAK");

            }
            while (processed <= 4096) {

                Output.outputDMX(0);

                for (int i = 0; i < 8; i++) {
                    processed++;
                    Output.outputDMX(1);
                }
                Output.outputDMX(1);
            }
            Console.print("Finished", MessageType.INFO);
            processed = 0;
        }
        Console.print("The DMX routine was stopped!", MessageType.WARNING);
        // long timetest = System.currentTimeMillis()-time;
        // System.out.println("Took " + timetest + "ms");
    }
}
