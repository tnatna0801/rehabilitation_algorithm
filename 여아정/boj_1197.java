package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1197 {
    public static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());//정점 수
        int e = Integer.parseInt(st.nextToken());//간선 수

        List<Node>[] goList = new ArrayList[v + 1];//정점마다의 인접 리스트
        for (int i = 0; i <= v; i++) {
            goList[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            //양방향이무로 모두 값 넣어줌
            goList[start].add(new Node(end, cost));
            goList[end].add(new Node(start, cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();//우선순위큐 사용(우선순위 기준은 Node의 cost 오름차순)
        boolean[] chk = new boolean[v + 1];//체크
        int All = 0;
        pq.add(new Node(1, 0));//처음 시작인 1번째 정점을 임의로 넣어준다

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int nowIdx = cur.idx;

            if (chk[nowIdx]) continue;//이미 방문한 정점이면 넘어감
            chk[nowIdx] = true;//방문처리
            All += cur.cost;//최종비용에 현재 정점으로 오는 비용 더해준다

            for (Node next : goList[nowIdx]) {//현재 정점에서 갈 수 있는 아직 가지 않은 정점은 모두 pq에 넣는다
                if (chk[next.idx]) continue;
                pq.add(next);
            }
        }

        System.out.println(All);//출력

    }
}
