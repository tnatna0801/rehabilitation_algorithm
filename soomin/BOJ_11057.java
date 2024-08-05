import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11057 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 저번에 감소하는 수와 비슷한가? => 이번엔 갯수만 세면 된다! 흠 굳이 재귀를 할 필요가 있나?
        // 혹시 dp인가? => n(자릿수)과 각 자리의 숫자로 2차원 배열?
        N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][11];

        // 1자리 수일때는 자기 자신이 오르막 수임
        for(int i = 0; i<10; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i <= N; i++) { // n자리 수일때 오르막 수 구하기
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k<=j; k++) { // 오르막 수니까 j보다는 작은 수들을 모두 더함!
                    dp[i][j] += dp[i-1][k];
                }
                dp[i][j] %= 10007; //설마? => 오버플로우 가능성!
            }
        }

        long answer = 0;
        for(int i = 0; i<10; i++) {
            answer += dp[N][i];
        }

        System.out.println(answer % 10007);
    }
}
