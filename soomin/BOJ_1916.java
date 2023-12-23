import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1916 {

    static List<City>[] bus;
    static int[] dist;
    static int INF = 100000 * 1000 + 1;

    static class City implements Comparable<City> {
        int node, weight;

        public City(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }


        @Override
        public int compareTo(City o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 각 도시 별 비용 정보를 저장할 List 배열 초기화
        bus = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            bus[i] = new ArrayList<>();
        }

        // 버스의 정보 입력 받기
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            bus[start].add(new City(end, w));
        }

        // 출발 도시와 도착 도시 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 초기에 모든 도시간의 거리를 최댓값으로 초기화한다. 인접한 도시간의 거리 중 최소 거리로 갱신하기 위함
        dist = new int[N+1];
        for(int i = 0; i<=N; i++){
            dist[i] = INF;
        }

        boolean[] visited = new boolean[N+1];
        dijkstra(start, end, visited);

        System.out.println(dist[end]);
    }

    /**
     * 다익스트라  => 그리디 + 다이나믹 프로그래밍
     * 1. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택한다.
     * 2. 해당 노드로부터 갈 수 있는 노드들의 비용을 갱신한다.
     *
     * @param start 출발 도시
     * @param end 도착 도시
     */
    public static void dijkstra(int start, int end, boolean[] visited) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));

        dist[start] = 0;
        //visited[start] = true;

        while(!pq.isEmpty()) {
            City now = pq.poll();

            if(now.node == end) {
                break;
            }

            // 시간초과가 나서 해결 하기 위함 => 현재 도시까지의 비용이 dist에 저장된 비용보다 크면 고려할 필요가 없다.
            if(dist[now.node] < now.weight) continue;

            for(City next : bus[now.node]){

                // 인접한 노드로의 최소 비용 갱신
                if(dist[next.node] > dist[now.node] + next.weight){
                    dist[next.node] = dist[now.node] + next.weight;
                    pq.offer(new City(next.node, next.weight));
                }

                // 방문 처리를 왜 안해줘도 될까?
                //if(visited[next.node]) continue;
                //pq.offer(new City(next.node, next.weight));
                //visited[next.node] = true;
            }
        }
    }
}
