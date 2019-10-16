package day1.q7578;

import java.time.LocalDateTime;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/7578
 * 공장 문제
 * 전략 : 첫째 배열은그냥 배열로 처리
 *       둘째 배열은 TreeMap 으로 <식별번호:index> 로 저장
 *       둘째 배열 index 를 segment tree 로 구성
 *       첫째 배열을 돌면서
 *           둘째 배열 TreeMap 에서 index 찿고
 *           그 index 를 seg tree 에서 찿아 1로 업데이트 (트리 상위 노드는 하위 트리 총 값 저장)
 *
 * 쿼리 전략
 *     범위가 오른쪽 자식 노드 -> 오른쪽 자식노드로 recursive 호출 후 결과 응답
 *     범위가 왼쪽 자식 노드 -> 왼쪽 자식노드 recursive 결과 + 오른쪽 자식 노드값
 *     leap 노드이면 0 반환
 *
 * Input
 *     5
 *     132 392 311 351 231
 *     392 351 132 311 231
 * Output
 *     3
 */
public class Main {

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
        else return m1 + m2;
    }

    static int updateTree(int[] tree, int node, int start, int end, int pos, int val) {
        if (start == end) {
            tree[node] = val;
            System.out.println(" U  tree : " + Arrays.toString(tree));
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
        System.out.println(" U  tree : " + Arrays.toString(tree));

        return tree[node];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] list1 = readInts(sc, length);
        int[] list2 = readInts(sc, length);


        System.out.println("start : " + LocalDateTime.now());

        // list2 를 TreeMap 으로 변환 (TreeMap으로 바로 받으면 시간&메모리 단축 가능)
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i=0; i<list2.length; i++) {
            treeMap.put(list2[i], i);
        }

        // Segment Tree 구성
        int treeSize = 0;
        for (int i=0; treeSize < length; i++) {
            treeSize = (int) Math.pow(2, i);
        }
        treeSize <<= 1;
        int[] tree = new int[treeSize];

        // list1 순회
        int crossCount = 0;
        for (int el : list1) {
            crossCount += query(tree, 1, 1, length, treeMap.get(el) + 1, length);
            updateTree(tree, 1, 1, length, treeMap.get(el) + 1, 1);
        }

        System.out.println(crossCount);
        System.out.println("end   : " + LocalDateTime.now());
    }
}
