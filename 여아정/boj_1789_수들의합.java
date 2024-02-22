package test_0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1789_수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());
        long all = 0L;
        int cnt = 0;
        long cur = 0L;

        while (all <= S) {
            all += ++cur;
        }
        System.out.println(--cur);
    }
}
