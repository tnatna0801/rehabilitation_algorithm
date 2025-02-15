package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제 수
        int M = Integer.parseInt(st.nextToken()); // 정보의 수

        List<Integer>[] problems = new ArrayList[N+1];
        for(int i =0; i<=N; i++) {
            problems[i] = new ArrayList<>();
        }

        int[] count = new int[N+1];
        for(int i =0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 먼저
            int b = Integer.parseInt(st.nextToken());

            problems[a].add(b);
            count[b]++; // 먼저 풀어야하는 문제 수
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i<=N; i++) {
            if(count[i] == 0) pq.add(i); // 제일 처음 풀어야하는 문제들
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {

            int now = pq.poll();
            sb.append(now).append(" ");

            for(int next : problems[now]) {
                count[next]--; // now에 해당하는 문제를 풀었으므로 남은 선행 문제 갯수 감수

                if(count[next] == 0) pq.add(next);
            }
        }

        System.out.println(sb);

    }
}
