import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865 {

    static int[][] map;
    static int[] time;
    static int N, M, W;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < TC; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점의 수
            M = Integer.parseInt(st.nextToken()); // 도로의 개수
            W = Integer.parseInt(st.nextToken()); // 웜홀의 개수

            map = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(map[i], INF);
                map[i][i] = 0; // 자기 자신으로 가는 길의 비용은 0으로 설정
            }

            // 입력값 저장
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken()); // 걸리는 시간

                // 방향이 없으므로 양방향 처리
                if (map[S][E] > T) map[S][E] = map[E][S] = T;
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken()); // 걸리는 시간

                // 방향이 있으므로 단방향
                if (map[S][E] > -T) map[S][E] = -T;
            }

            // 시간이 되돌아가 있는 경우를 찾기 => 사이클이 만들어졌을 때 값이 음수값이 되는 경우가 있는가
            // 틈수값이 포함된 최단 거리 구하는 알고리즘 : 벨만 포드 알고리즘
            if (bellmanFord()) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord() {

        // 시작점이 따로 없다. 모두 0(시작점)으로 해서 동시에 벨만포드 알고리즘을 수행함
        time = new int[N + 1];

        for (int i = 0; i < N; i++) {
            boolean updated = false;
            for (int j = 1; j <= N; j++) { // 매 반복마다 모든 간선을 확인한다.
                for (int k = 1; k <= N; k++) {

                    if (map[j][k] == INF) continue;

                    int newWeight = time[j] + map[j][k];
                    if (time[k] <= newWeight) continue;
                    time[k] = newWeight; // 거리 업데이트!
                    updated = true;

                    if (i == N - 1) return true; // 마지막 정점에서도 값이 갱신된다면 음수 순환이 존재
                }
            }
            // 갱신이 없으면 반복 종료
            if (!updated) break;
        }

        return false;
    }
}
