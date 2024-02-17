import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1931_회의실_배정 {

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 회의가 끝나는 시간, 시작하는 시간을 기준으로 빠를 수록 우선순위로 뒀음
         * @param o the object to be compared.
         * @return 우선순위
         */
        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end)
                return this.start - o.start;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 회의 수
        int n = Integer.parseInt(br.readLine());

        // 우선순위를 기준으로 정렬할 자료구조
        Queue<Meeting> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(start, end));
        }

        // 사용한 회의 개수
        int count = 0;
        // 앞서 진행한 회의의 끝나는 시간
        int previousEnd = 0;

        while (!pq.isEmpty()) {
            // 다음 회의 후보
            Meeting now = pq.poll();

            // 후보 회의의 시작 시간이 현재 회의가 끝나기 전이라면
            if (previousEnd > now.start)
                // 넘깁니다
                continue;

            // 회의를 진행합니다
            // 회의 개수 + 1
            count++;
            // 끝나는 시간 갱신
            previousEnd = now.end;
        }

        // 정답 출력
        System.out.println(count);
    }
}