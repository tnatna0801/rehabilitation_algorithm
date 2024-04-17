import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //위까지 입력

        int answer = Integer.MAX_VALUE;
        int l=0; //투포인터
        int r=0;
        int tmp=arr[r];
        while(l<=r){ 
            if(tmp>=S) { //합이 조건 달성
                answer = Math.min(answer, r - l + 1); //최소길이 갱신
                tmp-=arr[l++]; //왼쪽포인터 이동
            }else{ //합이 작음
                if(r==N-1) break; //배열범위 벗어나면 탈출
                tmp+=arr[++r]; //오른쪽 포인터 이동
            }
        }
        if(answer==Integer.MAX_VALUE) answer=0;
        System.out.println(answer);
    }
}
