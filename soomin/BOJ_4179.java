import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

    static int R, C;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] fire;
    static int[][] jihoon;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fire = new int[R][C];
        jihoon = new int[R][C];

        // 불과 지훈이의 좌표 탐색을 위한 큐
        Queue<int[]> fireQ = new ArrayDeque<>();
        Queue<int[]> jihoonQ = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            // -1로 초기화
            Arrays.fill(fire[i], -1);
            Arrays.fill(jihoon[i], -1);

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    fireQ.add(new int[]{i, j});
                    fire[i][j] = 0;
                } else if (map[i][j] == 'J') {
                    jihoonQ.add(new int[]{i, j});
                    jihoon[i][j] = 0;
                }
            }
        }

        // 불 먼저
        fire(fireQ);

        int result = escape(jihoonQ);
        if(result == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(result);
    }

    public static int escape(Queue<int[]> q) {

        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + move[i][0];
                if (ny < 0 || ny >= R) continue;
                int nx = now[1] + move[i][1];
                if (nx < 0 || nx >= C) continue;

                if (fire[ny][nx] != -1 || map[ny][nx] == '#') continue;

                q.add(new int[]{ny, nx});
                fire[ny][nx] = fire[now[0]][now[1]] + 1;

            }
        }

        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + move[i][0];
                if (ny < 0 || ny >= R) return jihoon[now[0]][now[1]] + 1;
                int nx = now[1] + move[i][1];
                if (nx < 0 || nx >= C) return jihoon[now[0]][now[1]] + 1;

                // 지훈이 count <= 불 count
                if (map[ny][nx] == '#' || jihoon[ny][nx] != -1) continue;
                if (fire[ny][nx] != -1)
                    if (jihoon[now[0]][now[1]] + 1 >= fire[ny][nx]) continue;

                q.add(new int[]{ny, nx});
                jihoon[ny][nx] = jihoon[now[0]][now[1]] + 1;
            }

        }

        return -1;
    }

    // 불 번짐 횟수 세기
    public static void fire(Queue<int[]> q) {
        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + move[i][0];
                if (ny < 0 || ny >= R) continue;
                int nx = now[1] + move[i][1];
                if (nx < 0 || nx >= C) continue;

                if (fire[ny][nx] != -1 || map[ny][nx] == '#') continue;

                q.add(new int[]{ny, nx});
                fire[ny][nx] = fire[now[0]][now[1]] + 1;

            }
        }
    }
}
