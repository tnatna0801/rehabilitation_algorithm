package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1202_보석도둑 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //내림차순 우선순위큐
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int n, k;//보석 개수
        long ans = 0;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] bag = new int[k];
        Jewel[] jewelList = new Jewel[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelList[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(jewelList);

        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);//가방 오름차순 정렬

        for (int i = 0, j = 0; i < k; i++) {//중복 보석을 방지하게 위해 j 인덱스 선언을 i와 같이 진행
            while (j < n) {
                if (jewelList[j].m > bag[i])// 현재 가방에 가능한 무게보다 작거나 같은 보석만 우선순위큐에 넣기
                    break;
                pq.offer(jewelList[j++].v);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);

    }

    static class Jewel implements Comparable<Jewel> {
        int m; //무게
        int v;//가치

        Jewel() {
        }

        Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            //무게 오름차순, 가치 내림차순
            if (this.m == o.m) {
                return o.v - this.v;
            }
            return this.m - o.m;

        }
    }
}
