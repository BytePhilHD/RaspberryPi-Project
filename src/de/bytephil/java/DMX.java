import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;

public class DMX {

    public static int output = 0;



    /*
            DMX Vorbereitung:
            https://www.soundlight.de/techtips/dmx512/dmx512.htm
            http://www.dmx512-online.com/dmx512_packet.html

            RESET: Thread.sleep(0.088)
     */

    public static void main(String[] args) {
        String test = "Test";
        BitSet bitsSet;
        BigInteger bits;
        System.out.println(bitsSet.se);
    }


    public static Thread dmxthread = new Thread() {

        public void run() {

            try {
                output = 0;

                sleep(0, 88);
                System.out.println("BREAK SECTION");
                output = 1;

                sleep(0, 8);
                System.out.println("MARK zw. RESET und Startbyte");
                output = 0;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BitSet start = new BitSet(8);
            boolean[] startbits = new boolean[8];


        }
    };
}
