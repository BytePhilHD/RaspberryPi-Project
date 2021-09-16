package utils;

import enums.BitType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DMX_Send {

    private static int processed = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        sendDMXRoutine();
    }

    public static void sendDMXRoutine() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        List<Future<Integer>> futures = new ArrayList<>(100);
        // time = System.currentTimeMillis();
        while (true) {
            int iCount = 4096, iDelay = 4;
            System.out.println("Start");

            if (processed == 0) {
                Output.outputDMX(BitType.ZERO);
                System.out.println("BREAK SECTION");
                futures.add(scheduler.schedule(() -> 0, 1, TimeUnit.MILLISECONDS));                      // BREAK BIT = 88 us
                for (Future<Integer> e : futures) {
                    e.get();
                }

                Output.outputDMX(BitType.ONE);
                System.out.println("MARK after BREAK");

            }
            while (processed <= 4096) {

                Output.outputDMX(BitType.ZERO);

                for (int i = 0; i < 8; i++) {
                    processed++;
                    Output.outputDMX(BitType.ONE);
                }
                Output.outputDMX(BitType.ONE);
            }
            System.out.println("Finished");
            processed = 0;
        }
        // long timetest = System.currentTimeMillis()-time;
        // System.out.println("Took " + timetest + "ms");
    }
}
