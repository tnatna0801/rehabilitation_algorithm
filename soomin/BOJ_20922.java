import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] cnt = new int[100001]; // 숫자는 10,000이하의 정수

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, max = 0;
        while(true) {

            if(end == N) break;

            while(true) { // end를 가능한 만큼 이동 (최장)
                if(end == N || cnt[arr[end]] + 1 > K) break;
                cnt[arr[end++]]++; // 갯수 증가
            }

            max = Math.max(end - start, max);

            --cnt[arr[start++]]; // end는 최대로 있고 start를 조정
        }

        System.out.print(max);

    }
}
