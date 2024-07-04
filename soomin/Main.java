import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 행렬?
        int[][] friendship = new int[N+1][N+1];
        for (int i = 1; i<=N; i++) {
            Arrays.fill(friendship[i], Integer.MAX_VALUE);
            friendship[i][i] = 0;
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friendship[a][b] = 1;
            friendship[b][a] = 1;
        }

        int ansNum = Integer.MAX_VALUE;
        int ansIdx = 0;

        // bfs

        for (int i = 1; i<=N; i++){
            int sum = bfs(friendship, i, N);
            if(sum < ansNum) {
                ansNum = sum;
                ansIdx = i;
            }
        }


//        // 플로이드 워셜
//        for (int k = 1; k<=N; k++){
//            for(int i = 1; i<=N; i++){
//                for(int j = 1; j<=N; j++) {
//                    // 연결이 되있어야함
//                    if(friendship[i][k] == Integer.MAX_VALUE || friendship[j][k] == Integer.MAX_VALUE) continue;;
//                    friendship[i][j] = Math.min(friendship[i][j], friendship[i][k] + friendship[k][j]);
//                }
//            }
//        }
//
//        // 케빈 베이컨의 수가 가장 작은 사람
//        for(int i = 1; i<=N; i++) {
//            int sum = 0;
//            for (int j = 1; j<=N; j++){
//                if(i == j) continue;
//                sum += friendship[i][j];
//            }
//
//            if(sum < ansNum) {
//                ansNum = sum;
//                ansIdx = i;
//            }
//        }
//
        System.out.println(ansIdx);
    }

    private static int bfs(int[][] friendship, int start, int N) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1); // 방문체크와 거리계산을 같이
        dist[start] = 0;
        q.add(start);
        int cnt = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i<=N; i++){
                if(friendship[cur][i] != 1 || dist[i] != -1) continue;
                q.add(i);
                dist[i] = dist[cur] + 1;
                cnt += dist[i];
            }
        }

        return cnt;
    }
}
