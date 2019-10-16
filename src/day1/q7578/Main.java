package day1.q7578;

import java.util.*;

/**
 * https://www.acmicpc.net/problem/7578
 * 공장 문제
 * 전략 : 첫째 배열은그냥 배열로 처리
 *       둘째 배열은 TreeMap 으로 (식별번호:index) 로 저장
 *       둘째 배열 index 를 segment tree (summation) 로 구성
 *       첫째 배열을 돌면서
 *           둘째 배열 TreeMap 에서 index 찿고
 *           그 index 를 seg tree 에서 찿아 1로 업데이트
 *
 * 쿼리 전략
 *     Summation
 *
 * Input
 *     5
 *     132 392 311 351 231
 *     392 351 132 311 231
 * Output
 *     3
 */
public class Main {

    private static int[] readInts(Scanner sc, int length) {
        int[] iList = new int[length];
        for (int i=0; i<length; i++) {
            iList[i] = sc.nextInt();
        }
        return iList;
    }

    private static int getTreeSize(int length) {
        int treeSize = 0;
        for (int i=0; treeSize < length; i++) {
            treeSize = (int) Math.pow(2, i);
        }
        treeSize <<= 1;
        return treeSize;
    }

    private static int query(int[] tree, int node, int start, int end, int i, int j) {
        if (i > end || j < start) return -1;
        if (i <= start && end <= j) return tree[node];
        int m1 = query(tree, node << 1, start, (start+end) >> 1, i, j);
        int m2 = query(tree, (node << 1) + 1, ((start+end) >> 1)+1, end, i, j);

        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        else return m1 + m2;
    }

    private static int updateTree(int[] tree, int node, int start, int end, int pos, int val) {
        if (start == end) {
            tree[node] = val;
            System.out.println(" U  tree : " + Arrays.toString(tree));
            return val;
        }

        int leftChildValue=0, rightChildValue = 0;
        if (pos <= (start+end) >> 1) {
            leftChildValue = updateTree(tree, node << 1, start, (start + end) >> 1, pos, val);
            rightChildValue = tree[(node << 1) + 1];
        } else {
            rightChildValue = tree[node << 1];
            leftChildValue = updateTree(tree, (node << 1) + 1, ((start + end) >> 1) + 1, end, pos, val);
        }

        tree[node] = leftChildValue + rightChildValue;
        System.out.println(" U  tree : " + Arrays.toString(tree));

        return tree[node];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] list1 = readInts(sc, length);
        int[] list2 = readInts(sc, length);

        // list2 를 TreeMap 으로 변환 (TreeMap으로 바로 받으면 벼룩 간 만큼 시간&메모리 단축 가능)
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i=0; i<list2.length; i++) {
            treeMap.put(list2[i], i);
        }

        // Segment Tree 구성
        int[] tree = new int[getTreeSize(length)];

        // list1 순회
        long crossCount = 0;
        for (int el : list1) {
            crossCount += query(tree, 1, 1, length, treeMap.get(el) + 1, length);
            updateTree(tree, 1, 1, length, treeMap.get(el) + 1, 1);
        }

        System.out.println(crossCount);
    }
}
