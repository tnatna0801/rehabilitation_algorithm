import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_2252_줄_세우기 {
    // 정답으로 출력 할 변수 // String으로 만들면 비효율적이기에 StringBuilder 사용
    static StringBuilder sb = new StringBuilder();
    // 해당 학생보다 키가 큰 학생의 번호를 저장하기 위한 배열
    static Deque<Integer>[] tallerList;
    // 줄 선 학생을 기록할 배열
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 학생의 수, 키 관계 정보 제공의 횟수
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tallerList = new ArrayDeque[n + 1];
        for (int i = 1; i <= n; i++)
            tallerList[i] = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 큰 학생, 작은 학생
            int taller = Integer.parseInt(st.nextToken());
            int shorter = Integer.parseInt(st.nextToken());

            // 작은 학생(본인)보다 큰 학생들 저장
            tallerList[shorter].add(taller);
        }

        checked = new boolean[n + 1];

        // 1번 학생부터 확인 시작
        for (int i = 1; i <= n; i++)
            lineUp(i);

        System.out.println(sb);
    }

    /**
     * 줄을 세우는 함수
     * @param now 현재 확인 중인 학생
     */
    private static void lineUp(int now) {
        // 1. 만약 현재 학생이 줄을 섰다면 넘어감
        if (checked[now]) return;

        // 2. 현재 학생보다 큰 학생이 존재하는지 확인
        while (!tallerList[now].isEmpty())
            // 2-1. 만약 존재하다면, 크다고 기록한 배열에서 제거 한 후, 그 학생을 줄 세울지 확인
            lineUp(tallerList[now].remove());

        // 3. 줄을 세움
        checked[now] = true;
        sb.append(now).append(" ");
    }
}