import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
    int a;
    int b;
    int w;

    Edge(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }
}

public class BOJ_1922 {

    static int[] p;
    static int min;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.w - o2.w;
        }); // 오름차순 정렬

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, w));
        }

        // makeSet
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) p[i] = i; // 부모 노드(집합)

        min = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {

//            if(cnt == N-1) break;

            Edge now = pq.poll(); // 최소 간선
            if (union(now.a, now.b)) {  // 서클인지 확인
                min += now.w;
//                cnt++;
            }
        }

        System.out.println(min);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false; // 같은 root를 가지고 있다면 싸이클이 존재하게됨
        else {
            p[b] = a; // 부모 집합에 포함
        }
        return true;
    }

    private static int find(int point) {
        if (point == p[point])
            return p[point];
        else return p[point] = find(p[point]); // root 원소 찾기
    }
}
