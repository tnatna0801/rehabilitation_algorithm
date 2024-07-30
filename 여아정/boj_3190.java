package test_0913;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3190 {

    //상,우,하,좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //맵 크기
        int n = Integer.parseInt(br.readLine());

        // 사과 수
        int k = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            //사과 위치 맵에 표시
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }

        //방향 전환 수
        int L = Integer.parseInt(br.readLine());
        Map<Integer, Character> turnInfo = new HashMap<>();// map에 방향 전환 넣기

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            turnInfo.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        int ans = goSnake(n, k, map, turnInfo);
        System.out.println(ans);
    }

    private static int goSnake(int n, int k, int[][] snakeMap, Map<Integer, Character> turnInfo) {
        int[] head = {0, 0};//초기 위치

        //뱀의 꼬리부터 머리까지의 위치 값 저장할 큐
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(head);// 초기위치 넣기

        int way = 1;// 처음 방향은 오른쪽감
        int time = 0;

        //조건 잡을게 많아 보여서 일단 true로 둠
        while (true) {

            //방향 전환
            if (turnInfo.containsKey(time)) {
                if (turnInfo.get(time) == 'D') {// 오른쪽 90 회전
                    if ((way = way + 1) > 3)
                        way = 0;
                } else { //왼쪽 90회전
                    if ((way = way - 1) < 0)
                        way = 3;
                }
            }

            int nx = head[0] + dx[way];
            if (nx < 0 || nx >= n) return ++time;

            int ny = head[1] + dy[way];
            if (ny < 0 || ny >= n) return ++time;

            if (snakeMap[nx][ny] == 1) return ++time;

            head = new int[]{nx, ny};

            //사과 있는곳
            if (snakeMap[nx][ny] == -1) {
                snake.add(head);//길이 늘림
            } else {
                //기존 길이로 가므로 한칸 이동으로 이전 값 삭제
                int[] delete = snake.poll();
                snake.add(head);
                snakeMap[delete[0]][delete[1]] = 0;//
            }

            snakeMap[nx][ny] = 1;

            time++;
        }

    }


}
