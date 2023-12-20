package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int answer = 1_000_001;//sum을 다 더해도 더 클 수를 answer로 초기화 했습니다

        for (int i = 0; i < n; i++) {
            if ((num[i] = Integer.parseInt(st.nextToken())) >= s) {//입력을 받으면서 단일 값으로 s값 이상되는 조건이 만족되는지 확인 합니다.
                answer = 1;
            }
            sum += num[i];//sum으로 모든 입력값을 더합니다.
        }

        if (answer == 1_000_001) {//단일값으로 s를 만족하지 않은 경우
            if (sum < s) {//모든 입력값을 더했지만 조건인 s보다 작은 경우
                answer = 0;//값을 구할 수 없으므로 0으로 둡니다.
            } else {
                int sub = 0, start = 0, end = 0;

                while (true) {
                    if (sub < s) {//구간합이 s보다 작을 경우 end 값을 증가시킨다
                        if (end == n) {//마지막 index를 넘었다면 멈춰준다
                            break;
                        }
                        sub += num[end++];

                    } else {//구간합이 s보다 크거나 같다면 start값을 그 다음 값으로 증가시킨다.
                        if (answer > end - start) {//answer이 기존값보다 값이 작다면 갱신해준다
                            answer = end - start;
                        }
                        sub -= num[start++];
                    }
                }
            }
        }
        System.out.println(answer);//정답 출력해주새우
    }
}
