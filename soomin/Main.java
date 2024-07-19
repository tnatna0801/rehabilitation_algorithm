import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 그리디? => 각 문자열을 이중 포문 N2, 문자열 비교시 M => O(N2M)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }

        Arrays.sort(str);

        int count = 1;
        for (int i = 1; i < N; i++) { // 버블정렬? => 인접한 원소만 비교해도 된다. 왜냐면 알파벳 순으로 정렬했으니까 그게 보장이 된다!
            if (str[i].equals(str[i-1])) continue;
            if (str[i].indexOf(str[i - 1]) == 0) continue;
            count++;
        }

        System.out.println(count);
    }
}
