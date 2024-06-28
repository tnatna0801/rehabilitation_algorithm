import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++){
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // k = 거처가는 노드
        for(int k = 0; k<N; k++) {
            // i = 출발 노드
            for(int i = 0; i<N; i++) {
                // j = 도착 노드
                for(int j = 0; j<N; j++) {
                    if(dp[i][k] == 1 && dp[k][j] == 1) { // i에서 k, k에서 j로 가는 경로가 있을 경우에만 1
                        dp[i][j] = 1;
                    }
                }
            }
        }

        // 결과 출력
        StringBuilder sb;
        for(int i = 0; i<N; i++) {
            sb = new StringBuilder();
            for(int j = 0; j<N; j++) {
                sb.append(dp[i][j] + " ");
            }
            System.out.println(sb);
        }
    }
}
