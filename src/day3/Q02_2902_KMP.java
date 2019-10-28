package day3;

import java.util.*;

public class Q02_2902_KMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                stringBuffer.append(input.charAt(i));
            }
        }

        System.out.println(stringBuffer.toString());
    }
}
