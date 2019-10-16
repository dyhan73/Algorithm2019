package playground;

import java.util.Arrays;
import java.util.Random;

public class MyTest01 {
    static void testOperators() {
        int a = 53;
        System.out.println(a);
        System.out.println(a << 1);
        System.out.println(a << 1 + 1);
        System.out.println((a << 1) + 1);

        System.out.println(a >> 1);
        System.out.println((a >> 1) + 1);

        int pos = 4;
        int start = 1;
        int end = 5;
//        if (pos <= (start+end) >> 1)

        int intVal = 1000000;
        System.out.println(intVal);
    }

    static int[] genRandomInt(int count, int start, int end) {
        Random gen = new Random();
        int[] result = new int[count];

        for (int i=0; i<count; i++) {
            result[i] = gen.nextInt(end+1 - start) + start;
        }
        return result;
    }

    public static void main(String[] args) {
//        testOperators();

        int[] list = genRandomInt(1000000, 1, 1000000);
//        int[] list = genRandomInt(7, 1, 1000000);
//        System.out.println(Arrays.toString(list));
        for (int i=0; i<list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }

}
