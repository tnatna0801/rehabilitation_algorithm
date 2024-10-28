import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] order = new int[N]; // 위치

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            int count = 0; // 빈칸의 갯수를 세는 변수
            
            for (int j = 0; j < N; j++) {
                if (order[j] > 0) continue;
                if (count == num) {
                    order[j] = i + 1;
                    break;
                }
                count++;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(order[i] + " ");
        }

        System.out.println(sb);
    }
}
