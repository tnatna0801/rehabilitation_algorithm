import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584 {

    static int[] parents;

    static int root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {

            int N = Integer.parseInt(br.readLine());

            parents = new int[N + 1];
            boolean[] visited = new boolean[N + 1];

            StringTokenizer st;
            for (int i = 1; i < N; i++) { // 간선
                st = new StringTokenizer(br.readLine());

                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                parents[c] = p;
            }

            // 가장 가까운 조상을 찾아야하는 노드
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            // 역 추적
            while (a > 0) {
                visited[a] = true;
                a = parents[a];
            }

            while (b > 0 &&
                    !visited[b])
                b = parents[b];

//            dfs(a, b);
            sb.append(b).append("\n");

        }

        System.out.println(sb);
    }

//    private static int dfs(int p1, int p2) {
//
//        if(p1 == p2) {
//            return p1;
//        }
//
//        return dfs(parents[p1], parents[p2]);
//    }
}
