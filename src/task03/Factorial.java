package task03;

//Find the sum of the digits in the number 100! (i.e. 100 factorial)
//{Correct answer: 648}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by Ivan on 05.07.2016. 14:34 - 15:17
 */
public class Factorial {

    public static void main(String[] args) throws IOException {

//        System.out.print("Input number: ");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int input = Integer.parseInt(reader.readLine());
//        reader.close();

        int input = 100;
        System.out.println("Sum of the digits = " + summaryDigits(factorial(input)));
    }


    /* convert BigInteger to String and split to char array than every digit will be sum*/
    public static int summaryDigits(BigInteger bigInteger){
        int sumDigits = 0;
        char[] digits = bigInteger.toString().toCharArray();

        for (int i = 0; i < digits.length; i++) {
            sumDigits += Integer.parseInt(String.valueOf(digits[i]));
        }
        return sumDigits;
    }

    /* use recursion with BigInteger which will be enough amount of memory and accuracy*/
    public static BigInteger factorial(int n) {

        return n  <= 1 ? BigInteger.ONE : factorial(n-1).multiply(BigInteger.valueOf(n));
    }
}