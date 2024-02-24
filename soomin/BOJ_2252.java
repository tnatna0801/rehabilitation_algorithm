import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 초기화
        List<List<Integer>> student = new ArrayList<>();
        for (int i = 1; i<=N+1; i++) {
            student.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];

        // 진입차수, 진출차수 갱신
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //진입 차수 갱신
            indegree[b]++;

            //연결 노드 갱신
            student.get(a).add(b);
        }

        // 줄세우기
        topologySort(indegree, student, N, sb);

        System.out.println(sb);

    }

    private static void topologySort(int[] indegree, List<List<Integer>> student, int N, StringBuilder sb) {
        Queue<Integer> q = new ArrayDeque();

        // 진입차수가 0인 노드 넣기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                // 현재 친구 
                int current = q.poll();

                // 출력에 넣어야지
                sb.append(current).append(" ");

                // 방문안한 연결된 노드 있는 지 확인
                for (int next : student.get(current)) {

                    indegree[next]--;

                    if (indegree[next] == 0) {
                        q.add(next);
                    }
                }

            }

        }
    }
}
