import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11003_최솟값찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Deque<int[]> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (true) {
                if (dq.isEmpty())
                    break;

                if (dq.getLast()[0] < now)
                    break;

                dq.removeLast();
            }

            dq.add(new int[]{now, i});

            if (dq.peek()[1] == i - l)
                dq.removeFirst();

            sb.append(dq.peek()[0]).append(" ");
        }


        System.out.println(sb);
    }
}
