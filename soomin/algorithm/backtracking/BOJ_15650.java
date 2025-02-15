package algorithm.backtracking;

import java.util.*;
import java.io.*;

public class BOJ_15650 {
    static boolean[] c = new boolean[10];
    static int[] a = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        process(0,1,N,M);
    }

    private static void process(int depth, int start, int n, int m) {

        if (depth == m) {
            for (int i=0; i<m; i++) {
                System.out.print(a[i]);
                if (i != m-1) System.out.print(' ');
            }
            System.out.println();
            return;
        }

        for (int i=start; i<=n; i++) {
            c[i] = true;
            a[depth] = i;
            process(depth+1, i, n, m);
            c[i] = false;
        }
    }
}