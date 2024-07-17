import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {

    static class Bus {
        int end;
        int time;

        Bus(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }

    static long INF = Long.MAX_VALUE; // 음수 사이클 발생 시 최솟값 500 * 6000 * -10000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 음수 사이클이 만들어진다면 -1 출력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Bus>> info = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            info.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            info.get(A).add(new Bus(B, C));
        }

        // 벨만 포스
        long[] time = new long[N + 1];
        Arrays.fill(time, INF);
        time[1] = 0; // 출발지 초기화

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <N; i++) { // 최대 간선 수 만큼 반복 ( N-1번 )
            
            for (int k = 1; k <= N; k++) { // 모든 정점에 연결된 간선을 검사

                if (time[k] == INF) continue; // 1과 연결이 안되어있음

                // 최단 거리 갱신
                for (Bus bus : info.get(k)) { // k정점과 연결된 모든 간선
                    if (time[bus.end] > time[k] + bus.time) {
                        time[bus.end] = time[k] + bus.time;

                        if(i == N-1) { // 마지막 라운드인데도 업데이트가 있었다면 음수 사이클이 있는 것이 확실하므로 프로그램 종료
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }
        }



        for (int i = 2; i <= N; i++) {
            if (time[i] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(time[i]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
