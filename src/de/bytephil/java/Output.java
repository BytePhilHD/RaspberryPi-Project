import enums.OutputType;

import java.io.IOException;
import java.util.Scanner;

public class Output {

    private static void setOutput(int pin, int value, OutputType type) {
        Scanner s = null;
        String cmd = null;

        if (type == OutputType.PWM) {
            cmd = "gpio pwm " + pin + " " + value;
        } else if (type == OutputType.OUTPUT) {
            cmd = "gpio write " + pin + " " + value;
        }
        try {
            s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR while tried to execute " + cmd);
            e.printStackTrace();
        }
    }
}
