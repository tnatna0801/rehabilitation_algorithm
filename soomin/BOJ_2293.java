import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];
        for(int i = 0; i<n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin);

        int[] dp = new int[k+1];
        dp[0] = 1;

        for(int i = 0; i<n; i++){ // 해당 동전의 가치로 만들 수 있는 경우의 수
            for(int j = coin[i]; j<=k; j++){ // 해당 동전의 가치보다 큰 금액 탐색 => 1부터하면 중복될 수도?
                dp[j] += dp[j-coin[i]]; // coin[i]으로 j원을 만들 수 있는 경우의 수는 j-coin원을 만들 수 있는 경우의 수와 같슴다.
            }
        }

        System.out.println(dp[k]);
    }
}
