import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 14719번: 빗물
public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] block = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<W; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int left = block[0];
        int right = getRight(2, W, block);
        for(int i = 1; i<W-1; i++){ // 첫, 마지막은 안됨

            if(right == block[i]){
                right = getRight(i, W, block); // 오른쪽에서 젤 큰 애
            }

            left = Math.max(left, block[i]);

            if(block[i] >= right || block[i] >= left) continue; // 현재 블록이 가장 클 경우 빗물을 계산하지 않는다.

            int height = Math.min(right, left); // 최종 물높이를 정한다.
            result += height - block[i]; // 현재 블록에서 구할 수 있는 빗물을 구한다.

        }

        System.out.println(result);



    }

    private static int getRight(int i, int w, int[] block) {
        int right = 0;

        for(int idx = i+1; idx<w; idx++)
        {
            right = Math.max(right, block[idx]);
        }
        return right;
    }
}
