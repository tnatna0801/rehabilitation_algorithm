import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Coin {
    int y, x;

    public Coin(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class moveCoin {
    int y1, x1, y2, x2;

    public moveCoin(int y1, int x1, int y2, int x2) {
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
    }
}

public class BOJ_16197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N + 2][M + 2];
        for(int i = 0; i<map.length; i++)
            Arrays.fill(map[i], 'x'); // 범위 밖을 편리하게 구하기 위함

        List<Coin> coins = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line[j-1];
                if (map[i][j] == 'o') coins.add(new Coin(i, j)); // 동전 위치 파악
            }
        }

        // 각 동전을 bfs한 다음 비교? 둘 중 하나만 보드에서 떨어뜨려야한다
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<moveCoin> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N+2][M+2][N+2][M+2];

        q.add(new moveCoin(coins.get(0).y, coins.get(0).x, coins.get(1).y, coins.get(1).x));
        visited[coins.get(0).y][coins.get(0).x][coins.get(1).y][coins.get(1).x] = true;

        int count = 0;
        while (!q.isEmpty()) {

            count++;
            if(count == 11) break;
            int size = q.size();

            while (size-- > 0) { // 버튼을 누른 횟수를 제대로 세기 위함

                moveCoin now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int ny1 = now.y1 + move[d][0]; // 동전 1의 위치
                    int nx1 = now.x1 + move[d][1];

                    int ny2 = now.y2 + move[d][0]; // 동전 2의 위치
                    int nx2 = now.x2 + move[d][1];

                    // 동전이 둘다 떨어지면 안됨
                    if(map[ny1][nx1] == 'x' && map[ny2][nx2] == 'x') continue;

                    // 동전이 하나만 떨어져있는 지 확인!
                    if (map[ny1][nx1] == 'x' || map[ny2][nx2] == 'x') {
                        System.out.println(count);
                        return;
                    }

                    // 한 곳만 벽이라면?
                    if (map[ny1][nx1] == '#') {
                        ny1 = now.y1;
                        nx1 = now.x1;
                    }

                    if (map[ny2][nx2] == '#') {
                        ny2 = now.y2;
                        nx2 = now.x2;
                    }

                    // 방문했었더라면
                    if(visited[ny1][nx1][ny2][nx2]) continue;

                    // 다음 탐색 위치
                    q.add(new moveCoin(ny1, nx1, ny2, nx2));
                    visited[ny1][nx1][ny2][nx2] = true;
                }
            }
        }

        System.out.println(-1);

    }
}
