import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

    static int N, M, H, count;
    static int[][][] box;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[][] move = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[H + 1][N + 2][M + 2];

        count = 0;
        for (int k = 1; k <= H; k++) {
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    int status = Integer.parseInt(st.nextToken()) + 1;
                    box[k][i][j] = status;
                    if (status == 1) count++;
                    if (status == 2) q.add(new int[]{k, i, j}); // q에 초기 토마토를 넣기
                }
            }
        }

        int day = bfs();

        StringBuilder sb = new StringBuilder();
        if (count == 0) sb.append(day);
        else sb.append(-1);

        System.out.println(sb);
    }

    private static int bfs() {

        int day = -1;
        while (!q.isEmpty()) {

            day++;
            int size = q.size();

            while (size-- > 0) {

                int[] now = q.poll();

                // 상 하 좌 우 윗쪽 아랫쪽
                for (int d = 0; d < 6; d++) {
                    int nz = now[0] + move[d][2];
                    if (nz < 1 || nz > H) continue;
                    int ny = now[1] + move[d][0];
                    if (ny < 1 || ny > N) continue;
                    int nx = now[2] + move[d][1];
                    if (nx < 1 || nx > M) continue;

                    if (box[nz][ny][nx] != 1) continue; // 0이면 없는거, 2명 익은거니 볼 필요 없음

                    q.add(new int[]{nz, ny, nx});
                    box[nz][ny][nx] = 2;
                    count--;
                }
            }
        }

        return day;
    }
}
