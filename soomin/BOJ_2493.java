import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
    int num;
    int height;

    Top(int num, int height) {
        this.num = num;
        this.height = height;
    }
}

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Top> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            int h = Integer.parseInt(st.nextToken());

            // 스택이 비어있지 않아면, peek하여 현재 높이와 비교
            while(true) {

                // 스택이 비었다면 0을 출력
                if(stack.isEmpty()) {
                    sb.append("0 ");
                    stack.push(new Top(i, h));
                    break;
                }

                Top target = stack.peek();

                // 현재 높이보다 높다면?
                if (target.height >= h) {
                    sb.append(target.num).append(" ");
                    stack.push(new Top(i, h));
                    break;
                }

                if (target.height < h) {
                    stack.pop();
                }
            }
        }

        System.out.println(sb);
    }
}
