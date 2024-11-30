import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 단어, 알파벳 대문자
        // 대문자를 숫자로 바꿔서 N개의 수를 합하는 문제?
        // 같은 알파벳은 같은 숫자로
        // 그 수의 합을 최대로 만들 것

        int N = Integer.parseInt(br.readLine());
        int[] alpha = new int[26];
        for(int i = 0; i<N; i++){

            String str = br.readLine(); // 입력 받은 문자
            for(int j = 0; j<str.length(); j++){ // 자릿수 체크
                alpha[str.charAt(j) - 'A'] += (int)Math.pow(10, str.length() - 1 - j); // 맨 앞부터니까
            }
        }

        Arrays.sort(alpha);
        int result = 0;
        int num = 9;
        for(int i = 25; i>=0; i--) { // 큰자리수 부터
            result += alpha[i] * num;
            num--;
        }

        System.out.println(result);
    }
}
