package day2;

/**
 * https://www.acmicpc.net/problem/9658
 * 문제 : 돌 N 개 (1 <= N <= 1000)
 *     상근(SK) - 창영(CY) 순서로 진행, 승자 이니셜 출력
 *     1, 3, 4 개의 돌을 가져갈 수 있음
 * 제약 : 1초, 128M,
 * Input : 5
 * Output : SK
 */

import java.util.Arrays;
import java.util.Scanner;

public class CombinatorialGame02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] picks = { 1, 3, 4 };
        int nStone = sc.nextInt();
        boolean[] bTable = new boolean[nStone + 1];

        bTable[0] = true;

        for (int i=1; i<bTable.length; i++) {
            for (int j=0; j<picks.length; j++) {
                if (i - picks[j] >= 0 && bTable[i-picks[j]] == false) {
                    bTable[i] = true;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(bTable));

        if (bTable[bTable.length - 1])
            System.out.println("SK");
        else System.out.println("CY");

    }
}
