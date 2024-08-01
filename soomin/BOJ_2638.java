import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x, y;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class BOJ_2638 {

    static int N, M;
    static int[][] map;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static List<Point> cheese = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese.add(new Point(i, j)); // 치즈리스트 => 반목문 제어용
            }
        }

        int time = 0;
        while(!cheese.isEmpty()) {  // 3. 1, 2 반복
            time++;
            // 1. 외부공기 파악하기
            bfs(0, 0);

            // 2. 치즈 녹이기
            melting();
        }

        System.out.println(time);
    }

    // 치즈를 녹이자!
    private static void melting() {
        for(int i = 0; i<cheese.size(); i++){
            int count = 0; // 2면 1시간안에 녹음

            int y = cheese.get(i).y;
            int x = cheese.get(i).x;

            for(int d = 0; d<4; d++) {
                int ny = y + move[d][0];
                int nx = x + move[d][1]; // 치즈는 가장자리에 있을 수 없다

                if(map[ny][nx] == 2) count++; // 접촉 면을 센다
            }

            if(count >= 2) {
                map[y][x] = 0; // 녹여버리자
                cheese.remove(i); // 치즈 목록에서 제거
                i--;
            }
        }
    }

    // bfs로 탐색하면서 외부 공기랑 접촉된 곳은 2로 표시
    // 내부공기 인지는 어떻게 파악하지? => 모르겠음 일단 킵고잉 => 공기를 체크하기 때문에 치즈내부의 공기는 체크될 수가 없음 보장됨
    private static void bfs(int y, int x) { 
        Queue<Point> q = new ArrayDeque();
        boolean[][] visited = new boolean[N][M];

        q.add(new Point(y, x));
        visited[y][x] = true;
        map[y][x] = 2; // 공기

        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int d = 0; d<4; d++) {
                int ny = now.y + move[d][0];
                if(ny < 0 || ny >= N) continue;
                int nx = now.x + move[d][1];
                if(nx < 0 || nx >= M) continue;

                if(visited[ny][nx] ||
                        map[ny][nx] == 1) continue;

                visited[ny][nx] = true;
                map[ny][nx] = 2; // 공기
                q.add(new Point(ny, nx));
            }
        }
    }
}