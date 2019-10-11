package day1.minimum;

/**
 * https://www.acmicpc.net/problem/10868
 */

import java.util.*;
public class Main{
    int[] list;
    int[] tree;

    public String getStringOfArray(int[] arr) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (int i=0; i<arr.length; i++) {
            buffer.append(arr[i]).append(",");
        }
        buffer.append("]");

        return buffer.toString();
    }

    public void readItems(Scanner sc, int length) {
        this.list = new int[length];
        for (int i=0; i<length; i++) {
            this.list[i] = sc.nextInt();
        }
        this.tree = new int[(int) ((length +1) * Math.log(length)) + 1];
    }

    public void initTree(int node, int start, int end) {
        System.out.println(String.format("initTree : node(%d), start(%d), end(%d)", node, start, end));
        if (start == end) {
            this.tree[node] = this.list[start];
        } else {
            initTree(node*2, start, (start+end) /2);
            initTree(node * 2 + 1, (start + end) / 2 + 1, end);
            this.tree[node] = Math.min(this.tree[node * 2], this.tree[node * 2 + 1]);
        }
        System.out.println("    list : " + getStringOfArray(this.list));
        System.out.println("    tree : " + getStringOfArray(this.tree));
    }

    public int query(int node, int start, int end, int i, int j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return this.tree[node];
        int m1 = query(node * 2, start, (start+end)/2, i, j);
        int m2 = query(node * 2 + 1, (start+end)/2+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return Math.min(m1, m2);
    }


    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int length, times;
        length = sc.nextInt();
        times = sc.nextInt();

        Main proc = new Main();
        proc.readItems(sc, length);
        System.out.println("list : " + proc.getStringOfArray(proc.list));

        proc.initTree(1, 0, length-1);
        System.out.println("tree : " + proc.getStringOfArray(proc.tree));

        for (int i=0; i<times; i++) {
            int min = proc.query(1, 1, length, sc.nextInt(), sc.nextInt());
            System.out.println("min : " + min);
        }
    }
}