package day3;

import java.util.*;

/**
 * Rabin-Karp 알고리즘
 * 문자열 S에서 패턴 P를 찾을 때,
 * P의 해시값을 구한 후 S를 P의 길이만큼 순차적으로 해시값을 구해 P의 해시값과 비교
 *
 * 1. P의 해시값
 * 2. loop : P의 길이만큼 S를 순회
 *    2-1 : S의 부분 문자열에 대한 해시값 계산
 *    2-2 : 해시값이 동일하면 실제 문자열 비교 후 성공 시 반환
 * 3. 실패 반환
 *
 * Java hashCode() 사용 버전
 * - String.substring() 사용 -> 타임아웃 될 것으로 예상
 */
public class RabinKarp {
    static int hashCode(String in) {
        return in.hashCode();
    }

    static int getRabinKarp(String string, String pattern) {
        int p_hash = pattern.hashCode();

        for (int i=0; i <= string.length() - pattern.length(); i++) {
            String subString = string.substring(i, i + pattern.length());
            if (p_hash == subString.hashCode() && pattern.equals(subString)) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(getRabinKarp("Sealover", "ver"));

        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String pattern = sc.nextLine();

        System.out.println(getRabinKarp(string, pattern));
    }
}
