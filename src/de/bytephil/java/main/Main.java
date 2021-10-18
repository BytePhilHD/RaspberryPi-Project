package main;

import utils.DMX_Send;
import utils.DMX_old;
import utils.Output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String FILE = "/sys/class/thermal/thermal_zone0/temp";
    private static final List<Integer> values = new ArrayList<>();

    private static int temp = 0;

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static Scanner scanner = new Scanner(System.in);

    public Main() {
        instance = this;
    }

    public boolean debugMSG = true;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        startUp();
    }

    public static void startUp() throws ExecutionException, InterruptedException {
        System.out.println("Starting System...");

        boolean raspberry = Output.testOutput();
        if (raspberry) {
            System.out.println("Your System is supported! The DMX program will start shortly!");
            DMX_Send.sendDMXRoutine();
        } else {
            System.out.println("Your System is not supported!");
            System.out.println("You have to run Ubuntu20 or higher with LGPIO installed (sudo apt install python3-lgpio) on a RaspberryPi");
            System.out.println("");
            System.out.println("You can find more information on https://github.com/BytePhilHD/RaspberryPi-Project");
            System.out.println("");
            Thread.sleep(1000);
            System.out.println("If you still want to continue, type 'y', if you want to close type 'n'. Note: this option is not supported and may not work!");
            String input = scanner.next();
            switch (input) {
                case("y"):
                    System.out.println("Trying to start the program... Note: this option is not supported and may not work!");
                    DMX_Send.sendDMXRoutine();
                    break;
                case("n"):
                    System.out.println("System will shutdown...");
                    Thread.sleep(1000);
                    System.exit(0);
                    break;
            }
        }
    }

    private static void checkTemp() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            int value = Integer.valueOf(br.readLine());
            values.add(value);
            int total = values.stream().mapToInt(Integer::valueOf).sum();
            temp = value;

            System.out.println("Now: " + value
                    + " - Average: " + (total / values.size())
                    + " - Number of measurements: " + values.size());

            if (temp > 35000) {
                runCommand("gpio pwm 1 480");
            } else {
                runCommand("gpio pwm 1 430");
            }
        } catch (Exception ex) {
            System.err.println("Error during temperature check: "
                    + ex.getMessage());
        }
    }


    private static void runCommand(String cmd) {
        Scanner s = null;
        try {
            s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
