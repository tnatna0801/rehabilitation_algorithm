public class pg_42898_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        // 나눌 값
        final long DIV_VALUE = 1_000_000_007;

        // 웅덩이 위치
        boolean[][] isPuddle = new boolean[n + 1][m + 1];
        for (int[] now : puddles) {
            isPuddle[now[1]][now[0]] = true;
        }

        // 구간 별 최단경로 개수
        long[][] memo = new long[n + 1][m + 1];
        // ---------------------------------------------------------
        // 맨 갓길 최단경로 먼저 계산
        for (int i = 1; i <= n; i++) {
            // 웅덩이가 나오면
            if (isPuddle[i][1]) {
                // 탐색 중지
                // 위쪽에서 올 수 있는 경우는 없기에 길이 끊김
                break;
            }
            memo[i][1] = 1;
        }
        for (int i = 1; i <= m; i++) {
            if (isPuddle[1][i]) {
                break;
            }
            memo[1][i] = 1;
        }
        // ---------------------------------------------------------

        // 전체 최단경로 계산
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                // 웅덩이가 나오면
                if (isPuddle[i][j]) {
                    // 건너 뛰기
                    continue;
                }
                // 위와 왼쪽에서 올 수 있는 경우 합
                memo[i][j] = (memo[i - 1][j] + memo[i][j - 1]) % DIV_VALUE;
            }
        }

        // 최종 경로
        return answer = (int) memo[n][m];
    }
}
