import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 수의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for(int i =0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums); // 오름차순으로 정렬 => 투포인터 사용

        int result = 0;
        for(int i = 0; i<N; i++){
            int target = nums[i]; // 더해서 나와야하는 값!

            int left = 0, right = N-1; // 투 포인터

            while(left < right) {

                // target과 동일한 위치일 경우
                if(left == i) left++;
                else if(right == i) right--;

                // 결과를 찾을 수 없다.
                if(left >= right) break;

                int cur = nums[left] + nums[right];

                // 두수의 합과 target을 비교하여 포인터 위치 조정
                if(cur < target) left++;
                else if(cur > target) right--;
                else { // 좋다!@!
                    result++;
                    break; // target이 좋은 수 임을 알았으므로 종료
                }

            }

        }

        System.out.println(result);
    }
}
