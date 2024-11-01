import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1082 {

    static class Num implements Comparable<Num>{
        int n, c;

        Num(int n, int c){
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Num o) {
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2초임
        // N은 10이하
        // 그리디?

        int N = Integer.parseInt(br.readLine());

        List<Num> roomNums = new ArrayList<>(); // 번호와 비용을 저장하는 리스트
        int[] price = new int[N]; // 위 리스트는 정렬됨으로 고정된 배열이 필요함

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int cost = Integer.parseInt(st.nextToken());
            price[i] = cost;
            roomNums.add(new Num(i, price[i]));
        }

        // 가격순으로 오름차순 정렬
        Collections.sort(roomNums);

        int M = Integer.parseInt(br.readLine()); // 준비한 금액

        // 1. 제일 낮은 가격의 번호를 최대한 많이 사서 자리수를 늘려야함
        // 2. 맨 앞자리를 젤 큰 수로 지정해야함

        int count = 0;
        int totalCost = 0;

        int[] answerNums = new int[51];

        if(roomNums.get(0).n == 0) { // 제일 싼 숫자가 0이라면? 맨 앞에 0이 올 수 없다
            if( N > 1 && roomNums.get(1).c <= M ) {
                totalCost += roomNums.get(1).c;
                answerNums[count++] = roomNums.get(1).n;
            }
            else {
                System.out.println(0);
                return;
            }
        }

        // 제일 싼 수로 자릿수 구하기
        while(totalCost + roomNums.get(0).c <= M) {
            totalCost += roomNums.get(0).c;
            answerNums[count++] = roomNums.get(0).n;
        }

        // 앞자리부터 큰 수로 교체
        for(int i = 0; i<count; i++){
            for(int j = N-1; j>=0; j--) { // 가장 큰 수부터

                if(j == 0 && i == 0) continue; // 맨 앞자리에 0이 올 수 없당

                int tmpCost = totalCost - price[answerNums[i]] + price[j];

                if(tmpCost <= M) { // 최대한 큰 값을 위해 변경
                    totalCost = tmpCost;
                    answerNums[i] = j; // i번째 자리에 j 삽입
                    break; // 다음 자리수로 이동한다.
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<count; i++) {
            sb.append(answerNums[i]);
        }

        System.out.println(sb);
    }
}
