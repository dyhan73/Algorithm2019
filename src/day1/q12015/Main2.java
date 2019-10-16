package day1.q12015;

import java.time.LocalDateTime;
import java.util.*;

public class Main2 {
    static class Item {
        int key;
        int value;

        Item(int key, int val) {
            this.key = key;
            this.value = val;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
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
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
//        int[] list = new int[length];
//        for (int i=0; i<length; i++) {
//            list[i] = scanner.nextInt();
//        }
        int[] list = genRandomInt(length, 1, 1000000);

        LocalDateTime startTime = LocalDateTime.now();
//        System.out.println(startTime);
//        System.out.println(Arrays.toString(list));

        LinkedList<Item> linkedList = new LinkedList<>();

        for (Integer el : list) {
//            System.out.println("-- Item : " + el);
            boolean isWritten = false;

            int max = 0;
            for (int i=0; i<linkedList.size(); i++) {
                Item item = linkedList.get(i);
                if (item.key < el) {
                    if (max < item.value) max = item.value;
                } else if (item.key == el) {
                    if (max + 1 > item.value) item.value = max + 1;
                    isWritten = true;
                    break;
                } else {
                    linkedList.add(i, new Item(el, max + 1));
                    isWritten = true;
                    break;
                }
            }

            if (!isWritten) linkedList.add(new Item(el, max + 1));

//            System.out.println(linkedList);
        }

//        System.out.println("-- final");
//        System.out.println(linkedList);

        int max = 0;
        for (Item item : linkedList) {
            if (item.value > max) max = item.value;
        }
        System.out.println(max);

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(startTime);
        System.out.println(endTime);
    }
}
