package utils;

import enums.BitType;
import enums.MessageType;
import enums.OutputType;
import main.Main;
import utils.Console;

import java.io.IOException;
import java.util.Scanner;

public class Output {

    public static int dmxpin = 0;

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

    public static boolean testOutput() {
        try {
            new Scanner(Runtime.getRuntime().exec("gpio " + dmxpin + " output").getInputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    private static ProcessBuilder processBuilder = new ProcessBuilder();

    public static void outputDMX(int bit) {
        Scanner s = null;

        // Console.print("Value " + bit + " sent to pin " + dmxpin, MessageType.DEBUG);
        try {
            processBuilder.command("bash", "-c", "gpio write " + dmxpin + " " + bit);
            Process process = processBuilder.start();

            //Process p = Runtime.getRuntime().exec("gpio write " + dmxpin + " " + bit);
            //p.waitFor();
           // s = new Scanner(Runtime.getRuntime().exec("gpio write " + dmxpin + " " + bit).getInputStream());
        }catch (Exception e) {
            Console.print(e.getMessage(), MessageType.ERROR);
        }



        //Console.print("Value " + bit + " sent to pin " + dmxpin, MessageType.DEBUG);
    }
}
