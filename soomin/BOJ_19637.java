import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19637 {

    static class Style {
        String name;
        int score;

        Style(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Style[] styles = new Style[N];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            styles[i] = new Style(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        int left = 0;
        int right = 0;
        int mid = 0;

        for(int i = 0; i<M; i++) {

            int target = Integer.parseInt(br.readLine());

            left = 0;
            right = N;

            while(left < right) {
                mid = (left + right) / 2;

                if(styles[mid].score < target) left = mid + 1;
                else
                    right = mid;
            }

            sb.append(styles[left].name).append("\n");
        }

        System.out.println(sb);
    }
}
