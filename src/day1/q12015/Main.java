package day1.q12015;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        int[] list = new int[length];
        for (int i=0; i<length; i++) {
            list[i] = scanner.nextInt();
        }

        System.out.println(Arrays.toString(list));

        Map<Integer, Integer> map = new TreeMap<>();

        for (Integer item : list) {
            System.out.println("-- Item : " + item);
            boolean isWritten = false;
            Map<Integer, Integer> tempMap = new HashMap<>();

            for (Integer key : map.keySet()) {
                tempMap.put(key, map.get(key));
                if (key < item) {
                    if (!tempMap.containsKey(item) || tempMap.get(item) < map.get(key) + 1)
                        tempMap.put(item, map.get(key) + 1);
                    isWritten = true;
                } else if (key.equals(item)) {
                    isWritten = true;
                }
            }
            if (!isWritten) {
                tempMap.put(item, 1);
            }
            map = tempMap;
            for (Integer key : map.keySet()) {
                System.out.println(key + " : " + map.get(key));
            }
        }

        System.out.println("-- final");
        for (Integer key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println(Collections.max(map.values()));
    }
}
