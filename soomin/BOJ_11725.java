import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11725 {

    static List<Integer>[] list;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        // 인접 리스트 초기화
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 1. 입력
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // 2. dfs로 뿌리 찾기
        //        dfs(1);
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);


        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : list[now]) {
                if (parent[next] > 0) continue;
                q.add(next);
                parent[next] = now;
            }
        }
    }

    private static void dfs(int node) {

        for (int next : list[node]) {
            if (parent[next] > 0) continue;
            parent[next] = node;
            dfs(next);
        }

    }
}
