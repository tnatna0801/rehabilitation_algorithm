import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    // 그리디? => 각 문자열을 이중 포문 N2, 문자열 비교시 M => O(N2M)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<String> sortedStrings = new PriorityQueue<>((o1, o2) -> {
            return o2.length() - o1.length();
        }); // 내림차순으로 정렬 => 부분집합이 제일 많아야하니까! 길이가 짧은 순으로 먼저 들어가버리면 접두사가 될 확률이 높다!
        for(int i = 0; i<N; i++){
            sortedStrings.add(br.readLine());
        }

        Set<String> strings = new HashSet<>(); // 입력으로 중복값이 주어질 수 있음
        strings.add(sortedStrings.poll()); // 하나 넣고 시작
        
        while(!sortedStrings.isEmpty()) {

            String target = sortedStrings.poll();
            boolean isPrefix = false;

            for(String str : strings) {
                // 최적화: target이 str보다 작거나 같을때만 비교함 (target이 더 크면 접두사가 될 수 없음)
                if(target.length() > str.length()) continue;
                if(str.indexOf(target) == 0) {// 0번째 인덱스에 target이 있으면 접두사임
                    isPrefix = true;
                    break;
                }
            }
            if(!isPrefix) strings.add(target); // 삽입
        }

        System.out.println(strings.size());
    }
}
