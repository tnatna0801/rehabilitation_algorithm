import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        Arrays.sort(cards); // 이분탐색으로 시작 인덱스와 끝 인덱스를 찾기 위해서 정렬이 필요하다

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<M; i++) {
            int target = Integer.parseInt(st.nextToken()); // 탐색할 값
            sb.append(upperBound(cards, target) - lowerBound(cards, target) + " "); // 중복되는 값이 시작되는 인덱스와 끝 인덱스를 찾아서 구간 길이를 구하기

        }
        System.out.println(sb);
    }

    private static int upperBound(int[] cards, int value) {
        int left = 0;
        int right = cards.length; // N!! => n-1일경우 갯수를 구할때 +1해줘야해서 그냥 배열의 갯수로 설정

        while(left < right) {
            int mid = (left + right) / 2;
            if(value < cards[mid]) { // 마지막 인덱스를 구하기위해서
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 첫번째 인덱스를 구하는 함수
    private static int lowerBound(int[] cards, int value) {
        int left = 0;
        int right = cards.length; // N!! => n-1일경우 갯수를 구할때 +1해줘야해서 그냥 배열의 갯수로 설정

        while(left < right) {
            int mid = (left + right) / 2;
            if(value <= cards[mid]) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
