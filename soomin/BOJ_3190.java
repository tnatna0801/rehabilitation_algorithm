import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 사과의 위치
        int[][] map = new int[N+1][N+1];

        for(int i =0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 1;
        }

        // 방향
        int L = Integer.parseInt(br.readLine());
        Map<Integer, String> info = new HashMap<>();
        for(int i =0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            info.put(t, d);
        }

        // play
        int time = 0;
        int direction = 0; // 0: 오른쪽, 1: 아래, 2: 왼쪽, 3: 위쪽
        int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int y = 1;
        int x = 1;

        Queue<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{1, 1});
        map[1][1] = -1;

        while(true) {

            // 0. 시간
            time++;

            // 1. 이동 & 2. 벽
            int ny = y + move[direction][0];
            if(ny <1 || ny > N) break;
            int nx = x + move[direction][1];
            if(nx <1 || nx > N) break;

            // 3. 자기 자신
            if(map[ny][nx] == -1) break;

            // 4. 사과
            if(map[ny][nx] == 0) { // 사과가 아니라면
                int[] now = snake.poll();
                map[now[0]][now[1]] = 0; // 꼬리 제거
            }

            // 5. 이동 정보 확인
            if(info.containsKey(time)) {
                if(info.get(time).equals("D")) { // 오른쪽으로 90도
                    direction = (direction + 1) % 4;
                }
                else { // 왼쪽으로 90도
                    direction -= 1;
                    if(direction < 0) direction = 3;
                }
            }

            // 6. 현재 위치 추가
            snake.add(new int[]{ny, nx});
            map[ny][nx] = -1;
            y = ny;
            x = nx;

        }

        System.out.println(time);
    }
}
