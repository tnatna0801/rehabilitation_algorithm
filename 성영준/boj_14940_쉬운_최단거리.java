import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14940_쉬운_최단거리 {
    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] distances = new int[n + 2][m + 2];

        // 목표 지점
        Point target = null;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                String input = st.nextToken();
                if (input.equals("1"))
                    // 미리 -1을 채워, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치 탐색 작업 생략
                    distances[i][j] = -1;
                else if (input.equals("0"))
                    // 원래 갈 수 없는 땅 표시
                    distances[i][j] = 0;
                else
                    // 목표 지점 저장
                    target = new Point(i, j);
            }
        }

        // 거리 계산
        bfs(target, distances);

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                sb.append(distances[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    /**
     * 모든 지점에 대해서 목표지점까지의 거리를 구하는 함수
     *
     * @param target    목표 지점
     * @param distances 거리를 저장할 지도
     */
    private static void bfs(Point target, int[][] distances) {
        int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        // bfs를 위한 자료구조
        Queue<Point> q = new ArrayDeque<>();
        // 현재 탐색 단계에서의 거리
        int distance = 0;

        // 목표 지점 추가, 거리 표시
        q.add(target);
        distances[target.y][target.x] = distance;

        // 1. 다음 단계 탐색
        while (!q.isEmpty()) {
            // 다음 단계 후보의 개수
            int size = q.size();
            // 거리 1 증가
            distance++;
            // 2. 현제 단계에서의 표시
            while (size-- > 0) {
                // 현재 단계 후보 선택
                Point now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int dy = now.y + move[i][0];
                    int dx = now.x + move[i][1];

                    // 지나갈 땅이 아니면 건너뜀
                    if (distances[dy][dx] != -1)
                        continue;

                    // 후보 지점 추가, 거리 표시
                    q.add(new Point(dy, dx));
                    distances[dy][dx] = distance;
                }
            }
        }
    }
}