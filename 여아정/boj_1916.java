package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1916 {
    public static class Bus implements  Comparable<Bus>{
        int idx;
        int cost;

        public Bus() {
        }

        public Bus(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return cost-o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Bus>pq=new PriorityQueue<>();
        List<Bus>[]busList;

        int n=Integer.parseInt(br.readLine());//도시 수
        int m=Integer.parseInt(br.readLine());//버스 종류 수

        busList=new ArrayList[n+1];//버스 시작 도시 기준으로 버스 리스트 배열 생성
        for(int i=0;i<=n;i++){//
            busList[i]=new ArrayList<>();//객체 할당
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int idx=Integer.parseInt(st.nextToken());
            busList[idx].add(new Bus(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));//각 버스의 시작점 마다의 도착지와 비용을 추가
        }

        st=new StringTokenizer(br.readLine());
        int start=Integer.parseInt(st.nextToken());//시작지점
        int end=Integer.parseInt(st.nextToken());//도착지점
        int MAX=Integer.MAX_VALUE;//최대값

        int[] canGo=new int[n+1];//최적비용 넣을 배열로 n은 1부터 할거라 n+1함
        Arrays.fill(canGo,MAX);//모든 배열값에 MAX로 integer max값을 넣어준다
        boolean[] chk=new boolean[n+1];//체크배열도 생성

        canGo[start]=0;//시작 지점은 비용을 임의로 0으로 설정한다
        pq.add(new Bus(start, 0));//우선순위큐에 start지점으로 0의 비용으로 가는 것을 임의로 넣어준다

        while(!pq.isEmpty()){
            Bus cur=pq.poll();//pq에서 가장 최소 비용인 값을 뺀다

            if(chk[cur.idx])continue;//이미 온 곳이면 넘어감
            chk[cur.idx]=true;//방문 처리

            for(Bus next:busList[cur.idx]){//현 위치에서 갈 수 있는 다음 위치들을 탐색해본다
                if(!chk[next.idx] && canGo[next.idx]>canGo[cur.idx]+next.cost){//아직 가보지 않은 곳이면서 그 곳의 최적비용이 현재 지점을 거처서가는 비용보다 비싼경우
                    canGo[next.idx]=canGo[cur.idx]+next.cost;//갱신해준다!!!
                    pq.add(new Bus(next.idx, canGo[next.idx]));//그리고 pq에 최적 비용과 함께 넣어준다
                }
            }
        }

        System.out.println(canGo[end]);//도착지의 최소비용을 출력한다



    }
}
