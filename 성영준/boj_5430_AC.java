import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_5430_AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());
            // 수만 걸러서 입력받기
            String[] arr = br.readLine().split("[\\[\\],]");

            sb.append(process(commands, n, arr)).append("\n");
        }

        System.out.println(sb);
    }

    private static String process(String commands, int n, String[] arr) {
        StringBuilder sb = new StringBuilder();
        // 순서
        boolean isForward = true;
        // 버리고 난 좌, 우 위치
        int left = 1;
        int right = n + 1;

        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);

            // 함수가 R이면 순서를 바꿈
            if (command == 'R') {
                isForward = !isForward;
                continue;
            }

            // 만약 역전이 되면 오류
            if (left == right)
                return "error";

            // 정방향이면 왼쪽에서 한칸 올림
            if (isForward)
                left++;
            // 역방향이면 오른쪽에서 한칸 내림
            else
                right--;
        }

        sb.append("[");

        // left와 right가 같다면 값이 없음
        if (left != right)
            // 정방향이면
            if (isForward) {
                // 왼쪽부터 오른쪽으로
                for (int i = left; i < right - 1; i++)
                    sb.append(arr[i]).append(",");
                sb.append(arr[right - 1]);
            } else {
                for (int i = right - 1; i > left; i--)
                    sb.append(arr[i]).append(",");
                sb.append(arr[left]);
            }

        sb.append("]");

        return sb.toString();
    }
}
