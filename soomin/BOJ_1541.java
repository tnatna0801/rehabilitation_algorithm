import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] exp = br.readLine().split("-");

        int size = exp.length;

        int answer = 0;
        for (int i = 0; i < size; i++) {
            int sum = 0;

            String[] tmp = exp[i].split("\\+");

            for (int j = 0; j < tmp.length; j++) {
                sum += Integer.parseInt(tmp[j]);
            }

            if(i == 0) {
                answer = sum;
                continue;
            }
            answer -= sum;
        }

        System.out.println(answer);

    }
}
