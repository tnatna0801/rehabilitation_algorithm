import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    // 그리디? => 각 문자열을 이중 포문 N2, 문자열 비교시 M => O(N2M)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }

        Arrays.sort(str, Comparator.reverseOrder()); // 내림차순 => 그리디 탐색을 위하여 (긴 값이 먼저 채택되어야 가장 많은 부분집합을 선택할 수 있다. )
        ArrayList<String> words = new ArrayList<>(); // => 굳이 자료구조를 만들지 않고 count만 셀 수는 없을까?
        words.add(str[0]);


        for (int i = 1; i < N; i++) {

            String target = str[i];
            boolean isPrefix = false;

            for(String word : words) {
                if(target.length() > word.length()) continue;   // 최적화: target이 str보다 작거나 같을때만 비교함 (target이 더 크면 접두사가 될 수 없음)
                if(word.indexOf(target) == 0) { // 0번째 인덱스에 target이 있으면 접두사임
                    isPrefix = true;
                    break;
                }
            }

            if(!isPrefix) words.add(target);
        }

        System.out.println(words.size());

    }
}
