import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_29160 {

    static class Player implements Comparable<Player>  {
        int p;
        int v;

        Player(int p, int v) {
            this.p = p;
            this.v = v;
        }

        public int compareTo(Player o) {
            return o.v - this.v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, PriorityQueue<Player>> players = new HashMap<>();
        for (int i = 1; i <= 11; i++) {
            players.put(i, new PriorityQueue<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int position = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            players.get(position).add(new Player(position, value));
        }

        int[] team = new int[12];
        // 초기 팀 구성 -> 3월은 초기에만 중요하고 이후는 걍 11월 그래도 가니까 한번만 해도 될 듯
        for (int i = 1; i <= 11; i++) {
            if (players.get(i).isEmpty()) continue;
            team[i] = players.get(i).poll().v;
        }

        while (K-- > 0) {

            // 8월 가치 감소
            for(int i = 1; i<=11; i++) {
                if(team[i] == 0) continue;
                team[i]--;
                players.get(i).add(new Player(i, team[i])); // 이럼 재정렬 흠 시간 괜찮나...
            }

            // 11월 재선발
            for (int i = 1; i <= 11; i++) {
                if (players.get(i).isEmpty()) continue;
                team[i] = players.get(i).poll().v;
            }
        }

        int sum = 0;
        for(int i = 1; i<=11; i++){
            sum += team[i];
        }

        System.out.println(sum);
    }
}
