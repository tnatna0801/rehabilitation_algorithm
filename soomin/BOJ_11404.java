import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {

    static final int INF = 1000000000;
    static int N, M;
    static int[][] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 제목이 스포인걸요: 플로이드 워샬
        // 모든 정점에서 다른 모든 정점으로 가는 방법
        // 거쳐가는 정점이라는 개념이 중요!
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 그래프 초기화 => 무한값
        init();

        // 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // a~b 여러개일 수 있음 => 최소 비용을 택해야함
            if (cities[a][b] > w)
                cities[a][b] = w;
        }

        // 거쳐가는 정점을 탐색하면서 비용을 갱신
        floyd();

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cities[i][j] == INF) { // 갈 수 없으면 0 출력
                    sb.append(0 + " ");
                    continue;
                }
                sb.append(cities[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) { // 출발 a
                if (i == k || cities[i][k] == INF) continue; // 자기자신이거나 연결된 경로가 없을 경우 skip
                for (int j = 1; j <= N; j++) { // 도착 b

                    if(i == j || cities[k][j] == INF) continue; // 자기자신이거나 연결된 경로가 없을 경우 skip

                    int newPath = cities[i][k] + cities[k][j];
                    if (cities[i][j] > newPath) cities[i][j] = newPath; // 최소 비용으로 갱신

                }
            }
        }
    }

    private static void init() {
        cities = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue; // 자기자신은 제외
                cities[i][j] = INF; // 연결된 경로가 아닌 경우 모두 무한대로 표시
            }
        }
    }
}
