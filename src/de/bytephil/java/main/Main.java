package main;

import utils.DMX_old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE = "/sys/class/thermal/thermal_zone0/temp";
    private static final List<Integer> values = new ArrayList<>();

    private static int temp = 0;

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public Main (){
        instance = this;
    }

    public boolean debugMSG = true;

    public static void main(String[] args) throws InterruptedException {

        DMX_old.dmxthread.run();

        while(true) {
            //runCommand("gpio mode 1 pwm");
            //checkTemp();
            //Thread.sleep(1000);
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
