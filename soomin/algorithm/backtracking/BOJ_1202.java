package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {

    static class Jewel implements Comparable<Jewel> {
        int m, v;
        Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) { // 무게 오름차순, 가치는 내림차순
            if(this.m == o.m) return o.v - this.v;
            return this.m - o.m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(m, v);
        }

        // 가방 무게
        int[] bags = new int[K];
        for(int i = 0; i<K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);
        Arrays.sort(jewels);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 가격 내림차순?
        long sum = 0; // 300,000 * 1,000,000
        for(int i = 0 , j = 0; i<K; i++) {

            while(j<N && bags[i] >= jewels[j].m) {
                pq.add(jewels[j++].v);
            }

            if(!pq.isEmpty()) sum += pq.poll();
        }

        System.out.println(sum);
    }
}
