import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1806_부분합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        // 수열을 저장할 배열
        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        // 정답을 저장할 변수
        // 불가능한 경우를 위해서 0으로 초기화 하였습니다.
        int answer = 0;
        // 부분합이 s 이상이 되는 시점의 길이 중 가장 짧은 길이를 저장할 변수
        int shortest = Integer.MAX_VALUE;
        // 부분합을 저장할 변수
        int sum = 0;

        // two pointer를 위한 변수들
        int left = 0;
        int right = 0;
        while(true){
            // 1. 부분합이 s보다 작은지 봅니다.
            if(sum < s){
                // 1-1. 작다면
                // 2. 이미 우측의 pointer가 범위를 벗어났는지 확인합니다.
                // 2-1. 벗어났다면 멈춥니다.
                if(right == n)
                    break;
                // 2-2. 벗어나지 않았다면 부분합에 우측 값을 더하고 우측 pointer를 증가시킵니다.
                sum += sequence[right++];
            }else{
                // 1-2. 크다면
                // 3. 정답과 가장 짧은 길이를 저장할 변수를 갱신 합니다.
                answer = shortest = Integer.min(shortest, right - left);
                // 3-1. 만약 가장 짧은 길이가 1이면 멈춥니다.
                if(answer == 1)
                    break;
                // 3-2. 1이 아니라면 부분합에 좌측 값을 빼고 좌측 pointer를 증가시킵니다.
                sum -= sequence[left++];
            }
        }
        System.out.println(answer);
    }
}
