import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {

    private static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int N, M, max;
    private static int[][] map;
    private static Queue<int[]> virus = new ArrayDeque<>();
    private static List<int[]> empty = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new int[]{i, j});
                else if (map[i][j] == 0) empty.add(new int[]{i, j});
            }
        }

        // 바이러스가 퍼지지 않도록 해야함
        // 1: 벽, 2: 바이러스
        // 반복되는 코드가 너무 많아...

        // 3개의 벽을 세우는 모든 경우의 수 => dfs
        dfs(0, 0);

        System.out.println(max);

    }

    private static void dfs(int count, int start) {
        // 기저 조건 = 벽 3개
        if (count == 3) {
            bfs(); // 바이러스가 퍼지고 안전 영역 구해야함
            return;
        }

        for (int i = start; i < empty.size(); i++) {
            int[] now = empty.get(i);
            map[now[0]][now[1]] = 1; // 벽 세우기
            dfs(count + 1, i + 1);
            map[now[0]][now[1]] = 0; // 벽 제거
        }
    }


    // 바이러스가 퍼지는 지 확인 & 안전 영역 크기 구하기 => bfs
    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>(virus);
        boolean[][] visited = new boolean[N][M];

        int safeCnt = empty.size() - 3; // 벽은 3개는 무조건 세워야함

        // 바이러스 퍼짐~~
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now[0] + move[d][0];
                if (ny < 0 || ny >= N) continue;
                int nx = now[1] + move[d][1];
                if (nx < 0 || nx >= M) continue;

                if (map[ny][nx] != 0) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
                safeCnt--; // 바이러스가 퍼짐

            }
        }

        max = Math.max(max, safeCnt);
    }
}
