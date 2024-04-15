public class pg_43163_단어_변환 {
    class Solution {
        // 최소 단계 수 비교용
        // 미리 최댓값으로 초기화, 변환 가능한 경우 해당 값으로 적용
        static int min = Integer.MAX_VALUE;
        // 최소 단계 수 정답용
        // 미리 0으로 초기화, 변환 불가능한 경우 기본 값으로 초기화
        static int answer = 0;

        public int solution(String begin, String target, String[] words) {
            // target 단어가 없으면
            if (hasTarget(target, words)) {
                // 종료
                return answer;
            }

            // 1. 모든 단어를 비교하여, 한 자리만 다른 단어끼리 인접행렬 생성
            // 매번 불필요한 탐색 방지
            boolean[][] canTranslate = new boolean[words.length][words.length];
            setCanTranslate(canTranslate, words);

            // 2. backtracking을 통해 가장 짧은 경우 탐색
            getMin(begin, target, words, canTranslate);

            // 3. 정답 출력
            return answer;
        }

        /**
         * 목표 단어가 단어 목록에 있는지 확인하는 함수
         * @param target 목표 단어
         * @param words 단어 목록
         * @return 존재 여부
         */
        public static boolean hasTarget(String target, String[] words) {
            for (String now : words) {
                if (now.equals(target)){
                    return false;
                }
            }
            return true;
        }

        /**
         * 변환 가능 단어 인접 행렬 설정하는 함수
         * @param canTranslate 변환 가능 단어 인접 행렬
         * @param words 단어 목록
         */
        public static void setCanTranslate(boolean[][] canTranslate, String[] words) {
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    canTranslate[i][j] = compareDifferent(words[i], words[j]);
                }
                canTranslate[i][i] = false;
            }
        }

        /**
         * 두 단어의 차이가 1글자인지 확인하는 함수
         * @param word1 단어 1
         * @param word2 단어 2
         * @return 단어 차이가 1글자인지 여부
         */
        public static boolean compareDifferent(String word1, String word2) {
            // 두 단어의 차이 횟수
            int count = 0;
            for (int i = 0; i < word1.length(); i++){
                // 글자가 다르다면
                if (word1.charAt(i) != word2.charAt(i)){
                    // 차이 횟수 증가
                    // 2글자 이상 차이 나면
                    if (++count == 2) {
                        // 부적합 판정
                        return false;
                    }
                }
            }
            // 적합 판정
            return true;
        }

        /**
         * 최소 변환 횟수를 탐색하기 위한 함수
         * @param begin 시작 단어
         * @param target 목표 단어
         * @param words 단어 목록
         * @param canTranslate 변환 가능 단어 인접 행렬
         */
        public static void getMin(String begin, String target, String[] words, boolean[][] canTranslate) {
            // 중복 탐색 방지를 위한 확인 배열
            boolean[] visited = new boolean[words.length];
            // 목표 단어의 위치
            int end = 0;
            for (int i = 0; i < words.length; i++){
                if (target.equals(words[i])){
                    end = i;
                    break;
                }
            }

            // backtracking을 통해 최솟값 획득
            for (int i = 0; i < words.length; i++){
                // 시작 단어 다음 단어가 될 수 있는지 따로 체크
                // 시작 단어는 단어 배열에 포함되지 않은 채 따로 주어지기 때문
                if (compareDifferent(begin, words[i])) {
                    visited[i] = true;
                    backtracking(1, i, end, visited, canTranslate);
                    visited[i] = false;
                }
            }
        }

        /**
         * 최소 변환 횟수를 획득하기 위한 함수
         * @param count 현재 탐색 단계의 변환 횟수
         * @param now 현재 탐색 단계의 단어
         * @param end 목표 단어
         * @param visited 중복 탐색 방지를 위한 확인 배열
         * @param canTranslate 변환 가능 단어 인접 행렬
         */
        public static void backtracking(int count, int now, int end, boolean[] visited, boolean[][] canTranslate) {
            // 현재 탐색 단계의 단어가 목표 단어라면
            if (now == end){
                // 정답과 최솟값 갱신
                answer = min = Math.min(min, count);
                // 탐색 중지
                return;
            }

            // 현재 탐색 단계의 변환 횟수가 최솟값과 같다면
            if (count == min){
                // 탐색 중지
                return;
            }

            // 다음 탐색 단어 선택
            for (int i = 0; i < visited.length; i++){
                // 이미 확인한 단어라면
                if (visited[i]) {
                    // 건너 뜀
                    continue;
                }

                // 변환이 가능하지 않다면
                if (!canTranslate[now][i]) {
                    // 건너 뜀
                    continue;
                }

                // 다음 단계 탐색
                visited[i] = true;
                backtracking(count + 1, i, end, visited, canTranslate);
                visited[i] = false;
            }
        }
    }
}
