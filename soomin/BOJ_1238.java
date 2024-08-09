import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int e, w;

    Edge(int e, int w) {
        this.e = e;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}

public class BOJ_1238 {

    static int INF = 10000000;
    //static ArrayList<Edge>[] list, reverseList;
    static int N, M, X;
    //static int[] goHome, goParty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // x에서 N까지 최단거리 => 다익스트라
        // 근데 왕복을 구해야함 단방향이라서 촤핫
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        List<Edge>[] list = new ArrayList[N + 1]; // x에서 모이고 집갈 때
        List<Edge>[] reverseList = new ArrayList[N + 1]; // 집에서 x로 갈때 => 이럼 집만큼 반복해야하니까 동일하게 x에서 출발하는 것으로 역으로 생각해서 풀이 => 즉 입력을 반대로 받

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }

        // 입력받는다. 인접 리스트
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e, w));
            reverseList[e].add(new Edge(s, w));
        }

        int[] goHome = dijkstra(list); // 집가는 거리 계산
        int[] goParty = dijkstra(reverseList); // 파티장 가는 거리 계산

        int sum = 0;
        for(int i = 1; i<=N; i++) {
            sum = Math.max(sum, goParty[i] + goHome[i]);
        }

        System.out.println(sum);
    }

    private static int[] dijkstra(List<Edge>[] edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(X, 0));

        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[X] = 0;


        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            // 방문체크
            if(distance[now.e] < now.w) continue;

            for (Edge edge : edges[now.e]) {
                if(distance[edge.e] > distance[now.e] + edge.w) { // 경로 갱신
                    distance[edge.e] = distance[now.e] + edge.w;
                    pq.add(new Edge(edge.e, distance[edge.e]));
                }
            }

        }

        return distance;

    }
}


