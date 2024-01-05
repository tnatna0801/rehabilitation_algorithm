import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {

    static class Node implements Comparable<Node>{
        int num, w;
        public Node(int num, int w) {
            this.num = num;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static List<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점
        int E = Integer.parseInt(st.nextToken()); // 간선

        // 각 간선에 대한 정보를 저장할 List 배열 선언 및 초기화
       list = new ArrayList[V+1];
        for(int i = 1; i<=V; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향이므로
            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        System.out.println(prim(V));
    }

    private static int prim(int V) {
        boolean[] visited = new boolean[V+1];

        int result = 0;

        // 최소 비용인 경로를 다음에 탐색하기 위해서 PriorityQueue를 사용함
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.num]) continue; // 방문 여부를 확인한다. 방문을 했다면 해당 노드를 볼 필요가 없다 이미 최소이므로
            visited[now.num] = true;
            result += now.w;

            // 현재 노드에 인접한 노드를 탐색하면서 아직 방문하지 않은 노드를 pq에 넣는다.
            // pq에서 알아서 정렬되므로 항상 최소 비용인 경로 순으로 노드를 탐색하게 되고 가중치 합이 최소인 트리가 만들어진다.
            for(Node next : list[now.num]){
                if(visited[next.num]) continue;
                pq.add(new Node(next.num, next.w));
            }
        }
        return result;
    }
}
