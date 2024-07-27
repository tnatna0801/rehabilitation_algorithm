import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        // 1 1 1 2 2 3 4 5 7 9 12에서 규칙을 찾았다.
        // dp[N] = dp[N - 2] + dp[N - 3]
        for(int i = 4; i<=100; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }

        for(int tc = 0; tc<T; tc++) {

            int N = Integer.parseInt(br.readLine());

            // DP다 무조건 DP다
            sb.append(dp[N] + "\n");
        }

        System.out.println(sb);
    }
}
