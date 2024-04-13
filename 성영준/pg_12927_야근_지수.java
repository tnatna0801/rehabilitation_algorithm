import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class pg_12927_야근_지수 {
    class Solution {
        public long solution(int n, int[] works) {
            // 작업량이 높은 순으로 각 일 정렬
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            // 각 일의 총 작업량
            int sum = 0;
            // 각 일을 정렬 및 합산
            for (int work : works) {
                sum += work;
                pq.offer(work);
            }

            // 총 작업량이 퇴근까지 남은 시간보다 적다면 야근 지수는 0
            if (sum <= n)
                return 0;

            // 퇴근까지 남은 시간 만큼 작업량이 높은 일에서 1시간 씩 차감
            for (int i = 0; i < n; i++) {
                pq.add(pq.poll() - 1);
            }

            // 야근 지수
            long answer = 0;
            while (!pq.isEmpty()) {
                // 남은 일의 작업량을 제곱하여 합산
                answer += Math.pow(pq.poll(), 2);
            }

            return answer;
        }
    }
}