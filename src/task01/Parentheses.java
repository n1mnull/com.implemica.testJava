package task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ivan on 05.07.2016. 15:37 - 18:46
 */

/**
 * in this task I represent "((()()))" like "11101000" where "(" = "1" and ")" = "0"
 * in case if I had n-pair bracket that mean I have binary number with length = 2*n
 * I will generate all binary number with length = 2*n
 * I exclude number which haven`t equals amount digits "one" and "zero" (ex. 111100 or 000010)
 * also i will exclude binary number which have "0" = ")" before "1" = "(" (ex. "000111" = ")))((("   )
 * The answer is the rest of numbers.
*/

public class Parentheses {

    public static void main(String[] args) throws IOException {

        System.out.print("Input number of bracket: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        reader.close();
        ArrayList<Integer> listBracers = generateBracers(input*2);
        System.out.println("The number of correct bracket expression which contains " + input + " pair bracket = " + listBracers.size());
    }

    /* the method generate array binary numbers with length 2*n (ex. n=3 100000 - 111111)
     method return the amount of numbers
    * */
    public static ArrayList<Integer> generateBracers(int numberBracers) {
        ArrayList<Integer> listBracers = new ArrayList<>();
        int beginBinaryNumber = (int)Math.pow(2,numberBracers-1);
        int endBinaryNumber = (int)(Math.pow(2,numberBracers))-1;

        for (int i = beginBinaryNumber; i <= endBinaryNumber; i++) {
            if (isFullBracer(Integer.toBinaryString(i))) {
                listBracers.add(i);
            }
        }
        return listBracers;
    }

    /* the method analyzes the incoming binary number and check fulfilment of conditions
        * */
    public static boolean isFullBracer(String binaryString) {
        char[] digits = binaryString.toCharArray();
        int countBracers = 0;
        int countOpenedBracers = 0;
        for (int i = 0; i < digits.length; i++) {
            countBracers += Integer.parseInt(String.valueOf(digits[i]));
            if (digits[i] == '1') {
                countOpenedBracers++;
            }
            if (digits[i] == '0') {
                countOpenedBracers--;
                if (countOpenedBracers < 0) {
                    return false;
                }
            }
        }
        return countBracers == binaryString.length()/2 ? true : false;
    }
}