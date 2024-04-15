import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2589_보물섬 {

    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // 탐색할 좌표들
    static Queue<Point> starts = new ArrayDeque<>();

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

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++)
            map[i] = br.readLine().toCharArray();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 만약 땅이 아니라면
                if (map[i][j] != 'L')
                    continue; // 스킵

                Point now = new Point(i, j);

                // 코너인지 확인
                // 상하로 땅이 있다면
                if (upDown(map, h, now))
                    continue; // 스킵

                // 좌우로 땅이 있다면
                if (leftRight(map, w, now))
                    continue; // 스킵

                // 탐색할 좌표들에 저장
                starts.add(now);
            }
        }

        int answer = 0;
        // 탐색 시작
        for (Point start : starts)
            answer = Math.max(answer, bfs(map, h, w, start));

        System.out.println(answer);
    }

    private static boolean upDown(char[][] map, int h, Point now) {
        int up = now.y - 1;
        int down = now.y + 1;
        if (up < 0 || down >= h)
            return false;

        if (map[up][now.x] == 'W' || map[down][now.x] == 'W')
            return false;

        return true;
    }

    private static boolean leftRight(char[][] map, int w, Point now) {
        int left = now.x - 1;
        int right = now.x + 1;
        if (left < 0 || right >= w)
            return false;

        if (map[now.y][left] == 'W' || map[now.y][right] == 'W')
            return false;

        return true;
    }

    // 평범한 bfs
    private static int bfs(char[][] map, int h, int w, Point start) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];

        int count = -1;
        q.add(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            count++;

            while (size-- > 0) {
                Point now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int dy = now.y + move[i][0];
                    if (dy < 0 || dy >= h)
                        continue;

                    int dx = now.x + move[i][1];
                    if (dx < 0 || dx >= w)
                        continue;

                    if (map[dy][dx] != 'L')
                        continue;

                    if (visited[dy][dx])
                        continue;

                    visited[dy][dx] = true;
                    q.add(new Point(dy, dx));
                }
            }
        }
        return count;
    }
}
