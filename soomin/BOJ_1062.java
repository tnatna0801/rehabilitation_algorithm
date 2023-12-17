import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {

    static int N, K, max;
    static String[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new String[N];
        for(int i = 0; i<N; i++){
            input[i] = br.readLine();
        }

        // acint 11111111111111111111111111 (26) 이게... 맞아?
        // 어떨 때 무조건 안될까 => anint 없으면 안될듯
        if(K < 5) {
            System.out.println(0);
        }
        else if(K == 26) { // 무조건 됨
            System.out.println(N);
        }
        else {
            max = Integer.MIN_VALUE;
            int flag = 1;
            flag |= (1 << ('c' - 'a'));
            flag |= (1 << ('i' - 'a'));
            flag |= (1 << ('n' - 'a'));
            flag |= (1 << ('t' - 'a'));
            find(0,0, flag);
            System.out.println(max);
        }
    }
    //백트레킹!
    public static void find(int start, int cnt, int flag) {
        if(cnt == K - 5){
            int total = 0;
            for(int i = 0; i<N; i++){
                boolean isPossible = true;
                for(int j = 0; j<input[i].length(); j++){
                  //System.out.println(input[i].charAt(j) + "=>" + ((flag & ( 1 << (input[i].charAt(j) - 'a'))) == 0));
                  if( (flag & 1 << (input[i].charAt(j) - 'a')) == 0 ) {
                      isPossible = false;
                      break;
                  }
                }
                if(isPossible) {
                    //System.out.println(flag + " " + input[i]);
                    total++;
                }
            }
            max = Math.max(total, max);
            return;
        }

        for(int i = start; i<26; i++){
            if((flag & (1 <<  i)) != 0) continue;
            find(i+1, cnt + 1, (flag | (1 << i)));
        }
    }
}
