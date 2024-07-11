import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_29160 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 선수정보 입력
        Queue<Integer>[] players = new PriorityQueue[12];
        for (int i = 1; i <= 11; i++) {
            players[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int position = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            players[position].add(value);
        }

        int sum = 0;
        while (K-- > 0) {
            for(int i = 1; i<=11; i++) {

                if(players[i].isEmpty() || players[i].peek() <= 0) continue;
                players[i].add(players[i].poll() - 1); // 가치 감소 & 재선발

            }
        }

        for(int i = 1; i<=11; i++){
            if(players[i].isEmpty()) continue;
            sum += players[i].poll();
        }

        System.out.println(sum);
    }
}
