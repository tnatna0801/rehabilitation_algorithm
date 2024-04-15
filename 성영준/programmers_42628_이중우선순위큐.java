import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class programmers_42628_이중우선순위큐 {
    public int[] solution(String[] operations) {
        List<Integer> q = new ArrayList<>();

        // 원소 분리
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);

            // 명령어, 데이터 분리
            String commend = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            // q 크기
            int size = q.size();

            // Insert면
            if (commend.equals("I")) {
                // 적절한 위치에 넣는 함수
                insert(q, value, size);
                continue;
            }

            // size가 0이면 제거할 데이터 없음
            if (size == 0)
                continue;

            // 최솟값, 최댓값 제거
            if (value == 1)
                q.remove(size - 1);
            else
                q.remove(0);
        }
        if (q.size() == 0)
            return new int[]{0, 0};
        return new int[]{q.get(q.size() - 1), q.get(0)};
    }

    /**
     * 이진 탐색을 이용하여 insert
     * @param q 큐
     * @param value 넣을 값
     * @param size 큐의 사이즈
     */
    public static void insert(List<Integer> q, int value, int size) {
        int left = 0;
        int right = size - 1;
        int mid = 0;

        // 탐색의 범위가 벗어나기 전이면
        while (left <= right) {
            // 가운데를 좁혀갑니다
            mid = (left + right) / 2;
            int compare = q.get(mid);

            // 낮은 값이면 mid값을 한칸 늘리고 좌측값에 적용
            if (compare < value)
                left = ++mid;
            // 높은 값이면 오른쪽 값만 늘림
            else
                right = mid - 1;
        }
        // 위치에 값 넣음
        q.add(mid, value);
    }
}