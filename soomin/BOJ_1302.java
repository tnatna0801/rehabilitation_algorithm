import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1302 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 입력
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> books = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String title = br.readLine();
            books.put(title, books.getOrDefault(title, 0) + 1);
        }

        int max = 0;
        String maxTitle = "";
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            String title = entry.getKey();
            int count = entry.getValue();

            if (max < count) { // 가장 많이 팔린 책 구하기
                max = count;
                maxTitle = title;
            } else if (max == count) { // 많이 팔린 책이 여러개면 사전 순 정렬!!
                if (maxTitle.compareTo(title) <= 0) continue;
                maxTitle = title;
            }
        }

        System.out.println(maxTitle);

    }
}
