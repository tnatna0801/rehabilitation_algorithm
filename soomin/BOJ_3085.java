import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3085 {

    static int N;

    public static void main(String[] args) throws IOException {
        // 잘 모르겟습니다 완전 탐색입니까?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 입력 값
        char[][] candy = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            candy[i] = line.toCharArray();
        }

        // 사탕의 색이 다른 인접한 두칸을 고른다...
        // 우하
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 2. 사탕 교환, 우하

                if (i < N - 1) {
                    // 2.1 서로 다른 색의 사탕인지 확인
                    //if (candy[i + 1][j] == (tmp)) continue;

                    // 2.2 트레이드 진행
                    swap(candy, i, j, i + 1, j);

                    // 2.3 가장 긴 연속 부분 찾기
                    result = Math.max(result, dfs(candy));

                    // 2.4 복구
                    swap(candy, i, j, i + 1, j);
                }
                if (j < N - 1) {
                    //if (candy[i][j + 1] == (tmp)) continue;

                    // 2.2 트레이드 진행
                    swap(candy, i, j, i, j + 1);

                    // 2.3 가장 긴 연속 부분 찾기
                    result = Math.max(result, dfs(candy));

                    // 2.4 복구
                    swap(candy, i, j, i, j + 1);
                }
            }
        }
        System.out.println(result);
    }

    private static int dfs(char[][] candy) {
        int count = 1;
        // 행
        for (int i = 0; i < N; i++) {
            int same = 1;

            for (int j = 1; j < N; j++) {
                if (candy[i][j] == candy[i][j - 1])
                    same++;
                else same = 1;
                count = Math.max(count, same);
            }
        }
        // 열
        for (int i = 0; i < N; i++) {
            int same = 1;

            for (int j = 1; j < N; j++) {
                if (candy[j][i] == candy[j - 1][i])
                    same++;
                else same = 1;
                count = Math.max(count, same);
            }
        }
        return count;
    }

    // 교환
    private static void swap(char[][] candy, int y1, int x1, int y2, int x2) {
        char tmp = candy[y1][x1];
        candy[y1][x1] = candy[y2][x2];
        candy[y2][x2] = tmp;
    }
}


