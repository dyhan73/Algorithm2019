package day2;

/**
 * https://www.acmicpc.net/problem/11867
 */

import java.util.Arrays;
import java.util.Scanner;

public class DevideBox01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int box1 = sc.nextInt();
        int box2 = sc.nextInt();

        boolean[] list = new boolean[Math.max(box1, box2) + 1];

        // 0개, 1개는 무의미하고, 2개짜리 박스가 만들어지면 진다! (false)
//        list[1] = true;
        for (int i=3; i<list.length; i++) {
//            list[i] = true;
            for (int j=1; j <= (i+1) >> 1; j++) {
                if (!list[j] && !list[i-j]) {
                    list[i] = true;
                    break;
                }
            }
        }

        /*
        2 -> 1, 1 (false)
        3 -> 1, 2 (true)
        4 -> 1, 3 (?), 2, 2 (false)
         */

        System.out.println(Arrays.toString(list));

    }
}
