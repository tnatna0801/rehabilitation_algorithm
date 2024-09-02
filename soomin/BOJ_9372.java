import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 신장트리 => 최소 간선 갯수는 N-1임
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int i = 0; i<M; i++){
                br.readLine();
            }
            sb.append(N-1).append("\n");
        }
        System.out.println(sb);
    }
}
