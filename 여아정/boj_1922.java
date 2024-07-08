package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] lineList = new int[N + 1][N + 1];
        boolean[] chk = new boolean[N + 1];
        PriorityQueue<Line> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (lineList[start][end] > 0) { //기존의 값이 있다면
                //기존 값과 비교해서 최소 값으로 갱신
                int last = lineList[start][end];
                //양방향이요
                lineList[start][end] = Math.min(last, cost);
                lineList[end][start] = lineList[start][end];

                continue;
            }

            //양방향이요
            lineList[start][end] = cost;
            lineList[end][start] = cost;
        }

        //첫 지점인 1번 노드에서 시작해서 1번 노드에서 갈 수 있는 다른 점에 대한 값을 우선순위 큐에 넣기
        for (int i = 1; i <= N; i++) {
            if (lineList[1][i] <= 0) continue;
            pq.add(new Line(i, lineList[1][i]));
        }

        Long sum = 0L;
        chk[1] = true;

        while (!pq.isEmpty()) {
            Line now = pq.poll();
            if (chk[now.node]) continue; // 이미 방문한 곳이면 넘어가기

            sum += now.cost;
            chk[now.node] = true;// 방문처리

            for (int i = 1; i <= N; i++) {
                if (lineList[now.node][i] <= 0) continue;
                if (chk[i]) continue;
                pq.add(new Line(i, lineList[now.node][i]));// 갈 수 있는 곳 pq에 다 넣기
            }
        }

        System.out.println(sum);

    }

    static class Line implements Comparable<Line> {
        int node;
        int cost;

        Line(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return this.cost - o.cost;
        }

    }
}
