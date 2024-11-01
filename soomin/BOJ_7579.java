import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 앱 개수
        int M = Integer.parseInt(st.nextToken()); // 추가로 필요한 메모리 바이트

        // 입력
        int[] memory = new int[N+1];
        int[] cost = new int[N+1];

        // 메모리 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        // 비용 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // 냅색 알고리즘
        // dp[i][j]: i번째 앱까지 고려했을 때, 비용 j로 얻을 수 있는 최대 메모리
        int[][] dp = new int[N+1][10001]; // 100 * 100

        int answer = Integer.MAX_VALUE; // 최소 비용을 구하기 위한 변수
        for(int i = 1; i<=N; i++){
            for(int j = 0; j<=10000; j++){ // 필요한 메모리 양보다 더 커도 상관 없기때문에 비용의 최댓 값까지 탐색

                dp[i][j] = dp[i-1][j]; // 현재 앱을 사용하지 않았을 때의 최대 메모리를 dp 배열에 저장
                if(j < cost[i]) continue; // 현재 비용이 j보다 작으면 현재 앱을 추가할 수 없음

                // 현재 앱을 사용할 경우와 사용하지 않을 경우 중 더 큰 메모리를 선택
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cost[i]] + memory[i]);

                // 최소 비용 갱신
                if(dp[i][j] >= M) answer = Math.min(answer, j);
            }
        }


        System.out.println(answer);
    }
}
