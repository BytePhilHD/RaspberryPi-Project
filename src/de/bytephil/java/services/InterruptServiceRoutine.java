package services;

import enums.BitType;
import io.timeandspace.cronscheduler.CronScheduler;
import utils.Output;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class InterruptServiceRoutine {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        interruptServiceRoutine();
    }

    public static long timestart = 0;
    public static ArrayList<Long> average = new ArrayList<>();
    public static long timeall = 0;
    public static long time = 0;

    public static void interruptServiceRoutine() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long period = 1;

        executor.scheduleAtFixedRate(InterruptServiceRoutine::print,0,period,TimeUnit.MILLISECONDS);

       /* ScheduledExecutorService exec1 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService exec2 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService exec3 = Executors.newSingleThreadScheduledExecutor();

        exec1.scheduleAtFixedRate(print(), 0, 1000 / 3, TimeUnit.SECONDS);


        Duration syncPeriod = Duration.ofSeconds(1);
        CronScheduler cron = CronScheduler.create(syncPeriod);
        cron.scheduleAtFixedRateSkippingToLatest(0, 1, TimeUnit.MILLISECONDS, runTimeMillis -> {
            // Collect and send summary metrics to a remote monitoring system
            print();
        });
        while (true) {

        }

        */

    }


    public static void print() {
        if (time == 0) {
            time = System.nanoTime();
            return;
        }
        else {
            long currenttime = System.nanoTime();
            System.out.println("Took: " + (currenttime-time));
            time = currenttime;
        }
    }

    public static void interruptServiceRoutine1() throws InterruptedException, ExecutionException {
        int iCount = 1000, iDelay = 2;
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        List<Future<Integer>> futures = new ArrayList<>(iCount);

        while (true) {
            timestart = System.nanoTime();
            futures.add(scheduler.schedule(() -> 1, 4, TimeUnit.MICROSECONDS));                      // BREAK BIT = 88 us
            for (Future<Integer> e : futures) {
                e.get();
            }
            long timetook = System.nanoTime()-timestart;
            System.out.println("Took " + timetook);
        }

    }
}
