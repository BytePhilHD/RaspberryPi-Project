package main;

import enums.MessageType;
import utils.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private static String version = "0.0.2";

    public static Scanner scanner = new Scanner(System.in);

    public Main() {
        instance = this;
    }

    public boolean debugMSG = true;


    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        /*
        boolean started = false;
        long startzeit = 0;
        int cycles = 0;
        ArrayList<Long> liste = new ArrayList();
        while (true) {
            if (!started) {
                startzeit = System.nanoTime();
                started = true;
            }
            if (cycles <= 100) {
                long actualtime = System.nanoTime();
                if (actualtime >= startzeit + 4000) {
                    liste.add(actualtime-startzeit);
                    startzeit = actualtime;
                    //System.out.println("Time");
                    cycles++;
                }
            } else {
                System.out.println("Zeiten: ");
                for (int i = 0; i <=100; i++) {
                    System.out.println("Zeit " + i + ": " + liste.get(i));
                }
                break;
            }
           }
         */
        //startUp();
       /* gpio = GpioFactory.getInstance();

        red = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08,   // PIN NUMBER
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);

        while (true) {
            red.high();
            Thread.sleep(1000);
            red.low();
            Thread.sleep(1000);
            System.out.println("Should work");
        }

        */
    }

    //
    //              Startup function
    //
    public static void startUp() throws ExecutionException, InterruptedException {
        Console.print("Starting System...", MessageType.INFO);
        Console.print("Your System is running Version " + version + "!", MessageType.INFO);
        System.out.println("");

        boolean raspberry = Output.testOutput();    // Test if system is running with Pi4J installed
        if (raspberry) {        // if system is running Pi4J
            Console.print("Your System is supported! The DMX program will start shortly!", MessageType.INFO);
            startServices();
        } else {                // If system is not running Pi4J
            Console.print("Your System is not supported!", MessageType.ERROR);
            Console.print("You have to run Ubuntu20 or higher with LGPIO installed (sudo apt install python3-lgpio) on a RaspberryPi", MessageType.INFO);
            System.out.println("");
            Console.print("You can find more information on https://github.com/BytePhilHD/RaspberryPi-Project", MessageType.INFO);
            System.out.println("");
            Thread.sleep(1000);
            Console.print("If you still want to continue, type 'y', if you want to close type 'n'. Note: this option is not supported and may not work!", MessageType.WARNING);
            String input = scanner.next();
            switch (input) {
                case ("y"):
                    Console.print("Trying to start the program... Note: this option is not supported and may not work!", MessageType.WARNING);
                    startServices();
                    break;
                case ("n"):
                    Console.print("System will shutdown...", MessageType.WARNING);
                    Thread.sleep(1000);
                    System.exit(0);
                    break;
            }
        }
    }

    public static void startServices() throws ExecutionException, InterruptedException {
        webserver.WebService.boot(); // Start WebService
        DMXService.dmxRoutine = true;
        DMXService.sendDMXRoutine();   //  Start DMX Service
    }
}
