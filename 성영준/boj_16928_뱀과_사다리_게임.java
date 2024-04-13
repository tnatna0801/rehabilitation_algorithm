import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16928_뱀과_사다리_게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int actions = n + m;

        // 뱀과 사다리 정보 저장
        Map<Integer, Integer> action = new HashMap<>();

        for (int i = 0; i < actions; i++) {
            st = new StringTokenizer(br.readLine());
            action.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 거리 저장, 미리 가장 큰 값으로 채워 처음 온 값이 더 작은 값이 되도록 설정
        int[] board = new int[101];
        Arrays.fill(board, Integer.MAX_VALUE);

        // 주사위를 굴려야 하는 횟수의 최솟값 계산
        bfs(board, action);

        // 주사위를 굴려야 하는 횟수의 최솟값 출력
        System.out.println(board[100]);
    }

    /**
     * 주사위를 굴려야 하는 최솟값 계산하는 함수
     *
     * @param board 이동 횟수를 기록할 배열
     * @param action 뱀, 사다리 정보를 저장하고 있는 자료구조
     */
    private static void bfs(int[] board, Map<Integer, Integer> action) {
        Queue<Integer> q = new ArrayDeque<>();

        // 이동 횟수
        int count = 0;
        // 출발
        board[1] = count;
        q.add(1);

        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            while (size-- > 0) {
                // 현재 출발 위치
                int now = q.poll();

                for (int i = 6; i >= 1; i--) {
                    // 현재 도착 후보지
                    int next = now + i;

                    // 100을 넘어서면 건너띔
                    if (next > 100)
                        continue;

                    // 뱀, 사다리 확인
                    if (action.get(next) != null)
                        // 맞으면 이동
                        next = action.get(next);
                    // 아니면 유지

                    // 도착 후보지가 이미 적은 횟수로 기록되있으면 건너띔
                    if (board[next] <= count)
                        continue;

                    // 이동 횟수 갱신, 후보에 등록
                    board[next] = count;
                    q.add(next);
                }
            }
        }
    }
}
