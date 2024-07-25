import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] tang = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            tang[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터?
        int[] fruit = new int[10]; // 과일 종류
        int left = 0, right = 0;
        int count = 0; // 탕후루에 포함된 과일 종류 수
        int max = 0; // 결과

        while(right < N) { // 흠

            if(fruit[tang[right]] == 0) count++; // 새로운 과일이면 종류 수 증가
            fruit[tang[right++]]++; // 과일 종류 업데이트

            while (count > 2) { // 종류가 2가지 이상이면 왼쪽 포인터 이동! 과일 종류가 2가지가 될 때까지 이동
                if(--fruit[tang[left]] == 0) count--; // 종류 감소
                left++;
            }

            max = Math.max(max, right - left);
        }

        System.out.println(max);
    }
}
