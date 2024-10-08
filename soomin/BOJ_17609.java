import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    static int start, end;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            String word = br.readLine();

            start = 0;
            end = word.length() - 1;

            sb.append(palindrome(word)).append("\n");
        }

        System.out.print(sb);

    }

    private static int palindrome(String word) {

        if (isEqual(word)) return 0;

        int tmpS = start++;
        int tmpE = end;
        if (isEqual(word)) return 1;

        start = tmpS;
        end = --tmpE;
        if (isEqual(word)) return 1;

        return 2;
    }

    private static boolean isEqual(String word) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) return false;
            start++;
            end--;
        }

        return true;
    }
}