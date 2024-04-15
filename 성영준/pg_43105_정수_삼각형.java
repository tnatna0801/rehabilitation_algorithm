public class pg_43105_정수_삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;

        // 누적 값을 저장할 배열
        int[][] dp = new int[triangle.length][];
        // 최상위 위치 값 부여
        dp[0] = new int[]{triangle[0][0]};

        // 2번 째 줄부터 돌면서 누적 값 갱신
        for (int i = 1; i < triangle.length; i++) {
            // 현재 줄 초기화
            dp[i] = new int[i + 1];

            // 처음 값 부여
            dp[i][0] = dp[i - 1][0] + triangle[i][0];

            // 사이 값 부여
            for (int j = 1; j < i; j++) {
                // 높은 값 부여
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }

            // 마지막 값 부여
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        // 가장 높은 누적 값 탐색
        for (int i = 0; i < triangle.length; i++)
            if (answer < dp[triangle.length - 1][i])
                answer = dp[triangle.length - 1][i];
        return answer;
    }
}
