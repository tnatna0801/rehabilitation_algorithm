import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] cost = new int[N + 1][2];
        int[] dp = new int[C+101]; // C명의 고객을 늘리기 위한 최소비용
        Arrays.fill(dp, 100000000);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // 비용 (무게)
            cost[i][1] = Integer.parseInt(st.nextToken()); // 고객 수 (가치)
        }


        for (int i = 1; i <= N; i++) {
            int num = cost[i][1];
            for (int j = num; j <= C+100; j++) {
                dp[j] = Math.min(dp[j], dp[j - num] + cost[i][0]);
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = C; i<= C+100; i++) {
            result = Math.min(dp[i], result);
        }

        System.out.println(result);

    }
}
