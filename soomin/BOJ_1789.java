import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 일단 그리디인 것 같다.

        // int형을 넘어가기 때문에 long
        long S = Long.parseLong(br.readLine());
        long sum = 0;

        int i = 1;
        while(sum <= S){ // 1부터 자연수를 더해서 S보다 커지면 종료
            sum += i;

            // 디버깅용 출력
            //System.out.println(i + "   " + "sum : " + sum);
            i++;
        }

        System.out.println(i-2);
    }
}
