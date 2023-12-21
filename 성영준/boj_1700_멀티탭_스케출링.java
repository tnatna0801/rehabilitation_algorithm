import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_1700_멀티탭_스케출링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 멀티탭과 전기용품 사용 순서를 저장할 배열입니다.
        int[] multitap = new int[n];
        int[] order = new int[k];

        // 각 용품 별 사용 순서를 따로 저장할 Deque입니다.
        Deque<Integer>[] waiting = new ArrayDeque[k + 1];
        for (int i = 1; i <= k; i++)
            waiting[i] = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++)
            waiting[order[i] = Integer.parseInt(st.nextToken())].add(i);

        int use = 0;
        int i = 0;
        // 1. 멀티탭을 가득 채우기 전 입니다.
        for (; i < k; i++) {
            // 1-2. 가득 채웠다면 다음 단계로 넘어갑니다.
            if (use == n)
                break;

            // 1-1. 가득 채우지 않았다면 계속 진행합니다.
            /**
             * 현재 전기용품의 사용 순서를 하나 지우며 멀티탭에서 사용 중인지 봅니다.
             * 사용 중 이라면 그대로 사용하고,
             * 사용 중이지 않다면 목표 칸에 꽂습니다.
             */
            int now = order[i];
            waiting[now].remove();
            if (isUse(now, use, multitap))
                continue;
            // 목표 칸은 다음칸 입니다.
            multitap[use++] = now;
        }

        int out = 0;
        // 2. 멀티탭을 가득 채운 후 입니다.
        for (; i < k; i++) {
            // 2-1. 계속 진행합니다.
            /**
             * 현재 전기용품의 사용 순서를 하나 지우며 멀티탭에서 사용 중인지 봅니다.
             * 사용 중 이라면 그대로 사용하고,
             * 사용 중이지 않다면 목표 칸에 꽂습니다.
             */
            int now = order[i];
            waiting[now].remove();
            if (isUse(now, use, multitap))
                continue;
            // 목표 칸은 계산된 칸 입니다.
            multitap[findTarget(n, multitap, waiting)] = now;
            out++;
        }
        System.out.println(out);
    }

    /**
     * 멀티탭에서 목표 전기용품을 사용 중인지 여부를 확인하는 함수입니다.
     * @param target 목표 전기용품
     * @param use 사용 중인 멀티탭 구멍의 갯수
     * @param multitap 멀티탭
     * @return 목표 전기용품 사용 여부
     */
    private static boolean isUse(int target, int use, int[] multitap) {
        for (int i = 0; i < use; i++)
            if (multitap[i] == target)
                return true;
        return false;
    }

    /**
     * 멀티탭에서 목표 전기용품을 꽂을 위치를 반환하는 함수입니다.
     * @param n 멀티탭 전체 구멍의 갯수
     * @param multitap 멀티탭
     * @param waiting 각 용품 별 사용 순서 대기열
     * @return 목표 전기용품을 꽂을 위치
     */
    private static int findTarget(int n, int[] multitap, Deque<Integer>[] waiting) {
        int target = 0;
        int far = 0;
        for (int i = 0; i < n; i++) {
            int now = multitap[i];

            // 목표 전기용품이 사용되는지 확인한 후
            // 더 이상 사용되지 않는다면 해당 위치를 반환합니다.
            if (waiting[now].isEmpty())
                return i;

            // 사용 된다면 기다릴 순서를 확인합니다.
            int wait = waiting[now].peek();

            // 현재까지 가장 나중에 사용할 전기용품보다 나중에 사용하는지 확인한 후
            // 빠르다면 넘어가고
            if (wait <= far)
                continue;

            // 느리다면 갱신합니다.
            target = i;
            far = wait;
        }
        // 가장 나중에 사용할 전기용품의 위치를 반환합니다.
        return target;
    }
}
