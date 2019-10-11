package day1.q2042;

/**
 * https://www.acmicpc.net/problem/2042
 *
 */

import java.util.*;
public class Main{
    long[] list;
    long[] tree;

    public static void printLogging(String string) {
        System.out.println(string);
    }

    long[] readInts(Scanner sc, int length) {
        long[] iList = new long[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    void initTree(int node, int start, int end) {
        printLogging(String.format("initTree : node(%d), start(%d), end(%d)", node, start, end));
        if (this.tree == null) {
            int treeSize = 0;
            for (int i=0; treeSize < this.list.length; i++) {
                treeSize = (int) Math.pow(2, i);
            }
            treeSize <<= 1;
            this.tree = new long[treeSize];
        }

        if (start == end) {
            this.tree[node] = this.list[start];
        } else {
            initTree(node << 1, start, (start+end) >> 1);
            initTree((node << 1) + 1, ((start + end) >> 1) + 1, end);
            this.tree[node] = this.tree[node << 1] + this.tree[(node << 1) + 1];
        }
        printLogging(" I  list : " + Arrays.toString(this.list));
        printLogging(" I  tree : " + Arrays.toString(this.tree));
    }

    long query(int node, int start, int end, long i, long j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return this.tree[node];
        long m1 = query(node << 1, start, (start+end) >> 1, i, j);
        long m2 = query((node << 1) + 1, ((start+end) >> 1)+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return m1 + m2;
    }

    void replaceListItem(long pos, long val) {
        this.list[(int)pos - 1] = val;
        printLogging(" U  list : " + Arrays.toString(this.list));

        // 일단 쉽게 재구성 (시간초과 실패 ㅋ)
//        this.tree = null;
//        this.initTree(1, 0, this.list.length-1);
        updateTree(1, 1, this.list.length, pos, val);
    }

    long updateTree(int node, int start, int end, long pos, long val) {
        if (start == end) {
            this.tree[node] = val;
            printLogging(" U  tree : " + Arrays.toString(this.tree));
            return val;
        }

        long childMinL=0, childMinR = 0;
        if (pos <= (start+end) >> 1) {
            childMinL = updateTree(node << 1, start, (start + end) >> 1, pos, val);
            childMinR = this.tree[(node << 1) + 1];
        } else {
            childMinR = this.tree[node << 1];
            childMinL = updateTree((node << 1) + 1, ((start + end) >> 1) + 1, end, pos, val);
        }

        this.tree[node] = childMinL + childMinR;
        printLogging(" U  tree : " + Arrays.toString(this.tree));

        return this.tree[node];
    }

    public static void main(String[] args){
        Main proc = new Main();
        Scanner sc = new Scanner(System.in);

        int length = 0, times = 0;

        length = sc.nextInt();
        times = sc.nextInt() + sc.nextInt();

        proc.list = proc.readInts(sc, length);
        printLogging("list : " + Arrays.toString(proc.list));

        proc.initTree(1, 0, length-1);
        printLogging("tree : " + Arrays.toString(proc.tree));

        for (int i=0; i<times; i++) {
            long[] query = proc.readInts(sc, 3);
            if (query[0] == 2) {
                long sum = proc.query(1, 1, length, query[1], query[2]);
                printLogging("sum : " + sum);
                System.out.println(sum);
            } else if (query[0] == 1) {
                proc.replaceListItem(query[1], query[2]);
            }
        }
    }
}