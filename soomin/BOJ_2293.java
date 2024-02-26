import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 각 동전의 가치를 저장하는 배열
        int[] cost = new int[n];
        // dp 배열 초기화
        int[] dp = new int[k + 1];

        // 0원을 만드는 경우의 수는 1 => 중요!
        dp[0] = 1;

        // 각 동전의 가치를 입력 받기 && 동전 별로 만들 수 있는 경우의 수 계산
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            for (int j = cost[i]; j <= k; j++) {
                // 현재 동전을 사용하여 금액 j를 만들 수 있는 경우의 수를 누적
                dp[j] += dp[j - cost[i]];
            }
        }

        // 출력
        System.out.println(dp[k]);
    }
}
