import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static List<Integer> plugs; //대기중인 플러그
    static List<Integer> holes; // 콘센트 구멍
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        plugs = new ArrayList<>();
        holes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            plugs.add(Integer.parseInt(st.nextToken()));
        }
        //위까지 입력
        Loop:
        while(!plugs.isEmpty()){
            int now = plugs.remove(0); //지금 꼽아야하는 플러그
            if(holes.contains(now)) //꼽혀있음
                continue;
            if(holes.size()<n){ //빈 콘센트 자리 남음
                holes.add(now);
                continue;
            }
            int max=-1;
            answer++; //하나 뽑음
            for(int j=0;j<n;j++){ //꼽혀있는 콘센트 돌면서 확인
                if(!plugs.contains(holes.get(j))){ //더 안쓰면 그냥 뽑음
                    holes.remove(j);
                    holes.add(now);
                    continue Loop;
                }
                if(plugs.indexOf(holes.get(j))>max){  //가장 나중에 쓰는거 뽑음
                    max=plugs.indexOf(holes.get(j));
                }
            }
            holes.remove(Integer.valueOf(plugs.get(max)));
            holes.add(now);
        }
        System.out.println(answer);
    }
}
