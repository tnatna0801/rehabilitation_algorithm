import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_16916_부분_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();

        int[] pi = getPi(p);
        int ans = kmp(s, p, pi);
        System.out.println(ans);
    }

    /**
     * 패턴 정보를 계산할 함수
     * @param p 패턴 문자열
     * @return 패턴 정보
     */
    private static int[] getPi(String p) {
        // 패턴 정보를 저장할 배열
        int[] pi = new int[p.length()];
        // 패턴 길이를 저장할 변수
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            // 1. 현재 비교하는 두 문자가 다를 경우
            while (true) {
                // 1-1. 현재 패턴에서 비교되는 문자의 위치가 0이라면 멈춤
                if (j == 0) break;
                // 1-2. 현재 비교되는 두 문자가 같다면 멈춤
                if (p.charAt(i) == p.charAt(j)) break;
                // 1-3. 다음 패턴의 위치를 이전 위치에 저장된 위치로 수정
                j = pi[--j];
            }
            // 2. 현재 비교하는 두 문자가 같을 경우
            // 현재 위치에 패턴 길이를 저장합니다.
            if (p.charAt(i) == p.charAt(j)) pi[i] = ++j;
        }

        return pi;
    }

    /**
     * 문자열과 패턴을 통해 부분 문자열 여부를 확인할 함수
     * @param s 문자열
     * @param p 패턴
     * @param pi 패턴 정보
     * @return 부분 문자열 여부
     */
    private static int kmp(String s, String p, int[] pi) {
        // 패턴 길이를 저장할 변수
        int j = 0;
        // 패턴의 끝을 판별할 변수
        int end = p.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            // 3. 현재 비교하는 두 문자가 다를 경우
            while (true) {
                // 3-1. 현재 패턴에서 비교되는 문자의 위치가 0이라면 멈춤
                if (j == 0) break;
                // 3-2. 현재 비교되는 두 문자가 같다면 멈춤
                if (s.charAt(i) == p.charAt(j)) break;
                // 3-3. 다음 패턴의 위치를 이전 위치에 저장된 위치로 수정
                j = pi[--j];
            }
            // 4. 현재 비교하는 두 문자가 다를 경우
            // 4-1. 건너뛰기
            if (s.charAt(i) != p.charAt(j)) continue;
            // 4-2. 패턴의 끝까지 비교했는지 확인
            // 4-2-1. 끝이라면 정답 출력
            if (j == end)
                return 1;
            // 4-2-2. 끝이 아니라면 비교되는 패턴의 문자열 위치 증가
            j++;
        }

        return 0;
    }
}