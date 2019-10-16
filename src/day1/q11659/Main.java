package day1.q11659;

/**
 * https://www.acmicpc.net/problem/11659
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void printLogging(String string) {
        System.out.println(string);
    }

    static int[] readInts(Scanner sc, int length) {
        int[] iList = new int[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    static int[] initTree(int[] list, int[] tree, int node, int start, int end) {
        printLogging(String.format("initTree : node(%d), start(%d), end(%d)", node, start, end));
        if (tree == null) {
            int treeSize = 0;
            for (int i=0; treeSize < list.length; i++) {
                treeSize = (int) Math.pow(2, i);
            }
            treeSize <<= 1;
            tree = new int[treeSize];
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

    static int query(int[] tree, int node, int start, int end, int i, int j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return tree[node];
        int m1 = query(tree, node << 1, start, (start+end) >> 1, i, j);
        int m2 = query(tree, (node << 1) + 1, ((start+end) >> 1)+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return m1 + m2;
    }

    static void replaceListItem(int[] list, int[] tree, int pos, int val) {
        list[(int)pos - 1] = val;
        printLogging(" U  list : " + Arrays.toString(list));

        updateTree(tree,1, 1, list.length, pos, val);
    }

    static int updateTree(int[] tree, int node, int start, int end, int pos, int val) {
        if (start == end) {
            tree[node] = val;
            printLogging(" U  tree : " + Arrays.toString(tree));
            return val;
        }

        int childMinL=0, childMinR = 0;
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
        int[] list;
        int[] tree;

        Scanner sc = new Scanner(System.in);

        int length = 0, times = 0;

        length = sc.nextInt();
        times = sc.nextInt();

        list = readInts(sc, length);
        printLogging("list : " + Arrays.toString(list));

        tree = initTree(list, null, 1, 0, length-1);
        printLogging("tree : " + Arrays.toString(tree));

        for (int i=0; i<times; i++) {
            int[] query = readInts(sc, 2);
            int sum = query(tree, 1, 1, length, query[0], query[1]);
            printLogging("sum : " + sum);
            System.out.println(sum);
        }
    }
}