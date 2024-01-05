import java.io.*;
import java.util.*;

public class boj_1916_최소비용_구하기 {
    static class Bus implements Comparable<Bus> {
        int station;
        int cost;

        /**
         * 버스 정보를 가지고 있는 객체
         * @param station 도착지(도시)
         * @param cost 버스 비용
         */
        public Bus(int station, int cost) {
            this.station = station;
            this.cost = cost;
        }

        /**
         * 낮은 비용을 우선시 하여 비교하는 함수
         * @param o the object to be compared.
         * @return 낮은 비용
         */
        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 도시별 이동 비용을 저장할 인접 배열
        // 인접배열을 선택한 이유 : 출발지와 도착지가 중복되는 버스가 존재할 가능성이 있음
        int[][] cities = new int[n + 1][n + 1];
        // 낮은 비용을 우선 시 하기 때문에 가장 큰 값으로 비용 초기화
        for (int i = 1; i <= n; i++)
            Arrays.fill(cities[i], Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 값이 존재할 경우 작은 값으로 저장하여 중복 제거
            cities[from][to] = Integer.min(cities[from][to], cost);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end, n, cities));
    }

    /**
     * 다익스트라
     * @param start 시작 도시
     * @param end 도착 도시
     * @param n 도시의 수
     * @param cities 도시별 이동 비용을 담은 배열
     * @return 시작 도시부터 도착 도시까지의 최저 이동 비용
     */
    private static int dijkstra(int start, int end, int n, int[][] cities) {
        // 낮은 비용을 갱신하기 위한 우선순위 큐
        Queue<Bus> pq = new PriorityQueue<>();
        // 비용이 정해진 도시의 재방문을 방지하기 위한 배열
        boolean[] visited = new boolean[n + 1];

        // 도시별 최저 도착 비용을 저장하기 위한 배열
        int[] costs = new int[n + 1];
        // 마찬가지로 최저 비용을 우선시 하기 때문에 최대 비용으로 초기화
        Arrays.fill(costs, Integer.MAX_VALUE);

        // 1. 시작 도시부터 출발
        costs[start] = 0;
        pq.add(new Bus(start, 0));

        while (!pq.isEmpty()) {
            // 현재까지 이동 비용이 가장 낮은 도시
            int now = pq.poll().station;

            // 8.(끝) 만약 현재 도시가 도착 도시라면 탐색 종료
            if (now == end)
                break;

            // 2. 최저 비용이 정해진 도시인지 확인
            if (visited[now])
                // 2-1. 정해진 도시라면 건너뛰기
                continue;
            // 2-2. 정해지지 않은 도시라면 최저 가격 동결을 위한 방문 기록
            visited[now] = true;

            for (int i = 1; i <= n; i++) {
                // 3. 방문한 도시부터 다음 도시로 이동이 가능한지 확인
                if (cities[now][i] == Integer.MAX_VALUE)
                    // 3-1. 불가능 하다면 건너뛰기
                    continue;

                // 4. 최저 비용이 정해진 도시인지 확인
                if (visited[i])
                    // 4-1. 정해진 도시라면 건너뛰기
                    continue;

                // 현 도시에서 이동이 가능한 다음 도시까지 비용
                int newCost = costs[now] + cities[now][i];
                // 5. 새로 계산한 비용이 기존 이동 비용보다 비싼지 확인
                if (costs[i] <= newCost)
                    // 5-1. 비싸다면 건너뛰기
                    continue;

                // 6. 현재까지의 가격을 기록
                costs[i] = newCost;
                // 7. 이동 비용을 기준으로 다음 방문 도시 후보로 등록
                pq.add(new Bus(i, costs[i]));
            }
        }

        return costs[end];
    }
}