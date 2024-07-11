import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] box = new int[N+2][M+2];
        Queue<int[]> q = new ArrayDeque<>();

        int count = 0;
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken()) + 1;
                if (box[i][j] == 2) q.add(new int[]{i, j});
                else if(box[i][j] == 1) count++;
            }
        }

        int day = -1;
        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!q.isEmpty()) {
            int size = q.size();
            day++;
            while(size-- > 0) {

                int[] now = q.poll();

                for(int d = 0; d<4; d++){
                    int ny = now[0] + move[d][0];
                    if( ny < 1 || ny > N ) continue;
                    int nx = now[1] + move[d][1];
                    if( nx < 1 || nx > M ) continue;

                    if(box[ny][nx] != 1) continue;

                    q.add(new int[]{ny, nx});
                    count--;
                    box[ny][nx] = 2;
                }

            }
        }

        if(count != 0) System.out.println(-1);
        else System.out.println(day);
    }
}
