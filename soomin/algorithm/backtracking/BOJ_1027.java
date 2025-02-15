package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] buildings = new int[N];
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) System.out.println(0);
        else {

            int[] count = new int[N];

            for (int i = 0; i < N; i++) {
                double max = 0;
                double min = 1000000001;

                // 오른쪽으로 한번
                for (int right = i + 1; right < N; right++) {
                    double inclination = getInclination(right, i, buildings[right], buildings[i]);
                    if ( right == i+1 || max < inclination) { //기울기가 증가해야 보임
                        count[i]++;
                        max = inclination; // 최대 기울기 갱신
                    }
                }

                // 왼쪽으로 한번
                for (int left = i - 1; left >= 0; left--) {
                    double inclination = getInclination(left, i, buildings[left], buildings[i]);
                    if (left == i - 1 || min > inclination) {
                        count[i]++;
                        min = inclination;
                    }
                }

            }

            // 최댓값
            int result = 0;
            for (int i = 0; i < N; i++) {
                result = Math.max(result, count[i]);
            }

            System.out.println(result);
        }
    }

    private static double getInclination(int x1, int x2, int y1, int y2) {
        return ((double)y2 - (double)y1) / ((double)x2 - (double)x1);
    }
}
