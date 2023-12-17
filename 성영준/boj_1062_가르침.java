import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1062_가르침 {
    static int n, k, max = 0, target = 0;
    static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // #1
        // "anta"와 "tica"를 구성하는 알파벳 5개를 2진수로 저장합니다.
        String init = "antic";
        int alpha = 0;
        for (int i = 0; i < 5; i++)
            alpha |= (1 << (init.charAt(i) - 'a'));

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 남극의 단어를 2진수로 저장합니다.
        words = new int[n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            // 미리 저장해 둔 2진수를 저장합니다.
            words[i] = alpha;
            // 사이의 알파벳을 2진수에 추가합니다.
            for (int j = 4; j < word.length() - 4; j++)
                words[i] |= 1 << (word.charAt(j) - 'a');
        }

        // #4
        // 정답을 출력합니다.
        System.out.println(checkCanTeach(alpha));
    }

    // #2
    /**
     * 가르칠 수 있는 단어의 수를 계산하는 함수
     * @param alpha : 현재까지 가르친 알파벳
     * @return 가르칠 수 있는 단어의 수
     */
    private static int checkCanTeach(int alpha) {
        // 가르칠 수 있는 글자의 수가
        // 기본 글자 수 보다 적다면 0을 리턴합니다.
        if (k < 5)
            return 0;
        // 알파벳 전체만큼 된다면 모든 단어의 수를 리턴합니다.
        if (k == 26)
            return n;
        // 가르쳐야 할 목표의 알파벳들을 2진수로 저장합니다.
        for (int i = 0; i < n; i++)
            target |= words[i];
        // 만약 가르쳐야 할 목표의 알파벳들이 가르칠 수 있는 수보다 적다면 모든 단어의 수를 리턴합니다.
        if(Integer.bitCount(target) < k)
            return n;
        // 기본 알파벳을 제외합니다.
        target ^= alpha;

        // 목표 알파벳들에 대해 조합을 진행합니다.
        countCanTeach(1, 5, alpha);
        return max;
    }

    // #3
    /**
     * 최대로 가르칠 수 있는 단어의 수를 계산하는 함수
     * @param index 현재 알파벳의 index
     * @param count 현재까지 가르친 알파벳의 수
     * @param key 현재까지 가르친 알파벳
     */
    private static void countCanTeach(int index, int count, int key) {
        // 만약 가르칠 수 있는 알파벳의 수 만큼 가르쳤다면, 최대로 읽을 수 있는 단어의 수를 갱신합니다.
        if (k == count) {
            int total = 0;
            for (int i = 0; i < n; i++)
                if (words[i] == (key & words[i]))
                    total++;
            max = Integer.max(max, total);
            return;
        }

        // 모든 알파벳을 돌면서
        for (int i = index; i < 26; i++)
            // 해당 알파벳이 가르쳐야할 목표 알파벳이라면
            if ((target & (1 << i)) != 0)
                // 현재 알파벳을 현재까지 가르친 알파벳에 추가한 후, 다시 현재 함수를 호출합니다.
                countCanTeach(i + 1, count + 1, key | (1 << i));
    }
}