import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11332 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        // 테케
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 시간초과인지 확인
            if (checkTime(st.nextToken(), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
                sb.append("May Pass.");
            else
                sb.append("TLE!");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean checkTime(String s, int N, int T, int L) {

        // 시간 = 시간복잡도 * T <= L * 100000000
        // 시간 복잡도 <= L * 100000000 / T

        double targetTime = (long) (L * 100000000) / T; // 제한 시간!
        boolean isPass = false; // 시간초과 여부
        boolean flag = true; // N!, s^N의 경우는 실제로 시간초과가 날 수 있으니까 중간에 시간 복잡도를 넘으면 중단해야함 중단 여부
        long time = 1; // 비교할 시간

        switch (s) {
            case "O(N)":
                if (N <= targetTime) isPass = true;
                break;

            case "O(N^2)":
                if ((long) N * N <= targetTime) isPass = true;
                break;

            case "O(N^3)":
                if ((long) N * N * N <= targetTime) isPass = true;
                break;

            case "O(2^N)":
                if ((long) Math.pow(2, N) <= targetTime) isPass = true;
                break;

            case "O(N!)":
                for (int i = 1; i <= N; i++) {
                    time *= i;
                    if (time > targetTime) { // 시간 비교
                        flag = false;
                        break;
                    }
                }
                if (flag) isPass = true;
                break;
        }

        return isPass;
    }
}
