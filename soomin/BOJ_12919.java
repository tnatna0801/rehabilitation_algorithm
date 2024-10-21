import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_12919 {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        // S -> T는 2^50으로 시간초과가 난다
        // T -> S로 문자를 제거하면서 해야함 => 이건 왜 시간초과가 안날까? => S->T보다 덜 탐색하니까

        recursion(S, T);

        System.out.println(result);

    }

    public static void recursion(String A, String B) {

        int len = B.length();
        if(A.length() == len) {
            if(A.equals(B)) result = 1;
            return;
        }

        // B의 끝자리가 A인 경우 => A지우기
        StringBuilder sb = new StringBuilder();
        sb.append(B);
        if(B.charAt(len - 1) == 'A') recursion(A, sb.substring(0, len-1));

        // B의 첫자리가 B인 경우 => 뒤집어야함
        if(B.charAt(0) == 'B') recursion(A, new StringBuilder(sb.substring(1)).reverse().toString());

    }
}
