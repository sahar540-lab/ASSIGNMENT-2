public class hammingAlgo {

    // Function to calculate parity bit (Hamming bit)
    public static int getHammingBit(String parityScheme, int sum) {
        int hammingBit;

        if (parityScheme.equalsIgnoreCase("ODD")) {
            // For ODD parity: make total 1s odd
            int r = sum % 2;
            if (r != 0)
                hammingBit = 0;
            else
                hammingBit = 1;
        } else {
            // For EVEN parity: make total 1s even
            int r = sum % 2;
            if (r != 0)
                hammingBit = 1;
            else
                hammingBit = 0;
        }

        return hammingBit;
    }

    // MAIN FUNCTION
    public static void main(String[] args) {

        // Example message bits
        // 110000111001
        int[] inp = {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1};

        // Message with space for parity bits (dummy 9s for now)
        // positions 1,2,4,8,16... are parity bits
        int[] msg = {9, 9, 1, 0, 9, 0, 0, 1, 9, 1, 1, 0, 0, 1};
        String parityScheme = "ODD";

        int msgLength = msg.length;
        int s1 = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0;
        int h1, h2, h3, h4, h5;

        //  CALCULATE h1 
        for (int i = 0; i < msgLength; i++) {
            // H1 checks bits at positions 1,3,5,7,9,11,13...
            if (((i + 1) & 1) != 0) // if position has 1st bit = 1
                s1 += msg[i];
        }
        h1 = getHammingBit(parityScheme, s1);

        // CALCULATE h2
        for (int i = 0; i < msgLength; i++) {
            // H2 checks bits at positions 2–3, 6–7, 10–11, 14–15...
            if (((i + 1) & 2) != 0)
                s2 += msg[i];
        }
        h2 = getHammingBit(parityScheme, s2);

        // CALCULATE h3 
        for (int i = 0; i < msgLength; i++) {
            // H3 checks bits at positions 4–7, 12–15, etc.
            if (((i + 1) & 4) != 0)
                s3 += msg[i];
        }
        h3 = getHammingBit(parityScheme, s3);

        // CALCULATE h4 
        for (int i = 0; i < msgLength; i++) {
            // H4 checks bits at positions 8–15, etc.
            if (((i + 1) & 8) != 0)
                s4 += msg[i];
        }
        h4 = getHammingBit(parityScheme, s4);

        // CALCULATE h5 
        for (int i = 0; i < msgLength; i++) {
            // H5 checks positions 16–31 etc.
            if (((i + 1) & 16) != 0)
                s5 += msg[i];
        }
        h5 = getHammingBit(parityScheme, s5);

        // DISPLAY RESULTS 
        System.out.println("Sum s1 = " + s1 + " → h1 = " + h1);
        System.out.println("Sum s2 = " + s2 + " → h2 = " + h2);
        System.out.println("Sum s3 = " + s3 + " → h3 = " + h3);
        System.out.println("Sum s4 = " + s4 + " → h4 = " + h4);
        System.out.println("Sum s5 = " + s5 + " → h5 = " + h5);

        System.out.println("\nFinal Hamming bits: [" + h1 + ", " + h2 + ", " + h3 + ", " + h4 + ", " + h5 + "]");
    }
}
