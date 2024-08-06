import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9177 {

    static char[] first, second;
    static String third;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 꿍
        // 순서는 그대로 흠 => dfs인가? 선택하고 안하고? => 시간 초과..? 1000*2^200*2^200 => 너무 커요...
        // bfs??가 돼? 돼? 경로찾는 것 처럼 그렇게 인가? => 숨바꼭질

        for(int i = 1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            first = st.nextToken().toCharArray();
            second = st.nextToken().toCharArray();
            third = st.nextToken();

            // bfs로 탐색?
            String result = "no";
            if(bfs()) result = "yes";


            sb.append("Data set " + i + ": " + result + "\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[first.length + 1][second.length + 1];

        q.add(new int[]{0, 0}); // 행: 첫번째 단어, 열: 두번째 단어
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            int idx1 = now[0];
            int idx2 = now[1];
            int idx3 = idx1 + idx2;

            if(idx3 == third.length()) return true; // 세번쨰 단어가 완성됨! => 항상 첫번쨰 단어와 두번째 단어의 길이의 합이래

            // 두가지 경우로 나눠서 생각
            // 1. 첫번째 단어랑 철자가 같을 경우
            if(idx1 < first.length &&
                    third.charAt(idx3) == first[idx1] && !visited[idx1+1][idx2]) {
                visited[idx1 + 1][idx2] = true;
                q.add(new int[]{idx1 + 1, idx2});
            }
            
            // 2. 두번째 단어랑 철자가 같을 경우?
            if(idx2 < second.length &&
                    third.charAt(idx3) == second[idx2] && !visited[idx1][idx2 + 1]) {
                visited[idx1][idx2 + 1] = true;
                q.add(new int[]{idx1, idx2 + 1});
            }
        }

        return false;

    }
}
