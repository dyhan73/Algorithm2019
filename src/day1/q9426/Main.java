package day1.q9426;

/**
 * https://www.acmicpc.net/problem/9426
 * 중앙값 측정
 */

import java.util.*;

public class Main {
    static final int MIN_VAL = 0;
    static final int MAX_VAL = 65535;

    private static int[] readInts(Scanner sc, int length) {
        int[] iList = new int[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length, windowSize;
        length = sc.nextInt();
        windowSize = sc.nextInt();

        int[] list = readInts(sc, length);

        int[] tree = initTree(list, windowSize);

        long midSum = 0;
        int mid;
        for (int i=0; i<=length - windowSize; i++) {
            mid = getMid(tree, 1, MIN_VAL, MAX_VAL, (windowSize + 1)>> 1);
            midSum += mid;
//            System.out.println(mid + ", " + midSum);

            if (i+windowSize < list.length) {
                updateTree(tree, 1, MIN_VAL, MAX_VAL, list[i], -1);
                updateTree(tree, 1, MIN_VAL, MAX_VAL, list[i + windowSize], 1);
            }

        }
        System.out.println(midSum);
    }

    private static int getMid(int[] tree, int node, int start, int end, int midPos) {
        if (start == end) return start;
        if (midPos <= tree[node << 1])
            return getMid(tree, node << 1, start, start + ((end - start) >> 1), midPos);
        else
            return getMid(tree, (node << 1) + 1, start + ((end - start) >> 1) + 1, end, midPos - tree[node << 1]);
    }

    private static int[] initTree(int[] list, int windowSize) {
        int treeSize = getTreeSize(MAX_VAL + 1);
        int[] tree = new int[treeSize];
        for (int i=0; i<windowSize; i++) {
            updateTree(tree, 1, MIN_VAL, MAX_VAL, list[i], 1);
//            System.out.println(Arrays.toString(tree));
        }

        return tree;
    }

    private static int updateTree(int[] tree, int node, int start, int end, int pos, int incVal) {
        if (start == end) {
            tree[node] += incVal;
            return tree[node];
        }
        int lv = 0, rv = 0;
        if (pos <= start + ((end - start) >> 1)) {
            lv = updateTree(tree, node << 1, start, start + ((end - start) >> 1), pos, incVal);
            rv = tree[(node << 1) + 1];
        } else {
            lv = tree[(node << 1)];
            rv = updateTree(tree, (node << 1) + 1, start + ((end - start) >> 1) + 1, end, pos, incVal);
        }

        tree[node] = lv + rv;
        return tree[node];
    }

    private static int getTreeSize(int size) {
        int treeSize = 0;
        for (int i=0; treeSize < size; i++) {
            treeSize = (int) Math.pow(2, i);
        }
        return treeSize << 1;
    }
}
