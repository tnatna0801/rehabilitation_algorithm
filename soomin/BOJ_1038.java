import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038 {

    private static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 이분탐색?
        // 재귀...?
        // 젤 큰 감소하는 수 = 9876543210임 이게 몇번째일까?
        // 10자리가 최대임 => 그럼 10자리로 만들 수 있는 수는 2^10=1024임
        // 왜냐하면 9876543210에서 각 자릿수를 선택하냐 마냐의 문제이기 때문입니당
        // 그래서 하나도 선택안한 경우 1개를 빼서 총 1023개임
        // => 예를 들어서 420이라는 감소하는 수는 9876531을 선택하지 않고 420을 선택한것이죵
        StringBuilder sb = new StringBuilder();
        if(N > 1022) sb.append(-1); // 감소하는 수의 최댓값을 넘었기 때문에 -1 반환
        else if (N < 11) sb.append(N); // 한자리 수는 그대로 반환
        else { // 찾자...감소하는 수

            for(int i = 0; i<10; i++) { // i: 현재 자릿수의 시작 숫자
                findNum(1, i); // 1: 숫자가 들어가는 자리!
            }
            Collections.sort(list);
            sb.append(list.get(N));
        }

        System.out.println(sb);

    }

    /**
     * N번째 감소하는 수 구하기!!
     * @param num 현재 만들어진 감소하는 수
     * @param index 자릿수!
     */
    private static void findNum(int index, long num) {
        if(index > 10) return; // 자릿수는 10이하여아함

        list.add(num);

        for(int i = 0; i < num % 10; i++) { // 큰 자릿수의 수보다 작아야하기 때문에!
            findNum(index + 1, (num*10) + i);
        }
    }
}
