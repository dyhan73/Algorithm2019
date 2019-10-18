package day2;

/**
 * https://www.acmicpc.net/problem/11872
 */

import java.util.*;

public class NimGameDevide01 {

    static int[] readInts(Scanner sc, int length) {
        int[] iList = new int[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] boxSize = readInts(sc, length);

//        ArrayList<int[]> boxList = new ArrayList<>();
//        for (int size : boxSize) {
//            boxList.add(new int[size]);
//        }

        int[] grundVals = calcGrund(17);

        System.out.println(Arrays.toString(grundVals));




    }

    private static int[] calcGrund(int length) {
        int[] grunds = new int[length + 1];

        for (int i=0; i<=length; i++) {
            boolean[] temp = new boolean[i+1];

            // 뺄 수 있는 경우에 grunds 값을 구하기 위함
            for (int j=0; j<i; j++) {
                temp[grunds[j]] = true; // used
            }

            // 나를 나눌 수 있는 경우의 수
            int xorRslt = 0;
            for (int j=1; j<i>>1; j++) {
                int xorVal = grunds[j] ^ grunds[i-j];
                temp[xorVal] = true; // used
            }

            int min=0;
            for (int j=0; j<=i; j++) {
                if (!temp[j]) {
                    min = j;
                    break;
                }
            }
            grunds[i] = min;
        }
        return grunds;
    }
}
