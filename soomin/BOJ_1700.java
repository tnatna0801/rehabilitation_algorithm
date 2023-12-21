import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] electronics = new int[K];
        List<Integer> multitap = new ArrayList<>();
        for(int i = 0; i<K; i++){
            electronics[i] = Integer.parseInt(st.nextToken());
        }

        //콘센트가 빈칸이면 꼽는다.
        int index = 0;
        while(true){
            if(multitap.size() == N) break;
            if(index == K) break;

            if(multitap.contains(electronics[index])) {
                index++;
                continue;
            }
            multitap.add(electronics[index]);
            index++;
        }

        //아니라면 하나를 빼고 꼽는다. => 뭘 빼야할까?
        //이미 꼽혀있는 경우
        int result = 0;
        for(int i = index; i<K; i++){

            boolean isPossible = false;

            // 멀티탭에 꽂혀있으면
            if(multitap.contains(electronics[i])) continue;

            // 멀티탭을 비워줘야한다면? => 다시 안쓰일 친구 뽑거나 제일 나중에 다시쓰이는 친구 뽑기
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[N];
            for(int j = i+1; j<K; j++){

                if(stack.size() == N) break; // 현재 멀티탭에 있는 애들 다 나중에 다시 쓰임 ㅜ

                for(int l = 0; l<N; l++){  // => 여길 잘 못 로직 짠 것같음. 멀티탭에 있는 애는 중복으로 stack에 넣을 필요가 없어서 방문 체크 해줄려고 한건디 먼가 이상함
                    if(visited[l]) continue;
                    if(multitap.get(l) == electronics[j]) stack.add(l);
                    visited[l] = true;
                }
            }

            if(stack.isEmpty()) {
                multitap.remove(0);
                multitap.add(electronics[i]); // 걍 아무거나 뽑으면 됨;
            } else if(stack.size() == N) {
                multitap.remove(stack.pop());
                multitap.add(electronics[i]); // 제일 나중에 사용 되는 애 뽑기
            } else { // 나중에 쓰일애말고 안쓰이는 애 (visited 안한 애) 뽑기 => 여길 잘 못 짠 것 같음
                for(int j = 0; j<N; j++){
                   if( visited[j] ) continue;

                   multitap.remove(j);
                   multitap.add(electronics[i]);
                   break;
                }
            }
            result++;
        }

    System.out.println(result);
    }
}
