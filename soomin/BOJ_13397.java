import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] array = new int[N];

        int right = 0;
        int left = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, array[i]);
        }

        while(left < right) {
            int mid = (left + right) / 2;
            if(find(mid, array) <= M) right = mid; // 왼쪽으로 이동
            else left = mid +1;
        }

        System.out.println(right);
    }

    private static int find(int mid, int[] arr) {
        int count = 1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i =0; i<arr.length; i++) {

            min = Math.min(min, arr[i]); // 해당 구간에서의 최소값
            max = Math.max(max, arr[i]); // 해당 구간에서의 최댓값

            if ((max - min) > mid) {// 구간을 쪼개야함
                count++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--; // 현재 인덱스 위치부터 다시 탐색해야해서
            }
        }

        return count;
    }
}
