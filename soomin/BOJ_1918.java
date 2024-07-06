import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BOJ_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<Character> nonAlpha = new Stack<>();
        Map<Character, Integer> order = new HashMap<>();

        order.put('(', 0);
        order.put('+', 1);
        order.put('-', 1);
        order.put('*', 2);
        order.put('/', 2);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {

            if (exp.charAt(i) >= 65 && exp.charAt(i) <= 90) {
                sb.append(exp.charAt(i));
                continue;
            }
            if (exp.charAt(i) == '(') {
                nonAlpha.add(exp.charAt(i));
                continue;
            }
            if (exp.charAt(i) == ')') {
                while (!nonAlpha.isEmpty() && nonAlpha.peek() != '(') {
                    sb.append(nonAlpha.pop());
                }
                nonAlpha.pop(); // ( 제거
                continue;
            }

            //우선순위 파악!
            while (!nonAlpha.isEmpty()) {
                char target = nonAlpha.peek();

                if (order.get(target) < order.get(exp.charAt(i))) break;// 우선순위가 크면 종료
                sb.append(nonAlpha.pop());
            }

            nonAlpha.add(exp.charAt(i));
        }
        while (!nonAlpha.isEmpty()) {
            sb.append(nonAlpha.pop());
        }

        System.out.println(sb);
    }
}
