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
 * 1차 시도 (시간초과)
 *   - Java hashCode() 사용 버전
 *   - String.substring() 사용 -> 타임아웃 될 것으로 예상
 *
 * 2차 시도 (1차시도보다 더 돌지만 시간초과)
 *   - string.substring 제거 필요 (new 제거)
 *   - 그러려면 새로운 hash 함수 필요
 *
 * 3차 시도
 *   - 매번 substring 의 해시를 재개산 하는 부분 개선
 *   - 첫 글자 값 빼고, base 곱하고, 마지막 글자 값 더하고... (뭔짓이여...)
 */
public class RabinKarp {
    static int getHash(String input, int start, int length) {
        final int base = 256;
        int hashVal = 0;
        for (int i=0; i<length; i++) {
            hashVal = hashVal * base + input.charAt(start + i);
        }

        return hashVal;
    }

    static int getRabinKarp01(String string, String pattern) {
        int p_hash = pattern.hashCode();

        for (int i=0; i <= string.length() - pattern.length(); i++) {
            String subString = string.substring(i, i + pattern.length());
            if (p_hash == subString.hashCode() && pattern.equals(subString)) {
                return 1;
            }
        }

        return 0;
    }

    static int getRabinKarp02(String string, String pattern) {
        int p_hash = getHash(pattern, 0, pattern.length());
        int s_hash;

        for (int i=0; i <= string.length() - pattern.length(); i++) {
            s_hash = getHash(string, i, pattern.length());
            if (p_hash == s_hash) {
                if (pattern.equals(string.substring(i, i + pattern.length())))
                    return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(getRabinKarp02("Sealover", "ver"));

        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String pattern = sc.nextLine();

        System.out.println(getRabinKarp02(string, pattern));
    }
}
