import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BOJ_11559 {

    static class Point {
        int y;
        int x;
        String c;

        Point(int y, int x, String c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }

    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean isPop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        String[][] map = new String[12][6];
        int y = 0;
        int x = 0;
//        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().split("");
//            for (int j = 0; j < 6; j++) {
//                map[i][j] = st.nextToken();
//                if(map[i][j].equals(".")) continue;
//                if(!flag) {
//                    y = i;
//                    x = j;
//                    flag = true;
//                }
//            }
        }

        // 완탐 + bfs
        // 연쇄가 더이상 없을 거라는 걸 어캐 알지? => flag?
        int cnt = 0;
        while (true) {

            isPop = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j].equals(".")) continue;
                    bfs(map, i, j);
                }
            }

            if (!isPop) break; // 탐색했을때 연쇄되는 뿌요가 없었다면 종료

            // 공백제거
            for (int j = 0; j < 6; j++) {
                Stack<String> tmp = new Stack<>();
                for (int i = 0; i < 12; i++) {
                    if (map[i][j].equals(".")) continue;
                    tmp.add(map[i][j]);
                    map[i][j] = ".";
                }

                int index = 11;
                while (!tmp.isEmpty()) map[index--][j] = tmp.pop();

            }

            cnt++;
        }
        System.out.println(cnt);
    }

    private static void bfs(String[][] map, int y, int x) {
        Queue<Point> q = new ArrayDeque<>(); // 탐색용?
        Queue<Point> list = new ArrayDeque<>(); // 터트릴용
        boolean[][] visited = new boolean[12][6];

        q.add(new Point(y, x, map[y][x]));
        list.add(new Point(y, x, map[y][x]));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now.y + move[d][0];
                if (ny >= 12 || ny < 0) continue;
                int nx = now.x + move[d][1];
                if (nx >= 6 || nx < 0) continue;

//                System.out.println("=====" + ny  + ", " + nx + " : " + now.c + " vs " + map[ny][nx]);

                if (map[ny][nx].equals(".") || !map[ny][nx].equals(now.c) || visited[ny][nx]) continue;
                Point next = new Point(ny, nx, now.c);
                q.add(next);
                list.add(next);
                visited[ny][nx] = true;
            }
        }

        if(list.size() > 3) {
            isPop = true;

            while(!list.isEmpty()) {
                Point now = list.poll();
                map[now.y][now.x] = ".";
            }
        }

    }
}
