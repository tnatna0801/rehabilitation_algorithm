package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11559 {

    static class Point {
        int y, x;
        char c;
        Point(int y, int x, char c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }

    static final int N = 12;
    static final int M = 6;

    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static char[][] field;

    static int count = 0;

    static boolean isPop = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        field = new char[N][M];
        for(int i = 0; i<N; i++) {
            field[i] = br.readLine().toCharArray();
        }

        process();
        System.out.println(count);

    }

    private static void process() {

        while(true) {

            boolean isPop = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] != '.') {
                        explode(i, j, field[i][j], isPop);
                    }
                }
            }

            if(!isPop) break;

            down();
            count++; // 연쇄!
        }

    }

    private static void explode(int y, int x, char c, boolean isPop) {

        List<int[]> points = new ArrayList<>(); // 연결되어있는 같은 색 뿌요
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new ArrayDeque<>();

        visited[y][x] = true;
        q.add(new Point(y, x, c));
        points.add(new int [] {y, x});

        while(!q.isEmpty()) {

            Point now = q.poll();
            for(int d = 0; d<4; d++) {
                int ny = now.y + move[d][0];
                if(ny <0 || ny >= N) continue;
                int nx = now.x + move[d][1];
                if(nx < 0 || nx >= M) continue;

                if(field[ny][nx] != now.c || visited[ny][nx]) continue;

                q.add(new Point(ny, nx, c));
                points.add(new int[] {ny, nx});
                visited[ny][nx] = true;
            }
        }

        if(points.size() >= 4) {
            isPop = true;

            for(int[] p : points) {
                field[p[0]][p[1]] = '.'; // 제거
            }
        }
    }

    private static void down() {

        Stack<Character> stack = new Stack<>();

        for(int j = 0; j<M; j++) {
            for(int i = 0; i<N; i++) {
                if(field[i][j] != '.') {
                    stack.add(field[i][j]);
                    field[i][j] = '.';
                }
            }

            int index = N-1;
            while(!stack.isEmpty()) {
                field[index--][j] = stack.pop();
            }
        }
    }

}
