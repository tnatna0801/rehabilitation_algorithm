package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2252_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());//사람 수
        int m = Integer.parseInt(st.nextToken());//비교한 경우 수

        List<Integer>[] all = new ArrayList[n + 1];//비교 리스트 [작은]에 대한 큰 정보들
        int[] indegree = new int[32001];//나에게 오는 간선의 수

        for (int i = 0; i <= n; i++) {
            all[i] = new ArrayList<>();//껍대기 만들기
        }

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());

            all[small].add(big);//작은키 list에 다음 올 애를 넣기
            indegree[big]++;//나에게 오는 경우 +1
        }

        //초기 설정
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0)//진입차수가 0일경우
                q.add(i);//q에 넣기
        }

        //q 다 돌기
        while (!q.isEmpty()) {
            int cur = q.poll();

            sb.append(cur).append(" ");

            for (int big : all[cur]) {//현재 키에서 다음으로 갈 수 있는 것 탐색
                if (--indegree[big] == 0) {//갈 수 있는 곳 중 진입차수가 0 인경우만 q에 넣기
                    q.add(big);
                }
            }
        }

        System.out.println(sb);

    }
}
