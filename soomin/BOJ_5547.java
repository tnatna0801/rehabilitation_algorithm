import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class House {
    int y, x;

    House(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class BOJ_5547 {

    static int[][] moveEven = {{0, -1}, {-1, -1}, {-1, 0}, {0, 1}, {1, 0}, {1, -1}}; // 짝수
    static int[][] moveOdd = {{0, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}}; // 홀수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 일단 고민인점 어떻게 외부 벽만 체크하지?
        // 사실은 육각형임 띠용!

        // 1과 0이 만나는 변을 카운트!

        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken()); // 가로
        int H = Integer.parseInt(st.nextToken()); // 세로

        int[][] map = new int[H + 2][W + 2];
        boolean[][] visited = new boolean[H + 2][W + 2];

        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs로 0인 것만 탐색한다 => 주변에 1인 건물이 있다면? 현재 위치에 +1
        Queue<House> q = new ArrayDeque<>();

        q.add(new House(0, 0));
        visited[0][0] = true;
        int totalWalls = 0;

        while (!q.isEmpty()) {

            House now = q.poll(); // 현재 탐색 위치
            for (int d = 0; d < 6; d++) {

                int ny = 0, nx = 0;

                if (now.y % 2 == 0) { // 짝수라면
                    ny = now.y + moveEven[d][0];
                    if (ny < 0 || ny > H + 1) continue; // 테두리
                    nx = now.x + moveEven[d][1];
                    if (nx < 0 || nx > W + 1) continue;
                } else {
                    ny = now.y + moveOdd[d][0];
                    if (ny < 0 || ny > H + 1) continue;
                    nx = now.x + moveOdd[d][1];
                    if (nx < 0 || nx > W + 1) continue;
                }

                // 인접한 곳이 집이라면? => 외벽을 세야지!
                if (map[ny][nx] == 1) {
                    totalWalls++;
                    continue;
                }

                // 방문체크
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new House(ny, nx)); // 0인곳을 큐에 넣는다!!
            }
        }

        System.out.println(totalWalls);
    }
}
