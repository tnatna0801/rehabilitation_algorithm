import java.io.*;
import java.util.*;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());

            // 스택이 비어있거나 현재 빌딩이 더 낮은 경우
            if (stack.isEmpty() || stack.peek() > now) {
                stack.add(now);
                continue;
            }

            // 현재 빌딩이 더 높은 경우, 낮은 빌딩 제거
            while (!stack.isEmpty() && stack.peek() < now) {
                stack.pop();
            }

            // 스택의 최상단과 현재 빌딩이 같다면 다리 추가
            if (!stack.isEmpty() && stack.peek() == now) {
                count++;
            }

            // 현재 빌딩을 스택에 추가
            stack.add(now);
        }

        System.out.println(count);
    }
}
