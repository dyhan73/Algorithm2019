package day1.q2042;

/**
 * https://www.acmicpc.net/problem/2042
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    static void printLogging(String string) {
        System.out.println(string);
    }

    static long[] readInts(Scanner sc, int length) {
        long[] iList = new long[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    static long[] initTree(long[] list, long[] tree, int node, int start, int end) {
        printLogging(String.format("initTree : node(%d), start(%d), end(%d)", node, start, end));
        if (tree == null) {
            int treeSize = 0;
            for (int i=0; treeSize < list.length; i++) {
                treeSize = (int) Math.pow(2, i);
            }
            treeSize <<= 1;
            tree = new long[treeSize];
        }

        if (start == end) {
            tree[node] = list[start];
        } else {
            initTree(list, tree, node << 1, start, (start+end) >> 1);
            initTree(list, tree, (node << 1) + 1, ((start + end) >> 1) + 1, end);
            tree[node] = tree[node << 1] + tree[(node << 1) + 1];
        }
        printLogging(" I  list : " + Arrays.toString(list));
        printLogging(" I  tree : " + Arrays.toString(tree));
        return tree;
    }

    static long query(long[] tree, int node, int start, int end, long i, long j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return tree[node];
        long m1 = query(tree, node << 1, start, (start+end) >> 1, i, j);
        long m2 = query(tree, (node << 1) + 1, ((start+end) >> 1)+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return m1 + m2;
    }

    static void replaceListItem(long[] list, long[] tree, long pos, long val) {
        list[(int)pos - 1] = val;
        printLogging(" U  list : " + Arrays.toString(list));

        updateTree(tree,1, 1, list.length, pos, val);
    }

    static long updateTree(long[] tree, int node, int start, int end, long pos, long val) {
        if (start == end) {
            tree[node] = val;
            printLogging(" U  tree : " + Arrays.toString(tree));
            return val;
        }

        long childMinL=0, childMinR = 0;
        if (pos <= (start+end) >> 1) {
            childMinL = updateTree(tree, node << 1, start, (start + end) >> 1, pos, val);
            childMinR = tree[(node << 1) + 1];
        } else {
            childMinR = tree[node << 1];
            childMinL = updateTree(tree, (node << 1) + 1, ((start + end) >> 1) + 1, end, pos, val);
        }

        tree[node] = childMinL + childMinR;
        printLogging(" U  tree : " + Arrays.toString(tree));

        return tree[node];
    }

    public static void main(String[] args){
        long[] list;
        long[] tree;

        Scanner sc = new Scanner(System.in);

        int length = 0, times = 0;

        length = sc.nextInt();
        times = sc.nextInt() + sc.nextInt();

        list = readInts(sc, length);
        printLogging("list : " + Arrays.toString(list));

        tree = initTree(list, null, 1, 0, length-1);
        printLogging("tree : " + Arrays.toString(tree));

        for (int i=0; i<times; i++) {
            long[] query = readInts(sc, 3);
            if (query[0] == 2) {
                long sum = query(tree, 1, 1, length, query[1], query[2]);
                printLogging("sum : " + sum);
                System.out.println(sum);
            } else if (query[0] == 1) {
                replaceListItem(list, tree, query[1], query[2]);
            }
        }
    }
}