import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 트리 순회?
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Integer>[] schedule = new ArrayList[N+1];
            for(int i = 0; i<=N; i++) {
                schedule[i] = new ArrayList<>();
            }

            // 인접리스트
            for(int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                schedule[a].add(b);
                schedule[b].add(a); // 왕복하는 비행기
            }
            sb.append(bfs(N, schedule) - 1).append("\n"); // 간선 수니까 -1
        }
        System.out.println(sb);
    }

    /**
     * bfs로 국가 순회
     * @param N
     * @param schedule
     * @return
     */
    private static int bfs(int N, List<Integer>[] schedule) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        q.add(1);
        visited[1] = true;

        int count = 0;
        while(!q.isEmpty()) {
            count++;
            int now = q.poll();
            for(int next : schedule[now]) {
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
        return count;
    }
}
