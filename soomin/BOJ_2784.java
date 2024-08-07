import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2784 {

    private static List<String> words = new ArrayList<>();
    private static boolean[] visited;
    private static int[] result;
    private static StringBuilder sb = new StringBuilder();
    private static boolean isFound = false; // 첫번째 유효한 조합을 찾았냐?

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        visited = new boolean[6];
        result = new int[3];
        for (int i = 0; i < 6; i++) {
            words.add(br.readLine());
        }

        // 사전순을 위해서 정렬
        Collections.sort(words);

        // 조합~~ => 6개중에 3개를 선택해서 가로 맞추고 세로로 검증?
        permutation(0);

        // 출력
        if (sb.length() == 0) System.out.println(0);
        else System.out.println(sb);

    }

    private static void permutation(int count) {

        if (isFound) return; // 첫번째 유효한 값이 나오면 종료!

        if (count == 3) {
            // 세로 확인~~
            List<String> copy = new ArrayList<>(words);

            for (int i = 0; i < 3; i++) {
                copy.remove(words.get(result[i])); // 가로에 선택된 단어는 제외
            }

            for (int i = 0; i < 3; i++) { // 선택한 단어들로 세로로 words에 포함되어있는 단어를 만들 수 있는 지 확인!
                String tmp = "" + words.get(result[0]).charAt(i)
                        + words.get(result[1]).charAt(i)
                        + words.get(result[2]).charAt(i);
                if (!copy.contains(tmp)) return;
                copy.remove(tmp);
            }

            // 유효한 조합이라면?
            for (int i = 0; i < 3; i++) {
                sb.append(words.get(result[i])).append("\n");
            }
            isFound = true;
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result[count] = i; // 단어의 인덱스 저장
            permutation(count + 1);
            visited[i] = false;
        }
    }
}
