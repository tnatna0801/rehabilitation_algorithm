import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2607 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 같은 문자는 같은 개수 만큼 있다 이게 무슨 말이지? => D와 DD는 불가능
        // 단어의 수는 100개 이하
        // 단어의 길이는 10 이하
        String target = br.readLine();
        int[] alpha = new int[26];
        for(char now : target.toCharArray()){
            alpha[now - 'A']++;
        }

        // 비슷한 단어 찾기
        int result = 0;
        for(int i = 1; i<N; i++) {
            String word = br.readLine();
            int[] copyAlpha = Arrays.copyOf(alpha, alpha.length);

            // 길이의 차이가 1이상이면 비슷한 단어가 될 수 없음
            if(Math.abs(word.length() - target.length()) > 1) continue;

            // 같은 문자 확인
            int count = 0; // 기준값과 같은 알파벳의 개수
            for(char now : word.toCharArray()){
                if(copyAlpha[now - 'A'] > 0) {
                    count++;
                    copyAlpha[now - 'A']--; // 중복체크를 방지하기 위함
                }
            }

            // 비교
            // 1. 기준값보다 길이가 1만큼 작을 경우 => 2개가 맞아야함
            if(word.length() < target.length() && count == word.length())
                result++;
            // 2. 기준값보다 길이가 1만큼 큰 경우 =>
            else if(word.length() > target.length() && count == target.length())
                result++;
            else if(word.length() == target.length()) {
                if(count == target.length()) result++;
                else if(count == target.length() - 1) result++;
            }
        }
        System.out.println(result);
    }
}
