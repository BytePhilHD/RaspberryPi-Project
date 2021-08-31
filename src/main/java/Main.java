import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE = "/sys/class/thermal/thermal_zone0/temp";
    private static final List<Integer> values = new ArrayList<>();

    private static int temp = 0;

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            checkTemp();
            Thread.sleep(1000);
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

            if (temp > 33000) {
                runCommand("gpio pwm 1 500");
            } else {
                runCommand("gpio pwm 1 430");
            }
        } catch (Exception ex) {
            System.err.println("Error during temperature check: "
                    + ex.getMessage());
        }
    }
 /*   private static void test() {
        runCommand("gpio pwm 1 " + i*100);
        System.out.println("PWM auf " + i*100);

        i++;
    }

  */

    private static void runCommand(String cmd) {
        Scanner s = null;
        try {
            s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
