package utils;

import com.pi4j.io.gpio.*;
import enums.BitType;
import enums.MessageType;
import enums.OutputType;
import main.Main;
import utils.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Output {

    public static Pin dmxpin = RaspiPin.GPIO_08;
    private static GpioController gpio = null;
    private static GpioPinDigitalOutput pin = null;

    // Test if system is running with Pi4J installed
    public static boolean testOutput() {
        try {
            gpio = GpioFactory.getInstance();       // If the system has Pi4J installed gpio and pin get initialized
            pin = gpio.provisionDigitalOutputPin(dmxpin,   // PIN NUMBER
                    "DMXPIN",           // PIN FRIENDLY NAME (optional)
                    PinState.LOW);
            return true;
        } catch (ExceptionInInitializerError e) {
            return false;
        }
    }

    // Output the bit to an specific Port
    public static void outputDMX(int bit) {
        Scanner s = null;

        try {
            if (bit == 0) {
                pin.low();
            } else {
                pin.high();
            }
        } catch (Exception e) {
            Console.print(e.getMessage(), MessageType.ERROR);
        }


        // DEBUG Console.print("Value " + bit + " sent to pin " + dmxpin, MessageType.DEBUG);
    }
}
