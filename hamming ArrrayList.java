import java.util.ArrayList;
import java.util.Scanner;
public class SimpleHamming {  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> msg = new ArrayList<>();      // cretaes the input to read.
        System.out.print("Enter number of bits in your message: ");
        int n = input.nextInt();    // n is the number of bits that will be entered.
        System.out.println("Enter the bits one by one (0 or 1):");
        for (int i = 0; i < n; i++) {      // reads bits "n" one by one and store them in same order in "msg".
            msg.add(input.nextInt());
        }
        System.out.print("Enter parity type (EVEN/ODD): ");
        String type = input.next().toUpperCase();
        // Step 3: calculate number of parity bits
        int r = 0;
        while (Math.pow(2, r) < (n + r + 1)) {    // fins how many parity bits "r: are needed for the 'hamming rule'. like(n=4 , r=2 , false and if r=3  then true).
            r++;            // The loop keeps increasing 'r' until the rule becomes true.
        }
        System.out.println("\nTotal parity bits needed: " + r); 
        ArrayList<Integer> hamming = new ArrayList<>();     //create list with space for parity bits.
        int j = 0; // for msg bits
        for (int i = 1; i <= n + r; i++) {
            if (isPowerOfTwo(i)) {
                hamming.add(0); // temporary parity placeholder
            } else {                                                  // if msg is: 1011  then hamming will be 0010011 temporary.
                hamming.add(msg.get(j));
                j++;
            }
        }
        for (int i = 0; i < r; i++) {          // the loop runs for each parity bit.
            int parityPos = (int) Math.pow(2, i);                    //parity bits in hamming are placed at position power of 2.
            int parity = calculateParity(hamming, parityPos, type);    // calls the helper method [calculate parity] to calculate the correct parity bit.
             // the method'calculate parity' sums a specific pattern of bits in hamming that parity bit is responsible for then apply even/odd parity rule.
            hamming.set(parityPos - 1, parity);    // the Arraylist is 0-based so we will (-1) later.
            System.out.println("H" + (i + 1) + " (position " + parityPos + ") = " + parity);
        }

        // Step 6: Show the sum logic
        System.out.println("\n Parity sum calculation");
        for (int nIndex = 0; nIndex < r; nIndex++) {
            int s = 0;
            int blockStart = (int) Math.pow(2, nIndex);
            int blockSize = (int) Math.pow(2, nIndex);

            for (int i = blockStart; i < hamming.size(); i += blockSize * 2) {
                for (int k = 0; k < blockSize; k++) {
                    if ((i + k) < hamming.size()) {
                        s += hamming.get(i + k);
                    }
                }
            }
            System.out.println("For Hamming bit-" + (nIndex + 1) + " the sum is " + s);
        }
        System.out.println("\nFinal Hamming Code:");
        for (int bit : hamming) {
            System.out.print(bit + " ");
        }
    }
    public static int calculateParity(ArrayList<Integer> bits, int parityPos, String type) {
        int count = 0;
        for (int i = parityPos; i <= bits.size(); i += (2 * parityPos)) {
            for (int j = i; j < i + parityPos && j <= bits.size(); j++) {
                count += bits.get(j - 1);
            }
        }
        if (type.equals("EVEN")) {       // parity rule.
            return count % 2 == 0 ? 0 : 1;
        } else {
            return count % 2 == 0 ? 1 : 0;
        }
    }
    public static boolean isPowerOfTwo(int x) {     // helper method to check power of two
        return (x & (x - 1)) == 0;
    }
}
