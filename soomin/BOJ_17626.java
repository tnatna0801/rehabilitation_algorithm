import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n+1];

        d[1] = 1;

        for(int i = 2; i<=n; i++) {
            int min = Integer.MAX_VALUE;

            for(int j = 1; j*j <= i;  j++) {
                min = Math.min(d[i - j*j], min);
            }

            d[i] = min + 1;
        }

        System.out.print(d[n]);
    }
}
