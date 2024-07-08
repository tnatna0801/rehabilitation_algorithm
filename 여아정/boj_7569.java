package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_7569 {
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<int[]> q = new ArrayDeque<>();

        int n, m, h;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[][][] tomato = new int[h][m][n];
        int cnt = 0;
        int day = 0;
        int all = h * m * n;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < n; k++) {
                    // 0: 안익음, 1: 익음, -1: 없음
                    if ((tomato[i][j][k] = Integer.parseInt(st.nextToken())) == 1) {
                        cnt++;
                        q.add(new int[]{i, j, k});
                    } else if (tomato[i][j][k] == -1) all--;


                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();

            if (cnt == all) {
                break;
            }

            while (size-- > 0) {
                int[] welldone = q.poll();

                for (int i = 0; i < 6; i++) {
                    int nx = welldone[2] + dx[i];
                    if (nx < 0 || nx >= n) continue;

                    int ny = welldone[1] + dy[i];
                    if (ny < 0 || ny >= m) continue;

                    int nz = welldone[0] + dz[i];
                    if (nz < 0 || nz >= h) continue;

                    if (tomato[nz][ny][nx] != 0) continue;

                    q.add(new int[]{nz, ny, nx});
                    tomato[nz][ny][nx] = 1;
                    cnt++;
                }
            }
            day++;
        }

        if (cnt != all) {
            day = -1;
        }

        System.out.println(day);
    }
}
