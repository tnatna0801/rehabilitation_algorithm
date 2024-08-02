import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class BOJ_1967 {

    static int max = 0;
    static List<Node>[] nodes;
    static boolean[] visited;
    static int maxIndex = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 어떤 자료구조를 써야할까? 인접행렬일까 인접리스트일까? 맵은 괜찮나? => 인접리스트 아님 맵?
        // 부모를 알아야할 것 같아여
        nodes = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) { // 아으
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, w));
            nodes[e].add(new Node(s, w)); // 리프노드를 기준으로 탐색을 역으로 해야하기 때문
        }

        // dfs로 깊이우선탐색을하면서 가장 긴 구간을...갱신?
        // 부모 어디서 부터 어디까지 더하기를 해야하는 걸까? => 모든 노드에서 깊이우선 탐색 갈기기? 그중 젤 긴거 두개?
        // 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 1개만 존재한다는 말은 무조건 이진트리라는 걸까

        // 루트를 기준으로 가장 비용이 큰 리프노드를 찾!
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);

        // 찾은 리프노드를 기준으로 경로를 계산
        visited = new boolean[n + 1];
        visited[maxIndex] = true;
        dfs(maxIndex, 0);

        System.out.println(max);
    }

    /**
     * dfs로 노드를 탐색하면서 가장 최대 비용을 가진 경로를 찾습니다.
     * @param index 리프노드의 번호
     * @param sum 비용 합산
     */
    private static void dfs(int index, int sum) {

        if(max < sum) {
            max = sum;
            maxIndex = index;
        }

        for(Node next : nodes[index]) {
            if(visited[next.end]) continue; // 방문체크!
            visited[next.end] = true;
            dfs(next.end, sum+next.weight);
        }

    }
}
