import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] time = new int[100001]; // 각 위치에서 시간
        Map<Integer, Integer> path = new HashMap<>(); // 직전 경로 저장
        int[] d = { -1, 1};


        // bfs
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        time[N] = 1;

        while(!queue.isEmpty()) {

            int now = queue.poll();

            if(now == K) break;

            for(int i = 0; i<3; i++) {
                int next;
                if(i != 2) next = now + d[i];
                else next = now * 2;

                if(next < 0 || next > 100000 || time[next] != 0) continue;

                queue.add(next);
                time[next] = time[now] + 1;
                path.put(next, now);
            }
        }

        // 경로를 순서대로 출력하기 위해서 한번 stack에 넣었다 뺀다
        Stack<Integer> reversePath = new Stack<>();
        reversePath.add(K);

        int index = K;
        while(true) {
            if(index == N) break;
            reversePath.add(path.get(index));
            index = path.get(index);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(time[K] - 1).append("\n");
        while(!reversePath.isEmpty()) {
            sb.append(reversePath.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
