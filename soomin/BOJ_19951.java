import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] ground = new int[N + 1];
        for (int i = 1; i <= N; i++) ground[i] = Integer.parseInt(st.nextToken());

        // 지시
        int[] diff = new int[N + 2];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

//            for(int j = a; j<=b; j++) { // 여기가 시간 초과의 원인 (최대 n*m)
//                ground[j] += amount;
//            }

            // 구간의 시작과 끝만 표시
            // 예) 2 4 3 => 0 0 3 0 0 -3 0 => 이렇게 해야 정확하게 2~4구간만 3이 더해짐
            diff[a] += amount;
            if (b + 1 > N) continue;
            diff[b + 1] -= amount;

        }

        // 업데이트
        // 출력
        StringBuilder sb = new StringBuilder();
        int current = 0;
        for (int i = 1; i <= N; i++) {
            current += diff[i];
            sb.append(ground[i] + current).append(" ");
        }

        System.out.println(sb);
    }
}
