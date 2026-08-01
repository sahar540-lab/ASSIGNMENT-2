import java.util.Scanner;
public class HammingAlgo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of bits in your message: ");
        int n = in.nextInt();
        int[] msg = new int[n];
        System.out.println("Enter message bits one by one (0 or 1): ");
        for (int i = 0; i < n; i++) {
           msg[i] = in.nextInt();       // the loop stores bits into array.
        }
        System.out.print("Enter parity type (EVEN/ODD): ");
        String parity = in.next().toUpperCase();          //convert it to uppercase so it works even if user types in small.
        int s;                                 // sum of bits in the block.
        int blockSize;                        //it check how many bits are checked together.
        int blockStart;                     // parity check begins.
        for (int bit = 0; bit < 5; bit++)      // H1, H2, H3, H4, H5
        {
            blockStart = (int) Math.pow(2, bit);
            blockSize = (int) Math.pow(2, bit);
            s = 0;
            for (int i = blockStart - 1; i < msg.length; i += blockSize * 2) {
                for (int k = 0; k < blockSize; k++) {
                    if ((i + k) < msg.length) {
                        s += msg[i + k];
                    }
                }
            }
            //The outer loop moves in jumps by skipping blocksize*2.
            // inner loppp adds up bits inside current group.
            //the whole nested loop checks which bits belong to that parity and adds them.
            System.out.println("For Hamming bit " + (bit + 1) + ", the sum is " + s);        // sum will be printed
            //calculating the parity value to be 1 or 0:
            int parityBit;
            if (parity.equals("EVEN")) {
                if (s % 2 == 0)
                    parityBit = 0;
                else
                    parityBit = 1;
            } else {                 // ODD parity
                if (s % 2 == 0)
                    parityBit = 1;
                else
                    parityBit = 0;
            }

            System.out.println("So H" + (bit + 1) + " = " + parityBit + "\n");
        }

        in.close();
    }
}
