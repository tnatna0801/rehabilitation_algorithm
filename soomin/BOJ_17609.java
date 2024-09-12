import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {

            String word = br.readLine();

            int start = 0, end = word.length()-1;
            int diffCnt = 0;

            sb.append(palindrome(word, start, end, diffCnt));

            sb.append("\n");
        }

        System.out.print(sb);

    }

    private static int palindrome(String word, int start, int end, int diffCnt) {
        while(start < end) {

            if(word.charAt(start) == word.charAt(end)) {
                start++;
                end--;
            }
            else if(diffCnt == 1) {
                return 2;
            }
            else {

                return Math.min(palindrome(word, start+1, end, diffCnt+1),
                        palindrome(word, start, end-1, diffCnt+1));

            }
        }

        return diffCnt;
    }
}
