import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 부분합의 합

        // 입력
        st = new StringTokenizer(br.readLine());
        int[] sums = new int[N+1];
        sums[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            sums[i] = sums[i-1] + Integer.parseInt(st.nextToken());
        }

        // 누적합??
        Map<Integer, Integer> map = new HashMap<>(); // 부분합, 부분합의 경우의 수를 저장하는 map임
        map.put(0, 1); // 아무것도 더하지 않은 상태를 경우의 수 1개로 친다.
        long count = 0;
        for(int i = 0; i<N; i++) {
            count += map.getOrDefault(sums[i] - K, 0); // 이전에 등장한 경우가 있는 지
            map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
        }

        System.out.println(count);

    }
}
