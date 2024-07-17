import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865 {

    static class Point {
        int end;
        int w;

        Point(int end, int w) {
            this.end = end;
            this.w = w;
        }
    }

    static ArrayList<ArrayList<Point>> info;
    static int[] time;
    static int N, M, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (TC-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점의 수
            M = Integer.parseInt(st.nextToken()); // 도로의 개수
            W = Integer.parseInt(st.nextToken()); // 웜홀의 개수

            // 도로와 웜홀의 정보를 저장할 ArrayList 초기화
            info = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                info.add(new ArrayList<>());
            }

            // 입력값 저장
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken()); // 걸리는 시간

                // 방향이 없으므로 양방향 처리
                info.get(S).add(new Point(E, T));
                info.get(E).add(new Point(S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                // 방향이 있으므로 단방향
                info.get(S).add(new Point(E, T * -1)); // 시간이 되돌아 가기 때문에 음수값!
            }

            // 시간이 되돌아가 있는 경우를 찾기 => 사이클이 만들어졌을 때 값이 음수값이 되는 경우가 있는가
            // 틈수값이 포함된 최단 거리 구하는 알고리즘 : 벨만 포드 알고리즘
            sb = new StringBuilder();
            boolean isCheck = false;
            for (int i = 1; i <= N; i++) { // 출발지 선택
                if (bellmanFord(i)) {
                    sb.append("YES").append("\n");
                    isCheck = true;
                    break;
                }
            }

            if (!isCheck) sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord(int start) {

        time = new int[N + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[start] = 0; // 시작점 초기화

        for (int i = 1; i <= N; i++) { // 전체 정점을 반복 => 툭정한 지점이 정해져있지 않기 때문
            for (int j = 1; j <= N; j++) { // 매 반복마다 모든 간선을 확인한다.
                for (Point point : info.get(j)) {
                    if (time[j] == Integer.MAX_VALUE || time[point.end] <= time[j] + point.w) continue;
                    time[point.end] = time[i] + point.w; // 거리 업데이트!
                    if (i == N) return true; // 마지막 정점에서도 값이 갱신된다면 음수 순환이 존재
                }
            }
        }

        return false;
    }
}
