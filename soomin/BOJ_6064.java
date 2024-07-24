import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1; // 만약 M이 5면 1~5가 반복이니까 0~4로 봐도 무방
            int y = Integer.parseInt(st.nextToken()) - 1;

            // 규칙을 찾자 x%M, y%N => 최소공배수인감?
            // 나머지 연산을 이용하면 될듯 => 어떻게?
            // 최대 년도는 M*N임
            // 년도를 찾자 => 최소년도는 x임... 증가하면서 N으로 나눴을때 y가 나오는 지점!
            // 의문 y == N이면 어떻게 해야할까? 그렇다고 year % N == 0을 하면 엉뚱한 year이 값이 될 수 있음
            // x와 y를 -1하자 그럼 딱 떨어짐! <0, 0> 이 첫번째 해니까 결과값에 +1
            boolean isFind = false;
            for(int year = x; year<=(M*N); year+=M){
                if(year % N == y) {
                    int answer = year + 1;
                    sb.append(answer + "\n");
                    isFind = true;
                    break;
                }
            }

            if(!isFind) sb.append(-1 + "\n");
        }

        System.out.println(sb);
    }
}
