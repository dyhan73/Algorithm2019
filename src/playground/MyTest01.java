package playground;

import java.time.LocalDateTime;
import java.util.*;

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

    static void testLoopingTime() {
        System.out.println("start : " + LocalDateTime.now());
        int[] list = new int[1000000];

        // 0.009초 소요
        for (int i=0; i<list.length; i++) {
            list[i] = i;
        }
        System.out.println("array loop: " + LocalDateTime.now());

        // 0.12초 소요
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0; i<list.length; i++) {
            hashMap.put(i, 1);
        }

        // 0.01초 소요
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i=0; i<10000; i++) {
            treeMap.put(i, i);
        }

        System.out.println("end   : " + LocalDateTime.now());
    }

    private static void testSortable() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(4);
        list.add(5);
        list.add(7);
        list.sort(Integer::compareTo);


        System.out.println(list.toString());
    }

    public static void main(String[] args) {
//        testOperators();

//        int[] list = genRandomInt(5, 1, 1000000);
//        System.out.println(Arrays.toString(list));

//        testLoopingTime();

        testSortable();
    }



}
