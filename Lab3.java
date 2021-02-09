import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;


public class Lab3 {
    private static ArrayList<Integer> tab = new ArrayList<>();
    private static BigInteger one = BigInteger.valueOf(1L);
    private static BigInteger three = BigInteger.valueOf(3L);
    private static BigInteger four = BigInteger.valueOf(4L);
    private static BigInteger lowerRange = BigInteger.valueOf(9725L);
    private static BigInteger upperRange = BigInteger.valueOf(10275L);
    private static BigInteger counter = BigInteger.valueOf(0L);
    private static BigInteger seed;
    private static BigInteger n;
    private static int number_of_successes = 0;
    private static int number_of_fails = 0;
    private static PrintWriter output;

    static {
        try {
            output = new PrintWriter("lab3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static BigInteger getNum(int bits, SecureRandom rand) {
        BigInteger num;
        do {
            num = new BigInteger(bits, 100, rand);             // 100 powoduje ze losuje tylko dodatnie
        } while (!num.mod(four).equals(three));
        return num;
    }

    private static BigInteger getN(int bit, SecureRandom rand) {  //tworzymy wartosc N bedaca iloczynem dwoch roznych liczb pierwszych kongruentnych z 3 mod 4
        BigInteger p = getNum(bit, rand);
        BigInteger q = getNum(bit, rand);
        while(p.equals(q)) q = getNum(bit, rand);
        return p.multiply(q);
    }

    private static BigInteger generateSeed(BigInteger n, int bit, SecureRandom rand) {
        BigInteger seed;
        do {
            seed = new BigInteger(bit, 100, rand);
        } while (one.compareTo(seed) != -1 || (n.subtract(one).compareTo(seed)) != 1 || !(seed.gcd(n)).equals(one));
        return seed;
    }

    private static int next() {
        BigInteger x;
        int result = 0;
        x = seed.pow(2).mod(n);
        if(x.testBit(0)) result = 1;
        seed = x;
        return result;
    }

    private static void generate() {
        System.out.print("\nGenerating 20000 bytes");
        for( int i = 0; i< 20000; i++){
            int d = next();
            tab.add(d);
            output.println(tab.get(i));
        }
        System.out.print("\nGenerating finished, bytes string has been written into lab3.txt");
    }

    private static void singleBits(){
        System.out.print("\n\nSingle bits test has started");
        for (Integer integer : tab) {
            if (integer == 1) {
                counter = counter.add(one);
            }
        }
        System.out.print("\nNumber of '1' bits= " + counter);
        if(counter.compareTo(lowerRange) > 0 && counter.compareTo(upperRange) < 0) {
            System.out.print("\nSingle bits test has been finished successfuly");
            number_of_successes ++;
        }
        else {
            System.out.print("\nSingle bits test has failed");
            number_of_fails++;
        }
    }

    private static void series(){
        System.out.print("\n\nSeries test has started");
        int onesCounter = 0;
        int jedynki = 0, dwojki = 0, trojki = 0, czworki = 0, piatki = 0, szostki = 0;
        for (Integer integer : tab) {

            if (integer == 1) onesCounter++;
            else {
                if (onesCounter == 1) jedynki++;
                if (onesCounter == 2) dwojki++;
                if (onesCounter == 3) trojki++;
                if (onesCounter == 4) czworki++;
                if (onesCounter == 5) piatki++;
                if (onesCounter >= 6) szostki++;
                onesCounter = 0;
            }
        }
        System.out.print("\n1: "+jedynki+"\n2: "+dwojki+"\n3: "+trojki+"\n4: "+czworki+"\n5: "+piatki+"\n6: "+szostki);
        if((jedynki >= 2315 && jedynki <= 2685) && (dwojki >= 1114 && dwojki <=1386) && (trojki >= 527 && trojki <= 723) && (czworki >= 240 && czworki <=384) && (piatki >= 103 && piatki <=209) && (szostki >= 103 && szostki <= 209)) {
            System.out.print("\nSeries test has been finished successfuly");
            number_of_successes ++;
        }
        else {
            System.out.print("\nSeries test has failed");
            number_of_fails ++;
        }
    }

    private static void longSet(){
        System.out.print("\n\nLong set test has started");
        counter = counter.subtract(counter);
        int onesCounter = 0;
        int zerosCounter = 0;
        int maxOnes = 0;
        int maxZeros = 0;
        boolean success = true;

        for (Integer integer : tab) {
            if (integer == 1) {
                if (zerosCounter > maxZeros) maxZeros = zerosCounter;
                zerosCounter = 0;
                onesCounter++;
            }
            else {
                if (onesCounter > maxOnes) maxOnes = onesCounter;
                onesCounter = 0;
                zerosCounter++;
            }
            if (onesCounter == 26 || zerosCounter == 26) {
                success = false;
                break;
            }
        }

        System.out.print("\nLongest 1's set: "+maxOnes);
        System.out.print("\nLongest 0's set: "+maxZeros);

        if(success) {
            System.out.print("\nLong set test has been finished successfuly");
            number_of_successes ++;
        }
        else {
            System.out.print("\nLong set test has failed");
            number_of_fails ++;
        }
    }

    private static void poker() {
        System.out.print("\n\nPoker test has started");
        String segment = "";
        int n = 0;
        int _0 = 0, _1 = 0, _2 = 0, _3 = 0, _4 = 0, _5 = 0, _6 = 0, _7 = 0, _8 = 0, _9 = 0, _10 = 0, _11 = 0, _12 = 0, _13 = 0, _14 = 0, _15 = 0;
        for (int i = 0; i < tab.size() / 4; i++) {
            for (int j = 0; j < 4; j++) {
                segment = segment.concat(String.valueOf(tab.get(n)));
                n++;
            }
            switch (segment) {
                case "0000":
                    _0++;
                    break;
                case "0001":
                    _1++;
                    break;
                case "0010":
                    _2++;
                    break;
                case "0011":
                    _3++;
                    break;
                case "0100":
                    _4++;
                    break;
                case "0101":
                    _5++;
                    break;
                case "0110":
                    _6++;
                    break;
                case "0111":
                    _7++;
                    break;
                case "1000":
                    _8++;
                    break;
                case "1001":
                    _9++;
                    break;
                case "1010":
                    _10++;
                    break;
                case "1011":
                    _11++;
                    break;
                case "1100":
                    _12++;
                    break;
                case "1101":
                    _13++;
                    break;
                case "1110":
                    _14++;
                    break;
                case "1111":
                    _15++;
                    break;
            }
            segment = "";
        }

        double sigma = (_0 * _0) + (_1 * _1) + (_2 * _2) + (_3 * _3) + (_4 * _4) + (_5 * _5) + (_6 * _6) + (_7 * _7) + (_8 * _8) + (_9 * _9) + (_10 * _10) + (_11 * _11)
                + (_12 * _12) + (_13 * _13) + (_14 * _14) + (_15 * _15);
        double x = ((16.00 / 5000.00) * sigma) - 5000.00;
        System.out.print("\nSigma: "+sigma+"\nX: "+x);
        if (2.16 < x && x < 46.17) {
            System.out.print("\nPoker test has been finished successfuly");
            number_of_successes ++;
        }

        else {
            System.out.print("\nTest has been failed");
            number_of_fails ++;
        }
    }

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();
        SecureRandom rand = new SecureRandom();
        rand.nextInt();
        int bit = 256;
        n = getN(bit, rand);
        System.out.print("\nN= " + n);
        seed = generateSeed(n, bit, rand);
        System.out.print("\nSeed= " + seed);
        generate();
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.print("\ntime elapsed: " + elapsedSeconds);
        singleBits();
        series();
        longSet();
        poker();
        System.out.print("\n\nNumber of successful tests: " + number_of_successes);
        System.out.print("\nNumber of failed tests: "+ number_of_fails+"\n");
    }
}