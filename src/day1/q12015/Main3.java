package day1.q12015;


// using segment tree to improvement speed
import java.util.*;

public class Main3 {

    static final int MAX_NUMBER = 10;

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

    static int query(int[] tree, int node, int start, int end, int i, int j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return tree[node];
        int m1 = query(tree, node << 1, start, (start+end) >> 1, i, j);
        int m2 = query(tree, (node << 1) + 1, ((start+end) >> 1)+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return Math.max(m1, m2);
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

        tree[node] = Math.max(childMinL, childMinR);
        printLogging(" U  tree : " + Arrays.toString(tree));

        return tree[node];
    }

    public static void main(String[] args){
        int[] list;

//        int treeSize = (int) Math.pow(2, 20 + 1); // 2^20 = 1,048,576
        int treeSize = (int) Math.pow(2, 4 + 1); // 2^4 = 16
        int[] tree = new int[treeSize];

        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();

        list = readInts(sc, length);
        printLogging("list : " + Arrays.toString(list));

        for (Integer el : list) {
            int maxBefore = query(tree, 1, 1, MAX_NUMBER, 1, el - 1);
            int elVal = query(tree, 1, 1, MAX_NUMBER, el, el);
            int max = Math.max(maxBefore + 1, Math.max(1, elVal));
            printLogging(" U : " + el + "," + (max));
            updateTree(tree, 1, 1, MAX_NUMBER, el, max);
        }

//        System.out.println(query(tree, 1, 1, MAX_NUMBER, 1, MAX_NUMBER));
        System.out.println(tree[1]);
    }
}