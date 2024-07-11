import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Jewelry implements Comparable<Jewelry> {
    int weight;
    int value;

    Jewelry(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewelry o) {
        if(this.weight == o.weight) return o.value - this.value; // 내림차순
        return this.weight - o.weight; // 오름차순
    }
}

public class BOJ_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(jewelries); // 무게 기준 오름차순 정렬

        Integer[] bag = new Integer[K];
        for(int i = 0; i<K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag); // 오름차순 => 무게가 큰 가방부터 넣으면 작은 가방에 넣는 거랑 중복될 수 있다.

        // 가방에 넣을 수 있는 최대 보석을 찾기
        PriorityQueue<Jewelry> pq = new PriorityQueue<>((a, b) -> b.value - a.value); // 가치 내림차순
        long answer = 0;
        int index = 0;

        for(int i = 0; i<K; i++) {

            while(true) {
                if(index >= N) break;
                if(jewelries[index].weight > bag[i]) break;
                pq.offer(jewelries[index++]);
            }

            if(!pq.isEmpty()) {
                answer += pq.poll().value;
            }
        }

        System.out.println(answer);
    }
}
