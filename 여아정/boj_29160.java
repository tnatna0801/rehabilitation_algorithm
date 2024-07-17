package test_0913;

import java.util.*;
import java.io.*;

public class boj_29160 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //선발 선수의 포지션당 우선순위큐 배열 선언 (1~11번)
        PriorityQueue<Integer>[] fildPlayer = new PriorityQueue[12];
        for (int i = 0; i < 12; i++) {
            fildPlayer[i] = new PriorityQueue<>(Comparator.reverseOrder());//내림차순
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            fildPlayer[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;

        //k년 동안 팀 선발 및 가치 갱신진행
        System.out.println(yearTeam(k, fildPlayer));

    }

    //1년간의 팀 선발 및 가치 갱신 진행
    private static int yearTeam(int k, PriorityQueue<Integer>[] fildPlayer) {

        //k 년 동안 선발선수 가치 -1
        while (k-- > 0) {
            for(int i=1;i<12;i++){
                if(fildPlayer[i].isEmpty()) continue;
                if(fildPlayer[i].peek()==0) continue;

                int player = fildPlayer[i].poll()-1;
                fildPlayer[i].add(player);
            }
        }

        //k년 마지막 선발선수들 가치 합
        int ans=0;

        for(int i=1;i<12;i++){
            if(fildPlayer[i].isEmpty()) continue;
            ans+=fildPlayer[i].poll();
        }

        return ans;

    }
}
